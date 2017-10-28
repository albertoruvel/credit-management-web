package com.albertoruvel.credit.web.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.UUID;

@Entity
public class CreditCardPurchase {

    @Id
    private String id;
    private String creditCardId;
    private String concept;
    private Double total;
    private Integer months;
    private boolean containingInterests;
    private Double interest;
    private String purchaseDate;
    private Integer paymentsLeft;

    public CreditCardPurchase() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public boolean isContainingInterests() {
        return containingInterests;
    }

    public void setContainingInterests(boolean containingInterests) {
        this.containingInterests = containingInterests;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getPaymentsLeft() {
        return paymentsLeft;
    }

    public void setPaymentsLeft(Integer paymentsLeft) {
        this.paymentsLeft = paymentsLeft;
    }
}
