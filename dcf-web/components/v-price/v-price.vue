<template>
	<view class="content">
		<view class="itme-box">
			<view>
				<u-collapse ref="collapseView">
					<u-collapse-item title="主营范围">
						<view  class="title">
							<text>{{price.zyfw}}</text>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
			<view>
				<u-collapse>
					<u-collapse-item title="净利润预测">
						<view  class="title">
						<!-- {{data.body}} -->
							<view>
								<view class="h-table h-table-my h-table-dashed" v-if="price.orgNetfitList && price.orgNetfitList.length > 0">
									<view class="h-tr h-thead">
										<view class="h-td h-td-left" style="width: 140rpx;">年份</view>
										<view class="h-td h-td-right h-td-colspan" :key="item.year" v-for="(item, index) in price.orgNetfitList">{{item.year}}年</view>
									</view>
									<view class="h-tr">
										<view class="h-td h-td-left" style="width: 140rpx;">机构预测</view>
										<view class="h-td h-td-right  h-td-colspan" :key="index" v-for="(item, index) in price.orgNetfitList">{{item.netfit}}</view>
									</view>
								</view>
								
								<view v-else>暂无机构预测净利润</view>
								
								<u-line color="#f07373" margin="20rpx 0"/>
								
								<view v-if="price.netGroupRatio > 0">
									<view >
										<view v-for="(item, index) in price.forecastsProfitList" :key="index">
											<view>
												自动预测{{ item.year }}年的净利润：
												<view>{{ price.lastNetfit }} * (1+{{ price.netGroupRatioStr }})^{{ index + 1 }} = {{ item.profit }}</view>
											</view>
										</view>
									</view>
								</view>
								<view v-else >归母净利润增速小于等于0，无法自动预测</view>
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
					fiveDiscountPrice: 0,
					zyfw:''
				};
			}
		}
	},
	data() {
		return {};
	},
	watch:{
		'price.zyfw'(val){
			if(!val) return
			this.$nextTick(() => {
				this.$refs.collapseView.init()
			})
		}
	}
};
</script>

<style lang="scss">
@import "../../static/helang-table.scss";

.tooltip {
	padding: 20rpx 0px;
	padding-bottom: 200rpx;
}
</style>
