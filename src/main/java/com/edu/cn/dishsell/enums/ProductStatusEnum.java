package com.edu.cn.dishsell.enums;

import lombok.Getter;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:38
 * @Description: 商品状态枚举类
 **/
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    //构造方法
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
