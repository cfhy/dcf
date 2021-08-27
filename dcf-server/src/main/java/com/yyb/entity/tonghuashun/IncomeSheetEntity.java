package com.yyb.entity.tonghuashun;

import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 利润表
 */
@Data
public class IncomeSheetEntity {
    //年份
    @Column(name = "科目\\时间")
    private Integer year;

    @Column(name = "一、营业总收入(元)")
    private BigDecimal total_revenue;

    @Column(name = "其中：营业收入(元)")
    private BigDecimal revenue;

    @Column(name = "二、营业总成本(元)")
    private BigDecimal total_operating_costs;

    @Column(name = "其中：营业成本(元)")
    private BigDecimal operating_cost;

    @Column(name = "营业税金及附加(元)")
    private BigDecimal operating_taxes_and_surcharge;

    @Column(name = "销售费用(元)")
    private BigDecimal sales_fee;

    @Column(name = "管理费用(元)")
    private BigDecimal manage_fee;

    @Column(name = "研发费用(元)")
    private BigDecimal rad_cost;

    @Column(name = "财务费用(元)")
    private BigDecimal financing_expenses;

    @Column(name = "其中：利息费用(元)")
    private BigDecimal finance_cost_interest_fee;

    @Column(name = "利息收入(元)")
    private BigDecimal finance_cost_interest_income;

    @Column(name = "资产减值损失(元)")
    private BigDecimal asset_impairment_loss;

    @Column(name = "信用减值损失(元)")
    private BigDecimal credit_impairment_loss;

    @Column(name = "加：公允价值变动收益(元)")
    private BigDecimal income_from_chg_in_fv;

    @Column(name = "投资收益(元)")
    private BigDecimal invest_income;

    @Column(name = "其中：联营企业和合营企业的投资收益(元)")
    private BigDecimal invest_incomes_from_rr;

    @Column(name = "资产处置收益(元)")
    private BigDecimal asset_disposal_income;

    @Column(name = "其他收益(元)")
    private BigDecimal other_income;

    @Column(name = "三、营业利润(元)")
    private BigDecimal op;

    @Column(name = "加：营业外收入(元)")
    private BigDecimal non_operating_income;

    @Column(name = "其中：非流动资产处置利得(元)")
    private BigDecimal noncurrent_assets_dispose_gain;

    @Column(name = "减：营业外支出(元)")
    private BigDecimal non_operating_payout;

    @Column(name = "其中：非流动资产处置损失(元)")
    private BigDecimal noncurrent_asset_disposal_loss;

    @Column(name = "四、利润总额(元)")
    private BigDecimal profit_total_amt;

    @Column(name = "减：所得税费用(元)")
    private BigDecimal income_tax_expenses;

    @Column(name = "五、净利润(元)")
    private BigDecimal net_profit;

    @Column(name = "一）持续经营净利润(元)")
    private BigDecimal continous_operating_np;

    @Column(name = "归属于母公司所有者的净利润(元)")
    private BigDecimal net_profit_atsopc;

    @Column(name = "少数股东损益(元)")
    private BigDecimal minority_gal;

    @Column(name = "扣除非经常性损益后的净利润(元)")
    private BigDecimal net_profit_after_nrgal_atsolc;

    @Column(name = "一）基本每股收益(元)")
    private BigDecimal basic_eps;

    @Column(name = "二）稀释每股收益(元)")
    private BigDecimal dlt_earnings_per_share;

    @Column(name = "七、其他综合收益(元)")
    private BigDecimal othr_compre_income;

    @Column(name = "归属母公司所有者的其他综合收益(元)")
    private BigDecimal othr_compre_income_atoopc;

    @Column(name = "八、综合收益总额(元)")
    private BigDecimal total_compre_income;

    @Column(name = "归属于母公司股东的综合收益总额(元)")
    private BigDecimal total_compre_income_atsopc;

    @Column(name = "归属于少数股东的综合收益总额(元)")
    private BigDecimal total_compre_income_atms;
}
