package com.kob.backend.controller.user.account;


import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.getUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class getUserListController {

    @Autowired
    private getUserListService getUserListService;

    @GetMapping("api/getlist/")
    public List<User> getUserList() {
        return getUserListService.getUserList();
    }

}
