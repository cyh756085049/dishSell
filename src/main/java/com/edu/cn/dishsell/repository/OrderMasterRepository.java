package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:00
 * @Description: 订单jpa接口
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
