package com.kob.backend.service.user.product;

import com.kob.backend.pojo.ProductDTO;

import java.util.Map;

public interface AddService {
    //添加一个product
    Map<String, String> add(ProductDTO data);

}
