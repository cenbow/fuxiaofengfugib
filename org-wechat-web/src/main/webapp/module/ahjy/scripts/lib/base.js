/** 手机号码验证表达式*/
PHONE_PATTERN = /^(((1[3|4|5|6|7|8|9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;
/** 汉字验证表达式*/
CHINESE_PATTERN =/^[\u4e00-\u9fa5]+$/;

seajs.config({
	alias:{
		"ahjyBase": "/module/ahjy/scripts/lib/base_ahjy.js",
		"qzAjax": "/module/ahjy/scripts/lib/qz_ajax.js",
		"qzAlert": "/module/ahjy/scripts/lib/qz_alert_layer.js",
		"qzValidate": "/module/ahjy/scripts/lib/qz_validate.js",
		"qzStr": "/module/ahjy/scripts/lib/qz_strUtil.js",
		"wxShare": "/module/ahjy/scripts/lib/wechat_share.js",
		"jweixin": "http://res.wx.qq.com/open/js/jweixin-1.0.0.js"
	},
	base: "/module/ahjy/scripts/lib/sea-debug.js"
});