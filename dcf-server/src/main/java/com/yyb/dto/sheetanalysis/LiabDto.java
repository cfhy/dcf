package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 有息负债
 */
@Data
public class LiabDto {
    //年份
    private String year;
    //货币资金
    private BigDecimal currencyFunds;
    //理财产品-其他流动资产里的理财产品
    private BigDecimal otherFinancialProduct;
    //理财产品-交易性金融资产里的理财产品或者结构性存款
    private BigDecimal tradeFinancialProduct;
    //准货币资金 = 货币资金 + 其他流动资产中的“理财产品”
    private BigDecimal currencyFundsPlus;

    //短期借款
    private BigDecimal stLoan;
    //一年内到期的非流动负债
    private BigDecimal noncurrentLiabDueIn1y;
    //长期借款
    private BigDecimal ltLoan;
    //应付债券
    private BigDecimal bondPayable;
    //长期应付款
    private BigDecimal ltPayable;
    //有息负债总额
    // 有息负债总额 = 短期借款 + 一年内到期的非流动负债 + 长期借款 + 应付债券 + 长期应付款
    private BigDecimal totalLiabWithInterest;
    /**
     * 准货币资金与有息负债之差 = 准货币资金 - 有息负债
     *  大于0，无偿债风险。 异常情况：准货币资金和短期借款或长期借款的金额都很大，很可能企业实际没有钱，后期风险很大。
     *  小于0，有偿债风险。
     */
    private BigDecimal liabZhbzjWithYxfzDiff;
}
