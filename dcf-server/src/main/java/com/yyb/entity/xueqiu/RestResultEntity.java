package com.yyb.entity.xueqiu;

import lombok.Data;

@Data
public class RestResultEntity<T> {
    private T data;
    private Integer error_code;
    private String error_description;
}
