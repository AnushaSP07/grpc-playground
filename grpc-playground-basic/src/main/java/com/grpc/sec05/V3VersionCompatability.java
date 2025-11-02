package com.grpc.sec05;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.models.sec05.v3.Television;
import com.grpc.models.sec05.v3.Type;
import com.grpc.sec05.parser.V1Parser;
import com.grpc.sec05.parser.V2Parser;
import com.grpc.sec05.parser.V3Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3VersionCompatability {

    private static final Logger log = LoggerFactory.getLogger(V3VersionCompatability.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {
        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setType(Type.HD)
                .build();

        V1Parser.parse(tv.toByteArray());
        V2Parser.parse(tv.toByteArray());
        V3Parser.parse(tv.toByteArray());

    }
}
