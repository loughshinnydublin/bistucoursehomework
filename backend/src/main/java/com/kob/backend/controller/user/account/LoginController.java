package com.kob.backend.controller.user.account;


//写api：controller中调用接口，service，serviceimpl 定义和实现接口

import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/api/user/account/login/") //在securityconfig中放行
    public Map<String, String> getToken(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
//        String captcha = map.get("captcha");

        Map<String, String> map1 = new HashMap<>();
        map1 = loginService.getToken(username, password);
        System.out.println(map1.get("token"));

        return map1;
    }
}
