package com.yyb.entity.xueqiu;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 利润表
 */
@Data
public class IncomeSheetItemEntity {
    //报告期：如果是2019年的年报，则为2019-12-31
    private Long report_date;
    private String report_name;
    //公布时间
    private Long ctime;
    //净利润
    private List<BigDecimal> net_profit;
    //其中:归属于母公司股东的净利润
    private List<BigDecimal> net_profit_atsopc;
    //营业总收入
    private List<BigDecimal> total_revenue;
    //营业利润
    private List<BigDecimal> op;
    //公允价值变动收益
    private List<BigDecimal> income_from_chg_in_fv;
    //对联营企业和合营企业的投资收益
    private List<BigDecimal> invest_incomes_from_rr;
    //投资收益
    private List<BigDecimal> invest_income;
    //营业税金及附加
    private List<BigDecimal> operating_taxes_and_surcharge;
    //资产减值损失
    private List<BigDecimal> asset_impairment_loss;
    //加:营业外收入
    private List<BigDecimal> non_operating_income;
    //减:营业外支出
    private List<BigDecimal> non_operating_payout;
    //利润总额
    private List<BigDecimal> profit_total_amt;
    //少数股东损益
    private List<BigDecimal> minority_gal;
    //基本每股收益
    private List<BigDecimal> basic_eps;
    //稀释每股收益
    private List<BigDecimal> dlt_earnings_per_share;
    //归属母公司所有者的其他综合收益
    private List<BigDecimal> othr_compre_income_atoopc;
    //归属于少数股东的其他综合收益的税后净额
    private List<BigDecimal> othr_compre_income_atms;
    //综合收益总额
    private List<BigDecimal> total_compre_income;
    //归属于母公司所有者的综合收益总额
    private List<BigDecimal> total_compre_income_atsopc;
    //归属于少数股东的综合收益总额
    private List<BigDecimal> total_compre_income_atms;
    //其他综合收益
    private List<BigDecimal> othr_compre_income;
    //扣除非经常性损益后的净利润
    private List<BigDecimal> net_profit_after_nrgal_atsolc;
    //减:所得税费用
    private List<BigDecimal> income_tax_expenses;
    //信用减值损失
    private List<BigDecimal> credit_impairment_loss;
    //营业收入
    private List<BigDecimal> revenue;
    //营业总成本
    private List<BigDecimal> operating_costs;
    //营业成本
    private List<BigDecimal> operating_cost;
    //销售费用
    private List<BigDecimal> sales_fee;
    //管理费用
    private List<BigDecimal> manage_fee;
    //财务费用
    private List<BigDecimal> financing_expenses;
    //研发费用
    private List<BigDecimal> rad_cost;
    //利息费用
    private List<BigDecimal> finance_cost_interest_fee;
    //利息收入
    private List<BigDecimal> finance_cost_interest_income;
    //资产处置收益
    private List<BigDecimal> asset_disposal_income;
    //其他收益
    private List<BigDecimal> other_income;
    //非流动资产处置利得
    private List<BigDecimal> noncurrent_assets_dispose_gain;
    //非流动资产处置损失
    private List<BigDecimal> noncurrent_asset_disposal_loss;
    //净利润差额(合计平衡项目)
    private List<BigDecimal> net_profit_bi;
    //持续经营净利润
    private List<BigDecimal> continous_operating_np;
}
