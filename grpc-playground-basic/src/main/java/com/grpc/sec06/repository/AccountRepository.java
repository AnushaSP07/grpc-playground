package com.grpc.sec06.repository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AccountRepository {

    private static final Map<Integer, Integer> db = IntStream.rangeClosed(1, 10)
            .boxed()
            .collect(Collectors.toConcurrentMap(Function.identity(), v->100));

    public static Integer getBalance(int accountNumber) {
        return db.get(accountNumber);
    }
}
