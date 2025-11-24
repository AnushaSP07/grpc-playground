package com.server;

import com.service.ReactiveChatService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ReactiveServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(50052)
                .addService(new ReactiveChatService())
                .build();
        server.start();
        System.out.println("Reactive gRPC Server started on port 50052");
        server.awaitTermination();
    }
}
