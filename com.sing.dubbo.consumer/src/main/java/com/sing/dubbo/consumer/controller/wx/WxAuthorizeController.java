package com.sing.dubbo.consumer.controller.wx;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 获取微信用户基本信息
 * @author songbo
 * @since 2022-09-19
 */
@Controller
@RequestMapping("/wxAuth")
public class WxAuthorizeController {
    @Autowired
    WxConfig wxConfig;

    @RequestMapping
    public String wxAuth(Model model, @RequestParam Map<String, String> param, HttpServletRequest request) {
        if (param == null || param.get("code") == null) {
            // 处理非法请求
            return "error-login";
        }
        String code = param.get("code");
        SnsToken snsToken = SnsAPI.oauth2AccessToken(wxConfig.getAppId(), wxConfig.getAppsecret(), code);
        User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
        System.out.println("openid:"+snsToken.getOpenid());
        request.getSession().setAttribute("user", user);

        // 登录前请求地址
        String uri = param.get("uri");
        return "redirect:" + uri;
    }
}
