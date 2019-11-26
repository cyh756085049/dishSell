package com.edu.cn.dishsell.enums;

import lombok.Getter;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:38
 * @Description: 异常抛出枚举类
 **/
@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "该商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单商品详情为空"),
    ORDER_PAT_ERROR(17, "订单支付不正确"),
    CART_EMPTY(18, "购物车不能为空"),
    ORDER_OWNER_ERROR(19, "该订单支付状态不正确"),
    WECHAT_MP_ERROR(20, "微信公众账号方面的错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(23, "订单完结"),
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),
    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    LOGOUT_SUCCESS(26, "登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
