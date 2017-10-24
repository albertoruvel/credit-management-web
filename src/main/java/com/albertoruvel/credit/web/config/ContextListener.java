package com.albertoruvel.credit.web.config;

import com.albertoruvel.credit.web.data.UserAccount;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.register;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        register(UserAccount.class);
        Logger.getLogger(getClass().getName()).info("Registered objectify entities");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
