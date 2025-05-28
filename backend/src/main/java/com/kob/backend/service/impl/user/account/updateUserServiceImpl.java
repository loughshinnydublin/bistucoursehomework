package com.kob.backend.service.impl.user.account;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.updateUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class updateUserServiceImpl implements updateUserService {

    private final UserMapper userMapper;

    public updateUserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Map<String, String> updateUser(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();
        Map<String, String> map = new HashMap<>();

        if (data.get("password").equals(data.get("confirmedPassword"))) {
            user.setPassword(data.get("password"));
            map.put("error_message", "success");
        } else {
            map.put("error_message", "密码不一致");
        }
        if (data.get("email") != null) {
            user.setEmail(data.get("email"));
            map.put("error_message", "success");
        }
        if (data.get("phone") != null) {
            user.setPhone(data.get("phone"));
            map.put("error_message", "success");
        }
        if (data.get("address") != null) {
            user.setAddress(data.get("address"));
            map.put("error_message", "success");
        }

        //插入数据库
        userMapper.updateById(user);
        return map;
    }
}