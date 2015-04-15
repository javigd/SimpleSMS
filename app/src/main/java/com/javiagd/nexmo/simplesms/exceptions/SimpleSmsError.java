package com.javiagd.nexmo.simplesms.exceptions;

/**
 * Created by javigd on 15/04/15.
 */
public enum SimpleSmsError {

    /* Messaging errors referenced by custom code (management) and brief description */
    CLIENT_RES(1, "Could not retrieve a valid result from the messaging operation"),
    CLIENT_CONFIG(2, "Failed to configure the Messaging Client"),
    ERR_ENCODE(3, "Failed to encode the text message");

    private final int code;
    private final String message;

    SimpleSmsError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
