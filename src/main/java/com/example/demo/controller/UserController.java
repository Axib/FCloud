package com.example.demo.controller;

import com.example.demo.model.FCUser;
import com.example.demo.model.ResultTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/user")
@Component
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultTO userLogin(@RequestBody FCUser login) throws Exception {
        ResultTO result = new ResultTO();
        if (login == null) {
            throw new Exception("登录信息不能为空！");
        }
        if (login.getUserName() == null || login.getUserName().isEmpty()) {
            throw new Exception("登录用户名不能为空！");
        }
        if (login.getPword() == null || login.getPword().isEmpty()) {
            throw new Exception("登录密码不能为空！");
        }

        result.setResult(userService.userLogin(login));
        return result;
    }
}
