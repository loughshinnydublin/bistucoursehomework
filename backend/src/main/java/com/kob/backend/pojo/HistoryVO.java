package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

//实现浏览历史
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryVO {
    private Date viewTime;
    private BigInteger productId;
    private String productName;
    private String description;
    private String imageUrl;
}
