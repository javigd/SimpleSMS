package com.javiagd.nexmo.simplesms.models;

/**
 * Created by javigd on 15/04/15.
 */
public class Message {

    private String sender;
    private String recipient;
    private String body;

    public Message() {

    }

    public Message(String sender, String recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
