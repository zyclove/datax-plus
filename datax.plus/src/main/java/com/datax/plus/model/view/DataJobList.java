package com.datax.plus.model.view;

import com.datax.plus.model.BaseVO;
import com.datax.plus.model.DataJob;

import java.util.ArrayList;
import java.util.List;

public class DataJobList extends BaseVO {
    List<DataJob> list = new ArrayList<DataJob>();

    public List<DataJob> getList() {
        return list;
    }

    public void setList(List<DataJob> list) {
        this.list = list;
    }
}
