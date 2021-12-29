package com.datax.plus.model.view;

import java.util.ArrayList;
import java.util.List;

public class DbColumn {
    private String columnName;
    private String columnType;
    private List targetTemp = new ArrayList();
    private String targetTempValue = "";

    public String getTargetTempValue() {
        return targetTempValue;
    }

    public void setTargetTempValue(String targetTempValue) {
        this.targetTempValue = targetTempValue;
    }

    public List getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(List targetTemp) {
        this.targetTemp = targetTemp;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
