package com.yyb.entity.dfcf;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 现金流量表
 */
@Data
public class CashFlowSheetEntity {
   private String ECURITY_CODE;
    private String REPORT_DATE;
    private BigDecimal SALES_SERVICES;//销售商品、提供劳务收到的现金
    private BigDecimal DEPOSIT_INTERBANK_ADD; //客户存款和同业存放款项净增加额 : 6676507.46,
    private BigDecimal LOAN_PBC_ADD;
    private BigDecimal OFI_BF_ADD;
    private BigDecimal RECEIVE_ORIGIC_PREMIUM;
    private BigDecimal RECEIVE_REINSURE_NET;
    private BigDecimal INSURED_INVEST_ADD;
    private BigDecimal DISPOSAL_TFA_ADD;
    private BigDecimal RECEIVE_INTEREST_COMMISSION;//收取利息、手续费及佣金的现金
    private BigDecimal BORROW_FUND_ADD;
    private BigDecimal LOAN_ADVANCE_REDUCE;
    private BigDecimal REPO_BUSINESS_ADD;
    private BigDecimal RECEIVE_TAX_REFUND;
    private BigDecimal RECEIVE_OTHER_OPERATE;//收到其他与经营活动有关的现金
    private BigDecimal OPERATE_INFLOW_OTHER;
    private BigDecimal OPERATE_INFLOW_BALANCE;
    private BigDecimal TOTAL_OPERATE_INFLOW;//经营活动现金流入小计
    private BigDecimal BUY_SERVICES;//购买商品、接受劳务支付的现金
    private BigDecimal LOAN_ADVANCE_ADD;//客户贷款及垫款净增加额
    private BigDecimal PBC_INTERBANK_ADD;//存放中央银行和同业款项净增加额
    private BigDecimal PAY_ORIGIC_COMPENSATE;
    private BigDecimal PAY_INTEREST_COMMISSION;//支付利息、手续费及佣金的现金
    private BigDecimal PAY_POLICY_BONUS;
    private BigDecimal PAY_STAFF_CASH;//支付给职工以及为职工支付的现金
    private BigDecimal PAY_ALL_TAX;//支付的各项税费
    private BigDecimal PAY_OTHER_OPERATE;//支付其他与经营活动有关的现金
    private BigDecimal OPERATE_OUTFLOW_OTHER;//经营活动现金流出的其他项目
    private BigDecimal OPERATE_OUTFLOW_BALANCE;
    private BigDecimal TOTAL_OPERATE_OUTFLOW;//经营活动现金流出小计
    private BigDecimal OPERATE_NETCASH_OTHER;
    private BigDecimal OPERATE_NETCASH_BALANCE;
    private BigDecimal NETCASH_OPERATE;//经营活动产生的现金流量净额
    private BigDecimal WITHDRAW_INVEST;//收回投资收到的现金
    private BigDecimal RECEIVE_INVEST_INCOME;//取得投资收益收到的现金
    private BigDecimal DISPOSAL_LONG_ASSET;//处置固定资产、无形资产和其他长期资产收回的现金净额
    private BigDecimal DISPOSAL_SUBSIDIARY_OTHER;//处置子公司及其他营业单位收到的现金
    private BigDecimal REDUCE_PLEDGE_TIMEDEPOSITS;
    private BigDecimal RECEIVE_OTHER_INVEST;
    private BigDecimal INVEST_INFLOW_OTHER;
    private BigDecimal INVEST_INFLOW_BALANCE;
    private BigDecimal TOTAL_INVEST_INFLOW;//投资活动现金流入小计
    private BigDecimal CONSTRUCT_LONG_ASSET;//购建固定资产、无形资产和其他长期资产支付的现金
    private BigDecimal INVEST_PAY_CASH;//投资支付的现金
    private BigDecimal PLEDGE_LOAN_ADD;
    private BigDecimal OBTAIN_SUBSIDIARY_OTHER;
    private BigDecimal ADD_PLEDGE_TIMEDEPOSITS;
    private BigDecimal PAY_OTHER_INVEST;//支付其他与投资活动有关的现金
    private BigDecimal INVEST_OUTFLOW_OTHER;
    private BigDecimal INVEST_OUTFLOW_BALANCE;
    private BigDecimal TOTAL_INVEST_OUTFLOW;//投资活动现金流出小计
    private BigDecimal INVEST_NETCASH_OTHER;
    private BigDecimal INVEST_NETCASH_BALANCE;
    private BigDecimal NETCASH_INVEST;//投资活动产生的现金流量净额
    private BigDecimal ACCEPT_INVEST_CASH;//吸收投资收到的现金
    private BigDecimal SUBSIDIARY_ACCEPT_INVEST;//其中:子公司吸收少数股东投资收到的现金
    private BigDecimal RECEIVE_LOAN_CASH;//取得借款收到的现金
    private BigDecimal ISSUE_BOND;
    private BigDecimal RECEIVE_OTHER_FINANCE;//收到的其他与筹资活动有关的现金
    private BigDecimal FINANCE_INFLOW_OTHER;
    private BigDecimal FINANCE_INFLOW_BALANCE;
    private BigDecimal TOTAL_FINANCE_INFLOW;//筹资活动现金流入小计
    private BigDecimal PAY_DEBT_CASH;//偿还债务所支付的现金
    private BigDecimal ASSIGN_DIVIDEND_PORFIT;//分配股利、利润或偿付利息支付的现金
    private BigDecimal SUBSIDIARY_PAY_DIVIDEND;//其中:子公司支付给少数股东的股利、利润
    private BigDecimal BUY_SUBSIDIARY_EQUITY;
    private BigDecimal PAY_OTHER_FINANCE;//支付的其他与筹资活动有关的现金
    private BigDecimal SUBSIDIARY_REDUCE_CASH;
    private BigDecimal FINANCE_OUTFLOW_OTHER;
    private BigDecimal FINANCE_OUTFLOW_BALANCE;
    private BigDecimal TOTAL_FINANCE_OUTFLOW;//筹资活动现金流出小计
    private BigDecimal FINANCE_NETCASH_OTHER;
    private BigDecimal FINANCE_NETCASH_BALANCE;
    private BigDecimal NETCASH_FINANCE;//筹资活动产生的现金流量净额
    private BigDecimal RATE_CHANGE_EFFECT;//汇率变动对现金及现金等价物的影响
    private BigDecimal CCE_ADD_OTHER;
    private BigDecimal CCE_ADD_BALANCE;
    private BigDecimal CCE_ADD;//现金及现金等价物净增加额
    private BigDecimal BEGIN_CCE;//加:期初现金及现金等价物余额
    private BigDecimal END_CCE_OTHER;
    private BigDecimal END_CCE_BALANCE;
    private BigDecimal END_CCE;//期末现金及现金等价物余额

