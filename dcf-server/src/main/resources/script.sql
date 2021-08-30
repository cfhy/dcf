-- 股票表
CREATE TABLE stock (
    stock_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_name varchar (100) COMMENT '股票名称' ,
    stock_code varchar (20) COMMENT '股票代码',
    industry varchar (500) COMMENT '所在行业',
    netfit decimal (15, 2) COMMENT '净利润' ,
    total_revenue decimal (15, 2) COMMENT '总收入' ,
    total_assets decimal (15, 2) COMMENT '总资产' ,
    PRIMARY KEY (stock_id)
);

-- 机构预测的净利润
CREATE TABLE org_net_fit (
    net_fit_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_name varchar (100) COMMENT '股票名称' ,
    stock_code varchar (20) COMMENT '股票代码',
    year int comment '年份',
    netfit varchar (100) COMMENT '净利润' ,
    PRIMARY KEY (net_fit_id)
);

-- 资产负债表
CREATE TABLE balance (
    balance_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_code varchar(20) COMMENT '股票代码' ,
    year int comment '年份',

    currency_funds decimal (15, 2) COMMENT '货币资金' ,
    settle_excess_reserve decimal (15, 2) COMMENT '结算备付金' ,
    funds_lent decimal (15, 2) COMMENT '拆出资金' ,
    tradable_fnncl_assets decimal (15, 2) COMMENT '交易性金融资产' ,
    fvtpl_finassets decimal (15, 2) COMMENT '以公允价值计量且其变动计入当期损益的金融资产',
    derive_finasset decimal (15, 2) COMMENT '衍生金融资产' ,
    ar_and_br decimal (15, 2) COMMENT '应收票据及应收账款' ,
    bills_receivable decimal (15, 2) COMMENT '应收票据' ,
    account_receivable decimal (15, 2) COMMENT '应收账款' ,
    finance_rece decimal (15, 2) COMMENT '应收款项融资' ,
    pre_payment decimal (15, 2) COMMENT '预付款项' ,
    premium_rece decimal (15, 2) COMMENT '应收保费' ,
    reinsure_rece decimal (15, 2) COMMENT '应收分保账款' ,
    rc_reserve_rece decimal (15, 2) COMMENT '应收分保合同准备金' ,
    othr_receivables_total decimal (15, 2) COMMENT '其他应收款合计' ,
    interest_receivable decimal (15, 2) COMMENT '应收利息' ,
    othr_receivables decimal (15, 2) COMMENT '其他应收款' ,
    dividend_rece decimal (15, 2) COMMENT '应收股利' ,
    buy_resale_finasset decimal (15, 2) COMMENT '买入返售金融资产',
    inventory decimal (15, 2) COMMENT '存货' ,
    contractual_assets decimal (15, 2) COMMENT '合同资产' ,
    holdsale_asset decimal (15, 2) COMMENT '持有待售资产' ,
    nca_due_within_one_year decimal (15, 2) COMMENT '一年内到期的非流动资产' ,
    loan_advance decimal (15, 2) COMMENT '发放贷款和垫款' ,
    othr_current_assets decimal (15, 2) COMMENT '其他流动资产' ,
    total_current_assets decimal (15, 2) COMMENT '流动资产合计' ,

    creditor_invest decimal (15, 2) COMMENT '债权投资' ,
    held_to_maturity_invest decimal (15, 2) COMMENT '持有至到期投资' ,
    other_creditor_invest decimal (15, 2) COMMENT '其他债权投资' ,
    long_rece decimal (15, 2) COMMENT '长期应收款' ,
    lt_equity_invest decimal (15, 2) COMMENT '长期股权投资' ,
    othr_equity_instruments decimal (15, 2) COMMENT '其他权益工具投资' ,
    saleable_finacial_assets decimal (15, 2) COMMENT '可供出售金融资产' ,
    other_illiquid_fnncl_assets decimal (15, 2) COMMENT '其他非流动金融资产' ,
    invest_property decimal (15, 2) COMMENT '投资性房地产' ,
    fixed_asset decimal (15, 2) COMMENT '固定资产' ,
    fixed_assets_disposal decimal (15, 2) COMMENT '固定资产清理' ,
    construction_in_process decimal (15, 2) COMMENT '在建工程' ,
    project_goods_and_material decimal (15, 2) COMMENT '工程物资' ,
    productive_biology_asset  decimal (15, 2) COMMENT '生产性生物资产' ,
    oil_gas_asset  decimal (15, 2) COMMENT '油气资产' ,
    useright_asset decimal (15, 2) COMMENT '使用权资产' ,
    intangible_assets decimal (15, 2) COMMENT '无形资产' ,
    develop_expense decimal (15, 2) COMMENT '开发支出' ,
    goodwill decimal (15, 2) COMMENT '商誉' ,
    lt_deferred_expense decimal (15, 2) COMMENT '长期待摊费用' ,
    dt_assets decimal (15, 2) COMMENT '递延所得税资产' ,
    othr_noncurrent_assets decimal (15, 2) COMMENT '其他非流动资产' ,
    total_noncurrent_assets decimal (15, 2) COMMENT '非流动资产合计' ,
    total_assets decimal (15, 2) COMMENT '资产合计' ,

    st_loan decimal (15, 2) COMMENT '短期借款' ,
    loan_pbc decimal (15, 2) COMMENT '向中央银行借款' ,
    borrow_fund decimal (15, 2) COMMENT '拆入资金' ,
    trade_finliab_notfvtpl decimal (15, 2) COMMENT '交易性金融负债' ,
    fvtpl_finliab decimal (15, 2) COMMENT '以公允价值计量且其变动计入当期损益的金融负债' ,
    derivative_fnncl_liab decimal (15, 2) COMMENT '衍生金融负债' ,
    bp_and_ap decimal (15, 2) COMMENT '应付票据及应付账款' ,
    bill_payable decimal (15, 2) COMMENT '应付票据' ,
    accounts_payable decimal (15, 2) COMMENT '应付账款' ,
    pre_receivable decimal (15, 2) COMMENT '预收款项' ,
    contract_liabilities decimal (15, 2) COMMENT '合同负债' ,
    sell_repo_finasset decimal (15, 2) COMMENT '卖出回购金融资产款' ,
    iofi_deposit decimal (15, 2) COMMENT '吸收存款及同业存放' ,
    agent_trade_security decimal (15, 2) COMMENT '代理买卖证券款' ,
    agent_underwrite_security decimal (15, 2) COMMENT '代理承销证券款' ,
    payroll_payable decimal (15, 2) COMMENT '应付职工薪酬' ,
    tax_payable decimal (15, 2) COMMENT '应交税费' ,
    total_othr_payables decimal (15, 2) COMMENT '其他应付款合计' ,
    interest_payable decimal (15, 2) COMMENT '应付利息' ,
    dividend_payable decimal (15, 2) COMMENT '应付股利' ,
    othr_payables decimal (15, 2) COMMENT '其他应付款' ,
    fee_commission_payable decimal (15, 2) COMMENT '应付手续费及佣金' ,
    reinsure_payable decimal (15, 2) COMMENT '应付分保账款' ,
    holdsale_liab decimal (15, 2) COMMENT '持有待售负债' ,
    noncurrent_liab_due_in1y decimal (15, 2) COMMENT '一年内到期的非流动负债' ,
    othr_current_liab decimal (15, 2) COMMENT '其他流动负债' ,
    total_current_liab decimal (15, 2) COMMENT '流动负债合计' ,

    insurance_contract_reserve decimal (15, 2) COMMENT '保险合同准备金' ,
    lt_loan decimal (15, 2) COMMENT '长期借款' ,
    bond_payable  decimal (15, 2) COMMENT '应付债券' ,
    preferred_shares_paybale decimal (15, 2) COMMENT '应付债券-优先股' ,
    perpetual_bond_paybale decimal (15, 2) COMMENT '应付债券-永续债' ,
    lease_liab decimal (15, 2) COMMENT '租赁负债' ,
    lt_payable decimal (15, 2) COMMENT '长期应付款' ,
    long_staffsalary_payable  decimal (15, 2) COMMENT '长期应付职工薪酬' ,
    estimated_liab decimal (15, 2) COMMENT '预计负债' ,
    defer_income decimal (15, 2) COMMENT '递延收益' ,
    defer_tax_liab decimal (15, 2) COMMENT '递延所得税负债' ,
    othr_non_current_liab decimal (15, 2) COMMENT '其他非流动负债' ,
    total_noncurrent_liab decimal (15, 2) COMMENT '非流动负债合计' ,
    total_liab decimal (15, 2) COMMENT '负债合计' ,

    shares decimal (15, 2) COMMENT '实收资本（或股本）' ,
    other_equity_tool decimal (15, 2) COMMENT '其他权益工具' ,
    preferred_shares decimal (15, 2) COMMENT '优先股' ,
    perpetual_bond decimal (15, 2) COMMENT '永续债' ,
    capital_reserve decimal (15, 2) COMMENT '资本公积' ,
    treasury_shares decimal (15, 2) COMMENT '库存股' ,
    othr_compre_income decimal (15, 2) COMMENT '其他综合收益' ,
    special_reserve decimal (15, 2) COMMENT '专项储备' ,
    earned_surplus decimal (15, 2) COMMENT '盈余公积' ,
    general_risk_reserve decimal (15, 2) COMMENT '一般风险准备' ,
    undstrbtd_profit decimal (15, 2) COMMENT '未分配利润' ,
    total_quity_atsopc decimal (15, 2) COMMENT '归属于母公司所有者权益合计' ,
    minority_equity decimal (15, 2) COMMENT '少数股东权益' ,
    total_holders_equity decimal (15, 2) COMMENT '所有者权益（或股东权益）合计' ,
    total_liab_and_holders_equity decimal (15, 2) COMMENT '负债和所有者权益（或股东权益）合计',

    PRIMARY KEY (`balance_id`)
);

