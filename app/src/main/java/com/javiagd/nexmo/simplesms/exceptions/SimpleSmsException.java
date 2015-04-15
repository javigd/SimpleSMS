package com.javiagd.nexmo.simplesms.exceptions;

/**
 * Created by javigd on 15/04/15.
 */
public class SimpleSmsException extends Exception {

    private static final long serialVersionUID = 1L;

    private SimpleSmsError error;

    public SimpleSmsException(String message) {
        super(message);
    }

    public SimpleSmsException(SimpleSmsError error) {
        super(error.getMessage());
        this.error = error;
    }

    public SimpleSmsError getError() {
        return this.error;
    }
}
