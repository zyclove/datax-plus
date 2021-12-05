package com.datax.plus.model;

public class DataJob {
    private int dataJobId;
    private String dataJobName;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDataJobId() {
        return dataJobId;
    }

    public void setDataJobId(int dataJobId) {
        this.dataJobId = dataJobId;
    }

    public String getDataJobName() {
        return dataJobName;
    }

    public void setDataJobName(String dataJobName) {
        this.dataJobName = dataJobName;
    }
}
