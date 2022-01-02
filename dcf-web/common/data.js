export const stockData = {
	yearList: [],
	priceInfo: {},
	item: {
		totalAssetsList: {
			columnList: [{
					title: '总资产',
					fieldName: 'total_assets',
					values: [],
					mainQuato: true,
				},
				{
					title: '总资产增长率',
					fieldName: 'total_assets_yoy',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 0
				},
				{
					title: '总负债',
					fieldName: 'total_liab',
					values: [],
					mainQuato: true
				},
				{
					title: '资产负债率',
					fieldName: 'asset_liab_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 70
				},
			],
			title: '第1步：看总资产和资产负债率',
			bodyList: [
				'看总资产，判断公司实力及扩张能力；',
				'1.1、总资产金额大的公司实力更强，总资产同比增长快的公司扩张能力更强。一般情况下，总资产规模排名第一的公司一般就是这个行业中的老大；',
				'1.2、同比增长较快，公司可能处于快速扩张之中，但也可能来自债务。同比负增长，公司很可能处于收缩或者衰退之中；',
				'1.3、大于等于10%，公司在扩张之中，成长性较好；0-10之间，公司处于成熟期，发展平稳；小于等于0，公司很可能处于收缩或者衰退之中；',
				'看资产负债率，判断公司的债务风险；',
				'2.1、比率越小，公司未来发生债务危机的可能较小；比率越大，公司未来发生债务危机的可能较大；一般同比大幅增加的公司，遇到问题的概率比较大，偿债风险较大；',
				'2.2、资产负债率并不是越低越好，要知道像应付票据、应付账款、预收款项之类的负债高恰恰是公司竞争力强的表现；',
				'2.3、大于等于70%，发生债务危机的可能性较大(银保地除外)；大于等于40%，小于70%，偿债风险较小，但在特殊情况下依然可能发生偿债危机；小于40%，基本没有偿债风险；',
				'2.4、在投资实践中一般会把资产负债率大于60%的公司淘汰掉；'
			],
		},
		liabList: {
			columnList: [{
					title: '货币资金',
					fieldName: 'currency_funds',
					values: []
				},
				{
					title: '交易性金融资产',
					fieldName: 'tradable_fnncl_assets',
					values: []
				},
				{
					title: '其他流动资产里的理财产品',
					fieldName: 'othr_current_assets_lccp',
					values: []
				},
				{
					title: '其他流动资产里的结构性存款',
					fieldName: 'trade_financial_jgxck',
					values: []
				},
				{
					title: '准货币资金',
					fieldName: 'currency_funds_plus',
					values: [],
					mainQuato: true
				},
				{
					title: '短期借款',
					fieldName: 'st_loan',
					values: []
				},
				{
					title: '一年内到期的非流动负债',
					fieldName: 'noncurrent_liab_due_in1y',
					values: []
				},
				{
					title: '长期借款',
					fieldName: 'lt_loan',
					values: []
				},
				{
					title: '应付债券',
					fieldName: 'bond_payable',
					values: []
				},
				{
					title: '长期应付款',
					fieldName: 'lt_payable',
					values: []
				},
				{
					title: '有息负债',
					fieldName: 'total_liab_with_interest',
					values: []
				},
				{
					title: '准货币资金与有息负债之差',
					fieldName: 'liab_zhbzj_with_yxfz_diff',
					values: [],
					mainQuato: true,
					min: 0
				},
				{
					title: '短期有息负债',
					fieldName: 'total_st_liab',
					values: []
				},
				{
					title: '货币资金减短期有息负债的差额',
					fieldName: 'currency_funds_with_dqfz_diff',
					values: [],
					mainQuato: true,
					min: 0
				}
			],
			title: '第2步：看有息负债和准货币资金',
			bodyList: [
				'看有息负债和准货币资金，排除偿债风险；',
				'1、准货币资金=货币资金+交易性金融资产+其他流动资产里的理财产品+其他流动资产里的结构性存款；',
				'2、有息负债=短期借款＋一年内到期的非流动负债＋长期借款＋应付债券＋长期应付款；',
				'3、准货币资金与有息负债之差大于0，无偿债风险。小于0，有偿债风险。 异常情况：准货币资金和短期借款或长期借款的金额都很大，很可能企业实际没有钱，后期风险很大；',
				'4、对于资产负债率大于40%的公司， 需要看他的准货币资金是否大于有息负债，准货币资金小于有息负债的公司淘汰；',
				'5、货币资金减短期有息负债的差额 = 货币资金 — 短期借款 — 一年内到期的非流动负债；差额小于0，肯定有风险；大于等于0，风险比较小。',
				'特别说明：目前其他流动资产里的理财产品和结构性存款，系统无法获取，需要自己看财报；',
			],
		},
		arBrApBpList: {
			columnList: [{
					title: '应付票据',
					fieldName: 'bill_payable',
					values: []
				},
				{
					title: '应付账款',
					fieldName: 'accounts_payable',
					values: []
				},
				{
					title: '预收款项',
					fieldName: 'pre_receivable',
					values: []
				},
				{
					title: '合同负债',
					fieldName: 'contract_liabilities',
					values: []
				},
				{
					title: '应付预收合计',
					fieldName: 'total_ap_bp_pr',
					values: [],
					mainQuato: true,
				},
				{
					title: '应收票据',
					fieldName: 'bills_receivable',
					values: []
				},
				{
					title: '应收账款',
					fieldName: 'account_receivable',
					values: []
				},
				{
					title: '应收款项融资',
					fieldName: 'finance_rece',
					values: []
				},
				{
					title: '预付款项',
					fieldName: 'pre_payment',
					values: []
				},
				{
					title: '合同资产',
					fieldName: 'contractual_assets',
					values: []
				},
				{
					title: '应收预付合计',
					fieldName: 'total_ar_br',
					values: [],
					mainQuato: true
				},
				{
					title: '应付预收与应收预付之差',
					fieldName: 'ap_bp_pr_with_ar_br_diff',
					values: [],
					mainQuato: true,
					min: 0
				},
				{
					title: '应收账款、合同资产占总资产的比率',
					fieldName: 'account_receivable_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 15
				},
			],
			title: '第3步：看应收应付和预付预收',
			bodyList: [
				'看应收应付和预付预收，判断公司的行业地位；',
				'1、应付预收合计=应付票据+应付账款+预收款项+合同负债；应收预付合计=应收票据+应收账款+应收款项融资+预付款项+合同资产；',
				'2、应付预收属于公司的“负债”，占用别人的资金。金额越大，代表公司的竞争力越强，行业地位越高；',
				'3、应收预付属于公司的“资产”，资金被别人占用了。 金额越小，代表公司的竞争力越强，行业地位越高；',
				'4、应付预收与应收预付之差大于0，公司无偿占用上游供应商和下游经销商的资金，竞争力强。小于0，公司的自有资金被其他公司无偿占用，竞争力相对较弱；',
				'5、应收账款、合同资产占总资产的比率 =（应收账款+合同资产）/总资产*100%；',
				'6、应收账款、合同资产占总资产的比率小于等于1%，最好的公司，公司产品很畅销；大于1%小于等于3%，优秀的公司，公司产品畅销；大于3%小于10%，优秀的公司，公司产品比较畅销；大于等于10%，小于20%，公司的产品比较难销售；大于等于20%，公司的产品很难销售。',
				'7、在投资实践中，我们一般会把（应收账款+合同资产）占总资产的比率大于15%的公司淘汰掉。在特别情况下，对于行业第一名并且同时具有3个或以上核心竞争力的公司可以放宽到20%，大于20%的一律淘汰掉。'
			],
		},
		fixedAssetList: {
			columnList: [{
					title: '固定资产',
					fieldName: 'fixed_asset',
					values: [],
				},
				{
					title: '在建工程',
					fieldName: 'construction_in_process',
					values: []
				},
				{
					title: '工程物资',
					fieldName: 'project_goods_and_material',
					values: []
				},
				{
					title: '固定资产合计',
					fieldName: 'total_fixed_asset',
					values: []
				},
				{
					title: '固定资产合计占总资产的比率',
					fieldName: 'fixed_asset_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 40
				},
			],
			title: '第4步：看固定资产',
			bodyList: [
				'看固定资产，判断公司的轻重；',
				'1、比率大于40%，重资产型公司。维持竞争力的成本比较高，风险相对较大。小于40%，轻资产型公司。保持持续的竞争力成本相对要低一些；',
			 '2、在投资实践中，一般选择比率小于20%的轻资产型公司，把比率大于40%的公司淘汰掉。在特别情况下，对于行业第一名并且同时具有3个或以上核心竞争力的公司可以放宽到50%，大于50%的一律淘汰掉；'
			],
		},
		investmentAssetsList: {
			columnList: [{
					title: '债权投资',
					fieldName: 'creditor_invest',
					values: []
				},
				{
					title: '其他债权投资',
					fieldName: 'other_creditor_invest',
					values: []
				},
				{
					title: '以公允价值计量且其变动计入当期损益的金融资产',
					fieldName: 'fvtpl_finassets',
					values: []
				},
				{
					title: '可供出售金融资产',
					fieldName: 'saleable_finacial_assets',
					values: []
				},
				{
					title: '持有至到期投资',
					fieldName: 'held_to_maturity_invest',
					values: []
				},
				{
					title: '长期股权投资',
					fieldName: 'lt_equity_invest',
					values: []
				},
				{
					title: '投资性房地产',
					fieldName: 'invest_property',
					values: []
				},
				{
					title: '其他权益工具投资',
					fieldName: 'othr_equity_instruments',
					values: []
				},
				{
					title: '其他非流动金融资产',
					fieldName: 'other_illiquid_fnncl_assets',
					values: []
				},
				{
					title: '投资类资产合计',
					fieldName: 'total_fnncl_assets',
					values: []
				},
				{
					title: '投资类资产合计占总资产的比率',
					fieldName: 'total_fnncl_assets_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 10
				},
			],
			title: '第5步：看投资类资产',
			bodyList: [
				'看投资类资产，判断公司的专注程度',
				'投资类资产合计=债权投资+其他债权投资+以公允价值计量且其变动计入当期损益的金融资产+可供出售金融资产+持有至到期投资+长期股权投资+投资性房地产+其他权益工具投资+其他非流动金融资产',
				'1、比率大于10%，不够专业，淘汰；优秀的公司一定是专注于主业的公司，与主业无关的投资类资产占总资产的比例应当很低才对，最好为0。',
				'2、如果某公司投资类资产占总资产的比率较大，就意味着这家公司在自己的行业内已经很难发现好的投资机会了；',
				'3、一家非常专注于主业的公司，在未来持续保持竞争优秀的概率较大；',
			],
		},
		inventoryList: {
			columnList: [{
					title: '应收账款占总资产比率',
					fieldName: 'only_account_receivable_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 5
				},
				{
					title: '存货',
					fieldName: 'inventory',
					values: [],

				},
				{
					title: '存货占总资产比率',
					fieldName: 'inventory_with_assets_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 15
				},
				{
					title: '商誉',
					fieldName: 'goodwill',
					values: []
				},
				{
					title: '商誉占总资产比率',
					fieldName: 'goodwill_with_assets_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 10
				}
			],
			title: '第6步：看存货、商誉',
			bodyList: [
				'看存货、商誉，了解公司未来业绩爆雷的风险；',
				'1、商誉是公司对外收购所花的超出被收购公司净资产的那部分钱。商誉占总资产比率大于等于10%，商誉有爆雷的风险；小于10%，商誉没有爆雷的风险；',
				'2、应收账款占总资产比率大于5%且存货占总资产比率大于15%，爆雷的风险比较大，需要淘汰掉；反之则暂时没有暴雷风险，需进一步分析；',
			],
		},
		incomeList: {
			columnList: [{
					title: '营业收入',
					fieldName: 'operate_income',
					values: [],
					mainQuato: true
				},
				{
					title: '营业收入增长率',
					fieldName: 'operate_income_yoy',
					ratio:true,
					values: [],
					mainQuato: true,
					min: 0
				},
				{
					title: '销售商品、提供劳务收到的现金',
					fieldName: 'cash_received_of_sales_service',
					values: []
				},
				{
					title: '销售商品、提供劳务收到的现金与营业收入的比率',
					fieldName: 'cash_received_of_sales_service_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 100
				},
			],
			title: '第7步：看营业收入',
			bodyList: [
				'看营业收入，判断公司的行业地位及成长能力；',
				'1、从营业收入的规模我们大概可以判断出公司的实力和行业地位；一般来说营业收入规模大的公司实力较强，行业地位较高；',
				'2、大于等于10%，说明公司处于成长较快，前景较好；小于10%，说明公司成长缓慢；小于等于0，说明公司可能正在处于衰落之中；',
				'3、销售商品、提供劳务收到的现金与营业收入的比率5年平均值大于等于100%，营业收入含金量较高；反之则，营业收入含金量较低；',
				'4、在投资实践中，选择营业收入增长率大于10%的公司，营业收入增长率小于10%的公司一般淘汰掉；'
			],
		},
		grossSellingRateList: {
			columnList: [{
					title: '营业收入',
					fieldName: 'operate_income',
					values: []
				},
				{
					title: '营业成本',
					fieldName: 'operating_costs',
					values: []
				},
				{
					title: '毛利',
					fieldName: 'gross_profit',
					values: []
				},
				{
					title: '毛利率',
					fieldName: 'gross_profit_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 40
				},
				{
					title: '毛利率波动幅度',
					fieldName: 'gross_profit_ratio_wave',
					ratio: true,
					values: [],
					mainQuato: true,
					max:20
				},
			],
			title: '第8步：看毛利率',
			bodyList: [
				'看毛利率，了解公司的产品竞争力及风险；',
				'1、毛利率=(营业收入-营业成本)/营业收入*100%；高毛利率说明公司的产品或服务的竞争力较强，中低毛利率说明公司的产品或服务的竞争力较差；',
				'2、毛利率波动幅度=（本年毛利率-上年毛利率）/上年毛利率*100%；毛利率波动幅度小于等于10%，优秀的公司；大于10%，公司经营或财务造假的风险大；',
				'3、毛利率低的公司要想获得高收益，就必须提高周转率或加杠杆，提高周转率需要强大的运营能力，经营难度较大，加杠杆会增大公司的财务风险。在投资实践中，毛利率波幅大于20%的公司一般淘汰掉。'
			],
		},
		feeRatioList: {
			columnList: [{
					title: '销售费用',
					fieldName: 'sales_fee',
					values: []
				},
				{
					title: '管理费用',
					fieldName: 'manage_fee',
					values: []
				},
				{
					title: '财务费用',
					fieldName: 'financing_expenses',
					values: []
				},
				{
					title: '研发费用',
					fieldName: 'rad_cost',
					values: []
				},
				{
					title: '四费合计',
					fieldName: 'total_fee',
					values: []
				},
				{
					title: '期间费用率',
					fieldName: 'total_fee_ratio',
					ratio: true,
					values: []
				},
				{
					title: '期间费用率/毛利率',
					fieldName: 'total_fee_with_mll_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 40
				},
			],
			title: '第9步：看期间费用率',
			bodyList: [
				'看期间费用率，了解公司的成本管控能力',
				'1、期间费用率=（销售费用+管理费用+研发费用+财务费用）/营业收入；说明：财务费用为负数时不算入合计；',
				'2、期间费用率与毛利率的比率小于等于40%，成本控制能力好，属于优秀的企业；大于40%，成本控制能力差。在投资实践中，一般把期间费用率与毛利率的比率大于60%的公司淘汰掉。'
			],
		},
		saleFeeList: {
			columnList: [{
				title: '销售费用率',
				fieldName: 'sales_fee_ratio',
				ratio:true,
				values: [],
				mainQuato: true,
				max: 15
			}],
			title: '第10步：看销售费用率',
			bodyList: [
				'看销售费用率，了解公司产品的销售难易程度',
				'1、销售费用率=销售费用/营业收入*100%；小于等于15%，产品比较容易销售，销售风险相对较小；大于15%，产品销售难度大，销售风险大；',
				'2、在投资实践中，一般把销售费用率大于30%的公司淘汰掉；'
			],
		},
		mainProfitList: {
			columnList: [{
					title: '营业收入',
					fieldName: 'operate_income',
					values: []
				},
				{
					title: '营业成本',
					fieldName: 'operating_costs',
					values: []
				},
				{
					title: '营业税金及附加',
					fieldName: 'operating_taxes_and_surcharge',
					values: []
				},
				{
					title: '四费合计',
					fieldName: 'total_fee',
					values: []
				},
				{
					title: '主营利润',
					fieldName: 'main_profit',
					values: []
				},
				{
					title: '主营利润率',
					fieldName: 'main_profit_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 15
				},
				{
					title: '营业利润',
					fieldName: 'operate_profit',
					values: []
				},
				{
					title: '主营利润占营业利润的比率',
					fieldName: 'main_profit_with_profit_total_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 80
				},
			],
			title: '第11步：看主营利润',
			bodyList: [
				'看主营利润，判断公司的盈利能力及利润质量；',
				'1、主营利润 = 营业收入 — 营业成本 — 四费（销售费用 + 管理费用 + 财务费用+研发费用）— 税金及附加；（说明：财务费用为负数时不算入合计）；',
				'2、主营利润率 = 主营利润 / 营业收入 * 100%；大于等于15%，主业盈利能力强；小于15%，主业盈利能力弱；',
				'3、主营利润占营业利润的比率，大于等于80%，利润质量高；小于80%，利润质量低；',
				'4、在投资实践中，主营利润与营业利润的比率小于80%或主营利润率小于15%的公司，一般淘汰掉，这样的公司不具备持续的竞争力；'
			],
		},
		netProfitList: {
			columnList: [{
					title: '净利润',
					fieldName: 'net_profit',
					values: [],
					mainQuato: true
				},
				{
					title: '经营活动产生的现金流量净额',
					fieldName: 'ncf_from_oa',
					values: []
				},
				{
					title: '净利润现金比率',
					fieldName: 'net_profit_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 100
				},
			],
			title: '第12步：看净利润',
			bodyList: [
				'看净利润，了解公司的经营成果及含金量',
				'1、净利润现金比率=经营活动产生的现金流量净额/净利润*100%；五年平均值大于100%且每一年需要大于等于80%，净利润含金量较高；反之则净利润含金量较低；',
				'2、过去5年的平均净利润现金比率小于100%的公司，淘汰掉；'
			],
		},
		netProfitAtsopcList: {
			columnList: [{
					title: '归属于母公司所有者的净利润',
					fieldName: 'net_profit_atsopc',
					values: [],
					mainQuato: true
				},
				{
					title: '归母净利润增长率',
					fieldName: 'parent_netprofit_yoy',
					ratio: true,
					values: [],
					mainQuato: true
				},
				{
					title: 'ROE',
					fieldName: 'roe',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 15
				},
				{
					title: '经营活动产生的现金流量净额',
					fieldName: 'ncf_from_oa',
					values: [],
					mainQuato: true
				},
				{
					title: '经营活动产生的现金流量净额增长率',
					fieldName: 'netcash_operate_yoy',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 0
				},
			],
			title: '第13步：看归母净利润、经营活动产生的现金流量净额',
			bodyList: [
				'看归母净利润，判断公司自有资本的获利能力；看经营活动产生的现金流量净额，了解公司的造血能力',
				'1、ROE=归属于母公司股东的净利润/归属于母公司所有者权益合计*100%；持续大于等于20%，最优秀的公司；持续大于等于15%，优秀的公司；小于15%，淘汰；',
				'2、归母净利润增长率大于10%，说明公司在以较快的速度成长；大于0小于等于10%，说明公司成长速度一般；小于0，很可能意味公司已经处于衰落之中；',
				'3、归母净利润规模大的公司盈利能力更强。ROE小于15%的公司需要淘汰掉，归母净利润增长率持续小于10%的公司也要淘汰掉；',
				'4、经营活动产生的现金流量净额必须大于等于0，越大越好；小于0，直接淘汰；',
				'5、经营活动产生的现金流量净额增长率大于等于0，意味着公司的造血能力在增强；小于0，意味着公司的造血能力在下降；'
			],
		},
		futureGroupList: {
			columnList: [{
					title: '购建固定资产、无形资产和其他长期资产支付的现金',
					fieldName: 'cash_paid_for_assets',
					values: []
				},
				{
					title: '经营活动产生的现金流量净额',
					fieldName: 'ncf_from_oa',
					values: []
				},
				{
					title: '购建支付的现金与经营活动产生的现金流量净额的比率',
					fieldName: 'buy_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					max: 100,
					min: 3
				}
			],
			title: '第14步：看购建固定资产、无形资产和其他长期资产支付的现金',
			bodyList: [
				'看购买固定资产、无形资产和其他长期资产支付的现金，了解公司的增长潜力',
				'比率为3%-60%，公司增长潜力一般并且风险相对较小；比率 大于60%，小于100%，公司增长潜力较大并且风险相对较小；大于等于100%或持续小于3%，类型的公司要么是太激进，要么是成长太慢。前者风险较大，后者回报较低；',
				'比率大于等于100%或持续小于3%的公司淘汰掉。销售商品提供劳务收到的现金应该与构建固定资产是同趋势增长。'
			],
		},
		bonusList: {
			columnList: [{
					title: '年度累计分红总额',
					fieldName: 'bonus_fee',
					values: []
				},
				{
					title: '归属于母公司所有者的净利润',
					fieldName: 'net_profit_atsopc',
					values: []
				},
				{
					title: '年度股利支付率',
					fieldName: 'bonus_ratio',
					ratio: true,
					values: [],
					mainQuato: true,
					min: 30
				},
			],
			title: '第15步：看分红，判断公司的品质',
			bodyList: [
				'年度股利支付率=年度累计分红总额/归属于母公司股东的净利润',
				'每年大于等于30%，对股东比较慷慨；每年小于30%，对股东比较吝啬。'
			],
		},
		typeList: {
			columnList: [{
					title: '经营活动产生的现金流量净额',
					fieldName: 'ncf_from_oa',
					values: []
				},
				{
					title: '投资活动产生的现金流量净额',
					fieldName: 'ncf_from_ia',
					values: []
				},
				{
					title: '筹资活动产生的现金流量净额',
					fieldName: 'ncf_from_fa',
					values: []
				},
				{
					title: '三项经营活动的类型',
					fieldName: 'type',
					notCalc:true,
					values: [],
					mainQuato: true
				},
			],
			title: '第16步：看三大活动现金流量净额的组合类型',
			bodyList: [
				'看三大活动现金流量净额的组合类型，选出最佳类型的公司；',
				'经营活动产生的现金为正，表示主业赚钱；投资活动产生的现金流量净额为正，表示投资支出大于投资回报；筹资活动产生的现金流量净额为正，表示借钱大于还钱；',
				'1. “负正正”型：公司的主业经营亏损，通过卖家当，借钱或股权融资过日子，未来可能没什么前途；',
				'2. “负正负”型：公司主业亏钱，还要还钱，没办法只能卖家当。这样的公司日子是很不好过的；',
				'3. “负负正”型：公司主业不赚钱，通过借钱继续投资。A股中这类公司通常是主业不行，尝试转型，风险还是很大的；',
				'4. “负负负”型：所有的活动都在造成现金流出，是真正的“烧钱”，生活中基本没有；',
				'5. “正正正”型：可能公司近期是有大规模的投资计划，或者在背地里干坏事的公司，远离比较好；',
				'6. “正负正”型：一般处于快速成长期的公司可能会出现这种情况。如果新项目顺利，公司将会获得快速的发展；如果新 项目并不顺利，投资出去的钱很可能就无法收回；',
				'7. “正正负”型：公司主业经营赚钱，通过卖家当或有投资收益，还钱或分红，一般在经营活动产生的现金流量净额为正的情况下，公司卖家当的可能性还是比较小的，一般是投资收益。如果经营和投资带来的现金流入大于还债或分红带来的现金流出，公司的情况就是比较健康的；',
				'8. “正负负”型：公司主业经营赚钱，在继续投资，公司处于扩张之中，公司在还钱或分红；如果经营活动产生的现金流量净额持续大于投资和筹资活动产生的现金净流出额，说明公司造血能力强大，靠自己就能实现扩张，这样的公司很有价值；'
			],
		}
	}

}
