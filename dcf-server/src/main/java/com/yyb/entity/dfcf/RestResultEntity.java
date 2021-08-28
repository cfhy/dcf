package com.yyb.entity.dfcf;

import lombok.Data;

import java.util.List;

@Data
public class RestResultEntity<T> {
    private Integer pages;
    private Integer counts;
    private List<T> data;
}
