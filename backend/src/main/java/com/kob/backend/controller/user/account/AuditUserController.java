package com.kob.backend.controller.user.account;


import com.kob.backend.service.user.account.auditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuditUserController {

    @Autowired
    private auditUserService auditUserService;

    // 审核用户注册
    @PostMapping("/audit-user")
    public Map<String, Object> auditUser(@RequestBody Map<String, String> map) {
        Map<String, Object> result = new HashMap<>();
        BigInteger userId = new BigInteger(map.get("userId"));
        String newStatus = map.get("status");

        try {
            boolean success = auditUserService.auditUser(userId, newStatus);
            result.put("error_message", success ? "审核成功" : "审核失败");
        } catch (Exception e) {
            result.put("error_message", "审核出错：" + e.getMessage());
        }

        return result;
    }
}
