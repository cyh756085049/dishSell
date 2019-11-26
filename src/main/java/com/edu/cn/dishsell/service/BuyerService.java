package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * @Author: cyh
 * @Date: 2019-11-12 22:24
 * @Description:
 **/
public interface BuyerService {

    /** 查询一个订单 */
    OrderDTO findOrderOne(String orderId, String openid);

    /** 取消订单 */
    OrderDTO cancelOrder(String orderId, String openid);
}
