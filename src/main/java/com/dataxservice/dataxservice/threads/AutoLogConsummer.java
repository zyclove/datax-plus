package com.dataxservice.dataxservice.threads;

import com.dataxservice.dataxservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AutoLogConsummer {

    @Autowired
    private RedisUtils redisUtils;
    @Async
    @PostConstruct
    public void consumeLogs(){
        while (true){
            Object object = redisUtils.popData("loging-test");
            System.out.println(object.toString());
        }
    }
}
