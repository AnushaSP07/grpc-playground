package com.grpc.sec06;

import com.grpc.models.sec06.AccountBalance;
import com.grpc.models.sec06.BalanceCheckRequest;
import com.grpc.models.sec06.BankServiceGrpc;
import com.grpc.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {
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
