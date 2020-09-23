package com.kaltons.order.repository;

import com.kaltons.order.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * ProductInfo —— 持久化接口测试类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    /**
     * 单一商品信息查询
     */
    @Test
    public void findOne(){
        Optional<ProductInfo> productInfo = repository.findById("123456");
        productInfo.ifPresent(productInfo1 -> System.out.println(productInfo1.toString()));
    }

    /**
     * 添加商品测试
     */
    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);


        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    /**
     * 根据上架状态查找商品信息测试操作
     */
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}