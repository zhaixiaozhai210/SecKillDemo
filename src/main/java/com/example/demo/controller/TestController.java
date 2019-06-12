package com.example.demo.controller;

import com.example.demo.domain.User;
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

    @GetMapping("/success")
    public Result<String> test(){
        return Result.success("success");
    }

    @GetMapping("/error")
    public Result<String> testError(){
        return Result.error(CodeMsg.SERVICE_ERROR);
    }

    @GetMapping("/user")
    public Result<User> getUser(){
        int id = 1410080408;
        return Result.success(userService.getUser(id));
    }
}
