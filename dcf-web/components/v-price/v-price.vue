<template>
	<view class="content">
		<view class="itme-box">
			<view class="itme-box">
				<u-collapse>
					<u-collapse-item title="主营范围">
						<view  class="title">
						<!-- {{data.body}} -->
							<view style="padding: 10rpx;overflow:scroll;">{{price.zyfw}}</view>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
			<view class="itme-box">
				<u-collapse>
					<u-collapse-item title="净利润预测">
						<view  class="title">
						<!-- {{data.body}} -->
							<view style="padding: 10rpx;">
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
												自动预测{{ item.year }}年的净利润：
												<view>{{ price.lastNetfit }} * (1+{{ price.netGroupRatioStr }})^{{ index + 1 }} = {{ item.profit }}</view>
											</view>
										</view>
									</view>
								</view>
								<view v-else class="h-table h-table-my"><view class="h-tr">归母净利润增速小于等于0，无法自动预测</view></view>
							</view>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
		
			<view class="tooltip">
				<u-alert-tips type="warning" title="风险提示" description="以上内容仅用于财报分析时作参考,不构成投资建议"></u-alert-tips>
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
