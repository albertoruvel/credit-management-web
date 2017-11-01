package com.albertoruvel.credit.web.service.impl;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.UserConfiguration;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardPurchase;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardRequest;
import com.albertoruvel.credit.web.data.dto.resp.CreditCardData;
import com.albertoruvel.credit.web.data.dto.resp.ExecutionResult;
import com.albertoruvel.credit.web.data.dto.resp.PieChartItem;
import com.albertoruvel.credit.web.service.CreditCardService;
import com.albertoruvel.credit.web.service.DataStoreService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

@Singleton
public class CreditCardServiceImpl implements CreditCardService {

    @Inject
    private DataStoreService dataStoreService;

    private final Logger log = Logger.getLogger(getClass().getCanonicalName());
    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));}

    @Override
    public Response saveCreditCard(String token, NewCreditCardRequest request) throws Exception {
        //get account
        UserAccount account = dataStoreService.getAccountByToken(token);
        if (account == null) {
            throw new RuntimeException("No account found for token");
        }

        //create new credit card
        CreditCard creditCard = new CreditCard();
        creditCard.setUserId(account.getId());
        creditCard.setAnnualFee(request.getAnnualFee());
        creditCard.setMaxSalary(request.getMaxSalary());
        creditCard.setName(request.getName());
        creditCard.setPayDate(request.getPayDate());
        creditCard.setPeriodDate(request.getPeriodDate());
        creditCard.setRegistrationDate(dateFormat.format(new Date()));

        dataStoreService.saveEntity(creditCard);
        log.info("New credit card has been created for " + account.getName());
        return Response.ok(new ExecutionResult("Credit card has been created", true)).build();
    }

    @Override
    public Response getUserCreditCards(String token) throws Exception {
        UserAccount userAccount = dataStoreService.getAccountByToken(token);
        if (userAccount == null) {
            throw new RuntimeException("No account found for token");
        }

        //get all credit cards
        List<CreditCard> creditCards = dataStoreService.getCreditCards(userAccount.getId());
        List<CreditCardData> data = new ArrayList<>();
        CreditCardData cData;
        for (CreditCard creditCard : creditCards) {
            cData = new CreditCardData(creditCard.getId(), creditCard.getName(), creditCard.getAnnualFee(),
                    creditCard.getMaxSalary(), creditCard.getPeriodDate(), creditCard.getPayDate(),
                    creditCard.getRegistrationDate(), calculateCurrentSalary(creditCard.getId()));
            data.add(cData);
        }

        //return response
        return Response.ok(data).build();
    }

    @Override
    public Response getPieChartByCreditCards(String token) throws Exception {
        try{
            UserAccount userAccount = dataStoreService.getAccountByToken(token);
            UserConfiguration configuration = dataStoreService.getUserConfiguration(userAccount.getId());
            if (userAccount == null) {
                throw new RuntimeException("No account found for token");
            }

            //get credit cards
            List<CreditCard> creditCards = dataStoreService.getCreditCards(userAccount.getId());
            List<CreditCardPurchase> purchases;
            List<PieChartItem> items = new ArrayList<>();
            double monthlyTotal = 0.0;
            for (CreditCard creditCard : creditCards) {
                //get all purchases from credit card
                purchases = dataStoreService.getCreditCardPurchases(creditCard.getId());
                double total = 0.0;
                for (CreditCardPurchase purchase : purchases){
                    total += purchase.getTotal();
                }
                monthlyTotal += total;
                items.add(new PieChartItem(creditCard.getName(), total));
            }

            double free = configuration.getMonthlyIncome() - monthlyTotal;
            items.add(new PieChartItem("Monthly free", free));
            return Response.ok(items).build();
        }catch(Exception ex){
            throw new RuntimeException("Could not complete request", ex);
        }
    }

    @Override
    public Response addNewCreditCardPurchase(String header, NewCreditCardPurchase request) throws Exception {
        //get account
        UserAccount account = dataStoreService.getAccountByToken(header);
        if (account == null){
            throw new RuntimeException("No account found for token");
        }

        //create a new purchase
        CreditCardPurchase purchase = new CreditCardPurchase();
        purchase.setConcept(request.getName());
        purchase.setDescription(request.getDescription());
        purchase.setCreditCardId(request.getCreditCardId());
        purchase.setMonths(request.getNumberOfMonths());
        purchase.setPaymentsLeft(request.getNumberOfMonths());
        purchase.setPurchaseDate(dateFormat.format(new Date()));
        purchase.setTotal(request.getTotal());
        dataStoreService.saveEntity(purchase);

        return Response.ok(new ExecutionResult("Credit card have been added", true)).build();
    }

    private Double calculateCurrentSalary(String creditCardId) {
        //TODO: calculate getting all current movements within current period and a sum of all of those prices
        return 999.9;
    }


}
