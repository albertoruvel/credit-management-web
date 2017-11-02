package com.albertoruvel.credit.web.config;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.service.AccountService;
import com.albertoruvel.credit.web.service.CreditCardService;
import com.albertoruvel.credit.web.service.DataStoreService;
import com.albertoruvel.credit.web.service.impl.AccountServiceImpl;
import com.albertoruvel.credit.web.service.impl.CreditCardServiceImpl;
import com.albertoruvel.credit.web.service.impl.DataStoreServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;

import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.register;

public class ContextListener extends GuiceServletContextListener {

    private final String RESOURCES_PACKAGE = "com.albertoruvel.credit.web.resource";
    private final Logger log = Logger.getLogger(getClass().getCanonicalName());

    private void registerDatastoreEntities(){
        register(UserAccount.class);
        register(UserConfiguration.class);
        register(CreditCard.class);
        register(CreditCardPurchase.class);
    }

    @Override
    protected Injector getInjector() {
        //register data store entities
        registerDatastoreEntities();
        //startScheduler();
        //create injector
        return Guice.createInjector(new RestServletModule(){
            @Override
            public void configureServlets(){
                rest("/rest/*").packages(RESOURCES_PACKAGE);
                bind(AccountService.class).to(AccountServiceImpl.class).in(Scopes.SINGLETON);
                bind(DataStoreService.class).to(DataStoreServiceImpl.class).in(Scopes.SINGLETON);
                bind(CreditCardService.class).to(CreditCardServiceImpl.class).in(Scopes.SINGLETON);
                bind(FcmPropertiesLoader.class).in(Scopes.SINGLETON);
            }
        });
    }

    /**private void startScheduler() {
        try{
            log.info("Creating scheduled jobs...");
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //create jobs
            JobDetail periodCheckJob = JobBuilder.newJob(CreditCardPeriodCheckJob.class).withIdentity("CreditCardPeriodCheckJob").build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    //.withSchedule(SimpleScheduleBuilder.repeatHourlyForever(24)).build(); TODO: Set to 24HRS after testing
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(2)).build(); //TODO: testing purposes
            scheduler.scheduleJob(periodCheckJob, trigger);
            scheduler.start();
        }catch(SchedulerException ex){
            log.severe("Could not start scheduled jobs: " + ex.getMessage());
        }
    }**/
}
