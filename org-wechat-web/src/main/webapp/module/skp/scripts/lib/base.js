/** 手机号码验证表达式*/
PHONE_PATTERN = /^(((1[3|4|5|6|7|8|9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;

seajs.config({
	alias:{
		"qzAjax": "/module/skp/scripts/lib/qz_ajax.js",
		"qzAlert": "/module/skp/scripts/lib/qz_alert_layer.js",
		"qzValidate": "/module/skp/scripts/lib/qz_validate.js",
		"wxShare": "/module/skp/scripts/lib/wechat_share.js",
		"jweixin": "http://res.wx.qq.com/open/js/jweixin-1.0.0.js"
	},
	base: "/module/skp/script/lib/sea-debug.js"
});