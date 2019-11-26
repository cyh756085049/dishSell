package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 订单详情jpa接口
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);


}
