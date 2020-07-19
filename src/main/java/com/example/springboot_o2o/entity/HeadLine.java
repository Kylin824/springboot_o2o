package com.example.springboot_o2o.entity;

import lombok.Data;
import java.util.Date;

@Data
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    //0不可用 1可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;

}
