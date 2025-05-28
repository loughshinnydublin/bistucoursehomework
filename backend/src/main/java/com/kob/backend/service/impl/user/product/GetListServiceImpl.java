package com.kob.backend.service.impl.user.product;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.product.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetListServiceImpl implements GetListService {


    @Autowired
    private ProductMapper productMapper;


    //获取当前用户的所有商品（仅限卖家）
    @Override
    public List<Product> getList() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        List<Product> products;

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());   //查询数据库里的userid等于前端提交的userid的项

        products = productMapper.selectList(queryWrapper);

        return products;
    }

    //获取所有商品

    @Override
    public List<Product> getListByPrice(double price) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery(Product.class);
        queryWrapper.gt(Product::getPrice, price);

        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<Product> getListByName(String name) {
        return productMapper.selectList(new QueryWrapper<Product>().eq("name", name));
    }

    @Override
    public List<Product> getListByComment(String comment) {
        return productMapper.selectList(new QueryWrapper<Product>().like("comment", comment));
    }

}
