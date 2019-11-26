package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: cyh
 * @Date: 2019-11-22 19:45
 * @Description: 卖家信息类jpa接口
 **/
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
