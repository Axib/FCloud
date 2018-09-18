package com.example.demo.mapper;

import com.example.demo.model.Gbm01;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper {
    List<Gbm01> getMemberList(@Param("compId") String compId);

}
