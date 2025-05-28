package com.kob.backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithOrderItemId {
    private Product product;
    private BigInteger orderItemId;
    private BigInteger orderId;
    private String status;
}
