package com.albertoruvel.credit.web.service;

import com.albertoruvel.credit.web.data.UserAccount;

public interface DataStoreService {
    public void addAccount(UserAccount account);
    public boolean isEmailRegistered(String email);
    public UserAccount getAccount(String email, String password);
    public boolean isTokenValid(String token);
}
