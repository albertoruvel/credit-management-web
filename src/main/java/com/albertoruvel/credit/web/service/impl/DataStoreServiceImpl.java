package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.service.DataStoreService;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DataStoreServiceImpl implements DataStoreService {
    @Override
    public void addAccount(UserAccount account) {
        ofy().save().entity(account).now();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        List<UserAccount> list = getAccounts();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public UserAccount getAccount(String email, String password) {
        List<UserAccount> list = getAccounts();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getEmail().equalsIgnoreCase(email) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean isTokenValid(String token) {
        List<UserAccount> list = getAccounts();
        //search for account with email
        for (UserAccount account : list) {
            if (account.getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    private List<UserAccount> getAccounts() {
        return ofy().load().type(UserAccount.class).list();
    }

    private List<UserConfiguration> getConfigurations() {
        return ofy().load().type(UserConfiguration.class).list();
    }

    @Override
    public UserAccount getAccountByToken(String token) {
        for (UserAccount account : getAccounts()) {
            if (account.getToken().equals(token)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public UserConfiguration getUserConfiguration(String userId) {
        for (UserConfiguration config : getConfigurations()) {
            if (config.getUserId().equals(userId)) {
                return config;
            }
        }
        return null;
    }

    @Override
    public void saveConfiguration(UserConfiguration configuration) {
        ofy().save().entity(configuration).now();
    }

    @Override
    public void saveUserCreditCard(CreditCard creditCard) {
        ofy().save().entity(creditCard).now();
    }

    @Override
    public List<CreditCard> getCreditCards(String userId) {
        List<CreditCard> list = ofy().load().type(CreditCard.class).list();
        List<CreditCard> userCreditCards = new ArrayList<>();
        for (CreditCard card : list) {
            if (card.getUserId().equals(userId)) {
                userCreditCards.add(card);
            }
        }
        return userCreditCards;
    }

    @Override
    public List<CreditCardPurchase> getCreditCardPurchases(String id) {
        List<CreditCardPurchase> allPurchases = ofy().load().type(CreditCardPurchase.class).list();
        List<CreditCardPurchase> cardPurchases = new ArrayList<>();
        for (CreditCardPurchase purchase : allPurchases) {
            if (purchase.getCreditCardId().equals(id)){
                cardPurchases.add(purchase);
            }
        }
        return cardPurchases;
    }


}
