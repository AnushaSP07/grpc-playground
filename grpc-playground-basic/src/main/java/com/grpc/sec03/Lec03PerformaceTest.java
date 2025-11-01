package com.grpc.sec03;

import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec03PerformaceTest {

    private static Logger log = LoggerFactory.getLogger(Lec03PerformaceTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        var protoPerson = Person.newBuilder()
                .setLastName("sam")
                .setAge(12)
                .setEmail("sam@gmail.com")
                .setEmployed(true)
                .setSalary(14000)
                .setBanlAccountNumber(112233)
                .setBalance(-1000)
                .build();

        var jsonPerson = new JsonPerson("sam",12,"same@gmail.com",true, 1000.234, 1234567890,-10000);

        for(int i = 0; i< 5; i++){
            runTest("json", ()->jsonPerson(jsonPerson));
            runTest("proto", ()->proto(protoPerson));
        }
    }

    private static void proto(Person person) {
        try{
            var bytes = person.toByteArray();
            Person.parseFrom(bytes);
        }catch (InvalidProtocolBufferException e){
            throw new RuntimeException(e);
        }
    }

    private static void jsonPerson(JsonPerson jsonPerson) {
        try{
            var bytes = mapper.writeValueAsBytes(jsonPerson);
            mapper.readValue(bytes, JsonPerson.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void runTest(String testName, Runnable runnable){
        var startTime = System.currentTimeMillis();
        for(int i = 0; i < 1_000_000; i++){
            runnable.run();
        }
        var endTime = System.currentTimeMillis();
        log.info("{} runs in {} ms", testName, endTime - startTime);
    }
}
