package com.yyb.entity.tonghuashun;

import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 资产负债表
 */
@Data
public class BalanceSheetEntity {
    //年份
    @Column(name = "科目\\时间")
    private Integer year;

    @Column(name = "货币资金(元)")
    private BigDecimal currency_funds;

    @Column(name = "拆出资金(元)")
    private BigDecimal funds_lent;

    @Column(name = "交易性金融资产(元)")
    private BigDecimal tradable_fnncl_assets;

    @Column(name = "应收票据及应收账款(元)")
    private BigDecimal ar_and_br;

    @Column(name = "其中：应收票据(元)")
    private BigDecimal bills_receivable;

    @Column(name = "应收账款(元)")
    private BigDecimal account_receivable;

    @Column(name = "预付款项(元)")
    private BigDecimal pre_payment;

    @Column(name = "其他应收款合计(元)")
    private BigDecimal othr_receivables_total;

    @Column(name = "其中：应收利息(元)")
    private BigDecimal interest_receivable;

    @Column(name = "其他应收款(元)")
    private BigDecimal othr_receivables;

    @Column(name = "存货(元)")
    private BigDecimal inventory;

    @Column(name = "一年内到期的非流动资产(元)")
    private BigDecimal nca_due_within_one_year;

    @Column(name = "其他流动资产(元)")
    private BigDecimal othr_current_assets;

    @Column(name = "总现金(元)")
    private BigDecimal total_cash;

    @Column(name = "流动资产合计(元)")
    private BigDecimal total_current_assets;

    @Column(name = "可供出售金融资产(元)")
    private BigDecimal saleable_finacial_assets;

    @Column(name = "持有至到期投资(元)")
    private BigDecimal held_to_maturity_invest;

    @Column(name = "长期股权投资(元)")
    private BigDecimal lt_equity_invest;

    @Column(name = "其他权益工具投资(元)")
    private BigDecimal othr_equity_instruments;

    @Column(name = "其他非流动金融资产(元)")
    private BigDecimal other_illiquid_fnncl_assets;

    @Column(name = "投资性房地产(元)")
    private BigDecimal invest_property;

    @Column(name = "固定资产合计(元)")
    private BigDecimal fixed_asset_sum;

    @Column(name = "其中：固定资产(元)")
    private BigDecimal fixed_asset;

    @Column(name = "固定资产清理(元)")
    private BigDecimal fixed_assets_disposal;

    @Column(name = "在建工程合计(元)")
    private BigDecimal construction_in_process_sum;

    @Column(name = "其中：在建工程(元)")
    private BigDecimal construction_in_process;

    @Column(name = "工程物资(元)")
    private BigDecimal project_goods_and_material;

    @Column(name = "无形资产(元)")
    private BigDecimal intangible_assets;

    @Column(name = "商誉(元)")
    private BigDecimal goodwill;

    @Column(name = "长期待摊费用(元)")
    private BigDecimal lt_deferred_expense;

    @Column(name = "递延所得税资产(元)")
    private BigDecimal dt_assets;

    @Column(name = "其他非流动资产(元)")
    private BigDecimal othr_noncurrent_assets;

    @Column(name = "非流动资产合计(元)")
    private BigDecimal total_noncurrent_assets;

    @Column(name = "资产合计(元)")
    private BigDecimal total_assets;

    @Column(name = "短期借款(元)")
    private BigDecimal st_loan;

    @Column(name = "以公允价值计量且其变动计入当期损益的金融负债(元)")
    private BigDecimal fnncl_liab_profit_and_loss_gyjzjl;

    @Column(name = "衍生金融负债(元)")
    private BigDecimal derivative_fnncl_liab;

    @Column(name = "应付票据及应付账款(元)")
    private BigDecimal bp_and_ap;

    @Column(name = "其中：应付票据(元)")
    private BigDecimal bill_payable;

    @Column(name = "应付账款(元)")
    private BigDecimal accounts_payable;

    @Column(name = "预收款项(元)")
    private BigDecimal pre_receivable;

    @Column(name = "合同负债(元)")
    private BigDecimal contract_liabilities;

    @Column(name = "应付职工薪酬(元)")
    private BigDecimal payroll_payable;

    @Column(name = "应交税费(元)")
    private BigDecimal tax_payable;

    @Column(name = "其他应付款合计(元)")
    private BigDecimal total_othr_payables;

    @Column(name = "其中：应付利息(元)")
    private BigDecimal interest_payable;

    @Column(name = "应付股利(元)")
    private BigDecimal dividend_payable;

    @Column(name = "其他应付款(元)")
    private BigDecimal othr_payables;

    @Column(name = "一年内到期的非流动负债(元)")
    private BigDecimal noncurrent_liab_due_in1y;

    @Column(name = "其他流动负债(元)")
    private BigDecimal othr_current_liab;

    @Column(name = "流动负债合计(元)")
    private BigDecimal total_current_liab;

    @Column(name = "应付债券(元)")
    private BigDecimal bond_payable;

    @Column(name = "长期借款(元)")
    private BigDecimal lt_loan;

    @Column(name = "长期应付款合计(元)")
    private BigDecimal lt_payable_sum;

    @Column(name = "其中：长期应付款(元)")
    private BigDecimal lt_payable;

    @Column(name = "预计负债(元)")
    private BigDecimal estimated_liab;

    @Column(name = "递延收益-非流动负债(元)")
    private BigDecimal noncurrent_liab_di;

    @Column(name = "递延所得税负债(元)")
    private BigDecimal dt_liab;

    @Column(name = "其他非流动负债(元)")
    private BigDecimal othr_non_current_liab;

    @Column(name = "非流动负债合计(元)")
    private BigDecimal total_noncurrent_liab;

    @Column(name = "负债合计(元)")
    private BigDecimal total_liab;

    @Column(name = "所有者权益（或股东权益）(元)")
    private BigDecimal holders_equity;

    @Column(name = "实收资本（或股本）(元)")
    private BigDecimal shares;

    @Column(name = "资本公积(元)")
    private BigDecimal capital_reserve;

    @Column(name = "其他综合收益(元)")
    private BigDecimal othr_compre_income;

    @Column(name = "盈余公积(元)")
    private BigDecimal earned_surplus;

    @Column(name = "未分配利润(元)")
    private BigDecimal undstrbtd_profit;

    @Column(name = "归属于母公司所有者权益合计(元)")
    private BigDecimal total_quity_atsopc;

    @Column(name = "少数股东权益(元)")
    private BigDecimal minority_equity;

    @Column(name = "所有者权益（或股东权益）合计(元)")
    private BigDecimal total_holders_equity;

    @Column(name = "负债和所有者权益（或股东权益）合计(元)")
    private BigDecimal total_liab_and_holders_equity;
}
