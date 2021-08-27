package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 营业收入
 */
@Data
public class IncomeDto {
    //年份
    private String year;
    //营业收入,营业收入金额较大 且 “销售商品、提供劳务收到的现金”与“营业收入”的比率大于110%的公司行业地位高，产品竞争力强。
    private BigDecimal revenue;
    //营业收入增长率,营业收入增长率大于10%的公司，成长性较好。
    private BigDecimal revenueGroupRatio;
    //销售商品、提供劳务收到的现金
    private BigDecimal cashReceivedOfSalesService;
    //销售商品、提供劳务收到的现金与营业收入的比率,比率小于100%的公司，营业收入增长率小于10%的公司，淘汰掉！
    private BigDecimal cashReceivedOfSalesServiceRatio;
}
