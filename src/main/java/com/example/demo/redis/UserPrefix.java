package com.example.demo.redis;

public class UserPrefix extends BasePrefix {

    private UserPrefix(int expireSeconds, String userPrefix) {
        super(expireSeconds, userPrefix);
    }

    public static UserPrefix getById = new UserPrefix(0,"id");

    public static UserPrefix getByName = new UserPrefix(0,"name");

    public static UserPrefix setUserPrefix(String userPrefix){
        return new UserPrefix(0,userPrefix);
    }
}
