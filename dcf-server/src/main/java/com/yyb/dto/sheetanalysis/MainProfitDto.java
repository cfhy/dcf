package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 主营利润
 */
@Data
public class MainProfitDto {
    //年份
    private Integer year;
    //营业收入
    private BigDecimal totalRevenue;
    //营业成本
    private BigDecimal operatingCost;
    //营业税金及附加
    private BigDecimal operatingTaxes;
    //四费合计
    private BigDecimal totalFourFee;
    //主营利润 = 营业收入 - 营业成本 - 税金及附加 - 四费合计
    private BigDecimal mainProfit;
    //主营利润率(主营占营业收入比率) "大于15%，说明主营盈利还可以。小于15%，公司不具备持续的竞争力，淘汰。"
    private BigDecimal mainProfitRatio;
    //利润总额
    private BigDecimal profitTotalAmt;
    //主营利润占利润总额的比率
    //大于80%，说明“利润总额”中的绝大部分利润是由主业创造的，这样的利润结构才是健康的，这样的利润才是可持续的。小于80%，这样的公司不具备持续的竞争力，淘汰。
    //这个比率越高越好。
    private BigDecimal mainProfitWithProfitTotalRatio;
}
