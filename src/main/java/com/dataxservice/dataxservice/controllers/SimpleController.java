package com.dataxservice.dataxservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.datax.core.Engine;



@RestController
public class SimpleController {

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String hello() {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
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

    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public String mysql() {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
//        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "-1"};
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "998877"};

//
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "Hello world";
    }


    @RequestMapping(value = "/oracle", method = RequestMethod.GET)
    public String oracle() {
        System.out.println("--->>");
        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
//        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "-1"};
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "998877"};

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
