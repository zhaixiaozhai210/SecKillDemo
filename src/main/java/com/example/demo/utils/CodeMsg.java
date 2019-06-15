package com.example.demo.utils;

public class CodeMsg {

    private int code;

    private String msg;

    //
    public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
    //服务错误
    public static final CodeMsg SERVICE_ERROR = new CodeMsg(500, "service_error");

    //登录错误
    public static final CodeMsg PASSWORD_ISEMPTY = new CodeMsg(500100, "password is empty");
    public static final CodeMsg PHONE_ISEMPTY = new CodeMsg(500200, "phone is empty");
    public static final CodeMsg PHONE_ERROR = new CodeMsg(500300, "phone is error");
    public static final CodeMsg PASSWORD_ISERROR = new CodeMsg(500400, "password is error");

    public CodeMsg(int code, String msg) {
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
