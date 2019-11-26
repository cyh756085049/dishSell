package com.edu.cn.dishsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: cyh
 * @Date: 2019-11-12 18:05
 * @Description: 买家创建订单类（直接前台要添加的字段）
 **/
@Data
public class OrderForm {

    /** 买家姓名 */
    @NotEmpty(message = "买家姓名必填")
    private String name;

    /** 买家手机号 */
    @NotEmpty(message = "买家手机号必填")
    private String phone;

    /** 买家地址 */
    @NotEmpty(message = "买家地址必填")
    private String address;

    /** 买家微信openid */
    @NotEmpty(message = "买家微信openid必填")
    private String openid;

    /** 购物车 */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
