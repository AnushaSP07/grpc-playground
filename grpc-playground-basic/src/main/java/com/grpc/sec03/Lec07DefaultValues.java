package com.grpc.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec07DefaultValues {

    private static Logger log = LoggerFactory.getLogger(Lec07DefaultValues.class);

    public static void main(String[] args) {

        //proto will never print null, it will print default values
        // if the value is not set, it will print empty or default values

        // default values
        // int - 0
        // string - ""
        // boolean - false
        // enum - first value
        // message - null

        var school = com.grpc.models.sec03.School.newBuilder()
                .setId(1)
                .build();

        log.info("School : {}", school);
        log.info("id : {}", school.getId());
        log.info("name : {}", school.getName());
        log.info("address : {}", school.hasAddress());
    }
}
