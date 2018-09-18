package com.example.demo.service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Gbm01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public List<Gbm01> getMemberList(String compId) {
        return memberMapper.getMemberList(compId);
    }
}
