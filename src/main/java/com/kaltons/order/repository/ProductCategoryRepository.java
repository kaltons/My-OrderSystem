package com.kaltons.order.repository;

import com.kaltons.order.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO
 * 商品类目 —— 持久化接口类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 16:50
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     * 通过类目编号查找所有类目信息
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
