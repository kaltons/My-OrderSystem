package com.kaltons.order.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * TODO
 * 定制返回给前端的商品视图对象
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 23:41
 */
@Data
public class ProductVO {

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
