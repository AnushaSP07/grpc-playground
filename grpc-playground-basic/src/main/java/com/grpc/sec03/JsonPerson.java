package com.grpc.sec03;

public class JsonPerson {
    String name;
    int age;
    String email;
    boolean employed;
    double salary;
    long bankAccountNumber;
    int balance;

    JsonPerson(String name, int age, String email, boolean employed, double salary, long bankAccountNumber, int balance) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.employed = employed;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
    }
}
