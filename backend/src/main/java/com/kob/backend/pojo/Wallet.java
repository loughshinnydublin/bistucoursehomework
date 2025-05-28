package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("wallet")
public class Wallet {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private BigInteger userId;
    private Double balance;
    private double points;
} 