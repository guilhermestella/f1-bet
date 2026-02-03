package com.gs.f1bet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class F1BetApplication {
    public static void main(String[] args) {
        SpringApplication.run(F1BetApplication.class, args);
    }
}