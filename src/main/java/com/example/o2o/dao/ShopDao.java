package com.example.o2o.dao;

import com.example.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDao {

    /**
     * 新增店铺记录
     * @param shop shop
     * @return 成功返回1，代表一行记录被影响
     */
    int insertShop(Shop shop);

    /**
     * 根据店铺ID查询店铺信息，返回shop实体类
     * @param shopId shopId
     * @return Shop
     */
    Shop queryByShopId(long shopId);

    /**
     * 更新店铺信息
     * @param shop shop
     * @return 成功返回1，代表一行记录被影响
     */
    int updateShop(Shop shop);

    /**
     * 分页查询店铺,可输入的条件有：店铺名（模糊），店铺状态，店铺Id,店铺类别,区域ID，ownerId
     *
     * @param shopCondition 店铺信息
     * @param rowIndex 从第几行开始取数据
     * @param pageSize 返回的条数
     * @return 店铺列表
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 查询店铺列表的结果总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
