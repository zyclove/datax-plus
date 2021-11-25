package com.dataxservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.dataxservice.core.Worker;
import com.dataxservice.models.DataJob;
import com.dataxservice.service.DataJobService;
import com.dataxservice.service.DataJobLogService;
import com.dataxservice.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.alibaba.datax.common.util.Configuration;


import com.alibaba.datax.core.Engine;

import javax.annotation.Resource;
import java.util.LinkedHashMap;


@RestController
public class SimpleController {

    Logger LOG = LoggerFactory.getLogger(SimpleController.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DataJobLogService jobLogsService;

    @Autowired
    private DataJobService dataJobService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        java.util.LinkedHashMap object = (LinkedHashMap) redisUtil.lPopData("loging-test");

        return String.valueOf(jobLogsService.simpleCount());
    }

    @RequestMapping(value = "/testReadLog/{logId}", method = RequestMethod.GET)
    public String testReadLog(@PathVariable String logId) {
        String loginfo;
        do {
            loginfo = redisUtil.lPopData("[" + logId + "]").toString();
            System.out.println(loginfo);
        } while (loginfo == null);
        System.out.println("read");
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
        String tempDir = System.getProperty("tempfile.dir");
        long startTime = System.currentTimeMillis();

        Worker worker = new Worker();
        worker.setJsonFileName("mysql2mysql.json");
        worker.setJobId(jobId);

        Thread thread1 = new Thread(worker);

        thread1.start();

        System.out.println("---???????>>>>");

        long endTime = System.currentTimeMillis();

        float seconds = (endTime - startTime) / 1000F;

        return "Hello worldeeeee ---------------" + seconds;
    }


    @RequestMapping(value = "/jobReport", method = RequestMethod.POST)
    public String jobReport(@RequestBody Configuration jobConfiguration) {
        LOG.info("HOOK REQUEST..........................");
        JSONObject jsonObject = JSONObject.parseObject(jobConfiguration.toJSON());
        LOG.info(jsonObject.getString("core"));
        LOG.info("HOOK REQUEST--------------------------");
        return "Hello worldeeeee ---------------" ;
    }


    @RequestMapping(value = "/jobSubmit", method = RequestMethod.GET)
    public String jobSubmit() {

        String tempDir = System.getProperty("tempfile.dir");
        long startTime = System.currentTimeMillis();

        //Generate Job record
        //Job job =  new Job();
        DataJob dataJob = new DataJob();
        dataJob.setDataJobName("");
        dataJobService.addDataJob(dataJob);
        // jobService

        Worker worker = new Worker();
        worker.setJsonFileName("mysql2mysql.json");
        worker.setJobId(dataJob.getDataJobId());

        Thread thread1 = new Thread(worker);
        thread1.start();

        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        return "Job submitted : " + seconds;
    }

    public static void main(String args[]) {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
