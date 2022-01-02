<template>
	<view>
		<u-search placeholder="请输入要搜索的股票" v-model="stockName" margin="10rpx" disabled @click="searchClick" :show-action="false"></u-search>
		<u-notice-bar v-if="industry" mode="vertical" :list="[industry, '只展示同行业总资产排名前3的上市公司','不支持银行、保险相关股票的分析']" color='#f07373' style="margin-bottom: 10rpx;"></u-notice-bar>
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
			showNoData:false,
			longitude:0,
			latitude:0
		};
	},
	onShow(options) {
		let _this = this
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
				stockInfo.priceInfo = _this.handlePrice(stock.orgNetfitList, netfitList, totalLiabRatio, lastYear,stock.zyfw,stock.totalShares);
				this.stockList.push(stockInfo);
			}
			_this.tabs = tabs;
		},
		handleValue(stockInfo, sheet) {
			stockInfo.yearList.push(sheet.year);
			//遍历item
			let props= Object.keys(stockInfo.item);
			props.forEach(prop=>{
				let columnList=stockInfo.item[prop].columnList;
				columnList.forEach(column=>{
					let val=sheet[column.fieldName];
					let isWarn=false;
					let showVal=val;
					if(column.min!=undefined && column.min!=null){
						if(val<=column.min){
							isWarn=true;
						}
					}
					if(column.max!=undefined && column.max!=null){
						if(val>=column.max){
							isWarn=true;
						}
					}
					column.values.push({
						isWarn:isWarn,
						value:column.notCalc?val: column.ratio?utils.handleRatio(val):utils.handleFee(val)
					})
				})
			})
		},
		handlePrice(orgNetfitList, netfitList, totalLiabRatio, lastYear,zyfw,totalShares) {
			let _this = this;
			//计算净利润增速
			let netGroupRatio = utils.handleNetGroupRatio(netfitList[0], netfitList[netfitList.length - 1], netfitList.length - 1);
			let avgLiabRatio = totalLiabRatio / 5;
			let ttmPE = avgLiabRatio >= 70 ? 12 : 20;
			let lastNetfit = netfitList[netfitList.length - 1];
			
			let priceInfo = {
				avgLiabRatio: utils.handleRatio(avgLiabRatio),
				ttmPE:ttmPE,
				netGroupRatioStr: utils.handleRatio(netGroupRatio*100),
				profitList: [],
				goodsPriceList:[],
				zyfw:zyfw,
				totalShares:utils.handleFee(totalShares)
			};
			
			let orgList=orgNetfitList || []
			let manualList=[];
			if (netGroupRatio > 0) {
				let groupRatio = 1 + parseFloat(netGroupRatio);
				let first = lastNetfit * groupRatio;
				let second = first * groupRatio;
				let third = second * groupRatio;
				
				manualList.push({ year: lastYear + 1, netfit:first });
				manualList.push({ year: lastYear + 2, netfit:second });
				manualList.push({ year: lastYear + 3, netfit:third });
			}
			for(let i=1;i<=3;i++){
				let year=lastYear+i;
				let orgs = orgList.filter(item => {return item.year==year});
				let org=orgs && orgs.length>0?orgs[0]:{year:year,netfit:0};
				let manuals = manualList.filter(item=>{return item.year==year});
				let manual=manuals && manuals.length>0?manuals[0]:{year:year,netfit:0};
				
				let minVal=0;
				if(netGroupRatio>0){
					if(!orgs || orgs.length==0){
						minVal=manual.netfit;
					}else{
						minVal=org.netfit>manual.netfit?manual.netfit:org.netfit;
					}
				}else{
					minVal=org.netfit;
				}
				
				priceInfo.profitList.push({
					year:year,
					orgProfit:utils.handleFee(org.netfit),
					manualProfit:utils.handleFee(manual.netfit),
					minVal:utils.handleFee(minVal),
					marketPrice:minVal * ttmPE,
					marketPriceStr:utils.handleFee(minVal * ttmPE)
				})
			}
			//好价格确定方法第一种：用三年后合理市值的一半作为当年的投资好机会（好价格 1=3年后合理市值/2/总股本）。
			let fiveDiscount=(priceInfo.profitList[2].marketPrice/(totalShares*2)).toFixed(2);
			//好价格确定方法第二种：在当年合理市值以下买入，具体根据个人风险承受能力不同来制定买入标准。
			let sevenDiscount=(priceInfo.profitList[0].marketPrice/totalShares*0.7).toFixed(2);
			let eightDiscount=(priceInfo.profitList[0].marketPrice/totalShares*0.8).toFixed(2);
			let nineDiscount=(priceInfo.profitList[0].marketPrice/totalShares*0.9).toFixed(2);
			priceInfo.goodsPriceList.push({
				discount:'5折',
				price:fiveDiscount
			});
			priceInfo.goodsPriceList.push({
				discount:'7折',
				price:sevenDiscount
			});
			priceInfo.goodsPriceList.push({
				discount:'8折',
				price:eightDiscount
			});
			priceInfo.goodsPriceList.push({
				discount:'9折',
				price:nineDiscount
			});
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
