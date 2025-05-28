package com.kob.backend.service.impl.user.merchant;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.merchant.merchantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class merchantRegisterServiceImpl implements merchantRegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> register() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        user.setRole("MERCHANT");
        user.setStatus("MERCHANT_REQUESTING");

        userMapper.updateById(user);
        Map<String, String> map = new HashMap<>();

        map.put("error_message", "success");
        return map;
    }
}
