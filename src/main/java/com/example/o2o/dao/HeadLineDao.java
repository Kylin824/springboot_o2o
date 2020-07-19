package com.example.o2o.dao;
import com.example.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadLineDao {
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
