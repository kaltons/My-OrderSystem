package com.kaltons.order.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信开发平台参数配置类
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 12:09
 */
@Configuration
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig accountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){

        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();

        wxMpDefaultConfig.setAppId(accountConfig.getOpenAppId());
        wxMpDefaultConfig.setSecret(accountConfig.getOpenAppSecret());

        return wxMpDefaultConfig;
    }
}
