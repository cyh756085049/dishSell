package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dto.OrderDTO;

/**
 * @Author: cyh
 * @Date: 2019-11-24 14:49
 * @Description: 微信模版推送消息
 **/
public interface PushMessageService {

    /** 订单状态变更消息 */
    void orderStatus(OrderDTO orderDTO);
}
