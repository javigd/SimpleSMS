package com.javiagd.nexmo.simplesms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by javigd on 15/04/15.
 */
public class Response {

    @JsonProperty("message-count")
    private String messageCount;
    private List<ResponseMessage> messages;

    public Response() {

    }

    public Response(String messageCount, List<ResponseMessage> messages) {
        this.messageCount = messageCount;
        this.messages = messages;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ResponseMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Response from Nexmo:\n" +
                "Message delivered in '" + messageCount + " parts.\n" +
                "\nInformation about your delivered message(s):\n" + messages.toString();
    }
}
