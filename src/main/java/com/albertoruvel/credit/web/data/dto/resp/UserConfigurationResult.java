package com.albertoruvel.credit.web.data.dto.resp;

public class UserConfigurationResult {
    private String name;
    private String email;
    private Long monthlyIncome;
    private boolean notificationEnabled;

    public UserConfigurationResult(String name, String email, Long monthlyIncome, boolean notificationEnabled) {
        this.name = name;
        this.email = email;
        this.monthlyIncome = monthlyIncome;
        this.notificationEnabled = notificationEnabled;
    }

    public UserConfigurationResult() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }
}
