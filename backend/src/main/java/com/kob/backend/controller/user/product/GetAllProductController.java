package com.kob.backend.controller.user.product;


import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.service.user.product.GetAllProductsService;
import com.kob.backend.service.user.product.GetImageService;
import com.kob.backend.service.user.product.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class GetAllProductController {
    @Autowired
    GetAllProductsService getAllProductsService;

    @Autowired
    GetImageService getImageService;

    @GetMapping("/api/product/getlist/")
    public List<Product> getList() {
        return getAllProductsService.getAllProducts();
    }

    @PostMapping("/api/product/image/")
    public ProductImage getImage(@RequestBody BigInteger productId) {
        return getImageService.getImage(productId).get(0);
    }
}
