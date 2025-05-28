package com.kob.backend.controller.user.product;

import com.kob.backend.pojo.ProductDTO;
import com.kob.backend.service.user.product.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddController {

    @Autowired
    private AddService addService;

    @PostMapping("/api/product/add/")
    public Map<String, String> add(@RequestBody ProductDTO data) {
        return addService.add(data);
    }
}

