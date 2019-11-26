package com.edu.cn.dishsell.dto;

import com.edu.cn.dishsell.dataobject.OrderDetail;
import com.edu.cn.dishsell.enums.OrderStatusEnum;
import com.edu.cn.dishsell.enums.PayStatusEnum;
import com.edu.cn.dishsell.utils.EnumUtil;
import com.edu.cn.dishsell.utils.serializer.DateToLongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: cyh
 * @Date: 2019-11-10 18:38
 * @Description: 订单
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)  //作用是当返回的数据中有字段为null时，则不显示该字段，比如此处返回的orderDetailList为null，则不显示
public class OrderDTO {

    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
//    private List<OrderDetail> orderDetailList = new ArrayList<>();
//    如果给字段初始化，则返回的字段的值不为null，而是变为该字段设定的数据类型

    // 该注解表示把字段忽略掉
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
