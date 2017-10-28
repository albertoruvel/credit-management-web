package com.albertoruvel.credit.web.data.dto.resp;

public class CreditCardData {
    private String id;
    private String name;
    private Double annualFee;
    private Double maxSalary;
    private String periodDate;
    private String payDate;
    private String registrationDate;
    private Double currentSalary;

    public CreditCardData(String id, String name, Double annualFee, Double maxSalary, String periodDate, String payDate, String registrationDate, Double currentSalary) {
        this.id = id;
        this.name = name;
        this.annualFee = annualFee;
        this.maxSalary = maxSalary;
        this.periodDate = periodDate;
        this.payDate = payDate;
        this.registrationDate = registrationDate;
        this.currentSalary = currentSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(Double annualFee) {
        this.annualFee = annualFee;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Double currentSalary) {
        this.currentSalary = currentSalary;
    }
}
