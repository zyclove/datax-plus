package com.datax.plus.model.view;

import java.util.ArrayList;
import java.util.List;

public class StringList extends ResultBaseVO  {
    List<String> list = new ArrayList<String>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
