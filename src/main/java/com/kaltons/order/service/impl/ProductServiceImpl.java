package com.kaltons.order.service.impl;

import com.kaltons.order.dto.CartDTO;
import com.kaltons.order.entity.ProductInfo;
import com.kaltons.order.enums.ProductStatusEnum;
import com.kaltons.order.enums.ResultEnum;
import com.kaltons.order.exception.SellException;
import com.kaltons.order.repository.ProductInfoRepository;
import com.kaltons.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * ProductInfo 业务操作接口类的实现类
 *
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
     * 添加商品
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo){
        return repository.save(productInfo);
    }

    /**
     * 加库存
     * @param cartDTOList
     */
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList) {

            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            productInfo.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));
            int result = productInfo.get().getProductStock() + cartDTO.getProductQuantity();
            repository.save(productInfo.get());
        }
    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());

            productInfo.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

            int result = productInfo.get().getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.get().setProductStock(result);

            repository.save(productInfo.get());
        }
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        productInfo.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 修改为上架状态
        productInfo.get().setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo.get());
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        productInfo.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 修改为上架状态
        productInfo.get().setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo.get());
    }

}
