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
    public void saveEntity(Object entity) {
        ofy().save().entity(entity).now();
    }

    @Override
    public <T> T getEntity(String id, Class type) {
        return (T)ofy().load().type(type).filter("id", id).first().now();
    }

    @Override
    public void deleteEntity(String id, Class type) {
        ofy().delete().type(type).id(id);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return ofy().load().type(UserAccount.class).filter("email", email).first().now() != null;
    }

    @Override
    public UserAccount getAccount(String email, String password) {
        return ofy().load().type(UserAccount.class)
                .filter("email", email)
                .filter("password", password)
                .first().now();
    }

    @Override
    public boolean isTokenValid(String token) {
        return ofy().load().type(UserAccount.class)
                .filter("token", token)
                .first().now() != null;
    }

    @Override
    public UserAccount getAccountByToken(String token) {
        return ofy().load().type(UserAccount.class)
                .filter("token", token)
                .first().now();
    }

    @Override
    public UserConfiguration getUserConfiguration(String userId) {
        return ofy().load().type(UserConfiguration.class)
                .filter("userId", userId).first().now();
    }

    @Override
    public List<CreditCard> getCreditCards(String userId) {
        return ofy().load().type(CreditCard.class)
                .filter("userId", userId)
                .list();
    }

    @Override
    public List<CreditCardPurchase> getCreditCardPurchases(String id) {
        return ofy().load().type(CreditCardPurchase.class)
                .filter("creditCardId", id).list();
    }


}
