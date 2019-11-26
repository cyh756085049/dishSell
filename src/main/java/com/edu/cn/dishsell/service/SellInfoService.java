package com.edu.cn.dishsell.service;

import com.edu.cn.dishsell.dataobject.SellerInfo;

/**
 * @Author: cyh
 * @Date: 2019-11-23 12:38
 * @Description:卖家信息service接口
 **/
public interface SellInfoService {

    /** 通过openID查询卖家信息 */
    SellerInfo findByOpenid(String openid);
}
