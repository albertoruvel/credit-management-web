package com.albertoruvel.credit.web.service;


import com.albertoruvel.credit.web.data.fcm.FcmMessage;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FcmMessagingService {

    /**
     * Send a single message to FCM service
     * @param message
     * @param apiKey
     */
    @POST("fcm/send")
    public void send(@Body FcmMessage message, @Header("Authorization") String apiKey);

}
