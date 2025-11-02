package com.grpc.sec05;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.models.sec05.v2.Television;
import com.grpc.models.sec05.v2.Type;
import com.grpc.sec05.parser.V1Parser;
import com.grpc.sec05.parser.V2Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V2VersionCompatability {

    private static final Logger log = LoggerFactory.getLogger(V2VersionCompatability.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {
        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setModel(2010)
                .setType(Type.HD)
                .build();

        V1Parser.parse(tv.toByteArray());
        V2Parser.parse(tv.toByteArray());

    }
}
