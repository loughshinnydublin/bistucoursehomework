package com.kob.backend.controller.user.product;


import com.kob.backend.service.user.account.auditUserService;
import com.kob.backend.service.user.product.auditProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuditProductController {

    @Autowired
    private auditProductService auditProductService;

    // 审核用户注册
    @PostMapping("/audit-product")
    public Map<String, Object> auditProduct(@RequestBody Map<String, String> map) {
        Map<String, Object> result = new HashMap<>();
        BigInteger productId = new BigInteger(map.get("productId"));
        String newStatus = map.get("status");

        try {
            boolean success = auditProductService
                    .auditProduct(productId , newStatus);
            result.put("error_message", success ? "审核成功" : "审核失败");
        } catch (Exception e) {
            result.put("error_message", "审核出错：" + e.getMessage());
        }

        return result;
    }
}
