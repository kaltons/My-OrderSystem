package com.kaltons.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 手工方式获取openid
 *
 * @author 衍方
 * @desc
 * @date 2020/9/23 - 20:16
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    /**
     * 1 第一步：用户同意授权，获取code
     *
     * 2 第二步：通过code换取网页授权access_token
     *
     * 3 第三步：刷新access_token（如果需要）
     *
     * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     *
     * 5 附：检验授权凭证（access_token）是否有效
     * @param code
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        // 1、用户同意授权，获取code
        log.info("进入auth方法。。。");
        log.info("code={}",code);
        // 2、通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa213ec9b2b608f08&secret=fd08229baa7b17cb79ec68c220c3d6491&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        // 3、获取Openid
        String response = restTemplate.getForObject(url,String.class);
        log.info("response={}",response);
    }
}
