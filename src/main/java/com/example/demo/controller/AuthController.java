package com.example.demo.controller;

import com.example.demo.annotation.AuthInfo;
import com.example.demo.model.ResultTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @AuthInfo
    @RequestMapping("/user/privilege/menu/get")
    public ResultTO getUserPrivilegeMenu() throws Exception {
        ResultTO result = new ResultTO();
        result.setResult(authService.getUserPrivilegeMenu());
        return result;
    }
}
