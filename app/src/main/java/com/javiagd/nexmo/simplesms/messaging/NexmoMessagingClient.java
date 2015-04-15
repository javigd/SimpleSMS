package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsError;
import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Response;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by javigd on 15/04/15.
 */
public class NexmoMessagingClient implements MessagingClient {

    private final static String NEXMO_SMS_API_URL = "https://rest.nexmo.com/sms/json";

    private static NexmoMessagingClient client;

    private String apiKey;
    private String apiSecret;

    private NexmoMessagingClient(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public static synchronized NexmoMessagingClient getInstance(String apiKey, String apiSecret) {
        if(client == null) {
            client = new NexmoMessagingClient(apiKey, apiSecret);
        }
        return client;
    }

    @Override
    public Response sendMessage(String sender, String receptor, String text) throws SimpleSmsException {
        Response response = null;
        // Call Nexmo SMS API
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            response = restTemplate.getForObject(NEXMO_SMS_API_URL, Response.class);
        } catch (RestClientException e) {
            throw new SimpleSmsException(SimpleSmsError.CLIENT_RES);
        }
        return response;
    }

    @Override
    public DeliveryReceipt getDeliveryReceipt() {
        return null;
    }
}
