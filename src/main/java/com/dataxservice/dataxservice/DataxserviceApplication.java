package com.dataxservice.dataxservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DataxserviceApplication {
 //   public class DataxserviceApplication implements CommandLineRunner {

//    static String DATAX_PATH = "";

    public static void main(String[] args) {
//        DATAX_PATH = System.getenv("DATAX_PATH");
        SpringApplication.run(DataxserviceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        while(true){
//            System.out.println(">>>>>>>>>>>>>>>>>服务启动执行");
//            Thread.sleep(4000);
//        }
//    }

}
