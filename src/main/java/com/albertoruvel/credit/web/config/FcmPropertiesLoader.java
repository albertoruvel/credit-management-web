package com.albertoruvel.credit.web.config;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class FcmPropertiesLoader {

    private  Properties properties;
    private final String FCM_PROPERTIES_FILE = "/META-INF/fcm-props.properties";
    private final String API_KEY_PROPERTY = "fcm.client.key";

    public String getApiKey() throws IOException{
        if (properties == null){
            properties = new Properties();
            properties.load(getClass().getResourceAsStream(FCM_PROPERTIES_FILE));
        }
        return (String)properties.get(API_KEY_PROPERTY);
    }
}
