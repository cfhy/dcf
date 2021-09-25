<template>
	<view>
		<u-search placeholder="请输入要搜索的股票" v-model="stockName" margin="10rpx" disabled @click="searchClick" :show-action="false"></u-search>
		<u-notice-bar v-if="industry" mode="vertical" :list="[industry, '只展示同行业总资产排名前3的上市公司']" color='#f07373' style="margin-bottom: 10rpx;"></u-notice-bar>
		<view v-if="stockList && stockList.length > 0">
			<view class="container">
				<u-tabs-swiper activeColor="#f07373" ref="tabs" :list="tabs" :current="current" @change="changeTab" :is-scroll="false"></u-tabs-swiper>
				<!-- 订单列表 -->
				<view class="container-tabs__list">
					<swiper class="container-tabs__swiper" :current="swiperCurrent" @change="animationFinished">
						<swiper-item class="swiper-item" v-for="(stock, outIndex) in stockList" :key="outIndex">
							<scroll-view scroll-y style="width: 100%;height: 100%">
								<v-table
								:yearList="stock.yearList" 
								:data="stock.item[prop]" 
								v-for="(prop,stockIndex) in Object.keys(stock.item)" :key="stockIndex"></v-table>

								<v-price :price="stock.priceInfo"></v-price>
							</scroll-view>
						</swiper-item>
					</swiper>
				</view>
			</view>
		</view>
		<u-empty :show="showNoData" text="暂无相关数据" mode="data" color="#f07373" class="u-demo-area"></u-empty>
	</view>
</template>

<script>

