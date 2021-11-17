package com.dataxservice.dataxservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DataxserviceApplication {

//    static String DATAX_PATH = "";

    public static void main(String[] args) {
//        DATAX_PATH = System.getenv("DATAX_PATH");
        SpringApplication.run(DataxserviceApplication.class, args);
    }

}