-- 利润表
CREATE TABLE income (
    income_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_code varchar(20) COMMENT '股票代码' ,
    year int comment '年份',
    total_operate_income decimal (15, 2) COMMENT '一、营业总收入' ,
    operate_income decimal (15, 2) COMMENT '其中：营业收入' ,
    interest_income decimal (15, 2) COMMENT '利息收入' ,
    earned_premium decimal (15, 2) COMMENT '已赚保费' ,
    fee_commission_income decimal (15, 2) COMMENT '手续费及佣金收入' ,

    total_operate_cost decimal (15, 2) COMMENT '二、营业总成本' ,
    operate_cost decimal (15, 2) COMMENT '营业总成本' ,
    operating_cost decimal (15, 2) COMMENT '其中：营业成本' ,
    interest_expense decimal (15, 2) COMMENT '利息支出' ,
    fee_commission_expense decimal (15, 2) COMMENT '手续费及佣金支出' ,
    surrender_value decimal (15, 2) COMMENT '退保金' ,
    compensate_expense decimal (15, 2) COMMENT '赔付支出净额' ,
    extract_insurance_reserve decimal (15, 2) COMMENT '提取保险责任合同准备金净额' ,
    reinsure_expense  decimal (15, 2) COMMENT '分保费用' ,
    operate_tax_add  decimal (15, 2) COMMENT '税金及附加' ,
    sales_fee  decimal (15, 2) COMMENT '销售费用' ,
    manage_fee  decimal (15, 2) COMMENT '管理费用' ,
    research_expense  decimal (15, 2) COMMENT '研发费用' ,
    finance_expense  decimal (15, 2) COMMENT '财务费用' ,
    fe_interest_expense  decimal (15, 2) COMMENT '其中：利息费用' ,

    fe_interest_income  decimal (15, 2) COMMENT '利息收入' ,
    other_income  decimal (15, 2) COMMENT '加：其他收益' ,
    invest_income  decimal (15, 2) COMMENT '投资收益' ,
    invest_joint_income  decimal (15, 2) COMMENT '其中：对联营企业和合营企业的投资收益' ,
    exchange_income  decimal (15, 2) COMMENT '汇兑收益' ,
    fairvalue_change_income  decimal (15, 2) COMMENT '公允价值变动收益' ,
    credit_impairment_income  decimal (15, 2) COMMENT '信用减值损失' ,
    asset_impairment_income  decimal (15, 2) COMMENT '资产减值损失' ,
    asset_disposal_income  decimal (15, 2) COMMENT '资产处置收益' ,
    operate_profit  decimal (15, 2) COMMENT '三、营业利润' ,
    nonbusiness_income  decimal (15, 2) COMMENT '加：营业外收入' ,
    nonbusiness_expense  decimal (15, 2) COMMENT '减：营业外支出' ,
    total_profit  decimal (15, 2) COMMENT '四、利润总额' ,
    income_tax  decimal (15, 2) COMMENT '减：所得税费用' ,
    netprofit  decimal (15, 2) COMMENT '五、净利润' ,
    continued_netprofit  decimal (15, 2) COMMENT '持续经营净利润' ,
    discontinued_netprofit  decimal (15, 2) COMMENT '终止经营净利润' ,
    parent_netprofit  decimal (15, 2) COMMENT '归属于母公司股东的净利润' ,
    minority_interest  decimal (15, 2) COMMENT '少数股东损益' ,
    total_compre_income  decimal (15, 2) COMMENT '七、综合收益总额' ,
    parent_tci  decimal (15, 2) COMMENT '（一）归属于母公司所有者的综合收益总额' ,
    minority_tci  decimal (15, 2) COMMENT '（二）归属于少数股东的综合收益总额' ,
    invest_incomes_from_rr  decimal (15, 2) COMMENT '其中：联营企业和合营企业的投资收益(元)' ,
    basic_eps  decimal (15, 2) COMMENT '（一）基本每股收益(元/股)' ,
    diluted_eps  decimal (15, 2) COMMENT '（二）稀释每股收益(元/股)' ,

    PRIMARY KEY (`income_id`)
);

