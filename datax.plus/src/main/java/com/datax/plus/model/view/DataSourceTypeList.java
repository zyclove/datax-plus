package com.datax.plus.model.view;

import com.datax.plus.model.DataSourceType;

import java.util.ArrayList;
import java.util.List;

public class DataSourceTypeList  extends ResultBaseVO {
    List<DataSourceType> list = new ArrayList<DataSourceType>();

    public List<DataSourceType> getList() {
        return list;
    }

    public void setList(List<DataSourceType> list) {
        this.list = list;
    }
}

