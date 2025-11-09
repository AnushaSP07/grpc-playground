package com.grpc.sec10;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpc.models.sec10.WithdrawRequest;
import com.grpc.models.sec10.AccountBalance;
import com.grpc.models.sec10.BalanceCheckRequest;
import com.grpc.models.sec10.BankServiceGrpc;
import com.grpc.models.sec10.Money;
import com.grpc.sec10.repository.AccountRepository;
import com.grpc.sec10.validator.RequestValidator;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver){
        RequestValidator.validateAccount(request.getAccountNumber())
                .ifPresentOrElse(
                        responseObserver::onError,
                        () -> setAccountBalance(request, responseObserver)
                );
    }

    public void setAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balace = AccountRepository.getBalance(accountNumber);
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balace)
                .build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }

    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
       RequestValidator.validateAccount(request.getAccountNumber())
               .or(() -> RequestValidator.isAmountDivisibleBy10(request.getAmount()))
               .or(() -> RequestValidator.hasSufficientBalance(request.getAmount(), AccountRepository
                       .getBalance(request.getAccountNumber())))
               .ifPresentOrElse(
                          responseObserver::onError,
                          () -> sendMoney(request, responseObserver)
               );
    }

    private void sendMoney(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var amount = request.getAmount();
        var balance = AccountRepository.getBalance(accountNumber);
        if (amount > balance) {
            responseObserver.onError(
                    Status.FAILED_PRECONDITION
                            .withDescription("Not enough money. Your balance: " + balance)
                            .asRuntimeException()
            );
            return;
        }
        for (int i = 0; i < amount / 10; i++) {
            var money = Money.newBuilder()
                    .setAmount(10)
                    .build();
            responseObserver.onNext(money);
            log.info("money sent {} ", money);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();
    }

}
