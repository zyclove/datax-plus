package com.datax.plus.model.view;

import com.datax.plus.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class DataSourceList extends ResultBaseVO {
    List<DataSource> list = new ArrayList<DataSource>();

    public List<DataSource> getList() {
        return list;
    }

    public void setList(List<DataSource> list) {
        this.list = list;
    }
}
