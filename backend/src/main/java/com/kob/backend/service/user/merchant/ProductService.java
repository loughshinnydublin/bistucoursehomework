package com.kob.backend.service.user.merchant;


import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.ProductDTO;
import com.kob.backend.pojo.ProductWithOrderItemId;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDTO> getList(); //商家发布的所有商品
    //已经售出的商品
    List<ProductWithOrderItemId> getProducts();  //根据merchantid返回product
    Map<String, String> merchantShip(BigInteger productId);

}
