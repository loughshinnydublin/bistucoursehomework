package com.kob.backend.service.user.account;

import org.springframework.web.multipart.MultipartFile;


import java.math.BigInteger;
import java.util.Map;

public interface updateUserService {
    Map<String, String> updateUser(Map<String, String> data);
    //上传图片文件
//    Map<String, String> uploadAvatar(MultipartFile file, BigInteger userId);


}
