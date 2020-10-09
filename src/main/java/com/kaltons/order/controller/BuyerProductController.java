package com.kaltons.order.controller;

import com.kaltons.order.entity.ProductCategory;
import com.kaltons.order.entity.ProductInfo;
import com.kaltons.order.service.CategoryService;
import com.kaltons.order.service.ProductService;
import com.kaltons.order.utils.ResultVOUtil;
import com.kaltons.order.vo.ProductInfoVO;
import com.kaltons.order.vo.ProductVO;
import com.kaltons.order.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品ProductInfo 业务控制器
 *
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 23:28
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list(){

        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //从查到的上架商品中获取类目编号
        List<Integer> categoryTypeList  = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //查询类目(一次性查询)
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //封装信息
        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
