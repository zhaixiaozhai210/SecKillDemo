package com.example.demo.utils;

public class CodeMsg {

    private int code;

    private String msg;

    //
    public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static final CodeMsg SERVICE_ERROR = new CodeMsg(500, "service_error");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
