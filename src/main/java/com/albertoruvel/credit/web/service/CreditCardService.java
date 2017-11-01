package com.albertoruvel.credit.web.service;


import com.albertoruvel.credit.web.data.dto.req.NewCreditCardPurchase;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardRequest;

import javax.ws.rs.core.Response;

public interface CreditCardService {

    /**
     * saves a new credit card info
     * @param token
     * @param request
     * @return
     * @throws Exception
     */
    public Response saveCreditCard(String token, NewCreditCardRequest request) throws Exception;

    /**
     * get a user credit cards using an authentication token
     * @param token
     * @return
     * @throws Exception
     */
    public Response getUserCreditCards(String token)throws Exception;

    /**
     * Get pie chart data from credit cards purchases and free cash for the month
     * @param token
     * @return
     * @throws Exception
     */
    public Response getPieChartByCreditCards(String token) throws Exception;

    /**
     * Add a new purchase to a credit card
     * @param header
     * @param purchase
     * @return
     * @throws Exception
     */
    public Response addNewCreditCardPurchase(String header, NewCreditCardPurchase purchase)throws Exception;
}
