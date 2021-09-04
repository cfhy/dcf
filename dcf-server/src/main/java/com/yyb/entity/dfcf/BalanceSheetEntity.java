package com.yyb.entity.dfcf;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class BalanceSheetEntity {
    @Column(name = "股票代码")
    private String SECUCODE;
    @Column(name = "股票名称")
    private String SECURITY_NAME_ABBR;
    @Column(name = "报告期")
    private String REPORT_DATE;
    private BigDecimal ACCEPT_DEPOSIT_INTERBANK;
    @Column(name = "应付账款")
    private BigDecimal ACCOUNTS_PAYABLE;//应付账款
    @Column(name = "应收账款")
    private BigDecimal ACCOUNTS_RECE; //应收账款

    private BigDecimal ACCRUED_EXPENS;

    @Column(name = "预收款项")
    private BigDecimal ADVANCE_RECEIVABLES;//预收账款
    @Column(name = "代理买卖证券款")
    private BigDecimal AGENT_TRADE_SECURITY;//代理买卖证券款
    @Column(name = "代理承销证券款")
    private BigDecimal AGENT_UNDERWRITE_SECURITY;//代理承销证券款

    private BigDecimal AMORTIZE_COST_FINASSET;
    private BigDecimal AMORTIZE_COST_FINLIAB;
    private BigDecimal AMORTIZE_COST_NCFINASSET;
    private BigDecimal AMORTIZE_COST_NCFINLIAB;
    private BigDecimal APPOINT_FVTPL_FINASSET;
    private BigDecimal APPOINT_FVTPL_FINLIAB;
    private BigDecimal ASSET_BALANCE;
    private BigDecimal ASSET_OTHER;
    private BigDecimal ASSIGN_CASH_DIVIDEND;
    @Column(name = "可供出售金融资产")
    private BigDecimal AVAILABLE_SALE_FINASSET;//可供出售金融资产
    @Column(name = "应付债券")
    private BigDecimal BOND_PAYABLE;//应付债券

    @Column(name = "拆入资金")
    private BigDecimal BORROW_FUND;//拆入资金
    @Column(name = "买入返售金融资产")
    private BigDecimal BUY_RESALE_FINASSET;//买入返售金融资产
    @Column(name = "资本公积")
    private BigDecimal CAPITAL_RESERVE; //资本公积
    @Column(name = "在建工程")
    private BigDecimal CIP;//在建工程

    private BigDecimal CONSUMPTIVE_BIOLOGICAL_ASSET;//消耗性生物资产
    @Column(name = "合同资产")
    private BigDecimal CONTRACT_ASSET;//合同资产
    @Column(name = "合同负债")
    private BigDecimal CONTRACT_LIAB;//合同负债

    private BigDecimal CONVERT_DIFF;
    @Column(name = "债权投资")
    private BigDecimal CREDITOR_INVEST;//债权投资

    private BigDecimal CURRENT_ASSET_BALANCE;

    private BigDecimal CURRENT_ASSET_OTHER;//流动资产其他项目

    private BigDecimal CURRENT_LIAB_BALANCE;

    private BigDecimal CURRENT_LIAB_OTHER;//流动负债其他项目
    @Column(name = "递延收益")
    private BigDecimal DEFER_INCOME;//递延收益

    private BigDecimal DEFER_INCOME_1YEAR;

    @Column(name = "递延所得税资产")
    private BigDecimal DEFER_TAX_ASSET;//递延所得税资产
    @Column(name = "递延所得税负债")
    private BigDecimal DEFER_TAX_LIAB;//递延所得税负债
    @Column(name = "衍生金融资产")
    private BigDecimal DERIVE_FINASSET;//衍生金融资产
    @Column(name = "衍生金融负债")
    private BigDecimal DERIVE_FINLIAB;//衍生金融负债
    @Column(name = "开发支出")
    private BigDecimal DEVELOP_EXPENSE;//开发支出
    private BigDecimal DIV_HOLDSALE_ASSET;
    private BigDecimal DIV_HOLDSALE_LIAB;
    @Column(name = "应付股利")
    private BigDecimal DIVIDEND_PAYABLE;//应付股利
    @Column(name = "应收股利")
    private BigDecimal DIVIDEND_RECE;//应收股利
    private BigDecimal EQUITY_BALANCE;
    private BigDecimal EQUITY_OTHER;
    private BigDecimal EXPORT_REFUND_RECE;
    @Column(name = "应付手续费及佣金")
    private BigDecimal FEE_COMMISSION_PAYABLE;//应付手续费及佣金
    private BigDecimal FIN_FUND;
    @Column(name = "应收款项融资")
    private BigDecimal FINANCE_RECE;//应收款项融资
    @Column(name = "固定资产")
    private BigDecimal FIXED_ASSET;//固定资产
    @Column(name = "固定资产清理")
    private BigDecimal FIXED_ASSET_DISPOSAL;//固定资产清理
    private BigDecimal FVTOCI_FINASSET;//以公允价值计量且其变动计入其他综合收益
    private BigDecimal FVTOCI_NCFINASSET;
    @Column(name = "以公允价值计量且其变动计入当期损益的金融资产")
    private BigDecimal FVTPL_FINASSET;//以公允价值计量且其变动计入当期损益的金融资产
    @Column(name = "以公允价值计量且其变动计入当期损益的金融负债")
    private BigDecimal FVTPL_FINLIAB;//以公允价值计量且其变动计入当期损益的金融负债
    @Column(name = "一般风险准备")
    private BigDecimal GENERAL_RISK_RESERVE;//一般风险准备
    @Column(name = "商誉")
    private BigDecimal GOODWILL;//商誉
    @Column(name = "持有至到期投资")
    private BigDecimal HOLD_MATURITY_INVEST;//持有至到期投资
    @Column(name = "持有待售资产")
    private BigDecimal HOLDSALE_ASSET;//持有待售资产
    @Column(name = "持有待售负债")
    private BigDecimal HOLDSALE_LIAB;//持有待售负债
    @Column(name = "保险合同准备金")
    private BigDecimal INSURANCE_CONTRACT_RESERVE;//保险合同准备金
    private BigDecimal INSURED_DEPOSIT_INVEST;//保户储金及投资款
    private BigDecimal INSURED_PLEDGE_LOAN;//保户质押贷款
    @Column(name = "无形资产")
    private BigDecimal INTANGIBLE_ASSET;//无形资产
    @Column(name = "其中：应付利息")
    private BigDecimal INTEREST_PAYABLE;//应付利息
    @Column(name = "其中：应收利息")
    private BigDecimal INTEREST_RECE;//应收利息
    private BigDecimal INTERNAL_PAYABLE;
    private BigDecimal INTERNAL_RECE;
    @Column(name = "存货")
    private BigDecimal INVENTORY;//存货
    @Column(name = "投资性房地产")
    private BigDecimal INVEST_REALESTATE;//投资性房地产
    @Column(name = "租赁负债")
    private BigDecimal LEASE_LIAB;//租赁负债
    @Column(name = "拆出资金")
    private BigDecimal LEND_FUND;//拆出资金
    private BigDecimal LIAB_BALANCE;
    private BigDecimal LIAB_EQUITY_BALANCE;
    private BigDecimal LIAB_EQUITY_OTHER;
    private BigDecimal LIAB_OTHER;
    @Column(name = "发放贷款和垫款")
    private BigDecimal LOAN_ADVANCE;//发放贷款及垫款
    @Column(name = "向中央银行借款")
    private BigDecimal LOAN_PBC;//向中央银行借款
    @Column(name = "吸收存款及同业存放")
    private BigDecimal IOFI_DEPOSIT;//吸收存款及同业存放
    @Column(name = "长期股权投资")
    private BigDecimal LONG_EQUITY_INVEST;//长期股权投资
    @Column(name = "长期借款")
    private BigDecimal LONG_LOAN;//长期借款
    @Column(name = "长期应付款")
    private BigDecimal LONG_PAYABLE;//长期应付款
    @Column(name = "长期待摊费用")
    private BigDecimal LONG_PREPAID_EXPENSE;//长期待摊费用
    @Column(name = "长期应收款")
    private BigDecimal LONG_RECE;//长期应收款
    @Column(name = "长期应付职工薪酬")
    private BigDecimal LONG_STAFFSALARY_PAYABLE;//长期应付职工薪酬
    @Column(name = "少数股东权益")
    private BigDecimal MINORITY_EQUITY;//少数股东权益
    @Column(name = "货币资金")
    private BigDecimal MONETARYFUNDS;//货币资金
    @Column(name = "一年内到期的非流动资产")
    private BigDecimal NONCURRENT_ASSET_1YEAR;//一年内到期的非流动资产
    private BigDecimal NONCURRENT_ASSET_BALANCE;
    private BigDecimal NONCURRENT_ASSET_OTHER;
    @Column(name = "一年内到期的非流动负债")
    private BigDecimal NONCURRENT_LIAB_1YEAR;//一年内到期的非流动负债
    private BigDecimal NONCURRENT_LIAB_BALANCE;
    private BigDecimal NONCURRENT_LIAB_OTHER;
    @Column(name = "应付票据及应付账款")
    private BigDecimal NOTE_ACCOUNTS_PAYABLE;//应付票据及应付账款
    @Column(name = "应收票据及应收账款")
    private BigDecimal NOTE_ACCOUNTS_RECE;//应收票据及应收账款
    @Column(name = "其中：应付票据")
    private BigDecimal NOTE_PAYABLE;//其中:应付票据
    @Column(name = "应收票据")
    private BigDecimal NOTE_RECE;//应收票据
    @Column(name = "油气资产")
    private BigDecimal OIL_GAS_ASSET;//油气资产
    @Column(name = "其他综合收益")
    private BigDecimal OTHER_COMPRE_INCOME;//其他综合收益
    @Column(name = "其他债权投资")
    private BigDecimal OTHER_CREDITOR_INVEST;//其他债权投资
    @Column(name = "其他流动资产")
    private BigDecimal OTHER_CURRENT_ASSET;//其他流动资产
    @Column(name = "其他流动负债")
    private BigDecimal OTHER_CURRENT_LIAB;//其他流动负债
    @Column(name = "其他权益工具投资")
    private BigDecimal OTHER_EQUITY_INVEST;//其他权益工具投资
    private BigDecimal OTHER_EQUITY_OTHER;
    @Column(name = "其他权益工具")
    private BigDecimal OTHER_EQUITY_TOOL;//其他权益工具
    @Column(name = "其他非流动资产")
    private BigDecimal OTHER_NONCURRENT_ASSET;//其他非流动资产
    @Column(name = "其他非流动金融资产")
    private BigDecimal OTHER_NONCURRENT_FINASSET;//其他非流动金融资产
    @Column(name = "其他非流动负债")
    private BigDecimal OTHER_NONCURRENT_LIAB;//其他非流动负债
    @Column(name = "其他应付款")
    private BigDecimal OTHER_PAYABLE;//其他应付款
    @Column(name = "其他应收款")
    private BigDecimal OTHER_RECE;//其他应收款
    private BigDecimal PARENT_EQUITY_BALANCE;
    private BigDecimal PARENT_EQUITY_OTHER;
    @Column(name = "永续债")
    private BigDecimal PERPETUAL_BOND;//永续债
    @Column(name = "应付债券-永续债")
    private BigDecimal PERPETUAL_BOND_PAYBALE;//应付债券-永续债
    private BigDecimal PREDICT_CURRENT_LIAB;
    @Column(name = "预计负债")
    private BigDecimal PREDICT_LIAB;//预计负债
    @Column(name = "优先股")
    private BigDecimal PREFERRED_SHARES;//优先股
    @Column(name = "应付债券-优先股")
    private BigDecimal PREFERRED_SHARES_PAYBALE;//应付债券-优先股
    @Column(name = "应收保费")
    private BigDecimal PREMIUM_RECE;//应收保费
    @Column(name = "预付款项")
    private BigDecimal PREPAYMENT;//预付款项
    @Column(name = "生产性生物资产")
    private BigDecimal PRODUCTIVE_BIOLOGY_ASSET;//生产性生物资产
    @Column(name = "工程物资")
    private BigDecimal PROJECT_MATERIAL;//工程物资
    @Column(name = "应收分保合同准备金")
    private BigDecimal RC_RESERVE_RECE;//应收分保合同准备金
    @Column(name = "应付分保账款")
    private BigDecimal REINSURE_PAYABLE;//应付分保账款
    @Column(name = "应收分保账款")
    private BigDecimal REINSURE_RECE;//应收分保账款
    @Column(name = "卖出回购金融资产款")
    private BigDecimal SELL_REPO_FINASSET;//卖出回购金融资产款
    @Column(name = "结算备付金")
    private BigDecimal SETTLE_EXCESS_RESERVE;//结算备付金
    @Column(name = "实收资本（或股本）")
    private BigDecimal SHARE_CAPITAL;//股本
    private BigDecimal SHORT_BOND_PAYABLE;//短期应付债券
    private BigDecimal SHORT_FIN_PAYABLE;
    @Column(name = "短期借款")
    private BigDecimal SHORT_LOAN;//短期借款
    private BigDecimal SPECIAL_PAYABLE;//专项应付款
    @Column(name = "专项储备")
    private BigDecimal SPECIAL_RESERVE;//专项储备
    @Column(name = "应付职工薪酬")
    private BigDecimal STAFF_SALARY_PAYABLE;//应付职工薪酬
    private BigDecimal SUBSIDY_RECE;
    @Column(name = "盈余公积")
    private BigDecimal SURPLUS_RESERVE;//盈余公积
    @Column(name = "应交税费")
    private BigDecimal TAX_PAYABLE;//应交税费
    @Column(name = "资产合计")
    private BigDecimal TOTAL_ASSETS;//总资产
    @Column(name = "总资产增长率")
    private BigDecimal TOTAL_ASSETS_YOY;//总资产增长率
    @Column(name = "流动资产合计")
    private BigDecimal TOTAL_CURRENT_ASSETS;//流动资产合计
    @Column(name = "流动负债合计")
    private BigDecimal TOTAL_CURRENT_LIAB;//流动负债合计
    @Column(name = "所有者权益（或股东权益）合计")
    private BigDecimal TOTAL_EQUITY;//股东权益合计
    @Column(name = "负债和所有者权益（或股东权益）合计")
    private BigDecimal TOTAL_LIAB_EQUITY;//负债和股东权益总计
    @Column(name = "负债合计")
    private BigDecimal TOTAL_LIABILITIES;//负债合计
    @Column(name = "非流动资产合计")
    private BigDecimal TOTAL_NONCURRENT_ASSETS;//非流动资产合计
    @Column(name = "非流动负债合计")
    private BigDecimal TOTAL_NONCURRENT_LIAB;//非流动负债合计
    @Column(name = "其他应付款合计")
    private BigDecimal TOTAL_OTHER_PAYABLE;//其他应付款合计
    @Column(name = "其他应收款合计")
    private BigDecimal TOTAL_OTHER_RECE;//其他应收款合计
    @Column(name = "归属于母公司所有者权益合计")
    private BigDecimal TOTAL_PARENT_EQUITY;//归属于母公司股东权益总计
    private BigDecimal TRADE_FINASSET;//
    @Column(name = "交易性金融资产")
    private BigDecimal TRADE_FINASSET_NOTFVTPL;//交易性金融资产
    private BigDecimal TRADE_FINLIAB;
    @Column(name = "交易性金融负债")
    private BigDecimal TRADE_FINLIAB_NOTFVTPL;//交易性金融负债
    @Column(name = "库存股")
    private BigDecimal TREASURY_SHARES;//库存股
    @Column(name = "未分配利润")
    private BigDecimal UNASSIGN_RPOFIT;//未分配利润
    private BigDecimal UNCONFIRM_INVEST_LOSS;
    @Column(name = "使用权资产")
    private BigDecimal USERIGHT_ASSET;//使用权资产

    @Column(name = "审计意见")
    private String OPINION_TYPE;//审计意见(境内)
    private String OSOPINION_TYPE;
}