-- 现金流量表
CREATE TABLE cash_flow (
    cash_flow_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_code varchar(20) COMMENT '股票代码' ,
    year int COMMENT '年份',
    sales_services decimal (15, 2) COMMENT '销售商品、提供劳务收到的现金' ,
    deposit_interbank_add decimal (15, 2) COMMENT '客户存款和同业存放款项净增加额' ,
    loan_pbc_add decimal (15, 2) COMMENT '向中央银行借款净增加额' ,
    receive_origic_premium decimal (15, 2) COMMENT '收到原保险合同保费取得的现金' ,
    insured_invest_add decimal (15, 2) COMMENT '保户储金及投资款净增加额' ,
    receive_interest_commission decimal (15, 2) COMMENT '收取利息、手续费及佣金的现金' ,
    borrow_fund_add decimal (15, 2) COMMENT '拆入资金净增加额' ,
    repo_business_add decimal (15, 2) COMMENT '回购业务资金净增加额' ,
    receive_agent_trade decimal (15, 2) COMMENT '代理买卖证券收到的现金净额' ,
    receive_other_operate decimal (15, 2) COMMENT '收到其他与经营活动有关的现金' ,
    total_operate_inflow decimal (15, 2) COMMENT '经营活动现金流入小计' ,
    buy_services decimal (15, 2) COMMENT '购买商品、接受劳务支付的现金' ,
    loan_advance_add decimal (15, 2) COMMENT '客户贷款及垫款净增加额' ,
    pbc_interbank_add decimal (15, 2) COMMENT '存放中央银行和同业款项净增加额' ,
    pay_origic_compensate decimal (15, 2) COMMENT '支付原保险合同赔付款项的现金' ,
    sub_total_of_ci_from_oa decimal (15, 2) COMMENT '经营活动现金流入小计' ,
    goods_buy_and_service_cash_pay decimal (15, 2) COMMENT '购买商品、接受劳务支付的现金' ,
    cash_paid_to_employee_etc decimal (15, 2) COMMENT '支付给职工以及为职工支付的现金' ,
    pay_interest_commission decimal (15, 2) COMMENT '支付利息、手续费及佣金的现金' ,
    pay_staff_cash decimal (15, 2) COMMENT '支付给职工及为职工支付的现金' ,
    pay_all_tax decimal (15, 2) COMMENT '支付的各项税费' ,
    pay_other_operate decimal (15, 2) COMMENT '支付其他与经营活动有关的现金' ,
    total_operate_outflow decimal (15, 2) COMMENT '经营活动现金流出小计' ,
    netcash_operate decimal (15, 2) COMMENT '经营活动产生的现金流量净额' ,
    withdraw_invest decimal (15, 2) COMMENT '收回投资收到的现金' ,
    receive_invest_income decimal (15, 2) COMMENT '取得投资收益收到的现金' ,
    disposal_long_asset decimal (15, 2) COMMENT '处置固定资产、无形资产和其他长期资产收回的现金净额' ,
    receive_other_invest decimal (15, 2) COMMENT '处置子公司及其他营业单位收到的现金净额' ,
    total_invest_inflow decimal (15, 2) COMMENT '投资活动现金流入小计' ,
    construct_long_asset decimal (15, 2) COMMENT '购建固定资产、无形资产和其他长期资产支付的现金' ,
    invest_pay_cash decimal (15, 2) COMMENT '投资支付的现金' ,
    obtain_subsidiary_other decimal (15, 2) COMMENT '取得子公司及其他营业单位支付的现金净额' ,
    pay_other_invest decimal (15, 2) COMMENT '支付其他与投资活动有关的现金' ,
    total_invest_outflow decimal (15, 2) COMMENT '投资活动现金流出小计' ,
    netcash_invest decimal (15, 2) COMMENT '投资活动产生的现金流量净额' ,
    accept_invest_cash decimal (15, 2) COMMENT '吸收投资收到的现金' ,
    subsidiary_accept_invest decimal (15, 2) COMMENT '其中:子公司吸收少数股东投资收到的现金' ,
    receive_loan_cash decimal (15, 2) COMMENT '取得借款收到的现金' ,
    receive_other_finance decimal (15, 2) COMMENT '收到其他与筹资活动有关的现金' ,

    total_finance_inflow decimal (15, 2) COMMENT '筹资活动现金流入小计' ,
    pay_debt_cash decimal (15, 2) COMMENT '偿还债务支付的现金' ,
    assign_dividend_porfit decimal (15, 2) COMMENT '分配股利、利润或偿付利息支付的现金' ,
    subsidiary_pay_dividend decimal (15, 2) COMMENT '其中：子公司支付给少数股东的股利、利润支付其他与筹资活动有关的现金' ,
    total_finance_outflow decimal (15, 2) COMMENT '筹资活动现金流出小计' ,
    netcash_finance decimal (15, 2) COMMENT '筹资活动产生的现金流量净额' ,
    rate_change_effect decimal (15, 2) COMMENT '四、汇率变动对现金及现金等价物的影响' ,
    cce_add decimal (15, 2) COMMENT '五、现金及现金等价物净增加额' ,
    begin_cce decimal (15, 2) COMMENT '加:期初现金及现金等价物余额' ,
    end_cce decimal (15, 2) COMMENT '六、期末现金及现金等价物余额' ,

    asset_impairment decimal (15, 2) COMMENT '资产减值准备' ,
    fa_ir_depr decimal (15, 2) COMMENT '固定资产和投资性房地产折旧' ,
    oilgas_biology_depr decimal (15, 2) COMMENT '其中:固定资产折旧、油气资产折耗、生产性生物资产折旧' ,
    ir_depr decimal (15, 2) COMMENT '投资性房地产折旧' ,
    ia_amortize decimal (15, 2) COMMENT '无形资产摊销' ,
    lpe_amortize decimal (15, 2) COMMENT '长期待摊费用摊销' ,
    PRIMARY KEY (`cash_flow_id`)
);

