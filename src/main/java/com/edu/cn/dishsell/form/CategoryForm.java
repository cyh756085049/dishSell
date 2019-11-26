package com.edu.cn.dishsell.form;

import lombok.Data;

/**
 * @Author: cyh
 * @Date: 2019-11-22 15:59
 * @Description:
 **/
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;
}
