package com.albertoruvel.credit.web.service;

import com.albertoruvel.credit.web.data.dto.req.AccountSigninRequest;
import com.albertoruvel.credit.web.data.dto.req.NewAccountRequest;

import javax.ws.rs.core.Response;

public interface AccountService {

    /**
     * Add a new account to cloud data store
     * @param account
     * @return
     */
    public Response addAccount(NewAccountRequest account) throws Exception;

    /**
     * Sign in to cloud data store
     * @param request
     * @return
     * @throws Exception
     */
    public Response signin(AccountSigninRequest request) throws Exception;

    /**
     * Validate a given token with datastore
     * @param token
     * @return
     * @throws Exception
     */
    public Response validateToken(String token)throws Exception;
}
