export const utils={
	handleRatio:function(fee) {
		if (!fee) return '';
		return fee.toFixed(2) + '%';
	},
	handleFee:function(fee) {
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
	handleNetGroupRatio:function(startNet, endNet, year) {
		//start*(1+rate)^5=end  => (1+rate)^5 = end/start
		let res = 0;
		if (startNet > 0 && endNet > 0) {
			res = (Math.pow((endNet * 1.0) / startNet, 1.0 / year) - 1).toFixed(2);
		}
		return res;
	}
}