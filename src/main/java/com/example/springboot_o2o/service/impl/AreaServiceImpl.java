package com.example.springboot_o2o.service.impl;

import com.example.springboot_o2o.entity.Area;
import com.example.springboot_o2o.mapper.AreaMapper;
import com.example.springboot_o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kylin
 * @create 2020/6/4 21:57
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAreaList() {
        return areaMapper.queryArea();
    }
}
