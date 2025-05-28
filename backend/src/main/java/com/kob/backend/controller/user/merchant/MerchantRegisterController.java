package com.kob.backend.controller.user.merchant;


import com.kob.backend.service.user.merchant.merchantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MerchantRegisterController {

    @Autowired
    private merchantRegisterService merchantRegisterService;

    @GetMapping("api/merchant/register/")
    public Map<String, String> register() {
        return merchantRegisterService.register();
    }

}
