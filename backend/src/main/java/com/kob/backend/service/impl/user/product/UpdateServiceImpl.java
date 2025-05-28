package com.kob.backend.service.impl.user.product;


import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.product.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {

    //查询bot信息
    @Autowired
    private ProductMapper productMapper;


    @Override
    public Map<String, String> update(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int product_id = Integer.parseInt(data.get("product_id"));  //用户想要修改的bot
        //用户想要修改的内容，在controller中获取参数
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");


        Map<String, String> map = new HashMap<>();

        if(title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }

        if(title.length() > 100 ) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        if(description == null || description.length() == 0) {
            description = "null";   //不算错误，不用返回
        }

        if(description.length() > 300 ) {
            map.put("error_message", "Bot描述长度不能大于300");
            return map;
        }

        if(content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }

        if(content.length() > 10000) {
            map.put("error_message", "代码长度不能大于10000");
            return map;
        }

        Product product = productMapper.selectById(product_id);

        if(product == null) {
            map.put("error_message","bot不存在或已被删除");
            return map;
        }

        if(!product.getMerchantId().equals(user.getId())) {
            map.put("error_message", "没有权限修改该bot");
        }

        Product newproduct = new Product(
        //todo:
        );

        productMapper.updateById(newproduct);   //传入修改后的对象

        map.put("error_message", "success");




        return map;
    }
}
