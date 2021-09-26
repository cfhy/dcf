<template>
	<view class="content">
		<view class="itme-box">
			<view>
				<u-collapse :arrow="false"><u-collapse-item title="主营范围" ></u-collapse-item></u-collapse>
			</view>
			<view class="h-table h-table-my h-table-dashed">
				<view class="h-tr">
					<view class="h-td h-td-left">{{ price.zyfw }}</view>
				</view>
			</view>
		</view>
		<view class="itme-box">
			<view>
				<u-collapse>
					<u-collapse-item title="净利润预测">
						<view class="title">
							<view style="color:#f07373">针对净利润现金含量大于 100%的好公司，我们有三种方法来预测它未来三年的净利润;</view>
							<view>方法一：在同花顺个股网站查看业绩预测详表，直接用券商预测的净利润平均值即可;</view>
							<view>方法二：根据公司过去五年“归母净利润”的复合增速和上年的“归母净利润”推算出当年和三年后的归母净利润;</view>
							<view>方法三：取用公司上年年报中经营计划对当年的净利润预测数据（如果有）;</view>
							<view>出于谨慎性原则，取用三种方法得到的最小值;</view>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
			<view>
				<view class="h-table h-table-my h-table-dashed">
					<view class="h-tr h-thead">
						<view class="h-td h-td-left" style="width: 140rpx;">年份</view>
						<view class="h-td h-td-right h-td-colspan" :key="item.year" v-for="(item, index) in price.profitList">{{ item.year }}年</view>
					</view>
					<view class="h-tr">
						<view class="h-td h-td-left" style="width: 140rpx;">机构预测</view>
						<view class="h-td h-td-right  h-td-colspan" :key="index" v-for="(item, index) in price.profitList">{{ item.orgProfit||'无机构预测' }}</view>
					</view>
					<view class="h-tr">
						<view class="h-td h-td-left" style="width: 140rpx;">自动预测(净利润增速={{ price.netGroupRatioStr}})</view>
						<view class="h-td h-td-right  h-td-colspan" :key="index" v-for="(item, index) in price.profitList">{{ item.manualProfit ||'净利润增速小于0，无法预测' }}</view>
					</view>
					<view class="h-tr">
						<view class="h-td h-td-left" style="width: 140rpx;">两者最小值</view>
						<view class="h-td h-td-right  h-td-colspan" :key="index" v-for="(item, index) in price.profitList">{{ item.minVal || '无'}}</view>
					</view>
				</view>
			</view>
		</view>
		<view class="itme-box">
			<view>
				<u-collapse ref="collapseView">
					<u-collapse-item title="好价格计算">
						<view class="title">
							<view>好价格确定方法第一种：用三年后合理市值的一半作为当年的投资好机会（好价格 1=3年后合理市值/2/总股本）；</view>
							<view>好价格确定方法第二种：在当年合理市值以下买入，具体根据个人风险承受能力,可以在合理价格的基础上打 7-9 折；</view>
							
							<view  v-if="price.profitList[0].minVal">
								<view style="padding-top:30rpx;">具体计算方式如下：</view>
								<view>5年平均资产负债率={{price.avgLiabRatio}}, 可取市盈率={{price.ttmPE}}；</view>
								<view>当年合理市值={{price.profitList[0].minVal}} * {{price.ttmPE}}= {{price.profitList[0].marketPriceStr}}；</view>
								<view>三年后合理市值={{price.profitList[2].minVal}} * {{price.ttmPE}}= {{price.profitList[2].marketPriceStr}}；</view>
								<view>总股本={{price.totalShares}}, 根据好价格的两者确定方式，可得到不同折扣下的价格；</view>
							</view>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
			<view>
				<view class="h-table h-table-my h-table-dashed">
					<view class="h-tr h-thead">
						<view class="h-td h-td-left" style="width: 140rpx;">折扣</view>
						<view class="h-td h-td-right h-td-colspan" :key="index" v-for="(item, index) in price.goodsPriceList">{{item.discount}}</view>
					</view>
					<view class="h-tr">
						<view class="h-td h-td-left" style="width: 140rpx;">对应的股价</view>
						<view class="h-td h-td-right  h-td-colspan" :key="index" v-for="(item, index) in price.goodsPriceList">{{ item.price==0.00?'无':item.price }}</view>
					</view>
				</view>
			</view>
		</view>
		<view class="tooltip">
			<u-alert-tips type="warning" title="风险提示" description="以上分析和计算逻辑来自微淼所学,仅用于财报分析时作参考,不构成投资建议"></u-alert-tips>
		</view>
	</view>
</template>

<script>
export default {
	name: 'v-price',
	props: {
		price: {
			type: Object,
			default: function() {
				return {
					avgLiabRatio: 0,
					ttmPE: 0,
					totalShares:'',
					netGroupRatioStr: '',
					profitList: [],
					forecastsProfitList: [
						{
							year: '',
							orgProfit: 0,
							manualProfit: 0,
							minVal: 0,
							marketPrice: 0,
							marketPriceStr: ''
						}
					],
					goodsPriceList: [
						{
							discount: '',
							price: ''
						}
					],
					zyfw: ''
				};
			}
		}
	},
	data() {
		return {};
	},
	watch: {
		'price.zyfw'(val) {
			if (!val) return;
			this.$nextTick(() => {
				if(this.$refs.collapseView){
					this.$refs.collapseView.init();
				}
			});
		}
	}
};
</script>

<style lang="scss">
@import '../../static/helang-table.scss';

.tooltip {
	padding: 20rpx 0px;
	padding-bottom: 200rpx;
}
</style>
