package com.yyb.entity.xueqiu;

import lombok.Data;

import java.util.List;

/**
 * 最近5年的资产负债表
 */
@Data
public class BalanceSheetListEntity {
    private List<BalanceSheetItemEntity> list;
}
