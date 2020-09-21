package com.kaltons.order.service.impl;

import com.kaltons.order.entity.ProductCategory;
import com.kaltons.order.repository.ProductCategoryRepository;
import com.kaltons.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 * ProductCategory 业务操作接口类的实现类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 20:12
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 查找单个商品类目
     * @param categoryId
     * @return
     */
    @Override
    public Optional<ProductCategory> findOne(Integer categoryId) {

        return  repository.findById(categoryId);
    }

    /**
     * 查找所有类目信息
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    /**
     * 买家端 - 根据类目编号集合查找所有类目信息
     * @param categoryTypeList
     * @return
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    /**
     * 新增类目信息
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
