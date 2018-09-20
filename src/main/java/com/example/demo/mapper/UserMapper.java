package com.example.demo.mapper;

import com.example.demo.model.FCUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    FCUser userLogin(@Param("login") FCUser login);
}
