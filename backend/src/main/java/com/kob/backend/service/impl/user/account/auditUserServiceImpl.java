package com.kob.backend.service.impl.user.account;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.auditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class auditUserServiceImpl implements auditUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean auditUser(BigInteger userId, String newStatus) {
        if (!newStatus.equals("APPROVED") && !newStatus.equals("REJECTED")) {
            throw new IllegalArgumentException("状态必须为 APPROVED 或 REJECTED");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        user.setStatus(newStatus);
        return userMapper.updateById(user) > 0;
    }


    //todo:待审核用户列表  //前端展示所有待审核用户，输入id和newstatus
    @Override
    public List<User> getPendingUsers() {
        System.out.println("获取pendinglist");
        return userMapper.selectPendingUsers();
    }
}
