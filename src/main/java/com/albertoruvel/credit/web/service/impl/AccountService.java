package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.dto.NewAccountRequest;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AccountService {

    private static final Logger log = Logger.getLogger("AccountService");

    public static String addAccount(NewAccountRequest account) {
        try {
            // create new entity
            UserAccount newAccount = new UserAccount();
            newAccount.setName(account.getName());
            newAccount.setLastName(account.getLastName());
            newAccount.setEmail(account.getEmail());
            newAccount.setPassword(account.getPassword());
            newAccount.setToken(UUID.randomUUID().toString());

            ofy().save().entity(newAccount).now();
            return newAccount.getId();
        } catch (Exception ex) {
            log.severe(ex.getMessage());
            return null;
        }

    }


    public static boolean isEmailRegistered(String email) {
        List<UserAccount> list = ofy().load().type(UserAccount.class).list();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getEmail().equals(email))
                return true;
        }
        return false;
    }

}
