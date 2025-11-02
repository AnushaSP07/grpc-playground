package com.grpc.sec03;

import com.example.sec03.BodyStyle;
import com.example.sec03.Car;
import com.example.sec03.Dealer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06Map {

    private static Logger log = LoggerFactory.getLogger(Lec06Map.class);

    public static void main(String[] args) {
        var car1 = Car.newBuilder()
                .setMake("Toyota")
                .setModel("Camry")
                .setYear(2000)
                .setBodyStyle(BodyStyle.COUPE)
                .build();

        var car2 = Car.newBuilder()
                .setMake("honda")
                .setModel("civic")
                .setYear(2002)
                .setBodyStyle(BodyStyle.SUV)
                .build();

        var dealer = Dealer.newBuilder()
                .putInventory(car1.getYear(), car1)
                .putInventory(car2.getYear(), car2)
                .build();

        log.info("Dealer : {}", dealer);

        log.info("2002 : {}", dealer.containsInventory(2002));
        log.info("2000 : {}", dealer.containsInventory(2000));
        log.info("2002 : {}", dealer.getInventoryOrThrow(2002).getBodyStyle());


    }
}
