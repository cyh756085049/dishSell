package com.edu.cn.dishsell.repository;

import com.edu.cn.dishsell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @Author: cyh
 * @Date: 2019-11-10 18:38
 * @Description: 商品类jpa接口
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 通过商品状态查询商品列表
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
