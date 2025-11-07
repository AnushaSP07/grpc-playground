package com.grpc.sec09;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import com.grpc.models.sec09.*;
import com.grpc.sec09.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balace = AccountRepository.getBalance(accountNumber);
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balace)
                .build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }
}
