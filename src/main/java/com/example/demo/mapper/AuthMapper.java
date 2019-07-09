package com.example.demo.mapper;

import com.example.demo.model.FCMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthMapper {
    List<FCMenu> getUserPrivilegeMenu();
}
