package com.edu.cn.dishsell.form;

import com.edu.cn.dishsell.enums.ProductStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: cyh
 * @Date: 2019-11-22 14:40
 * @Description: 卖家新增商品类（直接前台要添加的字段）
 **/
@Data
public class ProductForm {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常，1下架. */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号. */
    private Integer categoryType;

}
