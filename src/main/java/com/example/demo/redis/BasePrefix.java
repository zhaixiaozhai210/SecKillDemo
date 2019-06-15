package com.example.demo.redis;

public abstract class BasePrefix implements KeyPrefix {

    /**
     * 过期时间 0：永不过期
     */
    private int expireSeconds;

    private String userPrefix;

    public BasePrefix(String userPrefix){
        this(0,userPrefix);
    }

    public BasePrefix(int expireSeconds,String userPrefix){
        this.expireSeconds = expireSeconds;
        this.userPrefix = userPrefix;
    }

    public int expireSeconds() {
        return expireSeconds;
    }

    public String userPrefix() {
        return userPrefix;
    }

    public String getPrefix(){
        String className = getClass().getSimpleName();
        return className + ":" +userPrefix;
    }
}
