package com.edu.cn.dishsell.converter;

import com.edu.cn.dishsell.dataobject.OrderDetail;
import com.edu.cn.dishsell.dto.OrderDTO;
import com.edu.cn.dishsell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cyh
 * @Date: 2019-11-12 18:20
 * @Description: OrderDTO转化为OrderForm
 **/
@Slf4j
public class OrderDTOToOrderFormConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("[对象转换] 错误， String={}", orderForm.getItems());
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
