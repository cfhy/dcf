package com.yyb.entity.xueqiu;

import lombok.Data;

import java.util.List;

@Data
public class SelectedStockListEntity {
    private List<SelectedStockItemEntity> stocks;
}
