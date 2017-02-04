var share = {
    	title: "我终于拿到了传说中的顶级吃货证",
    	content: "吃到正佳吃货爆款可以拿到5000元吃货基金哦",
    	imgUrl: "http://musical.rongyi.com/module/zjchj/front/images/share_logo.png",
    	link: "http://musical.rongyi.com/zjchj/" + accId + "/index.html?type=s"
 };

function configShare() {
	var wxconfig = $("#wx_config").text();
	var wx_config = JSON.parse(wxconfig);
	wx.config(wx_config);
	
	wx.ready(function() {
		//TODO tt 分享给朋友
		wx.onMenuShareAppMessage({
			title : share.title,
			desc : share.content,
			link : share.link,
			imgUrl : share.imgUrl,
			trigger : function(res) {},
			success : function(res) {
				//关闭分享提示弹层
//				$(".pop_layer").hide();
			},
			cancel : function(res) {},
			fail : function(res) {}
		});
		
		//TODO tt 分享到朋友圈
		wx.onMenuShareTimeline({
			title : share.title,
			link : share.link,
			imgUrl : share.imgUrl,
			trigger : function(res) {},
			success : function(res) {
				//关闭分享提示弹层
//				$(".pop_layer").hide();
			},
			cancel : function(res) {},
			fail : function(res) {}
		});
		wx.error(function(res) {});
	});
}