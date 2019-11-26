package com.edu.cn.dishsell.dto;

import lombok.Data;


/**
 * @Author: cyh
 * @Date: 2019-11-10 19:53
 * @Description: 购物车
 **/
@Data
public class CartDTO {

    /** 商品Id */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
