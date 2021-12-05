package com.datax.plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DataXServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataXServiceApplication.class, args);
    }

}
