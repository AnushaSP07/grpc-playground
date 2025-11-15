package com.vinsguru.user.exception;

public class UnknownTickerException extends RuntimeException{

    private static final String MESSAGE = "Ticker is not found";

    public UnknownTickerException(String ticker) {
        super(MESSAGE + ": " + ticker);
    }
}
