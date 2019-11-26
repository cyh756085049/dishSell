package com.edu.cn.dishsell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: cyh
 * @Date: 2019-11-23 13:30
 * @Description:
 **/
@Data
@Component
public class ProjectUrlConfig {

    /** 微信公众平台授权url */
    @Value("${projectUrl.wechatMpAuthorize}")
    public String wechatMpAuthorize;

    /** 微信开放平台授权url */
    @Value("${projectUrl.wechatOpenAuthorize}")
    public String wechatOpenAuthorize;

    /** 点餐系统 */
    @Value("${projectUrl.sell}")
    public String sell;
}
