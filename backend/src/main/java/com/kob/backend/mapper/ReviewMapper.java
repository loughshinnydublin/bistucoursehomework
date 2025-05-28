package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    @Select("select * from review")
    List<Review> findByProductId(BigInteger productId);
} 