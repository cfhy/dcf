package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 费用率
 */
@Data
public class FeeRatioDto {
    //年份
    private String year;
    //营业收入
    private BigDecimal revenue;
    //销售费用
    private BigDecimal salesFee;
    //管理费用
    private BigDecimal manageFee;
    //财务费用
    // 出于保守考虑，如果“财务费用”是正数，就把“财务费用”计算在内。如果“财务费用”是负数，就不把“财务费用”计算在内。
    private BigDecimal financingExpenses;
    //研发费用，2018年，新会计准则，研发费用从管理费用里单设科目。
    private BigDecimal radCost;
    //四费合计= 销售费用 + 管理费用 + 研发费用 + 财务费用
    private BigDecimal totalFee;
    //费用率(四费占营业收入比率), 费用率 = （销售费用 + 管理费用 + 研发费用 + 财务费用） / 营业收入 = 四费 / 营业收入 * 100%
    private BigDecimal totalFeeRatio;
    //比率 = 费用率 / 毛利率,毛利率高、费用率低，净利润率才可能高。
    //小于40%，优秀公司的费用率与毛利率之比一般小于40%。大于60%，把费用率与毛利率的比率大于60%的公司淘汰掉。
    private BigDecimal totalFeeWithMllRatio;
}
