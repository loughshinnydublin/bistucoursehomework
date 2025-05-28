package com.kob.backend.service.impl.user.product;


import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.service.user.product.GetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class GetImageServiceImpl implements GetImageService {

    @Autowired
    ProductMapper productmapper;


    @Override
    public List<ProductImage> getImage(BigInteger productId) {
        return productmapper.getProductImagesByProductId(productId);
    }
}
