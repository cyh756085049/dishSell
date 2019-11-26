package com.edu.cn.dishsell.converter;

import com.edu.cn.dishsell.dataobject.OrderMaster;
import com.edu.cn.dishsell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cyh
 * @Date: 2019-11-11 23:43
 * @Description: OrderMaster转化为OrderDTO
 **/
public class OrderMasterToOrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map( e -> convert(e)).collect(Collectors.toList());
    }
}
