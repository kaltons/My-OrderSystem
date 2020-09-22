package com.kaltons.order.service.impl;

import com.kaltons.order.entity.ProductInfo;
import com.kaltons.order.enums.ProductStatusEnum;
import com.kaltons.order.repository.ProductInfoRepository;
import com.kaltons.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 * ProductInfo 业务操作接口类的实现类
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 22:32
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    /**
     * 根据商品ID查找
     * @param productId
     * @return
     */
    @Override
    public Optional<ProductInfo> findOne(String productId){
       return repository.findById(productId);
    }

    /**
     * 查找所有上架商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll(){
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 卖家查找所有商品
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    /**
     * 上架商品
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo){
        return repository.save(productInfo);
    }
}
