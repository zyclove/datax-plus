package com.datax.plus.model.view;

public class HttpRequestResult {
    private int code;
    private String msg;
    private String token;
    private ResultBaseVO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResultBaseVO getData() {
        return data;
    }

    public void setData(ResultBaseVO data) {
        this.data = data;
    }
}
