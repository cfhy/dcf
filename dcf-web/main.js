import Vue from 'vue'
import App from './App'
import uView from "uview-ui";
import httpInterceptor from '@/common/http.interceptor.js'
Vue.use(uView);
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	...App
})


Vue.use(httpInterceptor, app)

app.$mount()
