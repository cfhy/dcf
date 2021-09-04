// const baseUrl = 'http://47.108.180.48:8081/'   
 const baseUrl = process.env.NODE_ENV === 'development'?'http://localhost:8081/':'http://47.108.180.48:8081/'
const install = (Vue, vm) => {
	// 此为自定义配置参数，具体参数见上方说明
	Vue.prototype.$u.http.setConfig({
		baseUrl: baseUrl, // 请求的本域名
		//method: 'POST',
		// 设置为json，返回后会对数据进行一次JSON.parse()
		dataType: 'json',
		showLoading: true, // 是否显示请求中的loading
		loadingText: '努力加载中...', // 请求loading中的文字提示
		loadingTime: 500, // 在此时间内，请求还没回来的话，就显示加载中动画，单位ms
		originalData: false, // 是否在拦截器中返回服务端的原始数据
		loadingMask: true, // 展示loading的时候，是否给一个透明的蒙层，防止触摸穿透
		// 配置请求头信息
		header: {
			//'content-type': 'application/json;charset=UTF-8'
		},
	});
}

export default {
	install
}