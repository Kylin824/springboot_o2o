package com.example.o2o.entity;

import lombok.Data;
import java.util.Date;

@Data
public class PersonInfo {
    // 用户id
    private Long userId;
    // 用户名称
    private String name;
    // 用户头像
    private String profileImg;
    // 用户邮箱
    private String email;
    // 用户性别
    private String gender;
    // 用户状态
    private Integer enableStatus;

    private Integer customerFlag;
    private Integer shopOwnerFlag;
    private Integer adminFlag;

    private Date createTime;
    private Date lastEditTime;

}
