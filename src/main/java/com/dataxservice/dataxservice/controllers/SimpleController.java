package com.dataxservice.dataxservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.datax.core.Engine;

@RestController
public class SimpleController {

    @RequestMapping(value = "/t", method = RequestMethod.GET)
    public String hello() {
        return "Hello world";
    }

    public static void main(String args[]) {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
//        String[] datxArgs = {"-job", "E:\\Document\\intelliidea\\dataxText\\src\\main\\resources\\stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
//
    }
}
