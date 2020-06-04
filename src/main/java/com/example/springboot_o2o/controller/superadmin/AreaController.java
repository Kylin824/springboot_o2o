package com.example.springboot_o2o.controller.superadmin;

import com.example.springboot_o2o.entity.Area;
import com.example.springboot_o2o.mapper.AreaMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kylin
 * @create 2020/6/4 20:49
 */

@Slf4j
@RestController
@RequestMapping("/superadmin")
public class AreaController {

    private final static Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaMapper areaMapper;

    @GetMapping("listarea")
    public Map<String, Object> listArea() {
        logger.info("+++++++++++++start++++++++++++++");
        Map<String, Object> modelMap = new HashMap<>();

        try {
            List<Area> areaList = areaMapper.queryArea();
            modelMap.put("rows", areaList);
            modelMap.put("total", areaList.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.info("=============End==============");
        return modelMap;
    }
}
