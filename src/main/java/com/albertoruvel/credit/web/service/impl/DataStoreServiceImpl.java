package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.service.DataStoreService;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DataStoreServiceImpl implements DataStoreService {
    @Override
    public void addAccount(UserAccount account) {
        ofy().save().entity(account).now();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        List<UserAccount> list = ofy().load().type(UserAccount.class).list();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public UserAccount getAccount(String email, String password) {
        List<UserAccount> list = ofy().load().type(UserAccount.class).list();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getEmail().equalsIgnoreCase(email) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean isTokenValid(String token) {
        List<UserAccount> list = ofy().load().type(UserAccount.class).list();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getToken().equals(token)){
                return true;
            }
        }
        return false;
    }
}
