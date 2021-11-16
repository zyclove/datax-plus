package com.dataxservice.dataxservice.cores;

import com.alibaba.datax.core.Engine;

import java.io.File;

public class Worker implements Runnable {

    private String jsonFileName = "";

    private String tempFileDir = "";

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    @Override
    public void run() {
//        String tempDir = System.getProperty("tempfile.dir");
//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String[] datxArgs = {"-job", System.getProperty("tempfile.dir") + System.getProperty("file.separator") + this.jsonFileName, "-mode", "standalone", "-jobid", "998877"};
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
