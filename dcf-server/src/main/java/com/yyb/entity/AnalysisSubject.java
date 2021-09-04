package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("analysis_subject")
public class AnalysisSubject {
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
    private BigDecimal total_assets_yoy;
    //负债合计
    private BigDecimal total_liab;
    //资产负债率
    private BigDecimal asset_liab_ratio;
    //货币资金
    private BigDecimal currency_funds;

    //交易性金融资产
    private BigDecimal tradable_fnncl_assets;
    //'其他流动资产里的理财产品'
    private BigDecimal othr_current_assets_lccp;
    //''其他流动资产里的结构性存款''
    private BigDecimal trade_financial_jgxck;
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
    //'短期有息负债'
    private BigDecimal total_st_liab;
    //'货币资金减短期有息负债的差额'
    private BigDecimal currency_funds_with_dqfz_diff;

    //应付票据
    private BigDecimal bill_payable;
    //应付账款
    private BigDecimal accounts_payable;
    //预收款项
    private BigDecimal pre_receivable;
    //'合同负债'
    private BigDecimal contract_liabilities;
    //应付预收合计
    private BigDecimal total_ap_bp_pr;
    //应收票据
    private BigDecimal bills_receivable;
    //应收账款
    private BigDecimal account_receivable;
    //'应收款项融资'
    private BigDecimal finance_rece;
    //预付款项
    private BigDecimal pre_payment;
    //'合同资产'
    private BigDecimal contractual_assets;
    //应收预付合计
    private BigDecimal total_ar_br;
    //应付预收与应收预付之差
    private BigDecimal ap_bp_pr_with_ar_br_diff;
    //应收账款和合同资产占总资产的比率
    private BigDecimal account_receivable_ratio;
    //应收账款占总资产的比率
    private BigDecimal only_account_receivable_ratio;
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

    //'债权投资'
    private BigDecimal creditor_invest;
    //'其他债权投资'
    private BigDecimal other_creditor_invest;
    //'以公允价值计量且其变动计入当期损益的金融资产'
    private BigDecimal fvtpl_finassets;
    //可供出售金融资产
    private BigDecimal saleable_finacial_assets;
    //持有至到期投资
    private BigDecimal held_to_maturity_invest;
    //长期股权投资
    private BigDecimal lt_equity_invest;
    //投资性房地产
    private BigDecimal invest_property;
    //其他权益工具投资
    private BigDecimal othr_equity_instruments;
    //其他非流动金融资产
    private BigDecimal other_illiquid_fnncl_assets;
    //与主业无关的投资类资产合计
    private BigDecimal total_fnncl_assets;
    //与主业无关的投资类资产占总资产的比率
    private BigDecimal total_fnncl_assets_ratio;

    //'存货'
    private BigDecimal inventory;
    //'存货占总资产比率'
    private BigDecimal inventory_with_assets_ratio;
    //'商誉'
    private BigDecimal goodwill;
    //'商誉占总资产比率'
    private BigDecimal goodwill_with_assets_ratio;

    //营业收入
    private BigDecimal operate_income;
    //营业收入增长率
    private BigDecimal operate_income_yoy;
    //销售商品、提供劳务收到的现金
    private BigDecimal cash_received_of_sales_service;
    //销售商品、提供劳务收到的现金与营业收入的比率
    private BigDecimal cash_received_of_sales_service_ratio;

    //营业成本
    private BigDecimal operating_costs;
    //毛利
    private BigDecimal gross_profit;
    //毛利率
    private BigDecimal gross_profit_ratio;
    //'毛利率波动幅度'
    private BigDecimal gross_profit_ratio_wave;

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

    //'销售费用率'
    private BigDecimal sales_fee_ratio;

    //营业税金及附加
    private BigDecimal operating_taxes_and_surcharge;
    //主营利润
    private BigDecimal main_profit;
    //主营利润率
    private BigDecimal main_profit_ratio;
    //营业利润
    private BigDecimal operate_profit;
    //主营利润占营业利润的比率
    private BigDecimal main_profit_with_profit_total_ratio;

    //净利润
    private BigDecimal net_profit;
    //经营活动产生的现金流量净额
    private BigDecimal ncf_from_oa;
    //'经营活动产生的现金流量净额增长率'
    private BigDecimal netcash_operate_yoy;
    //净利润现金比率
    private BigDecimal net_profit_ratio;

    //归属于母公司所有者的净利润
    private BigDecimal net_profit_atsopc;
    //归母净利润增长率
    private BigDecimal parent_netprofit_yoy;
    //加权平均净资产收益率ROE
    private BigDecimal roe;

    //购建固定资产、无形资产和其他长期资产支付的现金
    private BigDecimal cash_paid_for_assets;
    //购建支付的现金与经营活动产生的现金流量净额的比率
    private BigDecimal buy_ratio;

    //'年度累计分红总额'
    private BigDecimal bonus_fee;
    //分红率
    private BigDecimal bonus_ratio;
    //投资活动产生的现金流量净额
    private BigDecimal ncf_from_ia;
    //筹资活动产生的现金流量净额
    private BigDecimal ncf_from_fa;
    //三项经营活动的类型
    private String type;

    //'审计意见'
    private String opinion_type;
}
