package com.grpc.server;

import com.grpc.service.ChatServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ChatServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new ChatServiceImpl())
                .build()
                .start();

        System.out.println("Server started, listening on " + 50051);
        server.awaitTermination();
    }
}
