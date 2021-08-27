package com.yyb.entity.xueqiu;

import lombok.Data;

@Data
public class SelectedStockItemEntity {
    private String symbol;
    private String name;
    private Integer type;
    private String remark;
    private String exchange;
    private Long created;
}
