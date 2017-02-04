;var share = {
    	title:"我们来了 | 黄晓明邀您加入群聊",
    	content:"砂之船（合肥）奥特莱斯秋季嘉年华，“晓狼”组合给您送礼包！",
    	imgUrl:"http://szc.rongyi.com/module/szc/award_img/huangxiaoming.jpg",
    	link:"http://szc.rongyi.com/szc/"+accId+"/index.html"
 };

;function configShare(){
	
	if(district == 2 ){//2南京  //3璧山
	    share.title = "砂之船奥莱周年庆 | 黄晓明邀您加入群聊";
	    share.content = "砂之船（南京）奥莱一周年庆，黄晓明给您送礼包~";
	    share.link += "?district=2"
	}else if(district == 3){
		share.title = "砂之船奥莱周年庆 | 黄晓明邀您加入群聊";
	    share.content = "砂之船（璧山）奥莱三周年庆，黄晓明给您送礼包~";
	    share.link += "?district=3"
	}
	
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
				//关闭分享提示弹层
				$(".pop_layer").hide();
			},
			cancel : function(res) {},
			fail : function(res) {}
		});
		wx.error(function(res) {});
	});
}