define([],function(){
	
	//获取滚动条当前的位置
	function getScrollTop(){
		var scrollTop = 0;
			if (document.documentElement && document.documentElement.scrollTop) {
			scrollTop = document.documentElement.scrollTop;
		}else if (document.body){
			scrollTop = document.body.scrollTop;
		}
		return scrollTop;
	}
	
	//获取当前可视范围的高度
	function getClientHeight(){
		var clientHeight = 0;
		if (document.body.clientHeight && document.documentElement.clientHeight){
			clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
		}else{
			clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
		}
		return clientHeight;
	}
	
	//获取文档完整的高度
	function getScrollHeight(){
		return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
	}
	
	//可视位置距离文档底部的位置
	function getViewBottom(){
		return getScrollHeight() -  getClientHeight() - getScrollTop();
	}
	
	return {
		getScrollTop:getScrollTop,
		getClientHeight:getClientHeight,
		getScrollHeight:getScrollHeight,
		getViewBottom:getViewBottom
	};
});

