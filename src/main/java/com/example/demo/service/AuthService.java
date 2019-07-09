package com.example.demo.service;

import com.example.demo.mapper.AuthMapper;
import com.example.demo.model.FCMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthMapper authMapper;

    public List<FCMenu> getUserPrivilegeMenu() {
        return authMapper.getUserPrivilegeMenu();
    }
}
