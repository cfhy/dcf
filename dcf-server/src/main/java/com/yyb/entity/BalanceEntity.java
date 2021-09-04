package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资产负债表
 *
 * 1.新增与新金融工具准则有关的“交易性金融资产”、“债权投资”、“其他债权投资”、“其他权益工具投资”、“其他非流动金融资产”、
 * “交易性金融负债”、“合同资产”和“合同负债”项目。
 *
 * 同时删除“以公允价值计量且其变动计入当期损益的金融资产”、“可供出售金融资产”、“持有至到期投资”以及“以公允价值计量且其变动计入当期损益的金融负债”项目。
 *
 * 2.“合同取得成本”科目、“合同履约成本”科目、“应收退货成本”科目、“预计负债——应付退货款”科目按照其流动性在“其他流动资产”或“其他非流动资产”项目中列示。
 *
 * 3. 将原“应收票据及应收账款”项目拆分为“应收票据”和“应收账款”两个项目；
 *    将原“应付票据及应付账款”项目拆分为“应付票据”及“应付账款”两个项目。
 *
 * 4. “应收利息”及“应收股利”项目归并至“其他应收款”项目
 *
 * 5. “固定资产清理”项目归并至“固定资产”项目
 *
 * 6. “工程物资”项目归并至“在建工程”项目
 *
 * 8. “应付利息”及“应付股利”项目归并至“其他应付款”项目
 *
 * 9. “专项应付款”项目归并至“长期应付款”项目
 *
 * 10.“持有待售资产”行项目及“持有待售负债”行项目核算内容发生变化
 */
@Data
@TableName("balance")
public class BalanceEntity {

    @TableId(type = IdType.AUTO)
    private Integer balance_id;

    @Column(name = "股票代码")
    private String stock_code;
    @Column(name = "股票名称")
    private String stock_name;
    @Column(name = "报告期")
    private Integer year;

    //=======================================流动资产=============================================

    @Column(name = "货币资金")
    private BigDecimal currency_funds;

    @Column(name = "结算备付金")
    private BigDecimal settle_excess_reserve;

    @Column(name = "拆出资金")
    private BigDecimal funds_lent;

    @Column(name = "交易性金融资产")
    private BigDecimal tradable_fnncl_assets;

    @Column(name = "以公允价值计量且其变动计入当期损益的金融资产")
    private BigDecimal fvtpl_finassets;

    @Column(name = "衍生金融资产")
    private BigDecimal derive_finasset;

    //1. “应收票据”及“应收账款”项目归并至新增的“应收票据及应收账款”项目
    @Column(name = "应收票据及应收账款")
    private BigDecimal ar_and_br;

    @Column(name = "应收票据")
    private BigDecimal bills_receivable;

    @Column(name = "应收账款")
    private BigDecimal account_receivable;

    @Column(name = "应收款项融资")
    private BigDecimal finance_rece;

    @Column(name = "预付款项")
    private BigDecimal pre_payment;

    @Column(name = "应收保费")
    private BigDecimal premium_rece;

    @Column(name = "应收分保账款")
    private BigDecimal reinsure_rece;

    @Column(name = "应收分保合同准备金")
    private BigDecimal rc_reserve_rece;

    // “应收利息”及“应收股利”项目归并至“其他应收款”项目
    @Column(name = "其他应收款合计")
    private BigDecimal othr_receivables_total;

    @Column(name = "其中：应收利息")
    private BigDecimal interest_receivable;

    @Column(name = "其他应收款")
    private BigDecimal othr_receivables;

    @Column(name = "应收股利")
    private BigDecimal dividend_rece;

    @Column(name = "买入返售金融资产")
    private BigDecimal buy_resale_finasset;

    @Column(name = "存货")
    private BigDecimal inventory;

    @Column(name = "合同资产")
    private BigDecimal contractual_assets;

    @Column(name = "持有待售资产")
    private BigDecimal holdsale_asset;

    @Column(name = "一年内到期的非流动资产")
    private BigDecimal nca_due_within_one_year;

    @Column(name = "发放贷款和垫款")
    private BigDecimal loan_advance;

    @Column(name = "其他流动资产")
    private BigDecimal othr_current_assets;

    @Column(name = "流动资产合计")
    private BigDecimal total_current_assets;

    //=======================================非流动资产=============================================
    //发放贷款和垫款

    @Column(name = "债权投资")
    private BigDecimal creditor_invest;
    @Column(name = "持有至到期投资")
    private BigDecimal held_to_maturity_invest;

    @Column(name = "其他债权投资")
    private BigDecimal other_creditor_invest;

    @Column(name = "长期应收款")
    private BigDecimal long_rece;

    @Column(name = "长期股权投资")
    private BigDecimal lt_equity_invest;

    @Column(name = "其他权益工具投资")
    private BigDecimal othr_equity_instruments;

    @Column(name = "可供出售金融资产")
    private BigDecimal saleable_finacial_assets;

    @Column(name = "其他非流动金融资产")
    private BigDecimal other_illiquid_fnncl_assets;

    @Column(name = "投资性房地产")
    private BigDecimal invest_property;

    //3. “固定资产清理”项目归并至“固定资产”项目
    @Column(name = "固定资产")
    private BigDecimal fixed_asset;
    @Column(name = "固定资产清理")
    private BigDecimal fixed_assets_disposal;

    //4. “工程物资”项目归并至“在建工程”项目
    @Column(name = "在建工程")
    private BigDecimal construction_in_process;
    @Column(name = "工程物资")
    private BigDecimal project_goods_and_material;

    @Column(name = "生产性生物资产")
    private BigDecimal productive_biology_asset;

    @Column(name = "油气资产")
    private BigDecimal oil_gas_asset;

