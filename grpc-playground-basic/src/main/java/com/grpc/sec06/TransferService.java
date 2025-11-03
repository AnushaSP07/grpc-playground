package com.grpc.sec06;

import com.grpc.models.sec06.TransferRequest;
import com.grpc.models.sec06.TransferResponse;
import com.grpc.models.sec06.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {
    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return super.transfer(responseObserver);
    }
}
