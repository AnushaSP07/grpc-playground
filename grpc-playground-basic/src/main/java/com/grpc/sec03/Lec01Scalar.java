package com.grpc.sec03;

import com.grpc.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Scalar {
    private static final Logger log = LoggerFactory.getLogger(Lec01Scalar.class);

    public static void main(String[] args) {
        var person = Person.newBuilder()
                .setLastName("sam")
                .setAge(12)
                .setEmail("sam@gmail.com")
                .setSalary(14000)
                .setBanlAccountNumber(112233)
                .build();

        log.info("person: {}", person);
    }
}
