package com.grpc.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec08OneOf {

    private static Logger log = LoggerFactory.getLogger(Lec08OneOf.class);

    public static void main(String[] args) {

        var email = Email.newBuilder()
                .setEmailAddress("anusha@gmail.com")
                .setPassword("1234")
                .build();

        var phone = Phone.newBuilder()
                .setCountryCode(91)
                .setNumber(9876543210L);

        login(Credentials.newBuilder().setEmail(email).build());
        login(Credentials.newBuilder().setPhone(phone).build());
    }

    private static void login(Credentials credentials){
        switch (credentials.getLoginMethodCase()){
            case EMAIL ->  log.info("email : {}", credentials.getEmail());
            case PHONE ->   log.info("phone : {}", credentials.getPhone());
        }
    }
}
