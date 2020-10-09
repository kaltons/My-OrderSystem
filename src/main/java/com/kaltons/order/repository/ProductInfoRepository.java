package com.kaltons.order.repository;

import com.kaltons.order.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductInfo —— 持久化接口类
 *
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 21:40
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据上架情况查找商品信息
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
