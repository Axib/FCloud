package com.example.demo.controller;

import com.example.demo.annotation.AuthInfo;
import com.example.demo.exception.CustomException;
import com.example.demo.model.ResultTO;
import com.example.demo.service.MemberService;
import com.example.demo.util.FCAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/member")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Resource
    private FCAuthUtil fcAuthUtil;


    @AuthInfo
    @RequestMapping(value = "/comp/{compId}/get")
    public ResultTO getMemberList(HttpServletRequest request,
                                  @PathVariable("compId") String compId) throws Exception {
        if (compId == null || compId.isEmpty()) {
            throw new CustomException("门店编号不能为空！");
        }
        String userName = fcAuthUtil.getLoginInfo(request).getUserName();
        ResultTO result = new ResultTO();
        result.setMsg(userName);
        result.setResult(memberService.getMemberList(compId));
        return result;
    }
}
