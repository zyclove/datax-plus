package com.dataxservice.dataxservice.cores;

import com.alibaba.datax.core.Engine;

public class Worker implements Runnable {
    
    private String jsonFileName = "";

    

    @Override
    public void run() {
//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\mysql2mysql.json", "-mode", "standalone", "-jobid", "998877"};
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
