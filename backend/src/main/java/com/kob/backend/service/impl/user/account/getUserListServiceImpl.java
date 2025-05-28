package com.kob.backend.service.impl.user.account;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.getUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class getUserListServiceImpl implements getUserListService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {

        List<User> list;

        list = userMapper.selectList(null);


        return list;
    }
}
