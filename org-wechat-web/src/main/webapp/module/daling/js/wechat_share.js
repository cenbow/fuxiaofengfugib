
;function configShare(share,config){
	
	var wxconfig = $.trim(config);
	if(!wxconfig)return;
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
				addUserShareHis();
				$(".pop_layer").hide();
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
				addUserShareHis();
				//关闭分享提示弹层
				$(".pop_layer").hide();
			},
			cancel : function(res) {},
			fail : function(res) {}
		});
		wx.error(function(res) {});
	});
}

function addUserShareHis(){
	var params = {shareType:share.shareType};
	var url = "/musicale/6/share.html";
	$.ajax({
		url:url,
		dataType:"json",
		type:"post",
		data:params,
		success:function(data,status){
		},
		error:function(e){
		}
	});
}

