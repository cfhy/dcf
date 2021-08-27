package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购建固定资产、无形资产和其他长期资产支付的现金
 */
@Data
public class FutureGroupDto {
    //年份
    private Integer year;
    //购建固定资产、无形资产和其他长期资产支付的现金
    //这个科目的金额应当小于“经营活动产生的现金流量净额”这个科目的金额，否则（大于的话）公司就需要通过融资维持投资，经营风险加大。
    private BigDecimal cashPaidForAssets;
    //经营活动产生的现金流量净额
    private BigDecimal ncfFromOa;
    //购建支付的现金与经营活动产生的现金流量净额的比率
    // 比率 = 购建固定资产、无形资产和其他长期资产支付的现金 / 经营活动产生的现金流量净额 * 100%
    // 比率在10%-60%之间，公司的成长性比较好。比率小于10%，公司成长太慢，回报较低。淘汰！比率大于100%，公司扩张大激进，经营风险大。淘汰！
    private BigDecimal buyRatio;
    //处置固定资产、无形资产和其他长期资产收回的现金净额
    private BigDecimal netCashOfDisposalAssets;
    // 处置收回的现金净额与购建支付的现金的比率
    // 比率 = 处置固定资产、无形资产和其他长期资产收回的现金净额 / 购建固定资产、无形资产和其他长期资产支付的现金   * 100%
    //比率小于5%，公司快速成长中；比率大于100%，公司在走下坡路，收缩状态。淘汰！
    private BigDecimal sellRatio;
}

