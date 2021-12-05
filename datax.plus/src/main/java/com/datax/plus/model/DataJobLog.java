package com.datax.plus.model;

public class DataJobLog {
    private int logRowId;
    private DataJob dataJob;
    private int logId;
    private String logBody = "";

    public int getLogRowId() {
        return logRowId;
    }

    public void setLogRowId(int logRowId) {
        this.logRowId = logRowId;
    }

    public DataJob getDataJob() {
        return dataJob;
    }

    public void setDataJob(DataJob dataJob) {
        this.dataJob = dataJob;
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