-- 具体分析的科目表
CREATE TABLE analysis_subject (
    analysis_subject_id  int NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    stock_code varchar(20) COMMENT '股票代码' ,
    year int comment '年份',
    total_assets decimal (15, 2) COMMENT '资产合计' ,
    total_assets_group_ratio decimal (15, 2) COMMENT '总资产增长率' ,
    total_liab decimal (15, 2) COMMENT '负债合计' ,
    asset_liab_ratio  decimal (15, 2) COMMENT '资产负债率' ,
    currency_funds decimal (15, 2) COMMENT '货币资金' ,
    other_financial_product decimal (15, 2) COMMENT '其他流动资产里的“理财产品”' ,
    trade_financial_product decimal (15, 2) COMMENT '交易性金融资产里的理财产品或者结构性存款' ,
    currency_funds_plus decimal (15, 2) COMMENT '准货币资金' ,
    st_loan decimal (15, 2) COMMENT '短期借款' ,
    noncurrent_liab_due_in1y decimal (15, 2) COMMENT '一年内到期的非流动负债' ,
    lt_loan decimal (15, 2) COMMENT '长期借款' ,
    bond_payable  decimal (15, 2) COMMENT '应付债券' ,
    lt_payable decimal (15, 2) COMMENT '长期应付款' ,
    total_liab_with_interest decimal (15, 2) COMMENT '有息负债总额' ,
    liab_zhbzj_with_yxfz_diff decimal (15, 2) COMMENT '准货币资金与有息负债之差' ,
    bill_payable decimal (15, 2) COMMENT '应付票据' ,
    accounts_payable decimal (15, 2) COMMENT '应付账款' ,
    pre_receivable decimal (15, 2) COMMENT '预收款项' ,
    total_ap_bp_pr decimal (15, 2) COMMENT '应付预收合计' ,
    bills_receivable decimal (15, 2) COMMENT '应收票据' ,
    bills_receivable_plus decimal (15, 2) COMMENT '应收款项融资里的应收票据' ,
    account_receivable decimal (15, 2) COMMENT '应收账款' ,
    account_receivable_plus decimal (15, 2) COMMENT '应收款项融资里的应收账款保理' ,
    pre_payment decimal (15, 2) COMMENT '预付款项' ,
    total_ar_br decimal (15, 2) COMMENT '应收预付合计' ,
    ap_bp_pr_with_ar_br_diff decimal (15, 2) COMMENT '应付预收与应收预付之差' ,
    account_receivable_ratio decimal (15, 2) COMMENT '应收账款占总资产的比率' ,
    fixed_asset decimal (15, 2) COMMENT '固定资产' ,
    construction_in_process decimal (15, 2) COMMENT '在建工程' ,
    project_goods_and_material decimal (15, 2) COMMENT '工程物资' ,
    total_fixed_asset  decimal (15, 2) COMMENT '固定资产总计' ,
    fixed_asset_ratio decimal (15, 2) COMMENT '固定资产工程占总资产的比率' ,
    tradable_fnncl_assets decimal (15, 2) COMMENT '交易性金融资产' ,
    saleable_finacial_assets decimal (15, 2) COMMENT '可供出售金融资产' ,
    othr_equity_instruments decimal (15, 2) COMMENT '其他权益工具投资' ,
    other_illiquid_fnncl_assets decimal (15, 2) COMMENT '其他非流动金融资产' ,
    held_to_maturity_invest decimal (15, 2) COMMENT '持有至到期投资' ,
    lt_equity_invest decimal (15, 2) COMMENT '长期股权投资' ,
    invest_property decimal (15, 2) COMMENT '投资性房地产' ,
    total_fnncl_assets decimal (15, 2) COMMENT '与主业无关的投资类资产合计' ,
    total_fnncl_assets_ratio decimal (15, 2) COMMENT '与主业无关的投资类资产占总资产的比率' ,
    revenue decimal (15, 2) COMMENT '营业收入' ,
    revenue_group_ratio decimal (15, 2) COMMENT '营业收入增长率' ,
    cash_received_of_sales_service decimal (15, 2) COMMENT '销售商品、提供劳务收到的现金' ,
    cash_received_of_sales_service_ratio decimal (15, 2) COMMENT '销售商品、提供劳务收到的现金与营业收入的比率' ,
    operating_costs decimal (15, 2) COMMENT '营业成本' ,
    revenue_with_operating_costs_diff decimal (15, 2) COMMENT '毛利' ,
    revenue_with_operating_costs_diff_ratio decimal (15, 2) COMMENT '毛利率' ,
    sales_fee decimal (15, 2) COMMENT '销售费用' ,
    manage_fee decimal (15, 2) COMMENT '管理费用' ,
    rad_cost decimal (15, 2) COMMENT '研发费用' ,
    financing_expenses decimal (15, 2) COMMENT '财务费用' ,
    total_fee decimal (15, 2) COMMENT '四费合计' ,
    total_fee_ratio decimal (15, 2) COMMENT '费用率' ,
    total_fee_with_mll_ratio decimal (15, 2) COMMENT '费用率与毛利率之比' ,
    operating_taxes_and_surcharge decimal (15, 2) COMMENT '营业税金及附加' ,
    main_profit decimal (15, 2) COMMENT '主营利润' ,
    main_profit_ratio decimal (15, 2) COMMENT '主营利润率' ,
    profit_total_amt decimal (15, 2) COMMENT '利润总额' ,
    main_profit_with_profit_total_ratio decimal (15, 2) COMMENT '主营利润占利润总额的比率' ,
    net_profit decimal (15, 2) COMMENT '净利润' ,
    ncf_from_oa decimal (15, 2) COMMENT '经营活动产生的现金流量净额' ,
    net_profit_ratio decimal (15, 2) COMMENT '净利润现金比率' ,
    net_profit_atsopc decimal (15, 2) COMMENT '归属于母公司所有者的净利润' ,
    net_profit_atsopc_group_ratio decimal (15, 2) COMMENT '归母净利润增长率' ,
    roe decimal (15, 2) COMMENT '加权平均净资产收益率ROE' ,
    asset_depreciation decimal (15, 2) COMMENT '固定资产折旧' ,
    intangible_assets_amortize decimal (15, 2) COMMENT '无形资产摊销' ,
    cash_paid_of_distribution decimal (15, 2) COMMENT '分配股利、利润或偿付利息支付的现金' ,
    ncf_from_oa_diff  decimal (15, 2) COMMENT '“经营现金净额”减去“固定折旧+无形摊销+借款利息+股利”之差' ,
    cash_paid_for_assets decimal (15, 2) COMMENT '购建固定资产、无形资产和其他长期资产支付的现金' ,
    buy_ratio decimal (15, 2) COMMENT '购建支付的现金与经营活动产生的现金流量净额的比率' ,
    net_cash_of_disposal_assets decimal (15, 2) COMMENT '处置固定资产、无形资产和其他长期资产收回的现金净额' ,
    sale_ratio decimal (15, 2) COMMENT '处置收回的现金净额与购建支付的现金的比率' ,
    bonus_ratio decimal (15, 2) COMMENT '分红率' ,
    ncf_from_ia decimal (15, 2) COMMENT '投资活动产生的现金流量净额' ,
    ncf_from_fa decimal (15, 2) COMMENT '筹资活动产生的现金流量净额' ,
    type  char(3)   COMMENT '三项经营活动的类型' ,
    net_increase_in_cce decimal (15, 2) COMMENT '现金及现金等价物净增加额' ,
    total_bonus decimal (15, 2) COMMENT '每年实际发放现金分红的总额' ,
    total_cash decimal (15, 2) COMMENT '"加回“现金分红”后的现金及现金等价物净增加额"' ,
    remark varchar (500) COMMENT '备注' ,
    correction int COMMENT '是否更正',
    PRIMARY KEY (`analysis_subject_id`)
);