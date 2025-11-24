package com.client;

import com.grpc.ChatMessage;
import com.grpc.ReactorChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import reactor.core.publisher.Flux;

import java.io.Flushable;

public class ReactiveClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        ReactorChatServiceGrpc.ReactorChatServiceStub stub = ReactorChatServiceGrpc.newReactorStub(channel);

        Flux<ChatMessage> requestFlux = Flux.just(
                ChatMessage.newBuilder().setUser("client user ").setMessage("Hello from reactive client!").build(),
                ChatMessage.newBuilder().setUser("client user ").setMessage("How are you, reactive server?").build()
        );

        //connect to server and receive responses
        stub.chatStream(requestFlux)
                .doOnNext(msg -> System.out.println("server response: " + msg.getMessage()))
                .blockLast(); // wait for the stream to complete
        channel.shutdown();
    }
}
