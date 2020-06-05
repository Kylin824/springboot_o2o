package com.example.springboot_o2o.mapper;

import com.example.springboot_o2o.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kylin
 * @create 2020/6/4 19:27
 */

@Repository
public interface AreaMapper {

//    @Select("SELECT area_id, area_name, priority, create_time, last_edit_time FROM tb_area ORDER BY priority DESC")
    List<Area> queryArea();

}




