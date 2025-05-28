package com.kob.backend.service.user.product;

import com.kob.backend.pojo.Product;

import java.util.List;

public interface GetAllProductsService {
    //返回所有商品

    List<Product> getAllProducts();


    //返回所有在售商品
    //返回所有不可售商品
}