import {stockData} from '../../common/data.js'
import {utils} from '../../common/utils.js'
export default {
	data() {
		return {
			stockName: '',
			tmpCurrent: 0,
			current: 0,
			swiperCurrent: 0,
			tabs: [],
			industry: '',
			stockList: [],
			showNoData:false
		};
	},
	onShow(options) {
		this.stockName = getApp().globalData.searchText;
		if (!this.stockName) {
			this.stockName = '贵州茅台';
		}
	},
	watch: {
		stockName(value) {
			this.getData();
		}
	},
	methods: {
		searchClick() {
			uni.navigateTo({
				url: '/pages/index/search',
				animationType: 'fade-in'
			});
		},
		getData() {
			let _this = this;
			if (!_this.stockName || _this.stockName=='cancel') {
				return;
			}
			this.$u.get('/list', {
					stockName: _this.stockName
				}).then(res => {
					_this.industry = res.industry;
					let stockList = res.list;
					if (!stockList || stockList.length === 0) {
						_this.stockList = [];
						_this.showNoData=true
						return;
					}
					_this.showNoData=false
					_this.stockList = [];
					_this.handleSheet(stockList);
					_this.$nextTick(function() {
						_this.current = _this.swiperCurrent = _this.tmpCurrent;
					});
				})
				.catch((err) => {
					console.log(err)
					uni.showToast({
						title: '请求错误，请稍后再试',
						icon: 'none'
					});
				});
		},
		//每次滑动会调用
		animationFinished(e) {
			let _this =this
			setTimeout(function(){
				let current = e.detail.current;
				_this.$refs.tabs.setFinishCurrent(current);
				_this.swiperCurrent = current;
				_this.current = current;
			},200)
		},
		changeTab(index) {
			this.swiperCurrent = index;
		},
		handleSheet(list) {
			let _this = this;
			let tabs = [];
		
			for (let i = 0; i < list.length; i++) {
				let stock = list[i];
				let stockInfo =_this.$u.deepClone(stockData);
				let netfitList = [];
				let totalLiabRatio = 0;
		
				tabs.push({ name: stock.stockName });
				if (stock.stockName == _this.stockName) {
					_this.tmpCurrent = i;
				}
		
				let lastYear = '';
				stock.sheetList.forEach(sheet => {
					_this.handleValue(stockInfo, sheet);
		
					netfitList.push(sheet.net_profit_atsopc);
					totalLiabRatio += sheet.asset_liab_ratio;
					lastYear = sheet.year;
				});
				stockInfo.priceInfo = _this.handlePrice(stock.orgNetfitList, netfitList, totalLiabRatio, lastYear,stock.zyfw);
				this.stockList.push(stockInfo);
			}
			_this.tabs = tabs;
		},
		handleValue(stockInfo, sheet) {
			let _this = this;
			stockInfo.yearList.push(sheet.year);

			stockInfo.item.totalAssetsList.columnList[0].values.push(utils.handleFee(sheet.total_assets));
			stockInfo.item.totalAssetsList.columnList[1].values.push(utils.handleRatio(sheet.total_assets_yoy));
			stockInfo.item.totalAssetsList.columnList[2].values.push(utils.handleFee(sheet.total_liab));
			stockInfo.item.totalAssetsList.columnList[3].values.push(utils.handleRatio(sheet.asset_liab_ratio));

			stockInfo.item.liabList.columnList[0].values.push(utils.handleFee(sheet.currency_funds));
			stockInfo.item.liabList.columnList[1].values.push(utils.handleFee(sheet.tradable_fnncl_assets));
			stockInfo.item.liabList.columnList[2].values.push(utils.handleFee(sheet.othr_current_assets_lccp));
			stockInfo.item.liabList.columnList[3].values.push(utils.handleFee(sheet.trade_financial_jgxck));
			stockInfo.item.liabList.columnList[4].values.push(utils.handleFee(sheet.currency_funds_plus));
			stockInfo.item.liabList.columnList[5].values.push(utils.handleFee(sheet.st_loan));
			stockInfo.item.liabList.columnList[6].values.push(utils.handleFee(sheet.noncurrent_liab_due_in1y));
			stockInfo.item.liabList.columnList[7].values.push(utils.handleFee(sheet.lt_loan));
			stockInfo.item.liabList.columnList[8].values.push(utils.handleFee(sheet.bond_payable));
			stockInfo.item.liabList.columnList[9].values.push(utils.handleFee(sheet.lt_payable));
			stockInfo.item.liabList.columnList[10].values.push(utils.handleFee(sheet.total_liab_with_interest));
			stockInfo.item.liabList.columnList[11].values.push(utils.handleFee(sheet.liab_zhbzj_with_yxfz_diff));
			stockInfo.item.liabList.columnList[12].values.push(utils.handleFee(sheet.total_st_liab));
			stockInfo.item.liabList.columnList[13].values.push(utils.handleFee(sheet.currency_funds_with_dqfz_diff));

			stockInfo.item.arBrApBpList.columnList[0].values.push(utils.handleFee(sheet.bill_payable));
			stockInfo.item.arBrApBpList.columnList[1].values.push(utils.handleFee(sheet.accounts_payable));
			stockInfo.item.arBrApBpList.columnList[2].values.push(utils.handleFee(sheet.pre_receivable));
			stockInfo.item.arBrApBpList.columnList[3].values.push(utils.handleFee(sheet.contract_liabilities));
			stockInfo.item.arBrApBpList.columnList[4].values.push(utils.handleFee(sheet.total_ap_bp_pr));
			stockInfo.item.arBrApBpList.columnList[5].values.push(utils.handleFee(sheet.bills_receivable));
			stockInfo.item.arBrApBpList.columnList[6].values.push(utils.handleFee(sheet.account_receivable));
			stockInfo.item.arBrApBpList.columnList[7].values.push(utils.handleFee(sheet.finance_rece));
			stockInfo.item.arBrApBpList.columnList[8].values.push(utils.handleFee(sheet.pre_payment));
			stockInfo.item.arBrApBpList.columnList[9].values.push(utils.handleFee(sheet.contractual_assets));
			stockInfo.item.arBrApBpList.columnList[10].values.push(utils.handleFee(sheet.total_ar_br));
			stockInfo.item.arBrApBpList.columnList[11].values.push(utils.handleFee(sheet.ap_bp_pr_with_ar_br_diff));
			stockInfo.item.arBrApBpList.columnList[12].values.push(utils.handleRatio(sheet.account_receivable_ratio));

			stockInfo.item.fixedAssetList.columnList[0].values.push(utils.handleFee(sheet.fixed_asset));
			stockInfo.item.fixedAssetList.columnList[1].values.push(utils.handleFee(sheet.construction_in_process));
			stockInfo.item.fixedAssetList.columnList[2].values.push(utils.handleFee(sheet.project_goods_and_material));
			stockInfo.item.fixedAssetList.columnList[3].values.push(utils.handleFee(sheet.total_fixed_asset));
			stockInfo.item.fixedAssetList.columnList[4].values.push(utils.handleRatio(sheet.fixed_asset_ratio));

			stockInfo.item.investmentAssetsList.columnList[0].values.push(utils.handleFee(sheet.creditor_invest));
			stockInfo.item.investmentAssetsList.columnList[1].values.push(utils.handleFee(sheet.other_creditor_invest));
			stockInfo.item.investmentAssetsList.columnList[2].values.push(utils.handleFee(sheet.fvtpl_finassets));
			stockInfo.item.investmentAssetsList.columnList[3].values.push(utils.handleFee(sheet.saleable_finacial_assets));
			stockInfo.item.investmentAssetsList.columnList[4].values.push(utils.handleFee(sheet.held_to_maturity_invest));
			stockInfo.item.investmentAssetsList.columnList[5].values.push(utils.handleFee(sheet.lt_equity_invest));
			stockInfo.item.investmentAssetsList.columnList[6].values.push(utils.handleFee(sheet.invest_property));
			stockInfo.item.investmentAssetsList.columnList[7].values.push(utils.handleFee(sheet.othr_equity_instruments));
			stockInfo.item.investmentAssetsList.columnList[8].values.push(utils.handleFee(sheet.other_illiquid_fnncl_assets));
			stockInfo.item.investmentAssetsList.columnList[9].values.push(utils.handleFee(sheet.total_fnncl_assets));
			stockInfo.item.investmentAssetsList.columnList[10].values.push(utils.handleRatio(sheet.total_fnncl_assets_ratio));

			stockInfo.item.inventoryList.columnList[0].values.push(utils.handleRatio(sheet.only_account_receivable_ratio));
			stockInfo.item.inventoryList.columnList[1].values.push(utils.handleFee(sheet.inventory));
			stockInfo.item.inventoryList.columnList[2].values.push(utils.handleRatio(sheet.inventory_with_assets_ratio));
			stockInfo.item.inventoryList.columnList[3].values.push(utils.handleFee(sheet.goodwill));
			stockInfo.item.inventoryList.columnList[4].values.push(utils.handleRatio(sheet.goodwill_with_assets_ratio));

			stockInfo.item.incomeList.columnList[0].values.push(utils.handleFee(sheet.operate_income));
			stockInfo.item.incomeList.columnList[1].values.push(utils.handleRatio(sheet.operate_income_yoy));
			stockInfo.item.incomeList.columnList[2].values.push(utils.handleFee(sheet.cash_received_of_sales_service));
			stockInfo.item.incomeList.columnList[3].values.push(utils.handleRatio(sheet.cash_received_of_sales_service_ratio));

			stockInfo.item.grossSellingRateList.columnList[0].values.push(utils.handleFee(sheet.operate_income));
			stockInfo.item.grossSellingRateList.columnList[1].values.push(utils.handleFee(sheet.operating_costs));
			stockInfo.item.grossSellingRateList.columnList[2].values.push(utils.handleFee(sheet.gross_profit));
			stockInfo.item.grossSellingRateList.columnList[3].values.push(utils.handleRatio(sheet.gross_profit_ratio));
			stockInfo.item.grossSellingRateList.columnList[4].values.push(utils.handleRatio(sheet.gross_profit_ratio_wave));

			stockInfo.item.feeRatioList.columnList[0].values.push(utils.handleFee(sheet.sales_fee));
			stockInfo.item.feeRatioList.columnList[1].values.push(utils.handleFee(sheet.manage_fee));
			stockInfo.item.feeRatioList.columnList[2].values.push(utils.handleFee(sheet.financing_expenses));
			stockInfo.item.feeRatioList.columnList[3].values.push(utils.handleFee(sheet.rad_cost));
			stockInfo.item.feeRatioList.columnList[4].values.push(utils.handleFee(sheet.total_fee));
			stockInfo.item.feeRatioList.columnList[5].values.push(utils.handleRatio(sheet.total_fee_ratio));
			stockInfo.item.feeRatioList.columnList[6].values.push(utils.handleRatio(sheet.total_fee_with_mll_ratio));
			
			stockInfo.item.saleFeeList.columnList[0].values.push(utils.handleRatio(sheet.sales_fee_ratio));

			stockInfo.item.mainProfitList.columnList[0].values.push(utils.handleFee(sheet.operate_income));
			stockInfo.item.mainProfitList.columnList[1].values.push(utils.handleFee(sheet.operating_costs));
			stockInfo.item.mainProfitList.columnList[2].values.push(utils.handleFee(sheet.operating_taxes_and_surcharge));
			stockInfo.item.mainProfitList.columnList[3].values.push(utils.handleFee(sheet.total_fee));
			stockInfo.item.mainProfitList.columnList[4].values.push(utils.handleFee(sheet.main_profit));
			stockInfo.item.mainProfitList.columnList[5].values.push(utils.handleRatio(sheet.main_profit_ratio));
			stockInfo.item.mainProfitList.columnList[6].values.push(utils.handleFee(sheet.operate_profit));
			stockInfo.item.mainProfitList.columnList[7].values.push(utils.handleRatio(sheet.main_profit_with_profit_total_ratio));

			stockInfo.item.netProfitList.columnList[0].values.push(utils.handleFee(sheet.net_profit));
			stockInfo.item.netProfitList.columnList[1].values.push(utils.handleFee(sheet.ncf_from_oa));
			stockInfo.item.netProfitList.columnList[2].values.push(utils.handleRatio(sheet.net_profit_ratio));

			stockInfo.item.netProfitAtsopcList.columnList[0].values.push(utils.handleFee(sheet.net_profit_atsopc));
			stockInfo.item.netProfitAtsopcList.columnList[1].values.push(utils.handleRatio(sheet.parent_netprofit_yoy));
			stockInfo.item.netProfitAtsopcList.columnList[2].values.push(utils.handleRatio(sheet.roe));
			stockInfo.item.netProfitAtsopcList.columnList[3].values.push(utils.handleFee(sheet.ncf_from_oa));
			stockInfo.item.netProfitAtsopcList.columnList[4].values.push(utils.handleRatio(sheet.netcash_operate_yoy));

			stockInfo.item.futureGroupList.columnList[0].values.push(utils.handleFee(sheet.cash_paid_for_assets));
			stockInfo.item.futureGroupList.columnList[1].values.push(utils.handleFee(sheet.ncf_from_oa));
			stockInfo.item.futureGroupList.columnList[2].values.push(utils.handleRatio(sheet.buy_ratio));

			stockInfo.item.bonusList.columnList[0].values.push(utils.handleFee(sheet.bonus_fee));
			stockInfo.item.bonusList.columnList[1].values.push(utils.handleFee(sheet.net_profit_atsopc));
			stockInfo.item.bonusList.columnList[2].values.push(utils.handleRatio(sheet.bonus_ratio));

			stockInfo.item.typeList.columnList[0].values.push(utils.handleFee(sheet.ncf_from_oa));
			stockInfo.item.typeList.columnList[1].values.push(utils.handleFee(sheet.ncf_from_ia));
			stockInfo.item.typeList.columnList[2].values.push(utils.handleFee(sheet.ncf_from_fa));
			stockInfo.item.typeList.columnList[3].values.push(sheet.type);
		},
		handlePrice(orgNetfitList, netfitList, totalLiabRatio, lastYear,zyfw) {
			let _this = this;
			let priceInfo = {
				avgLiabRatio: 0,
				ttmPE: 0,
				lastNetfit: 0,
				netGroupRatio: 0,
				netGroupRatioStr: '',
				orgNetfitList: orgNetfitList,
				forecastsProfitList: [],
				forecastsMarketPrice: 0,
				fiveDiscountPrice: 0,
				zyfw:zyfw
			};
			//计算净利润增速
			let netGroupRatio = utils.handleNetGroupRatio(netfitList[0], netfitList[netfitList.length - 1], netfitList.length - 1);

			if (netGroupRatio <= 0) {
				return priceInfo;
			}
			let avgLiabRatio = totalLiabRatio / 5;
			priceInfo.ttmPE = avgLiabRatio >= 70 ? 12 : 20;
			priceInfo.avgLiabRatio = utils.handleRatio(avgLiabRatio);

			let lastNetfit = netfitList[netfitList.length - 1];
			priceInfo.lastNetfit = utils.handleFee(lastNetfit);

			priceInfo.netGroupRatio = netGroupRatio;
			priceInfo.netGroupRatioStr = utils.handleRatio(netGroupRatio * 100);

			let groupRatio = 1 + parseFloat(netGroupRatio);
			let first = lastNetfit * groupRatio;
			let second = first * groupRatio;
			let third = second * groupRatio;

			priceInfo.forecastsProfitList.push({ year: lastYear + 1, profit:utils.handleFee(first) });
			priceInfo.forecastsProfitList.push({ year: lastYear + 2, profit:utils.handleFee(second) });
			priceInfo.forecastsProfitList.push({ year: lastYear + 3, profit:utils.handleFee(third) });

			let marketPrice = third * priceInfo.ttmPE;
			priceInfo.forecastsMarketPrice = utils.handleFee(marketPrice);
			priceInfo.fiveDiscountPrice = utils.handleFee(marketPrice * 0.5);
			return priceInfo;
		}
	}
};
</script>

<style lang="scss" scoped>
.container {
	/* #ifdef H5 */
	height: calc(100vh - 24px - 50px - 100rpx);
	/* #endif */
	/* #ifndef H5 */
	height: calc(100vh - 160rpx);
	/* #endif */
	background-color: #f3f3f3;
	padding: 1rpx;

	.container-tabs__list {
		padding-top: 10rpx;
		height: 100%;

		.container-tabs__swiper {
			height: 100%;
		}
	}
}
.tooltip {
	color: $uni-color-warning;
	padding: 20rpx 6rpx;
}

.u-demo-area {
	height: 160px;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
