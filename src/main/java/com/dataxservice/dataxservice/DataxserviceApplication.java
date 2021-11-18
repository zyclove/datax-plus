package com.dataxservice.dataxservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DataxserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataxserviceApplication.class, args);
    }

}
