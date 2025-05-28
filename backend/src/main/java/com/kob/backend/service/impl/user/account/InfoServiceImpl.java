package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class InfoServiceImpl implements InfoService {
    @Override

    //取出当前登录的用户
    public Map<String, String> getinfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();


        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("user_id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("role",user.getRole());
        map.put("phone",user.getPhone());
        map.put("email",user.getEmail());
        map.put("gender",user.getGender());
        map.put("city",user.getCity());
        map.put("bank_account",user.getBankAccount());
        map.put("status",user.getStatus());
        map.put("address",user.getAddress());
        map.put("avatar_url",user.getAvatar_url());
        return map;
    }
}
