package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 投资类资产
 */
@Data
public class InvestmentAssetsDto {
    //年份
    private String year;
    //以公允价值计量且其变动计入当期损益的金融资产（交易性金融资产）
    private BigDecimal tradableFnnclAssets;
    //可供出售金融资产
    private BigDecimal saleableFinacialAssets;
    //其他权益工具投资
    private BigDecimal othrEquityInstruments;
    //其他非流动金融资产
    private BigDecimal otherIlliquidFnnclAssets;
    //持有至到期投资
    private BigDecimal heldToMaturityInvest;
    //长期股权投资
    // 搜索“长期股权投资”,如果投资的是子公司、联营、合营企业，说明与主业有关；否则与主业无关。
    private BigDecimal ltEquityInvest;
    //投资性房地产
    private BigDecimal investProperty;
    //与主业无关的投资类资产合计(与主业无关投资类资产合计 = 以公允价值计量且其变动计入当期损益的金融资产 + 可供出售金融资产 + 持有至到期投资 + 投资性房地产 + 与主业无关的长期股权投资)
    private BigDecimal totalFnnclAssets;
    //与主业无关的投资类资产占总资产的比率,大于10%的公司不够专注，淘汰掉！
    private BigDecimal ratio;
}
