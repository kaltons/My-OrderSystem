package com.kaltons.order.service;

import com.kaltons.order.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 * ProductCategory 业务操作接口类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 20:09
 */
public interface CategoryService {

    /**
     * 查找单个商品类目
     * @param categoryId
     * @return
     */
    Optional<ProductCategory> findOne(Integer categoryId);

    /**
     * 查找所有类目信息
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 买家端 - 根据类目编号集合查找所有类目信息
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增类目信息
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
