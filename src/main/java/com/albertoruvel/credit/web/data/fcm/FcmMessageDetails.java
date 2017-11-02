package com.albertoruvel.credit.web.data.fcm;

public class FcmMessageDetails {
    private String title;
    private String detail;

    public FcmMessageDetails(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
