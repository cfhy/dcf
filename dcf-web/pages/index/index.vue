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
				stockInfo.priceInfo = _this.handlePrice(stock.orgNetfitList, netfitList, totalLiabRatio, lastYear);
				this.stockList.push(stockInfo);
				console.log(stockInfo)
			}
			_this.tabs = tabs;
		},
		handleValue(stockInfo, sheet) {
			let _this = this;
			stockInfo.yearList.push(sheet.year);

			stockInfo.item.totalAssetsList.columnList[0].values.push(_this.handleFee(sheet.total_assets));
			stockInfo.item.totalAssetsList.columnList[1].values.push(_this.handleRatio(sheet.total_assets_group_ratio));
			stockInfo.item.totalAssetsList.columnList[2].values.push(_this.handleFee(sheet.total_liab));
			stockInfo.item.totalAssetsList.columnList[3].values.push(_this.handleRatio(sheet.asset_liab_ratio));

			stockInfo.item.liabList.columnList[0].values.push(_this.handleFee(sheet.currency_funds));
			stockInfo.item.liabList.columnList[1].values.push(_this.handleFee(sheet.other_financial_product));
			stockInfo.item.liabList.columnList[2].values.push(_this.handleFee(sheet.trade_financial_product));
			stockInfo.item.liabList.columnList[3].values.push(_this.handleFee(sheet.currency_funds_plus));
			stockInfo.item.liabList.columnList[4].values.push(_this.handleFee(sheet.st_loan));
			stockInfo.item.liabList.columnList[5].values.push(_this.handleFee(sheet.noncurrent_liab_due_in1y));
			stockInfo.item.liabList.columnList[6].values.push(_this.handleFee(sheet.lt_loan));
			stockInfo.item.liabList.columnList[7].values.push(_this.handleFee(sheet.bond_payable));
			stockInfo.item.liabList.columnList[8].values.push(_this.handleFee(sheet.lt_payable));
			stockInfo.item.liabList.columnList[9].values.push(_this.handleFee(sheet.total_liab_with_interest));
			stockInfo.item.liabList.columnList[10].values.push(_this.handleFee(sheet.liab_zhbzj_with_yxfz_diff));

			stockInfo.item.arBrApBpList.columnList[0].values.push(_this.handleFee(sheet.bill_payable));
			stockInfo.item.arBrApBpList.columnList[1].values.push(_this.handleFee(sheet.accounts_payable));
			stockInfo.item.arBrApBpList.columnList[2].values.push(_this.handleFee(sheet.pre_receivable));
			stockInfo.item.arBrApBpList.columnList[3].values.push(_this.handleFee(sheet.total_ap_bp_pr));
			stockInfo.item.arBrApBpList.columnList[4].values.push(_this.handleFee(sheet.bills_receivable));
			stockInfo.item.arBrApBpList.columnList[5].values.push(_this.handleFee(sheet.bills_receivable_plus));
			stockInfo.item.arBrApBpList.columnList[6].values.push(_this.handleFee(sheet.account_receivable));
			stockInfo.item.arBrApBpList.columnList[7].values.push(_this.handleFee(sheet.account_receivable_plus));
			stockInfo.item.arBrApBpList.columnList[8].values.push(_this.handleFee(sheet.pre_payment));
			stockInfo.item.arBrApBpList.columnList[9].values.push(_this.handleFee(sheet.total_ar_br));
			stockInfo.item.arBrApBpList.columnList[10].values.push(_this.handleFee(sheet.ap_bp_pr_with_ar_br_diff));
			stockInfo.item.arBrApBpList.columnList[11].values.push(_this.handleRatio(sheet.account_receivable_ratio));

			stockInfo.item.fixedAssetList.columnList[0].values.push(_this.handleFee(sheet.fixed_asset));
			stockInfo.item.fixedAssetList.columnList[1].values.push(_this.handleFee(sheet.construction_in_process));
			stockInfo.item.fixedAssetList.columnList[2].values.push(_this.handleFee(sheet.project_goods_and_material));
			stockInfo.item.fixedAssetList.columnList[3].values.push(_this.handleFee(sheet.total_fixed_asset));
			stockInfo.item.fixedAssetList.columnList[4].values.push(_this.handleRatio(sheet.fixed_asset_ratio));

			stockInfo.item.investmentAssetsList.columnList[0].values.push(_this.handleFee(sheet.tradable_fnncl_assets));
			stockInfo.item.investmentAssetsList.columnList[1].values.push(_this.handleFee(sheet.saleable_finacial_assets));
			stockInfo.item.investmentAssetsList.columnList[2].values.push(_this.handleFee(sheet.othr_equity_instruments));
			stockInfo.item.investmentAssetsList.columnList[3].values.push(_this.handleFee(sheet.other_illiquid_fnncl_assets));
			stockInfo.item.investmentAssetsList.columnList[4].values.push(_this.handleFee(sheet.held_to_maturity_invest));
			stockInfo.item.investmentAssetsList.columnList[5].values.push(_this.handleFee(sheet.lt_equity_invest));
			stockInfo.item.investmentAssetsList.columnList[6].values.push(_this.handleFee(sheet.invest_property));
			stockInfo.item.investmentAssetsList.columnList[7].values.push(_this.handleFee(sheet.total_fnncl_assets));
			stockInfo.item.investmentAssetsList.columnList[8].values.push(_this.handleRatio(sheet.total_fnncl_assets_ratio));

			stockInfo.item.incomeList.columnList[0].values.push(_this.handleFee(sheet.revenue));
			stockInfo.item.incomeList.columnList[1].values.push(_this.handleRatio(sheet.revenue_group_ratio));
			stockInfo.item.incomeList.columnList[2].values.push(_this.handleFee(sheet.cash_received_of_sales_service));
			stockInfo.item.incomeList.columnList[3].values.push(_this.handleRatio(sheet.cash_received_of_sales_service_ratio));

			stockInfo.item.grossSellingRateList.columnList[0].values.push(_this.handleFee(sheet.revenue));
			stockInfo.item.grossSellingRateList.columnList[1].values.push(_this.handleFee(sheet.operating_costs));
			stockInfo.item.grossSellingRateList.columnList[2].values.push(_this.handleFee(sheet.revenue_with_operating_costs_diff));
			stockInfo.item.grossSellingRateList.columnList[3].values.push(_this.handleRatio(sheet.revenue_with_operating_costs_diff_ratio));

			stockInfo.item.feeRatioList.columnList[0].values.push(_this.handleFee(sheet.sales_fee));
			stockInfo.item.feeRatioList.columnList[1].values.push(_this.handleFee(sheet.manage_fee));
			stockInfo.item.feeRatioList.columnList[2].values.push(_this.handleFee(sheet.rad_cost));
			stockInfo.item.feeRatioList.columnList[3].values.push(_this.handleFee(sheet.financing_expenses));
			stockInfo.item.feeRatioList.columnList[4].values.push(_this.handleFee(sheet.total_fee));
			stockInfo.item.feeRatioList.columnList[5].values.push(_this.handleRatio(sheet.total_fee_ratio));
			stockInfo.item.feeRatioList.columnList[6].values.push(_this.handleRatio(sheet.total_fee_with_mll_ratio));

			stockInfo.item.mainProfitList.columnList[0].values.push(_this.handleFee(sheet.revenue));
			stockInfo.item.mainProfitList.columnList[1].values.push(_this.handleFee(sheet.operating_costs));
			stockInfo.item.mainProfitList.columnList[2].values.push(_this.handleFee(sheet.operating_taxes_and_surcharge));
			stockInfo.item.mainProfitList.columnList[3].values.push(_this.handleFee(sheet.total_fee));
			stockInfo.item.mainProfitList.columnList[4].values.push(_this.handleFee(sheet.main_profit));
			stockInfo.item.mainProfitList.columnList[5].values.push(_this.handleRatio(sheet.main_profit_ratio));
			stockInfo.item.mainProfitList.columnList[6].values.push(_this.handleFee(sheet.profit_total_amt));
			stockInfo.item.mainProfitList.columnList[7].values.push(_this.handleRatio(sheet.main_profit_with_profit_total_ratio));

			stockInfo.item.netProfitList.columnList[0].values.push(_this.handleFee(sheet.net_profit));
			stockInfo.item.netProfitList.columnList[1].values.push(_this.handleFee(sheet.ncf_from_oa));
			stockInfo.item.netProfitList.columnList[2].values.push(_this.handleRatio(sheet.net_profit_ratio));

			stockInfo.item.netProfitAtsopcList.columnList[0].values.push(_this.handleFee(sheet.net_profit_atsopc));
			stockInfo.item.netProfitAtsopcList.columnList[1].values.push(_this.handleRatio(sheet.net_profit_atsopc_group_ratio));
			stockInfo.item.netProfitAtsopcList.columnList[2].values.push(sheet.roe);

			stockInfo.item.ncfFromOaList.columnList[0].values.push(_this.handleFee(sheet.ncf_from_oa));
			stockInfo.item.ncfFromOaList.columnList[1].values.push(_this.handleFee(sheet.asset_depreciation));
			stockInfo.item.ncfFromOaList.columnList[2].values.push(_this.handleFee(sheet.intangible_assets_amortize));
			stockInfo.item.ncfFromOaList.columnList[3].values.push(_this.handleFee(sheet.cash_paid_of_distribution));
			stockInfo.item.ncfFromOaList.columnList[4].values.push(_this.handleFee(sheet.ncf_from_oa_diff));

			stockInfo.item.futureGroupList.columnList[0].values.push(_this.handleFee(sheet.cash_paid_for_assets));
			stockInfo.item.futureGroupList.columnList[1].values.push(_this.handleFee(sheet.ncf_from_oa));
			stockInfo.item.futureGroupList.columnList[2].values.push(_this.handleRatio(sheet.buy_ratio));
			stockInfo.item.futureGroupList.columnList[3].values.push(_this.handleFee(sheet.net_cash_of_disposal_assets));
			stockInfo.item.futureGroupList.columnList[4].values.push(_this.handleRatio(sheet.sale_ratio));

			stockInfo.item.bonusList.columnList[0].values.push(_this.handleRatio(sheet.bonus_ratio));

			stockInfo.item.typeList.columnList[0].values.push(_this.handleFee(sheet.ncf_from_oa));
			stockInfo.item.typeList.columnList[1].values.push(_this.handleFee(sheet.ncf_from_ia));
			stockInfo.item.typeList.columnList[2].values.push(_this.handleFee(sheet.ncf_from_fa));
			stockInfo.item.typeList.columnList[3].values.push(sheet.type);

			stockInfo.item.cashAddList.columnList[0].values.push(_this.handleFee(sheet.net_increase_in_cce));
			stockInfo.item.cashAddList.columnList[1].values.push(_this.handleFee(sheet.total_bonus));
			stockInfo.item.cashAddList.columnList[2].values.push(_this.handleFee(sheet.total_cash));
		},
		handlePrice(orgNetfitList, netfitList, totalLiabRatio, lastYear) {
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
				fiveDiscountPrice: 0
			};
			//计算净利润增速
			let netGroupRatio = _this.handleNetGroupRatio(netfitList[0], netfitList[netfitList.length - 1], netfitList.length - 1);

			if (netGroupRatio <= 0) {
				return;
			}
			let avgLiabRatio = totalLiabRatio / 5;
			priceInfo.ttmPE = avgLiabRatio >= 70 ? 12 : 20;
			priceInfo.avgLiabRatio = _this.handleRatio(avgLiabRatio);

			let lastNetfit = netfitList[netfitList.length - 1];
			priceInfo.lastNetfit = _this.handleFee(lastNetfit);

			priceInfo.netGroupRatio = netGroupRatio;
			priceInfo.netGroupRatioStr = _this.handleRatio(netGroupRatio * 100);

			let groupRatio = 1 + parseFloat(netGroupRatio);
			let first = lastNetfit * groupRatio;
			let second = first * groupRatio;
			let third = second * groupRatio;

			priceInfo.forecastsProfitList.push({ year: lastYear + 1, profit: _this.handleFee(first) });
			priceInfo.forecastsProfitList.push({ year: lastYear + 2, profit: _this.handleFee(second) });
			priceInfo.forecastsProfitList.push({ year: lastYear + 3, profit: _this.handleFee(third) });

			let marketPrice = third * priceInfo.ttmPE;
			priceInfo.forecastsMarketPrice = _this.handleFee(marketPrice);
			priceInfo.fiveDiscountPrice = _this.handleFee(marketPrice * 0.5);

			return priceInfo;
		},
		handleRatio(fee) {
			if (!fee) return '';
			return fee.toFixed(2) + '%';
		},
		handleFee(fee) {
			let str = '';
			if (!fee) return str;
			let yi = ((fee * 1.0) / 100000000).toFixed(2);
			if (Math.abs(yi) < 1) {
				str = ((fee * 1.0) / 10000).toFixed(2) + '万';
			} else {
				str = yi + '亿';
			}
			return str;
		},
		handleNetGroupRatio(startNet, endNet, year) {
			//start*(1+rate)^5=end  => (1+rate)^5 = end/start
			let res = 0;
			if (startNet > 0 && endNet > 0) {
				res = (Math.pow((endNet * 1.0) / startNet, 1.0 / year) - 1).toFixed(2);
			}
			return res;
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
