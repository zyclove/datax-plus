package com.dataxservice.dataxservice.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dataxservice.dataxservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;


//https://blog.csdn.net/qq_31145141/article/details/103864600

@Component
@Order(value = 1)
public class AutoLogConsummer implements CommandLineRunner {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Async
    public void run(String... args) throws Exception {
        if (System.getProperty("never.process.log") == null || !System.getProperty("never.process.log").toString().equals("1")) {
            while (true) {
                java.util.LinkedHashMap object = (LinkedHashMap) redisUtils.lPopData("loging-test");
                if (null == object) {
                    continue;
                } else {
                    System.out.println(">>> + " + object.get("message"));
                }
            }
        }
    }
}
