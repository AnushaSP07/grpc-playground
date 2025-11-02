package com.grpc.sec03;

import com.grpc.models.sec03.Address;
import com.grpc.models.sec03.School;
import com.grpc.models.sec03.Student;

import java.util.logging.Logger;

public class Lec04Composition {

    private static final Logger log = Logger.getLogger(Lec04Composition.class.getName());

    public static void main(String[] args) {
        var address = Address.newBuilder()
                .setCity("New York")
                .setState("NY")
                .setStreet("123 Main St")
                .build();


        var student = Student.newBuilder()
                .setName("John Doe")
                .setAddress(address)
                .build();

        var school = School.newBuilder()
                .setName("ABC High School")
                .setAddress(address.toBuilder().setCity("New York").build())
                .build();

        log.info("school = " + school);
        log.info("student = " + student);
    }
}
