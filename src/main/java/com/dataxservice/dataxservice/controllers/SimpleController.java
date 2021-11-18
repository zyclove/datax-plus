package com.dataxservice.dataxservice.controllers;

import com.dataxservice.dataxservice.cores.Worker;
import com.dataxservice.dataxservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.datax.core.Engine;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;


@RestController
public class SimpleController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
//        Object ooo = redisUtils.popData("loging-test");
//        System.out.println(ooo);
        java.util.LinkedHashMap object = (LinkedHashMap) redisUtils.lPopData("loging-test");
        System.out.println(object);
        return "Hello world";
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String hello() {


//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String tempDir = System.getProperty("tempfile.dir");
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
//
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    // https://www.jianshu.com/p/bb9189ec523e

    @RequestMapping(value = "/mysql/{jobId}", method = RequestMethod.GET)
    public String mysql(@PathVariable long jobId) {
//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
////        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "-1"};
//        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "998877"};

        String tempDir = System.getProperty("tempfile.dir");
        long startTime = System.currentTimeMillis();
//        try {
//            Engine.entry(datxArgs);   //从这里启动
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        Worker worker = new Worker();
        worker.setJsonFileName("mysql2mysql.json");
        worker.setJobId(jobId);

        Thread thread1 = new Thread(worker);

        thread1.start();

        System.out.println("---???????>>>>");

        long endTime = System.currentTimeMillis();

        float seconds = (endTime - startTime) / 1000F;

        return "Hello worldeeeee ---------------"+seconds;
    }


    @RequestMapping(value = "/oracle", method = RequestMethod.GET)
    public String oracle() {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
//        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "-1"};
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "dx-plusj-"+ UUID.randomUUID()};

//


        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    public static void main(String args[]) {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
//
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
