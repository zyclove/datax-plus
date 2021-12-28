package com.datax.plus.model.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleSqlResult extends ResultBaseVO {
    private List<String> columns;
    private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }
}
