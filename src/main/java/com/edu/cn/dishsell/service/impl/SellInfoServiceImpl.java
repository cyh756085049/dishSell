package com.edu.cn.dishsell.service.impl;

import com.edu.cn.dishsell.dataobject.SellerInfo;
import com.edu.cn.dishsell.repository.SellerInfoRepository;
import com.edu.cn.dishsell.service.SellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cyh
 * @Date: 2019-11-23 12:37
 * @Description: 卖家信息service实现类
 **/
@Service
public class SellInfoServiceImpl implements SellInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
