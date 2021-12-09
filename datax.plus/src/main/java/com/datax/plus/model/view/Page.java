package com.datax.plus.model.view;

public class Page {
    private int pageNum;
    private int start;
    private int limit;

    public Page(int page, int limit) {
        this.pageNum = page;
        this.limit = limit;
        this.start = (this.pageNum - 1) * this.limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

}
