package com.kob.backend.service.impl.user.product;


import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.product.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();


        BigInteger productId = new BigInteger(data.get("product_id"));
        Product product = productMapper.selectById(productId);
        Map<String, String> map = new HashMap<>();

        if(product == null) {
            map.put("error_message", "product不存在");
            return map;
        }

        //当前删除的good不是同一个用户创建
//        if(!product.getMerchantId().equals(user.getId())) {
//            map.put("error_message", "无权限删除");
//            return map;
//        }


        productMapper.deleteById(productId);
        map.put("error_message", "success");
        return map;
    }
}
