package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.CartItem;
import com.kob.backend.pojo.CartItemDTO;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {


    @Select("""
                SELECT
                    c.id, c.user_id, c.product_id, c.quantity,
                    p.name AS product_name,
                    p.price AS product_price,
                    p.discount_price AS product_discount_price,
                    pi.image_url AS product_image
                FROM cart_item c
                LEFT JOIN product p ON c.product_id = p.id
                LEFT JOIN product_image pi ON p.id = pi.product_id
                WHERE c.user_id = #{userId}
            """)
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "product_name", property = "name"),
            @Result(column = "product_price", property = "price"),
            @Result(column = "product_discount_price",
                    property = "discountPrice"),
            @Result(column = "product_image", property = "imageUrl")
    })
    List<CartItemDTO> getCartItemWithProduct(
            @Param("userId") BigInteger userId);
} 