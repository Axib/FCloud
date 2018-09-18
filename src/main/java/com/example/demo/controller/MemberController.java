package com.example.demo.controller;

import com.example.demo.model.ResultTO;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/comp/{compId}/get")
    public ResultTO getMemberList(@PathVariable("compId") String compId) throws Exception {
        if (compId == null || compId.isEmpty()) {
            throw new Exception("门店编号不能为空！");
        }
        ResultTO result = new ResultTO();
        result.setResult(memberService.getMemberList(compId));
        return result;
    }
}
