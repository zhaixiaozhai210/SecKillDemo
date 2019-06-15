package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.utils.CodeMsg;

public interface UserService {
    User getUser(int id);

    CodeMsg login(UserDTO dto);
}
