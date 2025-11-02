package com.grpc.sec02;

import com.grpc.models.sec02.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoDemo {

    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);

    public static void main(String[] args) {
        var person1 = createPerson();
        var person2 = createPerson();

        log.info("equals: {}", person1.equals(person2));
        log.info("==: {}", person1 == person2);

        // it is mutable - once assinged we cannot change

    }

    private static PersonOuterClass.Person createPerson() {
        return PersonOuterClass.Person.newBuilder()
                .setName("John")
                .setAge(23)
                .build();
    }
}
