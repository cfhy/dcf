package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 分红
 */
@Data
public class BonusDto {
    //年份
    private String year;
    //分红率
    // 即 现金分红金额占合并报表中归属于上市公司普通股股东的净利润的比率
    // 分红率 = 现金分红金额 / 合并报表中归属于上市公司普通股股东的净利润 * 100%
    // 在年报中可以搜索“现金分红”或“现金分红情况”直接得到相关的比率，每一年的比率以当年的财报为准。
    private BigDecimal bonusRatio;
}
