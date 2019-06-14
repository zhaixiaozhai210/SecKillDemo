package com.example.demo.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 将配置文件的信息，读取并自动封装成实体类
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    /**
     *
     */
    private String host;
    /**
     *
     */
    private int port;
    /**
     *
     */
    private int timeout;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private int jedisPoolMaxActive;
    /**
     *
     */
    private int jedisPoolMaxIdle;
    /**
     *
     */
    private int jedisPoolMaxWait;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getJedisPoolMaxActive() {
        return jedisPoolMaxActive;
    }

    public void setJedisPoolMaxActive(int jedisPoolMaxActive) {
        this.jedisPoolMaxActive = jedisPoolMaxActive;
    }

    public int getJedisPoolMaxIdle() {
        return jedisPoolMaxIdle;
    }

    public void setJedisPoolMaxIdle(int jedisPoolMaxIdle) {
        this.jedisPoolMaxIdle = jedisPoolMaxIdle;
    }

    public int getJedisPoolMaxWait() {
        return jedisPoolMaxWait;
    }

    public void setJedisPoolMaxWait(int jedisPoolMaxWait) {
        this.jedisPoolMaxWait = jedisPoolMaxWait;
    }
}
