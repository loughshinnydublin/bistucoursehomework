package com.kob.backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;



//map<string,string>不能传对象，所以用request类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private double totalAmount;
    private String status;
    private BigInteger merchantId;
    private List<OrderItem> items;
}
