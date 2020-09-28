package com.kaltons.order.form;

import lombok.Data;

/**
 * 存储表单提交过来的类目模型
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/28 - 23:47
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
