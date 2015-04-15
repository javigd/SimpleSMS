package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Response;

/**
 * Created by javigd on 15/04/15.
 */
public interface MessagingClient {

    /**
     * Send a text message from a sender to a specified receptor
     * @param sender
     * @param receptor
     * @param text
     * @return the resulting Response from the messaging Client
     */
    public Response sendMessage(String sender, String receptor, String text);

    /**
     * Get the delivery receipt of the latest sendMessage operation
     * @return
     */
    public DeliveryReceipt getDeliveryReceipt();

}