    private BigDecimal NETPROFIT;//净利润
    private BigDecimal ASSET_IMPAIRMENT;//资产减值准备
    private BigDecimal FA_IR_DEPR;//固定资产和投资性房地产折旧
    private BigDecimal OILGAS_BIOLOGY_DEPR;//其中:固定资产折旧、油气资产折耗、生产性生物资产折旧
    private BigDecimal IR_DEPR;
    private BigDecimal IA_AMORTIZE;//无形资产摊销
    private BigDecimal LPE_AMORTIZE;//长期待摊费用摊销
    private BigDecimal DEFER_INCOME_AMORTIZE;
    private BigDecimal PREPAID_EXPENSE_REDUCE;
    private BigDecimal ACCRUED_EXPENSE_ADD;
    private BigDecimal DISPOSAL_LONGASSET_LOSS;//处置固定资产、无形资产和其他长期资产的损失
    private BigDecimal FA_SCRAP_LOSS;//固定资产报废损失
    private BigDecimal FAIRVALUE_CHANGE_LOSS;//公允价值变动损失
    private BigDecimal FINANCE_EXPENSE;//财务费用
    private BigDecimal INVEST_LOSS;//投资损失
    private BigDecimal DEFER_TAX;//递延所得税
    private BigDecimal DT_ASSET_REDUCE;//其中:递延所得税资产减少
    private BigDecimal DT_LIAB_ADD;//递延所得税负债增加
    private BigDecimal PREDICT_LIAB_ADD;
    private BigDecimal INVENTORY_REDUC;//存货的减少
    private BigDecimal OPERATE_RECE_REDUCE;//经营性应收项目的减少
    private BigDecimal OPERATE_PAYABLE_ADD;//经营性应付项目的增加
    private BigDecimal OTHER;//其他
    private BigDecimal OPERATE_NETCASH_OTHERNOTE;
    private BigDecimal OPERATE_NETCASH_BALANCENOTE;
    private BigDecimal NETCASH_OPERATENOTE;//经营活动产生的现金流量净额
    private BigDecimal DEBT_TRANSFER_CAPITAL;
    private BigDecimal CONVERT_BOND_1YEAR;
    private BigDecimal FINLEASE_OBTAIN_FA;
    private BigDecimal UNINVOLVE_INVESTFIN_OTHER;
    private BigDecimal END_CASH;//现金的期末余额
    private BigDecimal BEGIN_CASH;//减:现金的期初余额
    private BigDecimal END_CASH_EQUIVALENTS;
    private BigDecimal BEGIN_CASH_EQUIVALENTS;
    private BigDecimal CCE_ADD_OTHERNOTE;
    private BigDecimal CCE_ADD_BALANCENOTE;
    private BigDecimal CCE_ADDNOTE;//现金及现金等价物的净增加额
}
