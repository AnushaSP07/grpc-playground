package com.grpc.common;

import com.google.common.util.concurrent.TimeLimiter;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractChannelTest {

    private ManagedChannel channel;

    @BeforeAll
    public void setUpChannel(){
        ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
    }

    @AfterAll
    public void shutdownChannel() throws InterruptedException {
        this.channel.shutdownNow()
                .awaitTermination(5, TimeUnit.SECONDS);
    }
}
