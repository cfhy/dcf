<template>
	<view class="content">
		<view class="itme-box">
			<view class="title">17.好价格计算</view>
			
			<view class="h-table h-table-my" v-if="price.orgNetfitList && price.orgNetfitList.length > 0">
				<view class="h-tr" :key="index"  v-for="(item, index) in price.orgNetfitList">
					<view>机构预测{{ item.year }}年的净利润：{{ item.netfit }}</view>
				</view>
			</view>
			<view v-else class="h-table h-table-my"><view class="h-tr">暂无机构预测净利润</view></view>
			
			<u-line color="#f07373" margin="20rpx 0"/>
			
			<view v-if="price.netGroupRatio > 0">
				<view class="h-table h-table-my">
					<view class="h-tr" :key="index" v-for="(item, index) in price.forecastsProfitList">
						<view>
							自动预测{{ item.year }}年的净利润：{{ price.lastNetfit }} * (1+{{ price.netGroupRatioStr }})^{{ index + 1 }} = {{ item.profit }}
						</view>
					</view>
					
					<u-line color="#f07373" margin="20rpx 0"/>
					
					<view class="h-tr">近5年的平均资产负债率：{{ price.avgLiabRatio }}，取市盈率：{{ price.ttmPE }}</view>
					<view class="h-tr">3年后的预测总市值：{{ price.forecastsProfitList[2].profit }} * {{ price.ttmPE }}={{ price.forecastsMarketPrice }}</view>
					<view class="h-tr">最终得出当前合理总市值（打五折）：{{ price.fiveDiscountPrice }}</view>
				</view>
			</view>
			<view v-else class="h-table h-table-my"><view class="h-tr">归母净利润增速小于等于0，无法自动预测</view></view>
			<view class="tooltip">
				<u-notice-bar mode="horizontal" :list="['风险提示：最终计算出来的合理市值仅作参考，是否合理请自行判断。']" :is-circular="true" color='#f07373'></u-notice-bar>
			</view>
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
					netGroupRatio: 0,
					lastNetfitStr: '',
					netGroupRatioStr: '',
					orgNetfitList: [],
					forecastsProfitList: [],
					forecastsMarketPrice: 0,
					fiveDiscountPrice: 0
				};
			}
		}
	},
	data() {
		return {};
	}
};
</script>

<style lang="scss">
@import "../../static/helang-table.scss";

.h-table-my {
	.h-tr {
		border:none;
		padding: 6rpx;
	}
}
.tooltip {
	padding: 20rpx 0px;
	padding-bottom: 200rpx;
}
</style>
