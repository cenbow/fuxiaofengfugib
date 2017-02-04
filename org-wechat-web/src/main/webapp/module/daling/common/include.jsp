<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/module/daling/front/css/animate.min.css" rel="stylesheet">
<link href="/module/daling/front/css/common.css" rel="stylesheet">
<link href="/module/daling/front/css/fn.css" rel="stylesheet">
<script src="/module/daling/front/js/libs/jquery.1.8.2.min.js"></script>
<script type="text/javascript" src="/module/daling/front/js/libs/zepto.js"></script>
<script src="/module/daling/front/js/index.js"></script>
<script type="text/javascript" src="/module/daling/js/util.js?v=v1"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/module/daling/js/wechat_share.js?v=v1"></script>
<script type="text/html" id="layerTemplate">
    <div class="pup-lsit util_alert">
        <b class="close close_rule_btn"></b>
        <div class="text">
            <p>现在不是投票时间哟</p>
        </div>
        <div class="btn two"><a href="javascript:;" class="close_rule_btn">关闭</a></div>
    </div>
</script>
<script type="text/javascript">
var share = {
    	title:"${shareDto.shareTitle}",
    	content:"${shareDto.shareContent}",
    	imgUrl:"${shareDto.shareImg}",
    	link:"${shareDto.shareUrl}",
    	shareType:"${shareDto.shareType}"
};
var config = '${config}';
$(function(){
	try{
		configShare(share,config);
	}catch(e){
		alert(e);
	}
});
</script>
