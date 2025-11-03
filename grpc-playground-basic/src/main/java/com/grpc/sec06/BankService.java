package com.grpc.sec06;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import com.grpc.models.sec06.*;
import com.grpc.sec05.V1VersionCompatability;
import com.grpc.sec06.repository.AccountRepository;
import com.grpc.sec06.requestHandlers.DepositRequestHandler;
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

    @Override
    public void getAllAccounts(Empty request, StreamObserver<AllAccountsResponse> responseObserver) {
        var accounts = AccountRepository.getAllAccounts()
                .entrySet()
                .stream()
                .map(e -> AccountBalance.newBuilder().setAccountNumber(e.getKey()).setBalance(e.getValue()).build())
                .toList();
        var response = AllAccountsResponse.newBuilder().addAllAccounts(accounts).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var requestedAmount = request.getAmount();
        var balance = AccountRepository.getBalance(accountNumber);

        if(requestedAmount > balance) {
            responseObserver.onError(new Throwable("Not enough balance. Your balance: " + balance));
            return;
        }

        for(int i = 0; i < (requestedAmount/10); i++){
            var money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("Dispensed $10");
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountBalance> responseObserver) {
        return new DepositRequestHandler(responseObserver);
    }
}
