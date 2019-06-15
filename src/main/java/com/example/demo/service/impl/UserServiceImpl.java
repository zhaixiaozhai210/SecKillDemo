package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.utils.CodeMsg;
import com.example.demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public CodeMsg login(UserDTO dto) {
        User user = userDao.getUserByPhone(dto.getPhone());
        if (user == null){
            return CodeMsg.PHONE_ISEMPTY;
        }
        String formPassword = dto.getPassword();
        String salt = user.getSalt();
        String calPassword = MD5Util.formPassToDBPass(formPassword,salt);
        String dbPassword = user.getPassword();
        if (!calPassword.equals(dbPassword)){
            return CodeMsg.PASSWORD_ISERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
