package com.example.demo.redis;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * 通过service来提供redis服务
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * @param Key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String Key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(Key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }

    }

    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param str
     * @param <T>
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }

    }

    /**
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 将redis返回连接池
     *
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 设置redis缓存
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成prefixKey
            String prefixKey = keyPrefix.getPrefix() + key;
            String stringValue = beanToString(value);
            if (stringValue == null || stringValue.length() <= 0) {
                return false;
            }
            int seconds = keyPrefix.expireSeconds();//判断是否超过有效期
            if (seconds <= 0) {
                jedis.set(prefixKey, stringValue);
            } else {
                jedis.setex(prefixKey,seconds,stringValue);//设置了有效期 使用setex方法
            }

            return true;
        } finally {
            jedis.close();
        }
    }

    /**
     * 获得redis缓存
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成prefixKey
            String prefixKey = keyPrefix.getPrefix() + key;
            String value = jedis.get(prefixKey);
            T t = stringToBean(value, clazz);
            return t;
        } finally {
            jedis.close();
        }
    }

    /**
     * 判断是否存在
     */
    public <T> boolean exit(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成prefixKey
            String prefixKey = keyPrefix.getPrefix() + key;
            return jedis.exists(prefixKey);
        } finally {
            jedis.close();
        }
    }

    /**
     * 增加一个值
     * @param keyPrefix
     * @param key
     * @return
     */
    public Long incr(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成prefixKey
            String prefixKey = keyPrefix.getPrefix() + key;
            return jedis.incr(prefixKey);
        } finally {
            jedis.close();
        }
    }

    /**
     * 减少一个值
     * @param keyPrefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成prefixKey
            String prefixKey = keyPrefix.getPrefix() + key;
            return jedis.decr(prefixKey);
        } finally {
            jedis.close();
        }
    }
}
