package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.data.dto.req.AccountSigninRequest;
import com.albertoruvel.credit.web.data.dto.resp.AuthenticationResult;
import com.albertoruvel.credit.web.data.dto.req.NewAccountRequest;
import com.albertoruvel.credit.web.data.dto.resp.ExecutionResult;
import com.albertoruvel.credit.web.data.dto.resp.TokenValidationResult;
import com.albertoruvel.credit.web.data.dto.resp.UserConfigurationResult;
import com.albertoruvel.credit.web.service.AccountService;
import com.albertoruvel.credit.web.service.DataStoreService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {

    private static final Logger log = Logger.getLogger("AccountServiceImpl");

    @Inject
    private DataStoreService dataStoreService;

    @Override
    public Response addAccount(NewAccountRequest account) throws Exception {
        if (dataStoreService.isEmailRegistered(account.getEmail())) {
            return Response.ok(new AuthenticationResult(false, "Email is already registered", "")).build();
        } else {
            // create new entity
            UserAccount newAccount = new UserAccount();
            newAccount.setName(account.getName());
            newAccount.setLastName(account.getLastName());
            newAccount.setEmail(account.getEmail());
            newAccount.setPassword(account.getPassword());
            newAccount.setToken(UUID.randomUUID().toString());
            dataStoreService.saveEntity(newAccount);
            return Response.ok(new AuthenticationResult(true, "Signed up successfully", newAccount.getToken())).build();
        }
    }

    @Override
    public Response signin(AccountSigninRequest request) throws Exception {
        if (request == null) {
            return Response.ok(new AuthenticationResult(false, "No request body was provided", "")).build();
        } else if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return Response.ok(new AuthenticationResult(false, "No email was provided", "")).build();
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return Response.ok(new AuthenticationResult(false, "No password was provided", "")).build();
        }

        UserAccount account = dataStoreService.getAccount(request.getEmail(), request.getPassword());
        if (account == null) {
            return Response.ok(new AuthenticationResult(false, "Wrong credentials, try again", "")).build();
        } else {
            return Response.ok(new AuthenticationResult(true, "", account.getToken())).build();
        }
    }

    @Override
    public Response validateToken(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            return Response.ok(new TokenValidationResult(false, "Security token was not provided")).build();
        }
        //find the token
        if (dataStoreService.isTokenValid(token)) {
            return Response.ok(new TokenValidationResult(true, "Token already valid")).build();
        } else {
            return Response.ok(new TokenValidationResult(false, "Token is not valid")).build();
        }
    }

    @Override
    public Response userConfiguration(String token) throws Exception {
        //get user using token
        UserAccount account = dataStoreService.getAccountByToken(token);
        if (account == null) {
            throw new RuntimeException("No account was found for specified token");
        }
        UserConfiguration configuration = dataStoreService.getUserConfiguration(account.getId());
        if (configuration == null) {
            //create a new one
            configuration = new UserConfiguration();
            configuration.setMonthlyIncome(0L);
            configuration.setNotificationEnabled(Boolean.TRUE);
            configuration.setUserId(account.getId());
            //save it
            dataStoreService.saveEntity(configuration);
        }
        //create response
        return Response.ok(new UserConfigurationResult(account.getName() + " " + account.getLastName(), account.getEmail(), configuration.getMonthlyIncome(), configuration.isNotificationEnabled())).build();
    }

    @Override
    public Response saveUserConfiguration(String token, UserConfigurationResult request) throws Exception {
        //get account
        UserAccount account = dataStoreService.getAccountByToken(token);
        if (account == null) {
            throw new RuntimeException("No account was found for specified token");
        }

        //update configuration
        UserConfiguration configuration = dataStoreService.getUserConfiguration(account.getId());
        if (configuration == null) {
            log.info("Creating new user configuration");
            configuration = new UserConfiguration();
        } else {
            log.info("Updating configuration");
            configuration.setMonthlyIncome(request.getMonthlyIncome());
            configuration.setUserId(account.getId());
            configuration.setNotificationEnabled(request.isNotificationEnabled());
        }

        dataStoreService.saveEntity(configuration);
        log.info("Configuration has been saved");
        return Response.ok(new ExecutionResult("Success", true)).build();
    }

}
