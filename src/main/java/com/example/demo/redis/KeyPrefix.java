package com.example.demo.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String userPrefix();

    public String getPrefix();
}
