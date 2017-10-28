package com.albertoruvel.credit.web.service;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardRequest;

import java.util.List;

public interface DataStoreService {

    //account operations
    public void addAccount(UserAccount account);
    public boolean isEmailRegistered(String email);
    public UserAccount getAccount(String email, String password);
    public boolean isTokenValid(String token);
    public UserAccount getAccountByToken(String token);
    public UserConfiguration getUserConfiguration(String userId);
    public void saveConfiguration(UserConfiguration configuration);

    //credit card operations
    public void saveUserCreditCard(CreditCard creditCard);
    public List<CreditCard> getCreditCards(String userId);
    public List<CreditCardPurchase> getCreditCardPurchases(String id);
}
