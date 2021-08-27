package com.yyb.entity.xueqiu;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 所有设计到百分比的指标已乘了100
 */
@Data
public class MainIndicatorItemEntity {
    //报告期：如果是2019年的年报，则为2019-12-31
    private Long report_date;
    private String report_name;
    //公布时间
    private Long ctime;
    //净资产收益率
    private List<BigDecimal> avg_roe;
    //每股净资产
    private List<BigDecimal> np_per_share;
    //每股经营现金流
    private List<BigDecimal> operate_cash_flow_ps;
    //每股收益
    private List<BigDecimal> basic_eps;
    //每股资本公积金
    private List<BigDecimal> capital_reserve;
    //每股未分配利润
    private List<BigDecimal> undistri_profit_ps;
    //总资产报酬率
    private List<BigDecimal> net_interest_of_total_assets;
    //销售净利率
    private List<BigDecimal> net_selling_rate;
    //销售毛利率
    private List<BigDecimal> gross_selling_rate;
    //营业收入
    private List<BigDecimal> total_revenue;
    //营业收入同比增长
    private List<BigDecimal> operating_income_yoy;
    //净利润
    private List<BigDecimal> net_profit_atsopc;
    //净利润同比增长
    private List<BigDecimal> net_profit_atsopc_yoy;
    //扣非净利润
    private List<BigDecimal> net_profit_after_nrgal_atsolc;
    //扣非净利润同比增长
    private List<BigDecimal> np_atsopc_nrgal_yoy;
    //净资产收益率-摊薄
    private List<BigDecimal> ore_dlt;
    //人力投入回报率
    private List<BigDecimal> rop;
    //资产负债率
    private List<BigDecimal> asset_liab_ratio;
    //流动比率
    private List<BigDecimal> current_ratio;
    //速动比率
    private List<BigDecimal> quick_ratio;
    //权益乘数
    private List<BigDecimal> equity_multiplier;
    //产权比率
    private List<BigDecimal> equity_ratio;
    //股东权益比率
    private List<BigDecimal> holder_equity;
    //现金流量比率
    private List<BigDecimal> ncf_from_oa_to_total_liab;
    //存货周转天数
    private List<BigDecimal> inventory_turnover_days;
    //应收账款周转天数
    private List<BigDecimal> receivable_turnover_days;
    //应付账款周转天数
    private List<BigDecimal> accounts_payable_turnover_days;
    //现金循环周期
    private List<BigDecimal> cash_cycle;
    //营业周期
    private List<BigDecimal> operating_cycle;
    //总资产周转率
    private List<BigDecimal> total_capital_turnover;
    //存货周转率
    private List<BigDecimal> inventory_turnover;
    //应收账款周转率
    private List<BigDecimal> account_receivable_turnover;
    //应付账款周转率
    private List<BigDecimal> accounts_payable_turnover;
    //流动资产周转率
    private List<BigDecimal> current_asset_turnover_rate;
    //固定资产周转率
    private List<BigDecimal> fixed_asset_turnover_ratio;
}
