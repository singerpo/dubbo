package com.sing.dubbo.consumer.listener;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.support.TicketManager;
import weixin.popular.support.TokenManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author songbo
 * @since 2022-09-17
 */
@WebListener
public class TokenManagerListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManagerListener.class);

    @Autowired
    WxConfig wxConfig;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("TokenManagerListener init....");
        TokenManager.init(wxConfig.getAppId(),wxConfig.getAppsecret());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        TokenManager.destroyed();

    }
}
