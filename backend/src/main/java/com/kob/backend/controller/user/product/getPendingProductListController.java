package com.kob.backend.controller.user.product;


import com.kob.backend.pojo.Product;
import com.kob.backend.service.user.product.auditProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class getPendingProductListController {
    @Autowired
    private auditProductService auditProductService;


    //测试用，暂时无需管理员身份
    @GetMapping("api/admin/getpendingProducts/")
    public List<Product> getPendingUserList() {
        return auditProductService
                .getPendingProducts();
    }


}
