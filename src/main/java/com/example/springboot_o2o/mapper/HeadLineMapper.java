package com.example.springboot_o2o.mapper;

import com.example.springboot_o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kylin
 * @create 2020/6/5 10:53
 */
public interface HeadLineMapper {

    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
