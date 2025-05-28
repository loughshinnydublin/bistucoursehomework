package com.kob.backend.controller.user.account;


import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.auditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class getPendingUserListController {
    @Autowired
    private auditUserService audituser;


    //测试用，暂时无需管理员身份
    @GetMapping("api/admin/getpendinglist/")
    public List<User> getPendingUserList() {
        return audituser.getPendingUsers();
    }


}
