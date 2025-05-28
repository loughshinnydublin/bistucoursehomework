package com.kob.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;


//实现联表查询
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO {

    //cartitem
    private BigInteger id;
    private BigInteger userId;
    private BigInteger productId;
    private Integer quantity;
    private Date createTime;

    //product
    private BigInteger merchantId;
    private String name;
    private double price;
    private double discountPrice;

    //productimage
    private String imageUrl;


}
