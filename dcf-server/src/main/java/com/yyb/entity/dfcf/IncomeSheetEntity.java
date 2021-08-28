package com.yyb.entity.dfcf;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 利润表
 */
@Data
public class IncomeSheetEntity {
    private String SECURITY_CODE;
    private String REPORT_DATE;
    private BigDecimal TOTAL_OPERATE_INCOME;//营业总收入
    private BigDecimal OPERATE_INCOME;//营业收入
    private BigDecimal INTEREST_INCOME;//利息收入
    private BigDecimal EARNED_PREMIUM;//已赚保费
    private BigDecimal FEE_COMMISSION_INCOME;//手续费及佣金收入
    private BigDecimal OTHER_BUSINESS_INCOME;//其他业务收入
    private BigDecimal TOI_OTHER;
    private BigDecimal TOTAL_OPERATE_COST;//营业总成本
    private BigDecimal OPERATE_COST;//营业成本
    private BigDecimal INTEREST_EXPENSE;//利息支出
    private BigDecimal FEE_COMMISSION_EXPENSE;//手续费及佣金支出
    private BigDecimal RESEARCH_EXPENSE;//研发费用
    private BigDecimal SURRENDER_VALUE;//退保金
    private BigDecimal NET_COMPENSATE_EXPENSE;
    private BigDecimal NET_CONTRACT_RESERVE;
    private BigDecimal POLICY_BONUS_EXPENSE;
    private BigDecimal REINSURE_EXPENSE;//分保费用
    private BigDecimal OTHER_BUSINESS_COST;//其他业务成本
    private BigDecimal OPERATE_TAX_ADD;//税金及附加
    private BigDecimal SALE_EXPENSE;//销售费用
    private BigDecimal MANAGE_EXPENSE;//管理费用
    private BigDecimal ME_RESEARCH_EXPENSE;
    private BigDecimal FINANCE_EXPENSE;//财务费用
    private BigDecimal FE_INTEREST_EXPENSE;//其中:利息费用
    private BigDecimal FE_INTEREST_INCOME;//其中:利息收入
    private BigDecimal ASSET_IMPAIRMENT_LOSS;//营业总成本-资产减值损失
    private BigDecimal CREDIT_IMPAIRMENT_LOSS;//信用减值损失
    private BigDecimal TOC_OTHER;
    private BigDecimal FAIRVALUE_CHANGE_INCOME;//公允价值变动收益
    private BigDecimal INVEST_INCOME;//投资收益
    private BigDecimal INVEST_JOINT_INCOME;//对联营企业和合营企业的投资收益
    private BigDecimal NET_EXPOSURE_INCOME;
    private BigDecimal EXCHANGE_INCOME;//汇兑损失
    private BigDecimal ASSET_DISPOSAL_INCOME;//资产处置收益
    private BigDecimal ASSET_IMPAIRMENT_INCOME;//其他经营收益-资产减值损失
    private BigDecimal CREDIT_IMPAIRMENT_INCOME;//其他经营收益-信用减值损失
    private BigDecimal OTHER_INCOME;//其他收益
    private BigDecimal OPERATE_PROFIT_OTHER;
    private BigDecimal OPERATE_PROFIT_BALANCE;
    private BigDecimal OPERATE_PROFIT;//营业利润
    private BigDecimal NONBUSINESS_INCOME;//加:营业外收入
    private BigDecimal NONCURRENT_DISPOSAL_INCOME;//其中:非流动资产处置利得
    private BigDecimal NONBUSINESS_EXPENSE;//减:营业外支出
    private BigDecimal NONCURRENT_DISPOSAL_LOSS;//其中:非流动资产处置净损失
    private BigDecimal EFFECT_TP_OTHER;
    private BigDecimal TOTAL_PROFIT_BALANCE;
    private BigDecimal TOTAL_PROFIT;//利润总额
    private BigDecimal INCOME_TAX;//减:所得税
    private BigDecimal EFFECT_NETPROFIT_OTHER;
    private BigDecimal EFFECT_NETPROFIT_BALANCE;
    private BigDecimal UNCONFIRM_INVEST_LOSS;
    private BigDecimal NETPROFIT;//净利润
    private BigDecimal PRECOMBINE_PROFIT;
    private BigDecimal CONTINUED_NETPROFIT;//持续经营净利润
    private BigDecimal DISCONTINUED_NETPROFIT;
    private BigDecimal PARENT_NETPROFIT;//归属于母公司股东的净利润
    private BigDecimal MINORITY_INTEREST;//少数股东损益
    private BigDecimal DEDUCT_PARENT_NETPROFIT;//扣除非经常性损益后的净利润
    private BigDecimal NETPROFIT_OTHER;
    private BigDecimal NETPROFIT_BALANCE;
    private BigDecimal BASIC_EPS;//基本每股收益
    private BigDecimal DILUTED_EPS;//稀释每股收益
    private BigDecimal OTHER_COMPRE_INCOME;//其他综合收益
    private BigDecimal PARENT_OCI;//归属于母公司股东的其他综合收益
    private BigDecimal MINORITY_OCI;//归属于少数股东的其他综合收益
    private BigDecimal PARENT_OCI_OTHER;
    private BigDecimal PARENT_OCI_BALANCE;
    private BigDecimal UNABLE_OCI;
    private BigDecimal CREDITRISK_FAIRVALUE_CHANGE;
    private BigDecimal OTHERRIGHT_FAIRVALUE_CHANGE;
    private BigDecimal SETUP_PROFIT_CHANGE;
    private BigDecimal RIGHTLAW_UNABLE_OCI;
    private BigDecimal UNABLE_OCI_OTHER;
    private BigDecimal UNABLE_OCI_BALANCE;
    private BigDecimal ABLE_OCI;
    private BigDecimal RIGHTLAW_ABLE_OCI;
    private BigDecimal AFA_FAIRVALUE_CHANGE;
    private BigDecimal HMI_AFA;
    private BigDecimal CASHFLOW_HEDGE_VALID;
    private BigDecimal CREDITOR_FAIRVALUE_CHANGE;
    private BigDecimal CREDITOR_IMPAIRMENT_RESERVE;
    private BigDecimal FINANCE_OCI_AMT;
    private BigDecimal CONVERT_DIFF;
    private BigDecimal ABLE_OCI_OTHER;
    private BigDecimal ABLE_OCI_BALANCE;
    private BigDecimal OCI_OTHER;
    private BigDecimal OCI_BALANCE;
    private BigDecimal TOTAL_COMPRE_INCOME;//综合收益总额
    private BigDecimal PARENT_TCI;//归属于母公司股东的综合收益总额
    private BigDecimal MINORITY_TCI;//归属于少数股东的综合收益总额
    private BigDecimal PRECOMBINE_TCI;
    private BigDecimal EFFECT_TCI_BALANCE;
    private BigDecimal TCI_OTHER;
    private BigDecimal TCI_BALANCE;
    private BigDecimal ACF_END_INCOME;
}