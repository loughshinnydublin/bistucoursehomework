package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;


//pojo层将数据库对象转为java类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //用户类
    //属性：id, username, phone, email, city, sex, bank_account, role(user, merchant, admin)


    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private String username;
    private String password;
    private String role;
    private String phone;
    private String email;
    private String city;
    private String gender;
    private String bankAccount;
    private String status;
    private String address;
    private String avatar_url;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
