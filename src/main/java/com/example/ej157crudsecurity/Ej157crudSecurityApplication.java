package com.example.ej157crudsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Ej157crudSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ej157crudSecurityApplication.class, args);
    }

}
