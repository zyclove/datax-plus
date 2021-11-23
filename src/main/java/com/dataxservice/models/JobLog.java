package com.dataxservice.models;

public class JobLog {
    private int logRowId;
    private Job job;
    private int logId;
    private String logBody = "";

    public int getLogRowId() {
        return logRowId;
    }

    public void setLogRowId(int logRowId) {
        this.logRowId = logRowId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogBody() {
        return logBody;
    }

    public void setLogBody(String logBody) {
        this.logBody = logBody;
    }
}


