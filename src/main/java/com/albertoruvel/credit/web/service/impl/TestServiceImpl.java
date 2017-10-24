package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.service.TestService;

import javax.inject.Singleton;

@Singleton
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("Hello from test service, your DI works!");
    }
}
