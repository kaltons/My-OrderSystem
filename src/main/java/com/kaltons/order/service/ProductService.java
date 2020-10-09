package com.kaltons.order.service;

import com.kaltons.order.dto.CartDTO;
import com.kaltons.order.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * ProductInfo 业务操作接口类
 *
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 22:27
 */
public interface ProductService {

    /**
     * 根据商品ID查找
     * @param productId
     * @return
     */
    Optional<ProductInfo> findOne(String productId);

    /**
     * 查找所有上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 卖家查找所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
