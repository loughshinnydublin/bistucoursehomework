package com.kob.backend.controller.user.product;


import com.kob.backend.service.user.product.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateController {
    @Autowired
    private UpdateService updateService;

    @PostMapping("/api/product/update/")
    //允许修改image_url
    public Map<String, String> update(@RequestParam Map<String, String> data) {

        return updateService.update(data);

    }



}
