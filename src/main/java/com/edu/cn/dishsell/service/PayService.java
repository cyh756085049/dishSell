package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Author: cyh
 * @Date: 2019-11-17 15:12
 * @Description:
 **/
public interface PayService {

    /** 创建支付 */
    PayResponse create(OrderDTO orderDTO);

    /** 异步通知 */
    PayResponse notify(String notifyData);

    /** 微信退款 */
    RefundResponse refund(OrderDTO orderDTO);
}
