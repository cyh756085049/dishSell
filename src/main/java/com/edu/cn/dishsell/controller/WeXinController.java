package com.edu.cn.dishsell.controller;

import com.edu.cn.dishsell.config.WeChatAccountConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: cyh
 * @Date: 2019-11-14 14:47
 * @Description:
 **/
@RestController
@RequestMapping("/wexin")
@Slf4j
public class WeXinController {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法...");
        // 用户同意授权，获取code
        log.info("code={}", code);
        // 通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + weChatAccountConfig.getMpAppId() + "&secret=" + weChatAccountConfig.getMpSecret() + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
