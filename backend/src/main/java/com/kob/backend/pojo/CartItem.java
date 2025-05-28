package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cart_item")
public class CartItem {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private BigInteger userId;
    private BigInteger productId;
    private Integer quantity;
    private Date createTime;
} 