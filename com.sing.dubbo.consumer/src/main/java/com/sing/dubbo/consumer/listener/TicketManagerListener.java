package com.sing.dubbo.consumer.listener;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.support.TicketManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author songbo
 * @since 2022-09-17
 */
@WebListener
public class TicketManagerListener implements ServletContextListener {
    @Autowired
    WxConfig wxConfig;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TicketManager.init(wxConfig.getAppId(), 15, 60 * 119);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        TicketManager.destroyed();

    }
}
