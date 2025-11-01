package com.grpc.sec01;

import com.grpc.models.sec01.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleProtoDemo {

    private static final Logger log = LoggerFactory.getLogger(SimpleProtoDemo.class);

    public static void main(String[] args) {

        PersonOuterClass.Person.newBuilder()
                .setName("sam")
                .setAge(12)
                .build();

    }
}
