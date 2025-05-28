package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(User user, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if (user.getUsername() == null) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (user.getPassword() == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        user.setUsername(user.getUsername().trim()); //删除首尾的空白字符
        if (user.getUsername().isEmpty()) {
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if (user.getUsername().length() > 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if (user.getPassword().length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (!user.getPassword().equals(confirmedPassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

//没有对role，phone的判断





        //查询数据库里是否有用户名等于username的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());  //判断数据库中用户名是否有一致
        List<User> users = userMapper.selectList(queryWrapper);

        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String Encoded_Password = passwordEncoder.encode(user.getPassword());
//        String photo = "https://cdn.acwing.com/media/user/profile/photo/247763_lg_f72e146e58.jpg";
        Date now = new Date();
        //todo:
        //id自增，不用传
        user.setPassword(Encoded_Password);
        user.setCreateTime(now);
        user.setStatus("PENDING");

        //测试用
        user.setCity(null);
        user.setBankAccount(null);
        user.setEmail(null);
        user.setGender("男");
        user.setPhone(null);
        user.setRole("USER");


        //end


        userMapper.insert(user);    //插入数据库


        map.put("error_message", "success");

        return map;
    }
}
