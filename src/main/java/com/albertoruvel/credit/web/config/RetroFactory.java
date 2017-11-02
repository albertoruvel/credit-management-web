package com.albertoruvel.credit.web.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFactory {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://fcm.googleapis.com/";

    public static <R> R create(Class type){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return (R)retrofit.create(type);
    }
}
