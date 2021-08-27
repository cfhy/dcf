<template>
	<view class="container">
		<view class="search-container">
			<!-- 搜索框 -->
			<view class="search-container-bar">
				<u-search placeholder="请输入要搜索的股票" 
				shape="round"
				clearabled
				show-action
				:maxlength="10"
				v-model="searchText" 
				:focus="focusStatus"
				margin="10rpx" 
				@search="confirm"
				@custom="cancel"
				@clear="clear"
				action-text="取消"
				/>
			</view>
		</view>
		<!-- 搜索 -->
		<view class="search-associative" v-if="associativeShow">
			<uni-list>
				<template v-for="(item, index) in associativeList">
					<uni-list-item
						:key="index"
						:ellipsis="1"
						:title="item.stock_name"
						@click="associativeClick(item)"
						show-extra-icon
						clickable
						:extra-icon="{ size: 18, color: iconColor, type: 'search' }"
					/>
				</template>
			</uni-list>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			searchText: '',
			focusStatus:true,
			iconColor: '#999999',
			associativeList: []
		};
	},
	created() {
		this.searchText = getApp().globalData.searchText;
	},
	methods: {
		clear() {
			this.searchText = '';
			this.associativeList.length = 0;
		},
		confirm(value) {
			if (!value) {
				return;
			}
			uni.hideKeyboard();
			this.loadList(value);
		},
		cancel(value) {
			uni.hideKeyboard();
			this.searchText = 'cancel';
			uni.navigateBack();
		},
		associativeClick(item) {
			this.loadList(item.stock_name);
		},
		loadList(text = '') {
			getApp().globalData.searchText = text;
			uni.navigateBack();
		},
		searchData(){
			let _this = this
			this.$u.get('/search', {
				keyword: _this.searchText
			}).then(res => {
				let data=res ||[];
				_this.associativeList=res ||[];
			}).catch(()=>{
				
			})
		}
	},
	computed: {
		associativeShow() {
			return this.searchText && this.associativeList.length;
		}
	},
	watch: {
		searchText(value) {
			if (value && value!='cancel') {
				this.$u.debounce(this.searchData,200)
			}
		}
	}
};
</script>

<style>
page {
	/* #ifndef APP-NVUE */
	height: 100%;
	/* #endif */
	flex: 1;
}
</style>

<style lang="scss" scoped>
$search-bar-height: 52px;

.container {
	/* #ifndef APP-NVUE */
	height: 100%;
	/* #endif */
	flex: 1;
	background-color: #f7f7f7;
}

.search-body {
	background-color: #fff;
	border-bottom-right-radius: 10px;
	border-bottom-left-radius: 10px;
}

@mixin uni-flex {
	/* #ifndef APP-NVUE */
	display: flex;
	/* #endif */
}

@mixin words-display {
	font-size: 26rpx;
	color: #666;
}

/* #ifdef APP-PLUS */
/* #ifndef APP-NVUE */
/deep/
/* #endif */
.uni-searchbar {
	padding-left: 0;
}

/* #endif */

/* #ifndef APP-NVUE */
/deep/
/* #endif */
.uni-searchbar__box {
	border-width: 0;
}

/* #ifndef APP-NVUE */
/deep/
/* #endif */
.uni-input-placeholder {
	font-size: 28rpx;
}

.search-container {
	height: $search-bar-height;
	@include uni-flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	position: relative;
	background-color: #fff;

	@at-root {
		#{&}-bar {
			@include uni-flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
		}
	}
}

.search-associative {
	/* #ifndef APP-NVUE */
	overflow-y: auto;
	/* #endif */
	position: absolute;
	top: $search-bar-height;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #fff;
	margin-top: 10rpx;
	padding-left: 10rpx;
	padding-right: 10rpx;
}

.search-icons {
	padding: 16rpx;
}

</style>
