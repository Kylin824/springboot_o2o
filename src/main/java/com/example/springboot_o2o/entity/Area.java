package com.example.springboot_o2o.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author kylin
 */

@Data
public class Area {
    // id 使用Integer默认为空  如果用int默认为0
    private Integer areaId;
    // 区域名称
    private String areaName;
    // 权重/优先级
    private Integer priority;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date lastEditTime;
}
