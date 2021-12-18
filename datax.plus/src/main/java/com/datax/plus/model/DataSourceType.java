package com.datax.plus.model;

public class DataSourceType {
    private int dataSourceTypeId;
    private String dataSourceTypeName;
    private String dataSourceHostUrlTemplate;
    private int status;

    public int getDataSourceTypeId() {
        return dataSourceTypeId;
    }

    public void setDataSourceTypeId(int dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
    }

    public String getDataSourceTypeName() {
        return dataSourceTypeName;
    }

    public void setDataSourceTypeName(String dataSourceTypeName) {
        this.dataSourceTypeName = dataSourceTypeName;
    }

    public String getDataSourceHostUrlTemplate() {
        return dataSourceHostUrlTemplate;
    }

    public void setDataSourceHostUrlTemplate(String dataSourceHostUrlTemplate) {
        this.dataSourceHostUrlTemplate = dataSourceHostUrlTemplate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
