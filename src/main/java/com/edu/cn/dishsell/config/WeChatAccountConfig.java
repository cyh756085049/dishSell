package com.edu.cn.dishsell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: cyh
 * @Date: 2019-11-14 16:00
 * @Description:
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
@Configuration
public class WeChatAccountConfig {

    /** 公众平台ID */
    @Value("${wechat.mpAppId}")
    private String mpAppId;

    /** 公众平台密钥 */
    @Value("${wechat.mpAppSecret}")
    private String mpSecret;

    /** 开放平台id */
    @Value("${wechat.openAppId}")
    private String openAppId;

    /** 开放平台密钥 */
    @Value("${wechat.openAppSecret}")
    private String openAppSecret;

    /** 商户号 */
    @Value("${wechat.mchId}")
    private String mchId;

    /** 商户密钥 */
    @Value("${wechat.mchKey}")
    private String mchKey;

    /** 商户证书路径 */
    @Value("${wechat.keyPath}")
    private String keyPath;

    /** 微信支付异步通知地址 */
    @Value("${wechat.notifyUrl}")
    private String notifyUrl;

    /**
     * 微信模版id
     */

//    @Value("${wechat.templateId}")
    private Map<String, String> templateId;
}
