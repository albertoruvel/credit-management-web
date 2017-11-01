package com.albertoruvel.credit.web.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.logging.Logger;

public class CreditCardPeriodCheckJob implements Job {

    private final Logger log = Logger.getLogger(getClass().getCanonicalName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing scheduling job to find credit card expired periods");
    }
}
