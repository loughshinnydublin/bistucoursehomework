package com.kob.backend.service.impl.user.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.service.user.product.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsServiceImpl implements GetAllProductsService {
    @Autowired
    ProductMapper productMapper;


    @Override
    public List<Product> getAllProducts() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "APPROVED");
        return productMapper
                .selectList(queryWrapper);
    }
}
