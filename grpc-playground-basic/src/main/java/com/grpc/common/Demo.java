package com.grpc.common;

import com.grpc.sec06.BankService;
import io.grpc.BindableService;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        GrpcServer.create(new BankService())
                .start()
                .awit();
    }
}
