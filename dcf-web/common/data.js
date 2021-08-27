export const stockData = {
	yearList: [],
	priceInfo:{},
	item: {
		totalAssetsList: {
			columnList: [{
					title: '总资产',
					values: []
				},
				{
					title: '总资产增长率',
					values: []
				},
				{
					title: '总负债',
					values: []
				},
				{
					title: '资产负债率',
					values: []
				},
			],
			title: '第1步：看总资产和资产负债率',
			bodyList: [
				'看总资产，判断公司实力及扩张能力；',
				'1.1、总资产金额大的公司实力更强，总资产同比增长快的公司扩张能力更强。一般情况下，一家公司的总资产规模在一定程度上能反映这家公司在行业中的地位。总资产规模排名第一的公司一般就是这个行业中的老大；',
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
					values: []
				},
				{
					title: '其他流动资产里的理财产品',
					values: []
				},
				{
					title: '交易性金融资产里的理财产品',
					values: []
				},
				{
					title: '准货币资金',
					values: []
				},
				{
					title: '短期借款',
					values: []
				},
				{
					title: '一年内到期的非流动负债',
					values: []
				},
				{
					title: '长期借款',
					values: []
				},
				{
					title: '应付债券',
					values: []
				},
				{
					title: '长期应付款',
					values: []
				},
				{
					title: '有息负债总额',
					values: []
				},
				{
					title: '准货币资金与有息负债之差',
					values: []
				}
			],
			title: '第2步：看有息负债和准货币资金',
			bodyList: [
				'看有息负债和准货币资金，排除偿债风险；',
				'1、准货币资金=货币资金+交易性金融资产里的理财产品+其他流动资产里的理财产品+其他流动资产里的结构性存款；',
				'2、有息负债=短期借款＋一年内到期的非流动负债＋长期借款＋应付债券＋长期应付款；',
				'3、准货币资金与有息负债之差大于0，无偿债风险。小于0，有偿债风险。 异常情况：准货币资金和短期借款或长期借款的金额都很大，很可能企业实际没有钱，后期风险很大；',
				'4、对于资产负债率大于40%的公司， 需要看他的准货币资金是否大于有息负债，准货币资金小于有息负债的公司淘汰；',
				'特别说明：目前准货币资金里除了货币资金外，其他项系统无法获取，需要自己看财报；',
			],
		},
		arBrApBpList: {
			columnList: [{
					title: '应付票据',
					values: []
				},
				{
					title: '应付账款',
					values: []
				},
				{
					title: '预收款项',
					values: []
				},
				{
					title: '应付预收合计',
					values: []
				},
				{
					title: '应收票据',
					values: []
				},
				{
					title: '应收款项融资里的应收票据',
					values: []
				},
				{
					title: '应收账款',
					values: []
				},
				{
					title: '应收款项融资里的应收账款保理',
					values: []
				},
				{
					title: '预付款项',
					values: []
				},
				{
					title: '应收预付合计',
					values: []
				},
				{
					title: '应付预收与应收预付之差',
					values: []
				},
				{
					title: '应收账款占总资产的比率',
					values: []
				},
			],
			title: '第3步：看应收应付和预付预收',
			bodyList: [
				'看应收应付和预付预收，判断公司的行业地位；',
				'1、应付预收属于公司的“负债”，占用别人的资金。金额越大，代表公司的竞争力越强，行业地位越高；',
				'2、应收预付属于公司的“资产”，资金被别人占用了。 金额越小，代表公司的竞争力越强，行业地位越高；',
				'3、应付预收与应收预付之差大于0，公司无偿占用上游供应商和下游经销商的资金，竞争力强。小于0，公司的自有资金被其他公司无偿占用，竞争力相对较弱；',
				'4、应收账款的规模较大，经营风险自然也较大。比率大于20%的公司淘汰；',
				'特别说明：目前 应收款项融资里的应收票据、应收款项融资里的应收账款保理 系统无法获取，需要自己看财报；',
			],
		},
		fixedAssetList: {
			columnList: [{
					title: '固定资产',
					values: []
				},
				{
					title: '在建工程',
					values: []
				},
				{
					title: '工程物资',
					values: []
				},
				{
					title: '固定资产合计',
					values: []
				},
				{
					title: '固定资产合计占总资产的比率',
					values: []
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
					title: '交易性金融资产',
					values: []
				},
				{
					title: '可供出售金融资产',
					values: []
				},
				{
					title: '其他权益工具投资',
					values: []
				},
				{
					title: '其他非流动金融资产',
					values: []
				},
				{
					title: '持有至到期投资',
					values: []
				},
				{
					title: '长期股权投资',
					values: []
				},
				{
					title: '投资性房地产',
					values: []
				},
				{
					title: '与主业无关的投资类资产合计',
					values: []
				},
				{
					title: '与主业无关的投资类资产占总资产的比率',
					values: []
				},
			],
			title: '第5步：看投资类资产',
			bodyList: [
				'看投资类资产，判断公司的专注程度',
				'1、比率大于10%，不够专业，淘汰；优秀的公司一定是专注于主业的公司，与主业无关的投资类资产占总资产的比例应当很低才对，最好为0。',
				'2、如果某公司投资类资产占总资产的比率较大，就意味着这家公司在自己的行业内已经很难发现好的投资机会了；',
				'3、一家非常专注于主业的公司，在未来持续保持竞争优秀的概率较大；',
			],
		},
		incomeList: {
			columnList: [{
					title: '营业收入',
					values: []
				},
				{
					title: '营业收入增长率',
					values: []
				},
				{
					title: '销售商品、提供劳务收到的现金',
					values: []
				},
				{
					title: '销售商品、提供劳务收到的现金与营业收入的比率',
					values: []
				},
			],
			title: '6.看营业收入，判断公司的行业地位及成长能力。',
			bodyList: [

			],
		},
		grossSellingRateList: {
			columnList: [{
					title: '营业收入',
					values: []
				},
				{
					title: '营业成本',
					values: []
				},
				{
					title: '毛利',
					values: []
				},
				{
					title: '毛利率',
					values: []
				},
			],
			title: '7.看毛利率，判断公司产品的竞争力。',
			bodyList: [

			],
		},
		feeRatioList: {
			columnList: [{
					title: '销售费用',
					values: []
				},
				{
					title: '管理费用',
					values: []
				},
				{
					title: '财务费用',
					values: []
				},
				{
					title: '研发费用',
					values: []
				},
				{
					title: '四费合计',
					values: []
				},
				{
					title: '四费占营业收入比率',
					values: []
				},
				{
					title: '费用率/毛利率',
					values: []
				},
			],
			title: '8.看费用率，判断公司成本管控能力。',
			bodyList: [

			],
		},
		mainProfitList: {
			columnList: [{
					title: '营业收入',
					values: []
				},
				{
					title: '营业成本',
					values: []
				},
				{
					title: '营业税金及附加',
					values: []
				},
				{
					title: '四费合计',
					values: []
				},
				{
					title: '主营利润',
					values: []
				},
				{
					title: '主营利润率',
					values: []
				},
				{
					title: '利润总额',
					values: []
				},
				{
					title: '主营利润占利润总额的比率',
					values: []
				},
			],
			title: '9.看主营利润，判断公司的盈利能力及利润质量。',
			bodyList: [

			],
		},
		netProfitList: {
			columnList: [{
					title: '净利润',
					values: []
				},
				{
					title: '经营活动产生的现金流量净额',
					values: []
				},
				{
					title: '净利润现金比率',
					values: []
				},
			],
			title: '10.看净利润，判断公司的经营成果及含金量。',
			bodyList: [

			],
		},
		netProfitAtsopcList: {
			columnList: [{
					title: '归属于母公司所有者的净利润',
					values: []
				},
				{
					title: '归母净利润增长率',
					values: []
				},
				{
					title: 'ROE',
					values: []
				},
			],
			title: '11.看归母净利润，判断公司自有资本的获利能力。',
			bodyList: [

			],
		},
		ncfFromOaList: {
			columnList: [{
					title: '经营活动产生的现金流量净额',
					values: []
				},
				{
					title: '固定资产折旧',
					values: []
				},
				{
					title: '无形资产摊销',
					values: []
				},
				{
					title: '分配股利、利润或偿付利息支付的现金',
					values: []
				},
				{
					title: '经营现金净额-(固定折旧+无形摊销+借款利息+股利)',
					values: []
				},
			],
			title: '12.看经营活动产生的现金流量净额，判断公司的造血能力。',
			bodyList: [

			],
		},
		futureGroupList: {
			columnList: [{
					title: '购建固定资产、无形资产和其他长期资产支付的现金',
					values: []
				},
				{
					title: '经营活动产生的现金流量净额',
					values: []
				},
				{
					title: '购建支付的现金与经营活动产生的现金流量净额的比率',
					values: []
				},
				{
					title: '处置固定资产、无形资产和其他长期资产收回的现金净额',
					values: []
				},
				{
					title: '处置收回的现金净额与购建支付的现金的比率',
					values: []
				},
			],
			title: '13.看购建固定资产、无形资产和其他长期资产支付的现金，判断公司未来的成长能力。',
			bodyList: [

			],
		},
		bonusList: {
			columnList: [{
				title: '分红率',
				values: []
			}, ],
			title: '14.看分红，判断公司的品质。',
			bodyList: [

			],
		},
		typeList: {
			columnList: [{
					title: '经营活动产生的现金流量净额',
					values: []
				},
				{
					title: '投资活动产生的现金流量净额',
					values: []
				},
				{
					title: '筹资活动产生的现金流量净额',
					values: []
				},
				{
					title: '三项经营活动的类型',
					values: []
				},
			],
			title: '15.看三大活动现金流量净额的组合类型，选出最佳类型的公司。',
			bodyList: [

			],
		},
		cashAddList: {
			columnList: [{
					title: '现金及现金等价物净增加额',
					values: []
				},
				{
					title: '每年实际发放现金分红的总额',
					values: []
				},
				{
					title: '加回 现金分红 后的现金及现金等价物净增加额',
					values: []
				},
			],
			title: '16.看现金及现金等价物的净增加额，判断公司的稳定性。',
			bodyList: [

			],
		}
	}

}