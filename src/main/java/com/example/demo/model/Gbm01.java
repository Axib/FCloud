package com.example.demo.model;

public class Gbm01 {
    private String gba00c;      // varchar(10) not null,	--门店
    private String gba01c;      // varchar(20) not null,	--编号
    private String gba02c;      // varchar(50) null,	--姓名
    private Integer gba03i;      // int	   null,	--性别
    private String gba04c;      // varchar(20) null,	--手机号

    public String getGba00c() {
        return gba00c;
    }

    public void setGba00c(String gba00c) {
        this.gba00c = gba00c;
    }

    public String getGba01c() {
        return gba01c;
    }

    public void setGba01c(String gba01c) {
        this.gba01c = gba01c;
    }

    public String getGba02c() {
        return gba02c;
    }

    public void setGba02c(String gba02c) {
        this.gba02c = gba02c;
    }

    public Integer getGba03i() {
        return gba03i;
    }

    public void setGba03i(Integer gba03i) {
        this.gba03i = gba03i;
    }

    public String getGba04c() {
        return gba04c;
    }

    public void setGba04c(String gba04c) {
        this.gba04c = gba04c;
    }
}