    @Column(name = "使用权资产")
    private BigDecimal useright_asset;

    @Column(name = "无形资产")
    private BigDecimal intangible_assets;

    @Column(name = "开发支出")
    private BigDecimal develop_expense;

    @Column(name = "商誉")
    private BigDecimal goodwill;

    @Column(name = "长期待摊费用")
    private BigDecimal lt_deferred_expense;

    @Column(name = "递延所得税资产")
    private BigDecimal dt_assets;

    @Column(name = "其他非流动资产")
    private BigDecimal othr_noncurrent_assets;

    @Column(name = "非流动资产合计")
    private BigDecimal total_noncurrent_assets;

    @Column(name = "资产合计")
    private BigDecimal total_assets;

    @Column(name = "总资产增长率")
    private BigDecimal total_assets_yoy;
    //=======================================非流动资产=============================================
    @Column(name = "短期借款")
    private BigDecimal st_loan;

    @Column(name = "向中央银行借款")
    private BigDecimal loan_pbc;

    @Column(name = "拆入资金")
    private BigDecimal borrow_fund;

    @Column(name = "交易性金融负债")
    private BigDecimal trade_finliab_notfvtpl;
    @Column(name = "以公允价值计量且其变动计入当期损益的金融负债")
    private BigDecimal fvtpl_finliab;

    @Column(name = "衍生金融负债")
    private BigDecimal derivative_fnncl_liab;

    @Column(name = "应付票据及应付账款")
    private BigDecimal bp_and_ap;

    @Column(name = "其中：应付票据")
    private BigDecimal bill_payable;

    @Column(name = "应付账款")
    private BigDecimal accounts_payable;

    @Column(name = "预收款项")
    private BigDecimal pre_receivable;

    @Column(name = "合同负债")
    private BigDecimal contract_liabilities;

    @Column(name = "卖出回购金融资产款")
    private BigDecimal sell_repo_finasset;

    @Column(name = "吸收存款及同业存放")
    private BigDecimal iofi_deposit;

    @Column(name = "代理买卖证券款")
    private BigDecimal agent_trade_security;

    @Column(name = "代理承销证券款")
    private BigDecimal agent_underwrite_security;

    @Column(name = "应付职工薪酬")
    private BigDecimal payroll_payable;

    @Column(name = "应交税费")
    private BigDecimal tax_payable;

    @Column(name = "其他应付款合计")
    private BigDecimal total_othr_payables;

    @Column(name = "其中：应付利息")
    private BigDecimal interest_payable;

    @Column(name = "应付股利")
    private BigDecimal dividend_payable;

    @Column(name = "其他应付款")
    private BigDecimal othr_payables;

    @Column(name = "应付手续费及佣金")
    private BigDecimal fee_commission_payable;

    @Column(name = "应付分保账款")
    private BigDecimal reinsure_payable;

    @Column(name = "持有待售负债")
    private BigDecimal holdsale_liab;

    @Column(name = "一年内到期的非流动负债")
    private BigDecimal noncurrent_liab_due_in1y;

    @Column(name = "其他流动负债")
    private BigDecimal othr_current_liab;

    @Column(name = "流动负债合计")
    private BigDecimal total_current_liab;

    //=======================================非流动负债=============================================
    @Column(name = "保险合同准备金")
    private BigDecimal insurance_contract_reserve;

    @Column(name = "长期借款")
    private BigDecimal lt_loan;

    @Column(name = "应付债券")
    private BigDecimal bond_payable;

    @Column(name = "应付债券-优先股")
    private BigDecimal preferred_shares_paybale;

    @Column(name = "应付债券-永续债")
    private BigDecimal perpetual_bond_paybale;

    @Column(name = "租赁负债")
    private BigDecimal lease_liab;

    @Column(name = "长期应付款")
    private BigDecimal lt_payable;

    @Column(name = "长期应付职工薪酬")
    private BigDecimal long_staffsalary_payable;

    @Column(name = "预计负债")
    private BigDecimal estimated_liab;

    @Column(name = "递延收益")
    private BigDecimal defer_income;

    @Column(name = "递延所得税负债")
    private BigDecimal defer_tax_liab;

    @Column(name = "其他非流动负债")
    private BigDecimal othr_non_current_liab;

    @Column(name = "非流动负债合计")
    private BigDecimal total_noncurrent_liab;

    @Column(name = "负债合计")
    private BigDecimal total_liab;

    //=======================================所有者权益=============================================
    @Column(name = "实收资本（或股本）")
    private BigDecimal shares;

    @Column(name = "其他权益工具")
    private BigDecimal other_equity_tool;

    @Column(name = "优先股")
    private BigDecimal preferred_shares;

    @Column(name = "永续债")
    private BigDecimal perpetual_bond;

    @Column(name = "资本公积")
    private BigDecimal capital_reserve;

    @Column(name = "库存股")
    private BigDecimal treasury_shares;

    @Column(name = "其他综合收益")
    private BigDecimal othr_compre_income;

    @Column(name = "专项储备")
    private BigDecimal special_reserve;

    @Column(name = "盈余公积")
    private BigDecimal earned_surplus;

    @Column(name = "一般风险准备")
    private BigDecimal general_risk_reserve;

    @Column(name = "未分配利润")
    private BigDecimal undstrbtd_profit;

    @Column(name = "归属于母公司所有者权益合计")
    private BigDecimal total_quity_atsopc;

    @Column(name = "少数股东权益")
    private BigDecimal minority_equity;

    @Column(name = "所有者权益（或股东权益）合计")
    private BigDecimal total_holders_equity;

    @Column(name = "负债和所有者权益（或股东权益）合计")
    private BigDecimal total_liab_and_holders_equity;

    @Column(name = "审计意见")
    private String opinion_type;









}
