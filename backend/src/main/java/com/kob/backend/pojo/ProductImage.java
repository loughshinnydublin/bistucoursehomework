package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    @TableId(type = IdType.AUTO)
    private BigInteger id;

    private BigInteger productId;
    private String imageUrl;    //实体类中使用驼峰命名法，不能使用下划线

}
