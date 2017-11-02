package com.albertoruvel.credit.web.service;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardRequest;

import java.util.List;

public interface DataStoreService {

    //generic crud operations
    public void saveEntity(Object entity);
    public <T> T getEntity(String id, Class type);
    public void deleteEntity(String id, Class type);

    //account operations
    public boolean isEmailRegistered(String email);
    public UserAccount getAccount(String email, String password);
    public boolean isTokenValid(String token);
    public UserAccount getAccountByToken(String token);
    public UserConfiguration getUserConfiguration(String userId);

    //credit card operations
    public List<CreditCard> getCreditCards(String userId);
    public CreditCard getCreditCard(String id, String userId);
    public List<CreditCardPurchase> getCreditCardPurchases(String id);
}
