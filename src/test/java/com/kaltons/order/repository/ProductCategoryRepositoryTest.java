package com.kaltons.order.repository;

import com.kaltons.order.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * ProductCategory —— 持久化接口测试类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 16:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 测试简单查询
     */
    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory  = productCategoryRepository.findById(1);
        System.out.println(productCategory.toString());
    }


    /**
     * 测试添加操作
     */
    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
        //Assert.assertNotEquals(null, result);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void updateTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);

    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}