package com.example.springboot_o2o.mapper;

import com.example.springboot_o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper {

    List<ProductCategory> queryProductCategoryByShopId(long shopId);

    int batchInsertProductCategories(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);


}
