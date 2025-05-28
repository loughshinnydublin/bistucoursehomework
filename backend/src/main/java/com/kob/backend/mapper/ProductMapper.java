package com.kob.backend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    // 通过 Product ID 查询 Product_image 表
    @Select("SELECT * FROM product_image WHERE product_id = #{productId}")
    List<ProductImage> getProductImagesByProductId(BigInteger productId);

//    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
//    List<Product> searchProducts(String keyword);

//    @Select("SELECT * FROM product WHERE category = #{category}")
//    List<Product> getProductsByCategory(String category);

    @Select("SELECT * FROM product WHERE status = 'PENDING'")
    List<Product> selectPendingProducts();
}
