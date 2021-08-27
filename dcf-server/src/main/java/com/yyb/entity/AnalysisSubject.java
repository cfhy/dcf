package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("analysis_subject")
public class AnalysisSubject {
    public AnalysisSubject(){}
    public AnalysisSubject(Integer year,String stock_name,String stock_code){
        this.year=year;
        this.stock_code=stock_code;
        this.stock_name=stock_name;
    }

    @TableId(type = IdType.AUTO)
    private Integer analysis_subject_id;
    //股票ID
    private String stock_code;
    private String stock_name;
    //年份
    private Integer year;
    //资产合计
    private BigDecimal total_assets;
    //总资产增长率
    private BigDecimal total_assets_group_ratio;
    //负债合计
    private BigDecimal total_liab;
    //资产负债率
    private BigDecimal asset_liab_ratio;
    //货币资金
    private BigDecimal currency_funds;
    //其他流动资产里的“理财产品”
    private BigDecimal other_financial_product;
    //交易性金融资产里的理财产品或者结构性存款
    private BigDecimal trade_financial_product;
    //准货币资金
    private BigDecimal currency_funds_plus;
    //短期借款
    private BigDecimal st_loan;
    //一年内到期的非流动负债
    private BigDecimal noncurrent_liab_due_in1y;
    //长期借款
    private BigDecimal lt_loan;
    //应付债券
    private BigDecimal bond_payable;
    //长期应付款
    private BigDecimal lt_payable;
    //有息负债总额
    private BigDecimal total_liab_with_interest;
    //准货币资金与有息负债之差
    private BigDecimal liab_zhbzj_with_yxfz_diff;
    //应付票据
    private BigDecimal bill_payable;
    //应付账款
    private BigDecimal accounts_payable;
    //预收款项
    private BigDecimal pre_receivable;
    //应付预收合计
    private BigDecimal total_ap_bp_pr;
    //应收票据
    private BigDecimal bills_receivable;
    //应收款项融资里的应收票据
    private BigDecimal bills_receivable_plus;
    //应收账款
    private BigDecimal account_receivable;
    //应收款项融资里的应收账款保理
    private BigDecimal account_receivable_plus;
    //预付款项
    private BigDecimal pre_payment;
    //应收预付合计
    private BigDecimal total_ar_br;
    //应付预收与应收预付之差
    private BigDecimal ap_bp_pr_with_ar_br_diff;
    //应收账款占总资产的比率
    private BigDecimal account_receivable_ratio;
    //固定资产
    private BigDecimal fixed_asset;
    //在建工程
    private BigDecimal construction_in_process;
    //工程物资
    private BigDecimal project_goods_and_material;
    //固定资产总计
    private BigDecimal total_fixed_asset;
    //固定资产工程占总资产的比率
    private BigDecimal fixed_asset_ratio;
    //交易性金融资产
    private BigDecimal tradable_fnncl_assets;
    //可供出售金融资产
    private BigDecimal saleable_finacial_assets;
    //其他权益工具投资
    private BigDecimal othr_equity_instruments;
    //其他非流动金融资产
    private BigDecimal other_illiquid_fnncl_assets;
    //持有至到期投资
    private BigDecimal held_to_maturity_invest;
    //长期股权投资
    private BigDecimal lt_equity_invest;
    //投资性房地产
    private BigDecimal invest_property;
    //与主业无关的投资类资产合计
    private BigDecimal total_fnncl_assets;
    //与主业无关的投资类资产占总资产的比率
    private BigDecimal total_fnncl_assets_ratio;
    //营业收入
    private BigDecimal revenue;
    //营业收入增长率
    private BigDecimal revenue_group_ratio;
    //销售商品、提供劳务收到的现金
    private BigDecimal cash_received_of_sales_service;
    //销售商品、提供劳务收到的现金与营业收入的比率
    private BigDecimal cash_received_of_sales_service_ratio;
    //营业成本
    private BigDecimal operating_costs;
    //毛利
    private BigDecimal revenue_with_operating_costs_diff;
    //毛利率
    private BigDecimal revenue_with_operating_costs_diff_ratio;
    //销售费用
    private BigDecimal sales_fee;
    //管理费用
    private BigDecimal manage_fee;
    //研发费用
    private BigDecimal rad_cost;
    //财务费用
    private BigDecimal financing_expenses;
    //四费合计
    private BigDecimal total_fee;
    //费用率
    private BigDecimal total_fee_ratio;
    //费用率与毛利率之比
    private BigDecimal total_fee_with_mll_ratio;
    //营业税金及附加
    private BigDecimal operating_taxes_and_surcharge;
    //主营利润
    private BigDecimal main_profit;
    //主营利润率
    private BigDecimal main_profit_ratio;
    //利润总额
    private BigDecimal profit_total_amt;
    //主营利润占利润总额的比率
    private BigDecimal main_profit_with_profit_total_ratio;
    //净利润
    private BigDecimal net_profit;
    //经营活动产生的现金流量净额
    private BigDecimal ncf_from_oa;
    //净利润现金比率
    private BigDecimal net_profit_ratio;
    //归属于母公司所有者的净利润
    private BigDecimal net_profit_atsopc;
    //归母净利润增长率
    private BigDecimal net_profit_atsopc_group_ratio;
    //加权平均净资产收益率ROE
    private BigDecimal roe;
    //固定资产折旧
    private BigDecimal asset_depreciation;
    //无形资产摊销
    private BigDecimal intangible_assets_amortize;
    //分配股利、利润或偿付利息支付的现金
    private BigDecimal cash_paid_of_distribution;
    //“经营现金净额”减去“固定折旧+无形摊销+借款利息+股利”之差
    private BigDecimal ncf_from_oa_diff;
    //购建固定资产、无形资产和其他长期资产支付的现金
    private BigDecimal cash_paid_for_assets;
    //购建支付的现金与经营活动产生的现金流量净额的比率
    private BigDecimal buy_ratio;
    //处置固定资产、无形资产和其他长期资产收回的现金净额
    private BigDecimal net_cash_of_disposal_assets;
    //处置收回的现金净额与购建支付的现金的比率
    private BigDecimal sale_ratio;
    //分红率
    private BigDecimal bonus_ratio;
    //投资活动产生的现金流量净额
    private BigDecimal ncf_from_ia;
    //筹资活动产生的现金流量净额
    private BigDecimal ncf_from_fa;
    //三项经营活动的类型
    private String type;
    //现金及现金等价物净增加额
    private BigDecimal net_increase_in_cce;
    //每年实际发放现金分红的总额
    private BigDecimal total_bonus;
    //"加回“现金分红”后的现金及现金等价物净增加额"
    private BigDecimal total_cash;
}
