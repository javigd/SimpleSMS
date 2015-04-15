package com.javiagd.nexmo.simplesms.messaging;

import com.javiagd.nexmo.simplesms.models.Response;

import junit.framework.TestCase;

import org.junit.Test;

public class NexmoMessagingClientTest extends TestCase {

    private MessagingClient client;

    private final String DEFAULT_API_KEY = "0ef54924";
    private final String DEFAULT_API_SECRET = "34963288";

    public void setUp() throws Exception {
        super.setUp();
        client = NexmoMessagingClient.getInstance(DEFAULT_API_KEY, DEFAULT_API_SECRET);
    }

    public void testSimpleSendMessage() throws Exception {
        String sender = "34630978821";
        String recipient = "34630978821";
        String messageBody = "[SimpleSMS] This is a unit test.";

        Response response = client.sendMessage(sender, recipient, messageBody);

        assertNotNull(response);
    }
}