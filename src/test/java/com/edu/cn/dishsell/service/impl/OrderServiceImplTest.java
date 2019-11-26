package com.edu.cn.dishsell.service.impl;

import com.edu.cn.dishsell.dataobject.OrderDetail;
import com.edu.cn.dishsell.dto.OrderDTO;
import com.edu.cn.dishsell.enums.OrderStatusEnum;
import com.edu.cn.dishsell.enums.PayStatusEnum;
import com.edu.cn.dishsell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";
    private final String ORDER_ID = "1573478804665257009";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("王丽");
        orderDTO.setBuyerAddress("太原市");
        orderDTO.setBuyerPhone("18234197663");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1004");
        orderDetail.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1002");
        o2.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        //打印日志
        log.info("【创建订单】 result= {}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("查询单个订单 result={}", orderDTO);
        Assert.assertEquals(ORDER_ID, orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        log.info("总数 result = {}", orderDTOPage.getTotalElements());
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1573479853058337914");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1573479853058337914");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void findAllList() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<OrderDTO> orderDTOPage = orderService.findAllList(pageRequest);
        log.info("列表总条数={}", orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有订单列表", orderDTOPage.getTotalElements() > 0);
    }


}
