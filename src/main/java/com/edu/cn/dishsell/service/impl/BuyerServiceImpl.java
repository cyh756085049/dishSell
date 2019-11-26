package com.edu.cn.dishsell.service.impl;

import com.edu.cn.dishsell.dto.OrderDTO;
import com.edu.cn.dishsell.enums.ResultEnum;
import com.edu.cn.dishsell.exception.SellException;
import com.edu.cn.dishsell.service.BuyerService;
import com.edu.cn.dishsell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cyh
 * @Date: 2019-11-12 22:26
 * @Description: 买家service实现类
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单
     * @param orderId
     * @param openid
     * @return
     */
    @Override
    public OrderDTO findOrderOne(String orderId, String openid) {
        return checkOrderOwner(orderId, openid);
    }

    /**
     * 取消订单
     * @param orderId
     * @param openid
     * @return
     */
    @Override
    public OrderDTO cancelOrder(String orderId, String openid) {
        OrderDTO orderDTO = checkOrderOwner(orderId, openid);
        if (orderDTO == null) {
            log.error("[取消订单] 查不到该订单，openid={}", openid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String orderId, String openid) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("[查询订单] 订单的openid不一致，openid={}", openid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
