package com.albertoruvel.credit.web.data.fcm;

public class FcmMessage {
    private FcmMessageDetails data;
    private String to;

    public FcmMessage(FcmMessageDetails data, String to) {
        this.data = data;
        this.to = to;
    }

    public FcmMessageDetails getData() {
        return data;
    }

    public void setData(FcmMessageDetails data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
