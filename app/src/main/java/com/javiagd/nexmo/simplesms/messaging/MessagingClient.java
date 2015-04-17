package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Message;
import com.javiagd.nexmo.simplesms.models.Response;

/**
 * Created by javigd on 15/04/15.
 */
public interface MessagingClient {

    /**
     * Send a text message from a sender to a specified receptor
     * @param message
     * @return the resulting Response from the messaging Client
     */
    public Response sendMessage(Message message) throws SimpleSmsException;

    /**
     * Get the delivery receipt of the latest sendMessage operation
     * @param messageId
     * @return
     */
    public DeliveryReceipt getDeliveryReceipt(String messageId) throws SimpleSmsException;

}
