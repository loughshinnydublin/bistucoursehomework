package com.kob.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    //表示由一个商家创建的商品类。
    //属性：id, merchant_id(由。。。创建), name, category,

    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private BigInteger merchantId;    //命名方式不能和数据库名称相同, queryWrapper中用数据库的名称
    private String name;
    private String category;
    private double price;
    private double discountPrice;
    private String size;
    private String description;
    private boolean bargainAllowed;
    private String productCondition;
    private int sales;
    private int quantity;
    private String status;



    //日期展示格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
