package com.datax.plus.model;

public class DataJob {
    private int dataJobId;
    private String dataJobName;
    private int status;
    private DataSource source;
    private DataSource target;
    private String sqlBody;
    private int currentStep;


    public DataSource getSource() {
        return source;
    }

    public void setSource(DataSource source) {
        this.source = source;
    }

    public DataSource getTarget() {
        return target;
    }

    public void setTarget(DataSource target) {
        this.target = target;
    }

    public String getSqlBody() {
        return sqlBody;
    }

    public void setSqlBody(String sqlBody) {
        this.sqlBody = sqlBody;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

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
