package com.example.springboot_o2o.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Product {

    private Long productId;
    private String productName;
    private String productDesc;
    private String imgAddr;// 简略图
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    // 0 下架 1 展示
    private Integer enableStatus;
    // 一个商品对应多张缩略图
    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;

}
