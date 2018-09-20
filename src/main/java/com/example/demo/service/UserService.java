package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.FCUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public FCUser userLogin(FCUser login) {
        return userMapper.userLogin(login);
    }
}
