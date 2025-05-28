package com.kob.backend.controller.user.account;


import com.kob.backend.pojo.Product;
import com.kob.backend.service.user.product.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;

    @GetMapping("user/getlist/")
    public List<Product> getList() {
        return getListService.getList();
    }


}
