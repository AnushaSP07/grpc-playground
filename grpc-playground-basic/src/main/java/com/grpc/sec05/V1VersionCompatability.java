package com.grpc.sec05;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.models.sec05.v1.Television;
import com.grpc.sec04.Lec01Import;
import com.grpc.sec05.parser.V1Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V1VersionCompatability {

    private static final Logger log = LoggerFactory.getLogger(V1VersionCompatability.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {
        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setYear(2010)
                .build();

        V1Parser.parse(tv.toByteArray());

    }
}
