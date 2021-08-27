package com.yyb.entity.xueqiu;

import lombok.Data;

import java.util.List;

/**
 * 现金流量表
 */
@Data
public class CashFlowSheetListEntity {
    private List<CashFlowSheetItemEntity> list;
}
