package com.datax.plus.model;

public class DataSource {
    private int dataSourceId;
    private DataSourceType dataSourceType;
    private String dataSourceName;
    private String dbHostUrl;
    private String dbUsername;
    private String dbPassword;
    private int status;
    private String createTime;
    private int connectionTestPassed;
    private String lastConnectionPassTime;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLastConnectionPassTime() {
        return lastConnectionPassTime;
    }

    public void setLastConnectionPassTime(String lastConnectionPassTime) {
        this.lastConnectionPassTime = lastConnectionPassTime;
    }

    public int getConnectionTestPassed() {
        return connectionTestPassed;
    }

    public void setConnectionTestPassed(int connectionTestPassed) {
        this.connectionTestPassed = connectionTestPassed;
    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDbHostUrl() {
        return dbHostUrl;
    }

    public void setDbHostUrl(String dbHostUrl) {
        this.dbHostUrl = dbHostUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
