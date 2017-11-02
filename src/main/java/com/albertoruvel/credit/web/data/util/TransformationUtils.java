package com.albertoruvel.credit.web.data.util;

import com.albertoruvel.credit.web.data.CreditCard;
import com.albertoruvel.credit.web.data.CreditCardPurchase;
import com.albertoruvel.credit.web.data.dto.resp.CreditCardData;
import com.albertoruvel.credit.web.data.dto.resp.CreditCardPurchaseData;
import com.albertoruvel.credit.web.data.dto.resp.PieChartItem;
import com.albertoruvel.credit.web.service.DataStoreService;

import java.util.ArrayList;
import java.util.List;

public class TransformationUtils {
    public static List<CreditCardData> transformCreditCards(List<CreditCard> cards) {
        List<CreditCardData> data = new ArrayList<>();
        CreditCardData cData;
        for (CreditCard creditCard : cards) {
            cData = new CreditCardData(creditCard.getId(), creditCard.getName(), creditCard.getAnnualFee(),
                    creditCard.getMaxSalary(), creditCard.getPeriodDate(), creditCard.getPayDate(),
                    creditCard.getRegistrationDate(), calculateCurrentSalary(creditCard.getId()));
            data.add(cData);
        }
        return data;
    }

    public static PieChartItem transformPieChartItem(String name, Double total) {
        return new PieChartItem(name, total);
    }

    public static List<CreditCardPurchaseData> transformCreditCardPurchases(List<CreditCardPurchase> purchases, DataStoreService currentDatastoreService) {
        List<CreditCardPurchaseData> data = new ArrayList<>();
        for (CreditCardPurchase purchase : purchases) {
            //TODO: get number of payments and calculate payments left
            //TODO: ( months - payments.count )
            data.add(new CreditCardPurchaseData(purchase.getConcept(), purchase.getDescription(), purchase.getMonths(), 0, purchase.getPurchaseDate(), purchase.getTotal()));
        }
        return data;
    }

    private static Double calculateCurrentSalary(String creditCardId) {
        //TODO: calculate getting all current movements within current period and a sum of all of those prices
        return 999.9;
    }
}
