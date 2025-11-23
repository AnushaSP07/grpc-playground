package com.grpc.service;

import com.grpc.ChatMessage;
import com.grpc.ChatServiceGrpc;
import io.grpc.stub.StreamObserver;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {


    @Override
    public StreamObserver<ChatMessage> chatStream(StreamObserver<ChatMessage> responseObserver) {
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                System.out.println("Received message from " + chatMessage.getUser() + ": " + chatMessage.getMessage());
                ChatMessage reply = ChatMessage.newBuilder()
                        .setUser("server user")
                        .setMessage("received : "+chatMessage.getMessage())
                        .build();
                responseObserver.onNext(reply);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error in chatStream: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
