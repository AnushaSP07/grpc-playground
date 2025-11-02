package com.grpc.sec04;

import com.example.sec04.Sample;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.time.Instant;

public class Lec02WellKnownTypes {

    private static final Logger log = LoggerFactory.getLogger(Lec02WellKnownTypes.class);

    public static void main(String[] args) {
        // well-known types: https://developers.google.com/protocol-buffers/docs/reference/google.protobuf

        var sample = Sample.newBuilder()
                .setAge(Int32Value.of(12))
                .setCreatedAt(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build()).build();

        log.info("Sample: {}", Instant.ofEpochSecond(sample.getCreatedAt().getSeconds()));
    }
}
