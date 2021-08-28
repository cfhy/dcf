package com.yyb.entity.xueqiu;

import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资产负债表
 */
@Data
public class BalanceSheetItemEntity {
    //报告期：如果是2019年的年报，则为2019-12-31，值示例：1609344000000
    private Long report_date;
    //年报名称，值示例：2020年报
    private String report_name;
    //公布时间
    private Long ctime;
    //总资产
    private List<BigDecimal> total_assets;
    //总负债
    private List<BigDecimal> total_liab;
    //资产负债率
    private List<BigDecimal> asset_liab_ratio;
    //归属于母公司股东权益合计
    private List<BigDecimal> total_quity_atsopc;
    @Column(name = "交易性金融资产")
    private List<BigDecimal> tradable_fnncl_assets;
    @Column(name = "其中：应收利息")
    private List<BigDecimal> interest_receivable;
    //可供出售金融资产
    private List<BigDecimal> saleable_finacial_assets;
    //持有至到期投资
    private List<BigDecimal> held_to_maturity_invest;
    //固定资产
    private List<BigDecimal> fixed_asset;
    //无形资产
    private List<BigDecimal> intangible_assets;
    //在建工程
    private List<BigDecimal> construction_in_process;
    //递延所得税资产
    private List<BigDecimal> dt_assets;
    //交易性金融负债
    private List<BigDecimal> tradable_fnncl_liab;
    //应付职工薪酬
    private List<BigDecimal> payroll_payable;
    //应交税费
    private List<BigDecimal> tax_payable;
    //预计负债
    private List<BigDecimal> estimated_liab;
    //递延所得税负债
    private List<BigDecimal> dt_liab;
    //应付债券
    private List<BigDecimal> bond_payable;
    //总股本
    private List<BigDecimal> shares;
    //资本公积
    private List<BigDecimal> capital_reserve;
    //盈余公积
    private List<BigDecimal> earned_surplus;
    //未分配利润
    private List<BigDecimal> undstrbtd_profit;
    //少数股东权益
    private List<BigDecimal> minority_equity;
    //股东权益合计
    private List<BigDecimal> total_holders_equity;
    //负债和股东权益总计
    private List<BigDecimal> total_liab_and_holders_equity;
    //长期股权投资
    private List<BigDecimal> lt_equity_invest;
    //衍生金融负债
    private List<BigDecimal> derivative_fnncl_liab;
    //一般风险准备
    private List<BigDecimal> general_risk_provision;
    //外币报表折算差额
    private List<BigDecimal> frgn_currency_convert_diff;
    //商誉
    private List<BigDecimal> goodwill;
    //投资性房地产
    private List<BigDecimal> invest_property;
    //应付利息
    private List<BigDecimal> interest_payable;
    //库存股
    private List<BigDecimal> treasury_stock;
    //其他综合收益
    private List<BigDecimal> othr_compre_income;
    //其他权益工具投资
    private List<BigDecimal> othr_equity_instruments;
    @Column(name = "货币资金")
    private List<BigDecimal> currency_funds;
    @Column(name = "结算备付金")
    private List<BigDecimal> settle_reserves;
    @Column(name = "拆出资金")
    private List<BigDecimal> lending_fund;
    @Column(name = "衍生金融资产")
    private List<BigDecimal> derivative_fnncl_assets;
    @Column(name = "应收保费")
    private List<BigDecimal> premium_receivable;
    @Column(name = "应收分保账款")
    private List<BigDecimal> rein_account_receivable;
    @Column(name = "应收分保合同准备金")
    private List<BigDecimal> rein_contract_reserve;
    @Column(name = "保户质押贷款")
    private List<BigDecimal> assured_pledge_loan;
    @Column(name = "保户储金及投资款")
    private List<BigDecimal> assured_saving_and_invest;
    @Column(name = "发放贷款和垫款")
    private List<BigDecimal> disbursement_loan_and_advance;
    @Column(name = "定期存款")
    private List<BigDecimal> fixed_deposit;

