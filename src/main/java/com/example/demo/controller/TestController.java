package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.redis.RedisService;
import com.example.demo.redis.UserPrefix;
import com.example.demo.service.UserService;
import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @GetMapping("/success")
    public Result<String> test() {
        return Result.success("success");
    }

    @GetMapping("/error")
    public Result<String> testError() {
        return Result.error(CodeMsg.SERVICE_ERROR);
    }

    @GetMapping("/user")
    public Result<User> getUser() {
        int id = 1410080408;
        return Result.success(userService.getUser(id));
    }

    @GetMapping("/redis-test")
    public Result<Long> redisTest() {
        Long v1 = redisService.get("aaa", Long.class);
        return Result.success(v1);
    }

    @GetMapping("/redis-set-test")
    public Result<String> redisSetTest() {
        boolean b = redisService.set("hello","world");
        String s = redisService.get("hello", String.class);
        return Result.success(s);
    }

    @GetMapping("/redis-set-test2")
    public Result<User> redisSetTest2() {
        User user   =new User();
        user.setId(1410080408);
        user.setUserName("user0");
        user.setPassword("ae2fe40a6242ef07a35a30da2232e10a");
        user.setPhone("18077200000");
        boolean b = redisService.set(UserPrefix.getById,"1",user);
        User result = redisService.get(UserPrefix.getById,"1", User.class);
        return Result.success(result);
    }
}
