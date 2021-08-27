package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 净利润
 */
@Data
public class NetProfitDto {
    //年份
    private Integer year;
    //净利润
    private BigDecimal netProfit;
    //经营活动产生的现金流量净额
    private BigDecimal ncfFromOa;
    //净利润现金比率 = 经营活动产生的现金流量净额 / 净利润 * 100%，比率小于100%的公司，淘汰。优秀公司的“净利润现金比率”会持续的大于100%。
    private BigDecimal netProfitRatio;
}
