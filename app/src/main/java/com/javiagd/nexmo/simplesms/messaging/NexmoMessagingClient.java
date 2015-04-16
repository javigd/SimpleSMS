package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsError;
import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Message;
import com.javiagd.nexmo.simplesms.models.Response;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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
    public Response sendMessage(Message message) throws SimpleSmsException {
        Response response = null;
        URI requestUrl = buildRequestUrl(message);
        // Call Nexmo SMS API
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            response = restTemplate.getForObject(requestUrl, Response.class);
        } catch (RestClientException e) {
            throw new SimpleSmsException(SimpleSmsError.CLIENT_RES);
        }
        return response;
    }

    private URI buildRequestUrl(Message message) throws SimpleSmsException {
        UriTemplate template = new UriTemplate(NEXMO_SMS_API_URL +
                "?api_key={api_key}" +
                "&api_secret={api_secret}" +
                "&from={from}" +
                "&to={to}" +
                "&text={text}");

        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("api_secret", apiSecret);
        params.put("from", message.getSender());
        params.put("to", message.getRecipient());
        params.put("text", message.getBody());

        return template.expand(params);
    }

    @Override
    public DeliveryReceipt getDeliveryReceipt() {
        return null;
    }
}
