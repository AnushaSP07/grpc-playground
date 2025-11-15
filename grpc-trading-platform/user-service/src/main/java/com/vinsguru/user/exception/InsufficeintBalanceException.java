package com.vinsguru.user.exception;

public class InsufficeintBalanceException extends RuntimeException{

    private static final String MESSAGE = "User [id=%d] has Insufficient balance";

    public InsufficeintBalanceException(Integer userId) {
        super(MESSAGE.formatted(userId));
    }
}
