package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 销售毛利率
 */
@Data
public class GrossSellingRateDto {
    //年份
    private String year;
    //营业收入
    private BigDecimal revenue;
    //营业成本
    private BigDecimal operatingCost;
    //毛利=营业收入-营业成本
    private BigDecimal revenueWithOperatingCostsDiff;
    //毛利率 = （营业收入 - 营业成本） / 营业收入 = 毛利 / 营业收入
    private BigDecimal revenueWithOperatingCostsDiffRatio;
}
