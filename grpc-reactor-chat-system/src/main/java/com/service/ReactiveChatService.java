package com.service;

import com.grpc.ChatMessage;
import com.grpc.ReactorChatServiceGrpc;
import reactor.core.publisher.Flux;

public class ReactiveChatService extends ReactorChatServiceGrpc.ChatServiceImplBase {
    @Override
    public Flux<ChatMessage> chatStream(Flux<ChatMessage> request) {
        return request.map(
                msg -> ChatMessage.newBuilder()
                        .setUser("server")
                        .setMessage("echo: " + msg.getMessage())
                        .build()
        );
    }
}
