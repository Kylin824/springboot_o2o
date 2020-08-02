package com.example.o2o.service;

import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Shop;
import com.example.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

    ShopExecution addShop(Shop shop, File shopImg);

    ShopExecution addShopPlus(Shop shop,
                              InputStream shopImgInputStream,
                              String fileName);

    Shop getByShopId(long shopId);


    /**
     * 更新店铺信息，包括对图片的处理
     */
    ShopExecution modifyShop(Shop shop,
                             InputStream shopImgInputStream,
                             String fileName) throws ShopOperationException;

    /**
     * 根据shopCondition分页返回店铺列表
     */
    ShopExecution getShopList(Shop shopCondition,
                              int pageIndex,
                              int pageSize);
}
