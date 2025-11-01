package com.grpc.sec03;

import com.grpc.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lec02Serialization {

    private static final Logger log = LoggerFactory.getLogger(Lec02Serialization.class);
    private static final Path PATH = Path.of("person.out");

    public static void main(String[] args) throws IOException {
        var person = Person.newBuilder()
                .setLastName("sam")
                .setAge(12)
                .setEmail("sam@gmail.com")
                .setSalary(14000)
                .setBanlAccountNumber(112233)
                .build();

        serializePerson(person);
        log.info("deserialize: {}", person);
        log.info("equals ,{}", person.equals(deserializePerson()));
    }

    // whenever we use input and output stream we have to close it
    public static void serializePerson(Person person) throws IOException {
       // person.writeTo(Files.newOutputStream(PATH)); // without try resource

        try(var stream = Files.newOutputStream(PATH)) {
            person.writeTo(stream);
        }
    }

    public static Person deserializePerson() throws IOException {
      // return Person.parseFrom(Files.newInputStream(PATH));
        try(var stream = Files.newInputStream(PATH)) {
            return Person.parseFrom(stream);
        }
    }

}
