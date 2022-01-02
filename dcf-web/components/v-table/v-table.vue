<template>
	<view class="content">
		<view class="itme-box">
			<view>
				<u-collapse>
					<u-collapse-item :title="data.title">
						<view  class="title">
						<!-- {{data.body}} -->
							<view style="padding: 10rpx;" v-for="(body,index) in data.bodyList" :key="index">{{body}}</view>
						</view>
					</u-collapse-item>
				</u-collapse>
			</view>
			<view class="h-table h-table-my h-table-dashed">
				<view class="h-tr h-thead">
					<view class="h-td h-td-left" style="width: 140rpx;">年份</view>
					<view class="h-td h-td-right h-td-colspan" v-for="year in yearList" :key="year">{{year}}年</view>
				</view>
				<view class="h-tr" v-for="(item, index) in data.columnList" :key="index">
					<view class="h-td h-td-left" style="width: 140rpx;" :class="{'not-high-light':!item.mainQuato}">{{ item.title }}</view>
					<view class="h-td h-td-right  h-td-colspan" :class="{'not-high-light':!item.mainQuato,'warn':subItem.isWarn}" v-for="(subItem, subIndex) in item.values" :key="index + subIndex">{{subItem.value}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	name: 'v-table',
	props: {
		yearList: {
			type: Array,
			default: function() {
				return [];
			}
		},
		data: {
			type: Object,
			default: function() {
				return {};
			}
		}
	},
	data() {
		return {};
	}
};
</script>

<style lang="scss">
	/* 引入表格样式表 */
	@import "../../static/helang-table.scss";
	
	.not-high-light{
		color: #909399 !important;
	}
	.warn{
		background-color: #f1a532 !important;
	}
</style>