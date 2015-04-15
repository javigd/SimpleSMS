package com.javiagd.nexmo.simplesms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by javigd on 15/04/15.
 */
public class Response {

    @JsonProperty("message-count")
    private String messageCount;
    private List messages;
    private String status;
    @JsonProperty("message-id")
    private String messageId;
    @JsonProperty("to")
    private String destination;
    @JsonProperty("client-ref")
    private String clientReference;
    @JsonProperty("remaining-balance")
    private String remainingBalance;
    @JsonProperty("message-price")
    private String messagePrice;
    private String network;
    @JsonProperty("error-text")
    private String errorText;

    public Response() {

    }

    public Response(String messageCount, List<String> messages, String status, String messageId,
                    String destination, String clientReference, String remainingBalance,
                    String messagePrice, String network, String errorText) {
        this.messageCount = messageCount;
        this.messages = messages;
        this.status = status;
        this.messageId = messageId;
        this.destination = destination;
        this.clientReference = clientReference;
        this.remainingBalance = remainingBalance;
        this.messagePrice = messagePrice;
        this.network = network;
        this.errorText = errorText;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public List getMessages() {
        return messages;
    }

    public void setMessages(List messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getClientReference() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public String getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(String remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public String getMessagePrice() {
        return messagePrice;
    }

    public void setMessagePrice(String messagePrice) {
        this.messagePrice = messagePrice;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public String toString() {
        return "Response{" +
                "messageCount='" + messageCount + '\'' +
                ", messages=" + messages +
                '}';
    }
}
