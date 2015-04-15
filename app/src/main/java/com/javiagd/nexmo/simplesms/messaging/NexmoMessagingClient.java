package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Response;

/**
 * Created by javigd on 15/04/15.
 */
public class NexmoMessagingClient implements MessagingClient {

    @Override
    public Response sendMessage(String sender, String receptor, String text) {
        return null;
    }

    @Override
    public DeliveryReceipt getDeliveryReceipt() {
        return null;
    }
}
