package com.datax.plus.model.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapList extends ResultBaseVO{
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
