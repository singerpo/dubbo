package com.sing.dubbo.consumer.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author songbo
 * @since 2022-09-17
 */
@Component
public class WxConfig {
    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.appsecret}")
    private String appsecret;
    @Value("${weixin.token}")
    private String token;
    @Value("${weixin.domain}")
    private String domain;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
