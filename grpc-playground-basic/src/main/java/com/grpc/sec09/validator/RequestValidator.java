package com.grpc.sec09.validator;

import io.grpc.Status;

import java.util.Optional;

public class RequestValidator {

    public static Optional<Status> validateAccount(int accountNumber){
        if(accountNumber > 0 && accountNumber < 11){
            return Optional.empty();
        }
        return Optional.of(Status.INVALID_ARGUMENT.withDescription("account number should be between 1 and 10"));
    }

    public static Optional<Status> isAmountDivisibleBy10(int amount){
        if(amount > 0 && amount % 10 == 0){
            return Optional.empty();
        }
        return Optional.of(Status.INVALID_ARGUMENT.withDescription("requestd amount should be 10 multiples"));
    }

    public static Optional<Status> hasSufficientBalance(int amount, int balance){
        if(amount <= 0){
            return Optional.empty();
        }
        return Optional.of(Status.INVALID_ARGUMENT.withDescription("insufficient balance"));
    }
}
