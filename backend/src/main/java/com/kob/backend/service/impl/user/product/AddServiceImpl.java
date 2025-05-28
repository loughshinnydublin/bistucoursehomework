package com.kob.backend.service.impl.user.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.ProductImageMapper;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.ProductDTO;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.product.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public Map<String, String> add(ProductDTO data) {


        //插入一个bot，需要取当前user(getinfo)
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();


        //获取插入的信息
        String name = data.getName();
        String description = data.getDescription();

        double price = data.getPrice();
        String size = data.getSize();
        boolean bargainAllowed = data.isBargain_allowed();
        String productCondition = data.getProduct_condition();
        int quantity = data.getQuantity();
        String category = data.getCategory();

        //返回的字典
        Map<String, String> map = new HashMap<>();
        System.out.println(user.getRole());
        if (!user.getRole().equals("MERCHANT")) {
            map.put("error_message", "只有商家可以创建商品");
            return map;
        }


//        if (title == null || title.length() == 0) {
//            map.put("error_message", "标题不能为空");
//            return map;
//        }

//        if (title.length() > 100) {
//            map.put("error_message", "标题长度不能大于100");
//            return map;
//        }

//        if (description == null || description.length() == 0) {
//            description = "null";   //不算错误，不用返回
//        }

//        if (description.length() > 1000) {
//            map.put("error_message", "描述长度不能大于1000");
//            return map;
//        }


        //找到对应的商家
//        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", user.getId());

        //创建新商品，注意构造函数
        Date now = new Date();


        Product product = new Product(
                null,           // id
                user.getId(),   // merchantId
                name,         // name
                category,      // category
                price,         // price
                price,         // discountPrice
                size,          // size
                description,   // description
                bargainAllowed,// bargainAllowed
                productCondition, // productCondition
                0,             // sales
                quantity,      // quantity
                "PENDING",     // status
                now,           // createTime
                now            // updateTime
        );

        productMapper.insert(product);

        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(data.getImgUrl());
        productImage.setProductId(product.getId());
        productImageMapper.insert(productImage);
        map.put("error_message", "success");
        map.put("productId", String.valueOf(product.getId()));
        return map;
    }
}
