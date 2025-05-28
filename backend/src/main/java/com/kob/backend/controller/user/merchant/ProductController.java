package com.kob.backend.controller.user.merchant;


import com.kob.backend.pojo.ProductDTO;
import com.kob.backend.pojo.ProductWithOrderItemId;
import com.kob.backend.service.user.merchant.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

//对应商家要供应的货物：查询所有paid order->WAIT_SHIP orderitem->productid->merchantid，
// 根据merchantid返回product，
// 商家发货：productid -> orderitem -> orderitem status = WAIT_SHIP
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/api/merchant/getproducts")
    public List<ProductWithOrderItemId> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/api/merchant/ship")
    public Map<String, String> ship(@RequestParam BigInteger productId) {
        return productService.merchantShip(productId);
    }

    @GetMapping("/api/merchant/getlist")
    public List<ProductDTO> getList() {
        return productService.getList();
    }


}
