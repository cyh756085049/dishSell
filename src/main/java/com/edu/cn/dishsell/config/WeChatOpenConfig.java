package com.edu.cn.dishsell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: cyh
 * @Date: 2019-11-23 12:22
 * @Description:
 **/
@Component
public class WeChatOpenConfig {

    @Autowired
    private WeChatAccountConfig accountConfig;

    @Bean
    public WxOpenService wxOpenService() {
        WxOpenService wxOpenService = new WxOpenServiceImpl();
        wxOpenService.setWxOpenConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxOpenConfigStorage wxOpenConfigStorage() {
        WxOpenInMemoryConfigStorage wxOpenConfigStorage = new WxOpenInMemoryConfigStorage();
        wxOpenConfigStorage.setComponentAppId(accountConfig.getOpenAppId());
        wxOpenConfigStorage.setComponentAppSecret(accountConfig.getOpenAppSecret());
        return wxOpenConfigStorage;
    }

//    教程版本
//    @Bean
//    public WxMpService wxOpenService() {
//        WxMpService wxOpenService = new WxMpServiceImpl();
//        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
//        return wxOpenService;
//    }
//
//    @Bean
//    public WxMpConfigStorage wxOpenConfigStorage() {
//        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
//        wxMpInMemoryConfigStorage.setAppId(accountConfig.getOpenAppId());
//        wxMpInMemoryConfigStorage.setSecret(accountConfig.getOpenAppSecret());
//        return wxMpInMemoryConfigStorage;
//    }
}
