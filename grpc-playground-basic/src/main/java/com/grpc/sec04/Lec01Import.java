package com.grpc.sec04;

import com.example.common.BodyStyle;
import com.example.common.Car;
import com.grpc.models.common.Address;
import com.grpc.models.sec04.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Import {

    private static final Logger log = LoggerFactory.getLogger(Lec01Import.class);

    public static void main(String[] args) {
        var address = Address.newBuilder().setCity("New York").build();
        var car = Car.newBuilder().setBodyStyle(BodyStyle.COUPE).build();

        var person = Person.newBuilder().setAge(18).setCars(car).setAddress(address).build();

        log.info("Person : {}", person);

    }
}
