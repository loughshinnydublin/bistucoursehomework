package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("orders")    //sql不能用order作为表名
public class Order {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private BigInteger userId;
    private Double totalAmount;
    private String status;
    private Date createTime;
    private Date updateTime;
}
