package com.albertoruvel.credit.web.config;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.service.AccountService;
import com.albertoruvel.credit.web.service.DataStoreService;
import com.albertoruvel.credit.web.service.impl.AccountServiceImpl;
import com.albertoruvel.credit.web.service.impl.DataStoreServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;

import static com.googlecode.objectify.ObjectifyService.register;

public class ContextListener extends GuiceServletContextListener {

    private final String RESOURCES_PACKAGE = "com.albertoruvel.credit.web.resource";

    private void registerDatastoreEntities(){
        register(UserAccount.class);
    }

    @Override
    protected Injector getInjector() {
        //register data store entities
        registerDatastoreEntities();

        //create injector
        return Guice.createInjector(new RestServletModule(){
            @Override
            public void configureServlets(){
                rest("/rest/*").packages(RESOURCES_PACKAGE);
                bind(AccountService.class).to(AccountServiceImpl.class).in(Scopes.SINGLETON);
                bind(DataStoreService.class).to(DataStoreServiceImpl.class).in(Scopes.SINGLETON);
            }
        });
    }
}
