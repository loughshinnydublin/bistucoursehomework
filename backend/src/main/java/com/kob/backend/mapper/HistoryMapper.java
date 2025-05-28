package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    //查询id为userId的历史记录
    @Select("SELECT * FROM history WHERE user_id = #{userId}")
    List<History> getHistoryByUserId(BigInteger userId);

}
