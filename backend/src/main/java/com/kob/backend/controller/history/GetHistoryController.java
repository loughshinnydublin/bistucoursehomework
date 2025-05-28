package com.kob.backend.controller.history;

import com.kob.backend.pojo.History;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.history.addHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GetHistoryController {

    @Autowired
    addHistoryService addHistoryService;

    @PostMapping("/api/history")
    public Map<String, String> addHistory(@RequestBody Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        History history = new History();

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        history.setUserId(user.getId());
        history.setProductId(new BigInteger(data.get("product_id")));
        Date date = new Date();
        history.setViewTime(date);

        addHistoryService.addHistory(history);
        map.put("error_message", "success");
        return map;
    }

}
