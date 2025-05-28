package com.kob.backend.service.impl.user.merchant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.OrderItemMapper;
import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.mapper.ProductImageMapper;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.*;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.merchant.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getList() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("merchant_id", user.getId());
        List<Product> list = productMapper.selectList(queryWrapper);
        List<ProductDTO> dtoList = new ArrayList<>();

        //封装成DTO
        for (Product product : list) {
            if (product.getStatus().equals("APPROVED")) {
                ProductDTO productDTO = new ProductDTO();
                BeanUtils.copyProperties(product, productDTO);

                productDTO.setImgUrl(productMapper
                        .getProductImagesByProductId(product.getId())
                        .get(0).getImageUrl());
                dtoList.add(productDTO);
            }
        }

        return dtoList;
    }

    @Override
    public List<ProductWithOrderItemId> getProducts() {

        //当前登陆的是商家
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "PAID");
        List<Order> list = orderMapper.selectList(queryWrapper);
        List<ProductWithOrderItemId> result = new ArrayList<>();

        for (Order order : list) {
            List<OrderItem> orderItemList = orderItemMapper
                    .selectByOrderId(order.getId());
            for (OrderItem orderItem : orderItemList) {
                BigInteger merchantId = productMapper.selectById(orderItem
                        .getProductId()).getMerchantId();
                Product product = productMapper
                        .selectById(orderItem.getProductId());
                if (Objects.equals(orderItem.getStatus(),
                        "WAIT_SHIP") && merchantId.equals(user.getId())) {
                    result.add(new ProductWithOrderItemId(product,
                            orderItem.getId(), order.getId(), orderItem.getStatus()));
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, String> merchantShip(BigInteger productId) {

        List<ProductWithOrderItemId> products = getProducts();
        Map<String, String> map = new HashMap<>();
        for (ProductWithOrderItemId product : products) {
            if (product.getProduct().getId().equals(productId)) {
                BigInteger itemId = product.getOrderItemId();
                OrderItem orderItem = orderItemMapper
                        .selectById(itemId);
                orderItem.setStatus("SHIPPED");
                orderItemMapper.updateById(orderItem);
                map.put("shipped", product.getProduct().getName());
            }
        }


        map.put("error_message", "success");
        return map;
    }
}
