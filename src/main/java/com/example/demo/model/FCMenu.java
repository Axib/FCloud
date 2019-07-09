package com.example.demo.model;

import java.util.List;

public class FCMenu {
    private String id;          //-- 编号
    private String menuName;    //-- 菜单名
    private String menuKey;     //-- 标识
    private String normalUrl;   //-- 地址1
    private String selectUrl;   //-- 地址2
    private String parentId;    //-- 父编号
    private int priority;       //-- 优先级
    List<FCMenu> children;      //子菜单

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public void setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
    }

    public String getSelectUrl() {
        return selectUrl;
    }

    public void setSelectUrl(String selectUrl) {
        this.selectUrl = selectUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<FCMenu> getChildren() {
        return children;
    }

    public void setChildren(List<FCMenu> children) {
        this.children = children;
    }
}