    private List<BigDecimal> receivable_invest;
    //存出资本保证金
    private List<BigDecimal> paid_capital_deposit;
    //其他资产
    private List<BigDecimal> othr_assets;
    //独立账户资产
    private List<BigDecimal> separate_account;
    //
    private List<BigDecimal> asset_si;
    //同业及其他金融机构存放款项
    private List<BigDecimal> interbank_deposit_etc;
    //拆入资金
    private List<BigDecimal> borrowing_funds;
    @Column(name = "买入返售金融资产")
    private List<BigDecimal> buy_resale_fnncl_assets;
    //卖出回购金融资产款
    private List<BigDecimal> fnncl_assets_sold_for_repur;
    //吸收存款
    private List<BigDecimal> savings_absorption;
    //
    private List<BigDecimal> acting_td_sec;
    //预付保费
    private List<BigDecimal> advance_premium;
    //应付手续费及佣金
    private List<BigDecimal> charge_and_commi_payable;
    //应付分保账款
    private List<BigDecimal> rein_payable;
    //应付赔付款
    private List<BigDecimal> claim_payable;
    //应付保单红利
    private List<BigDecimal> dvdnd_payable_for_the_insured;
    //
    private List<BigDecimal> insurance_contract_reserve;
    //
    private List<BigDecimal> independent_account_liab;
    //
    private List<BigDecimal> amortized_cost_fnncl_assets;
    //
    private List<BigDecimal> fv_chg_income_fnncl_assets;
    //
    private List<BigDecimal> rein_undue_liability_reserve;
    //
    private List<BigDecimal> receivable_rein_duty_reserve;
    //
    private List<BigDecimal> received_deposit;
    //
    private List<BigDecimal> unearned_premium_reserve;
    //
    private List<BigDecimal> life_insurance_reserve;
    //
    private List<BigDecimal> lt_health_insurance_reserve;
    //
    private List<BigDecimal> lt_staff_salary_payable;
    @Column(name = "应收票据")
    private List<BigDecimal> bills_receivable;
    @Column(name = "应收账款")
    private List<BigDecimal> account_receivable;
    //    @Column(name = "应收款项融资")
//    private List<BigDecimal> finance_rece;
    @Column(name = "预付款项")
    private List<BigDecimal> pre_payment;
    @Column(name = "应收股利")
    private List<BigDecimal> dividend_receivable;
    //其他应收款
    @Column(name = "其他应收款合计")
    private List<BigDecimal> othr_receivables;
    @Column(name = "存货")
    private List<BigDecimal> inventory;
    @Column(name = "一年内到期的非流动资产")
    private List<BigDecimal> nca_due_within_one_year;
    @Column(name = "其他流动资产")
    private List<BigDecimal> othr_current_assets;
    //流动资产-发放贷款及垫款
    @Column(name = "发放贷款和垫款")
    private List<BigDecimal> current_assets_si;
    @Column(name = "流动资产合计")
    private List<BigDecimal> total_current_assets;
    //长期应收款
    private List<BigDecimal> lt_receivable;
    //开发支出
    private List<BigDecimal> dev_expenditure;
    // 长期待摊费用
    private List<BigDecimal> lt_deferred_expense;
    // 其他非流动资产
    private List<BigDecimal> othr_noncurrent_assets;
    // 非流动资产-发放贷款及垫款
    private List<BigDecimal> noncurrent_assets_si;
    // 非流动资产合计
    private List<BigDecimal> total_noncurrent_assets;
    // 短期借款
    private List<BigDecimal> st_loan;
    // 应付票据
    private List<BigDecimal> bill_payable;
    // 应付账款
    private List<BigDecimal> accounts_payable;
    //预收款项
    private List<BigDecimal> pre_receivable;
    // 应付股利
    private List<BigDecimal> dividend_payable;
    // 其他应付款
    private List<BigDecimal> othr_payables;
    //一年内到期的非流动负债
    private List<BigDecimal> noncurrent_liab_due_in1y;
    //todo
    private List<BigDecimal> current_liab_si;
    //流动负债合计
    private List<BigDecimal> total_current_liab;
    //长期借款
    private List<BigDecimal> lt_loan;
    //长期应付款
    private List<BigDecimal> lt_payable;
    //专项应付款
    private List<BigDecimal> special_payable;
    //其他非流动负债
    private List<BigDecimal> othr_non_current_liab;
    //todo
    private List<BigDecimal> noncurrent_liab_si;
    //非流动负债合计
    private List<BigDecimal> total_noncurrent_liab;
    //todo
    private List<BigDecimal> salable_financial_assets;
    //其他流动负债
    private List<BigDecimal> othr_current_liab;
    //应收票据及应收账款
    private List<BigDecimal> ar_and_br;
    @Column(name = "合同资产")
    private List<BigDecimal> contractual_assets;
    //持有待售资产

    //应付票据及应付账款
    private List<BigDecimal> bp_and_ap;
    //合同负债
    private List<BigDecimal> contract_liabilities;
    //划分为持有待售的资产
    private List<BigDecimal> to_sale_asset;
    //其他权益工具投资
    private List<BigDecimal> other_eq_ins_invest;
    //其他非流动金融资产
    private List<BigDecimal> other_illiquid_fnncl_assets;
    //固定资产合计
    private List<BigDecimal> fixed_asset_sum;
    //固定资产清理
    private List<BigDecimal> fixed_assets_disposal;
    //在建工程合计
    private List<BigDecimal> construction_in_process_sum;
    //工程物资
    private List<BigDecimal> project_goods_and_material;
    //生产性生物资产
    private List<BigDecimal> productive_biological_assets;
    //油气资产
    private List<BigDecimal> oil_and_gas_asset;
    //划分为持有待售的负债
    private List<BigDecimal> to_sale_debt;
    //长期应付款合计
    private List<BigDecimal> lt_payable_sum;
    //递延收益-非流动负债
    private List<BigDecimal> noncurrent_liab_di;
    //永续债
    private List<BigDecimal> perpetual_bond;
    //专项储备
    private List<BigDecimal> special_reserve;
}
