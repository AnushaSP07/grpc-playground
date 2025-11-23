package com.grpc.client;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.grpc.Chat;
import com.grpc.ChatMessage;
import com.grpc.ChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceStub asyncStub = ChatServiceGrpc.newStub(channel);

        StreamObserver<ChatMessage> responseObserver = new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                System.out.println("Received from server: " + chatMessage.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error in chatStream: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Chat ended by server.");
            }
        };

        //sends messages to server
        StreamObserver<ChatMessage> requestObserver = asyncStub.chatStream(responseObserver);
        responseObserver.onNext(
                ChatMessage.newBuilder()
                        .setUser("client user")
                        .setMessage("Hello from client!")
                        .build()
        );
        Thread.sleep(2000);

            requestObserver.onNext(
                    ChatMessage.newBuilder()
                            .setUser("client user")
                            .setMessage("Another message, How are you, server?")
                            .build()
            );
            requestObserver.onCompleted();
            Thread.sleep(5000);
            channel.shutdown();
    }
}
