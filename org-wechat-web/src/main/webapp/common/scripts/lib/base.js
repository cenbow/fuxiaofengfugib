/** 手机号码验证表达式*/
PHONE_PATTERN = /^(((1[3|4|5|6|7|8|9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;

seajs.config({
	alias:{
		"qzAjax": "/common/scripts/lib/qz_ajax.js",
		"qzAlert": "/common/scripts/lib/qz_alert_layer.js",
		"qzValidate": "/common/scripts/lib/qz_validate.js"
	},
	base: "/common/script/lib/sea-debug.js"
});