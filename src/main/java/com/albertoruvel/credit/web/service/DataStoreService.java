package com.albertoruvel.credit.web.service;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;

public interface DataStoreService {
    public void addAccount(UserAccount account);
    public boolean isEmailRegistered(String email);
    public UserAccount getAccount(String email, String password);
    public boolean isTokenValid(String token);
    public UserAccount getAccountByToken(String token);
    public UserConfiguration getUserConfiguration(String userId);
    public void saveConfiguration(UserConfiguration configuration);
}
