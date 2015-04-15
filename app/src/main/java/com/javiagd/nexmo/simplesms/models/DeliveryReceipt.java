package com.javiagd.nexmo.simplesms.models;

/**
 * Created by javigd on 15/04/15.
 */
public class DeliveryReceipt {

    private String destination;
    private String networkCode;
    private String messageId;
    private String msisdn;
    private String status;
    private String errorCode;
    private String price;
    private String scts;
    private String messageTimestamp;
    private String clientRef;

    public DeliveryReceipt() {

    }

    public DeliveryReceipt(String destination, String networkCode, String messageId, String msisdn,
                           String status, String errorCode, String price, String scts,
                           String messageTimestamp, String clientRef) {
        this.destination = destination;
        this.networkCode = networkCode;
        this.messageId = messageId;
        this.msisdn = msisdn;
        this.status = status;
        this.errorCode = errorCode;
        this.price = price;
        this.scts = scts;
        this.messageTimestamp = messageTimestamp;
        this.clientRef = clientRef;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScts() {
        return scts;
    }

    public void setScts(String scts) {
        this.scts = scts;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(String messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    public String getClientRef() {
        return clientRef;
    }

    public void setClientRef(String clientRef) {
        this.clientRef = clientRef;
    }
}
