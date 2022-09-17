package com.sing.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设置与开发->开发者工具->公众号平台测试账号
 * appID：wx7b5cacf87cddb94f
 * appsecret：5775c28fcdac7452a456ac5380a60f12
 * 内容穿透
 * https://natapp.cn/
 * http://wenet.seejoke.com
 * https://www.ngrok.cc/
 *
 *  http://9qgwzj.natappfree.cc -> 127.0.0.1:8081  sing1edsawf
 *
 *  微信开发框架
 *  https://github.com/liyiorg/weixin-popular
 *
 *
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
