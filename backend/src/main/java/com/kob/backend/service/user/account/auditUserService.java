package com.kob.backend.service.user.account;

import com.kob.backend.pojo.User;

import java.math.BigInteger;
import java.util.List;

public interface auditUserService {
    boolean auditUser(BigInteger userId, String newStatus);
    List<User> getPendingUsers();
}
