package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private BigInteger id;
    private BigInteger orderItemId;
    private BigInteger productId;
    private BigInteger merchantId;
    private BigInteger userId;
    private int rating;
    private String comment;
    private Date createTime;
} 