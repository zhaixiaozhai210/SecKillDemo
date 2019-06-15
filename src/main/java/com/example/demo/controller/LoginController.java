package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.Result;
import com.example.demo.utils.VaildatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登录功能
     */
    @GetMapping("/to-login")
    public String login(){
        return "login1";
    }

    @PostMapping("/do-login")
    public Result<String> doLogin(@RequestBody UserDTO dto){
        //参数校验
        String password = dto.getPassword();
        if (StringUtils.isEmpty(password)){
            return Result.error(CodeMsg.PASSWORD_ISEMPTY);
        }
        String phone = dto.getPhone();
        if (StringUtils.isEmpty(phone)){
            return Result.error(CodeMsg.PHONE_ISEMPTY);
        }
        if (!VaildatorUtil.isMobile(phone)){
            return Result.error(CodeMsg.PHONE_ERROR);
        }
        //登录
        CodeMsg msg = userService.login(dto);
        if (msg.getCode() == 0){
            return Result.success("index");
        }else {
            return Result.error(msg);
        }

    }
}
