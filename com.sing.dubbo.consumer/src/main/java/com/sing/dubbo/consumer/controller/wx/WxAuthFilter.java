package com.sing.dubbo.consumer.controller.wx;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.bean.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author songbo
 * @since 2022-09-19
 */
@WebFilter(filterName = "WxAuthFilter", urlPatterns = {"/wxProfile/*"})
public class WxAuthFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxAuthFilter.class);
    @Autowired
    WxConfig wxConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 判断用户登录状态 检查session里面有没有User对象
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            String uri = req.getRequestURI();
            String redirectUrl = wxConfig.getDomain() + "/wxAuth?uri=" + uri;
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxConfig.getAppId() + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(url);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
