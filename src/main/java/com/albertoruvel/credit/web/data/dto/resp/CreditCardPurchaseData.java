package com.albertoruvel.credit.web.data.dto.resp;

public class CreditCardPurchaseData {
    private String name;
    private String description;
    private Integer months;
    private Integer paymentsLeft;
    private String purchaseDate;
    private Double total;

    public CreditCardPurchaseData() {
    }

    public CreditCardPurchaseData(String name, String description, Integer months, Integer paymentsLeft, String purchaseDate, Double total) {
        this.name = name;
        this.description = description;
        this.months = months;
        this.paymentsLeft = paymentsLeft;
        this.purchaseDate = purchaseDate;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getPaymentsLeft() {
        return paymentsLeft;
    }

    public void setPaymentsLeft(Integer paymentsLeft) {
        this.paymentsLeft = paymentsLeft;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
