package com.example.springboot_o2o.mapper;

import com.example.springboot_o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author kylin
 * @create 2020/6/4 19:41
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaMapperTest {
    
    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void testQueryArea() {
        List<Area> areaList =areaMapper.queryArea();
        System.out.println(areaList.size());
        System.out.println(areaList.get(0).toString());
        System.out.println(areaList.get(1).toString());
        System.out.println(areaList.get(2).toString());
    }

}
