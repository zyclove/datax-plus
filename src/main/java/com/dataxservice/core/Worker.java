package com.dataxservice.core;

import com.alibaba.datax.core.Engine;


public class Worker implements Runnable {

    private String jsonFileName = "";

    private long jobId = 0;

    public String getJsonFileName() {
        return jsonFileName;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    @Override
    public void run() {
//        String tempDir = System.getProperty("tempfile.dir");
//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String[] datxArgs = {"-job", System.getProperty("tempfile.dir") + System.getProperty("file.separator") + this.jsonFileName, "-mode", "standalone", "-jobid", String.valueOf(this.jobId)};
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
