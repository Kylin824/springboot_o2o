package com.example.o2o.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Shop {
    private Long shopId;
    private Long ownerId;
    private Long shopCategoryId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Double longitude;
    private Double latitude;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    // 店铺状态
    private Integer enableStatus;
    // 超级管理员给店家的提醒
    private String advice;

    private Area area; // <=> tb_area
    private ShopCategory shopCategory; // <=> tb_shop_category
    private PersonInfo owner; // <=> tb_person_info

}
