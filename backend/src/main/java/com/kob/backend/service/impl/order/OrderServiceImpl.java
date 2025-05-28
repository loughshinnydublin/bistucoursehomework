package com.kob.backend.service.impl.order;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.OrderItemMapper;
import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Order;
import com.kob.backend.pojo.OrderItem;
import com.kob.backend.pojo.OrderRequest;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.order.OrderService;
import com.kob.backend.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WalletService walletService;

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Order> getOrderList() {

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();


        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());

        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, String> createOrder(OrderRequest request) {
        Map<String, String> map = new HashMap<>();

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        //order和orderitem插入数据库
        Order order = new Order();

        order.setUserId(user.getId());
        order.setTotalAmount(request.getTotalAmount());
        System.out.println(order);

        orderMapper.insert(order);

        for (OrderItem item : request.getItems()) {
            item.setOrderId(order.getId());
            orderItemMapper.insert(item);
        }


        map.put("error_message", "success");
        map.put("order_id", String.valueOf(order.getId()));
        return map;
    }

    @Override
    public Map<String, String> updateOrder(Order order) {
        Map<String, String> map = new HashMap<>();
        System.out.println(order);
        orderMapper.updateById(order);
        map.put("error_message", "success");
        return map;
    }

    @Override
    @Transactional
    public Map<String, String> confirmOrder(BigInteger id) {
        Map<String, String> map = new HashMap<>();

        Order order = orderMapper.selectById(id);
        if(Objects.equals(order.getStatus(), "PAID")) {
            map.put("error_message", "success");
            return map;
        }
        order.setStatus("PAID");
        orderMapper.updateById(order);
        double amount = order.getTotalAmount();
//        walletService.deduct(amount); //扣钱

        //钱->merchant
        //此处不会被调用，修改方式：新建controller，前端deduct时执行
        List<OrderItem> items = orderItemMapper.selectByOrderId(order.getId());
        for(OrderItem item : items) {
            BigInteger merchantId = productMapper
                    .selectById(item.getProductId())
                    .getMerchantId();
            walletService.checkout(item.getPrice(),merchantId);
        }



        map.put("error_message", "success");
        map.put("amount", String.valueOf(amount));
        return map;
    }


    //用于前端我的订单显示是否发货
    @Override
    public Map<String, String> shipOrder(BigInteger id) {
        Map<String, String> map = new HashMap<>();

        //TODO：如果所有orderitem status 都为SHIPPED 则为发货
        List<OrderItem> orderItem = orderItemMapper
                .selectByOrderId(id);
        boolean flag = true;
        for (OrderItem item : orderItem) {
            if (!Objects.equals(item.getStatus(), "SHIPPED")) {
                flag = false;
                break;
            }
        }

        if (flag) {
            Order order = new Order();
            order.setId(id);
            order.setStatus("SHIPPED");
            orderMapper.updateById(order);
            map.put("error_message", "success");
            map.put("order_id", String.valueOf(id));
            map.put("status", "SHIPPED");
        } else {
            map.put("error_message", "未全部付款");
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, String> requestRefund(BigInteger id) {
        Map<String, String> map = new HashMap<>();
        Order order = orderMapper.selectById(id);

        order.setStatus("RETURN_REQUEST");
        orderMapper.updateById(order);

        map.put("error_message", "success");
        return map;
    }

    @Override
    @Transactional
    public Map<String, String> approveRefund(BigInteger id) {
        Order order = orderMapper.selectById(id);
        order.setStatus("RETURNED");
        orderMapper.updateById(order);

        // Refund money to buyer
        walletService.recharge(order.getTotalAmount());
//        walletService.deductPoint(order.getTotalAmount());    //扣除积分
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> rejectRefund(BigInteger id, String reason) {
        Map<String, String> map = new HashMap<>();
        //TODO

        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> receive(BigInteger id) {
        Order order = orderMapper.selectById(id);
        order.setStatus("RECEIVED");
        orderMapper.updateById(order);
        return Map.of("error_message", "success");
    }
} 