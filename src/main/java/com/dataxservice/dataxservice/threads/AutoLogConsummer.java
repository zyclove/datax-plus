package com.dataxservice.dataxservice.threads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dataxservice.dataxservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;

@Component
public class AutoLogConsummer {

    @Autowired
    private RedisUtils redisUtils;

    @Async
    @PostConstruct
    public void consumeLogs(){
        while (true){
            java.util.LinkedHashMap object = (LinkedHashMap) redisUtils.popData("loging-test");
            if (null == object) {
                continue;
            } else {
                System.out.println(">>> + "+object.get("message"));
            }
        }
    }
}
