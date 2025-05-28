package com.kob.backend.pojo;


//便于添加product

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private String size;
    private boolean bargain_allowed;
    private String product_condition;
    private int quantity;
    private String category;

    private String imgUrl;
}
