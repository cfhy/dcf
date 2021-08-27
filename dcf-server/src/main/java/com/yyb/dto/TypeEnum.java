package com.yyb.dto;


public enum TypeEnum {
    THS("同花顺", 1), XQ("雪球", 2);

    // 成员变量
    private String name;
    private int code;

    // 构造方法
    private TypeEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
