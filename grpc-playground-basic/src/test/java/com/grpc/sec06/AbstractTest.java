package com.grpc.sec06;

import com.grpc.common.GrpcServer;

import com.grpc.common.AbstractChannelTest;
import com.grpc.models.sec06.BankServiceGrpc;
import com.grpc.models.sec06.TransferServiceGrpc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public abstract class AbstractTest extends AbstractChannelTest {

    private final GrpcServer grpcServer = GrpcServer.create(new BankService(), new TransferService());
    protected BankServiceGrpc.BankServiceStub bankStub;
    protected BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;
    protected TransferServiceGrpc.TransferServiceStub transferStub;

    @BeforeAll
    public void setup() throws IOException {
        this.grpcServer.start();
        this.bankStub = BankServiceGrpc.newStub(channel);
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
        this.transferStub = TransferServiceGrpc.newStub(channel);
    }

    @AfterAll
    public void stop(){
        this.grpcServer.stop();
    }

}