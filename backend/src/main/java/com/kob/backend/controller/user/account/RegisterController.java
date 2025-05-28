package com.kob.backend.controller.user.account;


import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("api/user/account/register/")
    public Map<String, String> register(@RequestParam Map<String, String> map) {
//        String username = map.get("username");
//        String password = map.get("password");
//        String confirmedPassword = map.get("confirmedPassword");
//        String role = map.get("role");
//        String phone = map.get("phone");

        //前端提交以下数据

        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");

//        String email = map.get("email");
//        String phone = map.get("phone");
//        String city = map.get("city");
//        String gender = map.get("gender");
//        String bankAccount = map.get("bankAccount");
//        String role = map.get("role");  // 如果前端允许选择注册角色，如 "USER" 或 "MERCHANT"

        String inputCaptcha = map.get("captcha");   //captcha前端输入后返回，前端注册时请求api


        //todo:验证码校验 return map error_message。。。


        User user = new User();
        user.setUsername(username);
        user.setPassword(password); //在service中加密

//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setCity(city);
//        user.setGender(gender);
//        user.setBankAccount(bankAccount);
//        user.setRole(role); // 前端提供选项，返回角色

        // 状态在 Service 中置为 "PENDING"


        return registerService.register(user, confirmedPassword);
    }


}
