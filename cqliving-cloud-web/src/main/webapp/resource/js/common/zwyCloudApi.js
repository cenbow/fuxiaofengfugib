
(function(){
	ZWY_ClOUD = window.ZWY_ClOUD || {};
	
	ZWY_ClOUD = {
		version: "1.0"
	}
	
	/**
	 * 获取session和token
	 * canbackFnName String 回调方法名称
	 */
	ZWY_ClOUD.getSessionToken =  function(canbackFnName){
		return window.AppJsObj.getSessionToken(canbackFnName);
	};
	
	/**
	 * 获取token
	 */
	ZWY_ClOUD.getToken = function(){
		return window.AppJsObj.getToken();
	}
	
	/**
	 * 登录
	 */
	ZWY_ClOUD.login = function(){
		return window.AppJsObj.redirectLogin();
	}
	
	/**
	 * 上传图片
	 * canbackFnName String 回调方法名称
	 */
	ZWY_ClOUD.selectPic = function(canbackFnName){
		return window.AppJsObj.selectPic(canbackFnName);
	}
	
	/**
	 * 分享
	 * title String 分享的标题
	 * content String 分享的内容
	 * icon String 分享图片地址
	 * url String 分享的页面地址
	 */
	ZWY_ClOUD.share = function(title, content, icon, url){
		return window.AppJsObj.share(title, content, icon, url);
	}
	
	/**
	 * 获得图片集合
	 * imgList
	 * indexImg
	 */
	ZWY_ClOUD.getPhotoImagePathArray = function(imgList, indexImg){
		return window.AppJsObj.getPhotoImagePathArray(imgList,indexImg);
	}
	
	/**
	 * 获取地址
	 * canbackFnName String 回调方法名称
	 */
	ZWY_ClOUD.getPlace = function(canbackFnName){
		return window.AppJsObj.getPlace(canbackFnName);
	}
	/**
	 * 提示消息框，需要点击确定才消失
	 */
	ZWY_ClOUD.alert = function(msg){
		 window.AppJsObj.appAlert(msg);
	};
	/**
	 * 提示消息框，会自动消失
	 */
	ZWY_ClOUD.showMessage = function(msg){
		window.AppJsObj.appShowMessage(msg);
	};
	/**
	 * option格式用json字符串
	 * {msg: '提示消息内容', sureFn: '点击确定回调的方法名称'}
	 */
	ZWY_ClOUD.confirm = function(option){
		window.AppJsObj.appConfirm(option);
	};
	
	/**
	 * 跳转
	 */
	ZWY_ClOUD.redirectUrl = function(option){
		window.AppJsObj.redirectUrl(option);
	};
	
	/**
	 * 调用原生的评论窗口
	 */
	ZWY_ClOUD.showComment = function(option){
		window.AppJsObj.appShowComment(option);
	};
	
})();

//1小字体，2：中字体(默认字体),3大字体   不传为默认字体
function changeFontSize(fontSize){
	  
	var appId = document.getElementById("appId");
	
	if(!appId){
		appId = 25;
	}else{
		appId = appId.value;
	}
	 if(fontSize){
		 fontSize = parseInt(fontSize,10);
		 
		 if(fontSize == 1){
			 var url = "/front/detail/css/font_small_"+appId+".css";
			  document.styleSheets[2].ownerNode.href=url;
		 }else if(fontSize == 3){
			 var url = "/front/detail/css/font_big_"+appId+".css";
			  document.styleSheets[2].ownerNode.href=url;
		 }else{
			 var url = "/front/detail/css/main_"+appId+".css";
			  document.styleSheets[2].ownerNode.href=url;
		 }
	 }else{
		 var url = "/front/detail/css/main_"+appId+".css";
		  document.styleSheets[2].ownerNode.href=url;
	 }
	clearDetailContenFontSize();
	
	try{
		if('undefined' != typeof fontSizeObj){

			fontSizeObj.callBack(fontSize);
		}
	}catch(e){}
}

function clearDetailContenFontSize(){
	
	var detailContent = document.getElementById("detail_content");
	
	recursion(detailContent);
}

function recursion(ele){
	
	if(!ele)return;
	var children = ele.children;
	if(!children)return;
	for(var i=0;i<children.length;i++){
		children[i].style.fontSize = "";
		children[i].style.lineHeight = "";
		recursion(children[i]);
	}
}


//分享参数
function getJsonParams(){
	
	/*id:对应的内容主键ID
	infoid:对应的评论、收藏等内容ID
	images：分享图片
	title：分享标题
	depict: 分享描述
	url:分享后URL*/
	return JSON.stringify(jsonShareParam);
}

/*window.AppJsObj.redirectUrl(jsonParam);
var jsonParam = {
		id:"",//主ID
		sourceId:"",//内容ID
		detailViewType:"",//类型：1:多图新闻,2:普通新闻,3:专题新闻
		sourceType:"",//来源类型：来源类型：{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题,8:便民,9:热线,10:旅游,11:置业,12:招聘}'
		commentType:"",//评论：0允许，1不允许
		url:"",//跳转的URL
		shareUrl:"",//分享url
		title:"",//标题(base64编码)
		synopsis:"",//简介(base64编码)
		shareImgUrl:""//分享的图片地址
}*/

/*id:主键
sourceId: 内容ID  —— 目前：新闻取评论、点赞用到，其他情况  sourceId=id
sourceType: 来源类型：{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题,8:便民,9:热线,10:旅游,11:置业,12:招聘}
detailViewType:  详情页打开方式：1:多图（图文类型）,2:H5下有评论列表(详情,公告活动),3:H5下无评论列表(专题,投票活动等)，4: 随手拍,5:段子, 7:话题，8：纯Webview,9: 有导航栏的Webview
commentType:  是否允许评论：{0:允许,1:不允许}
url:跳转的URL，外链的情况直接返回外链
shareUrl:分享的url
title:标题（base64编码）
synopsis:简介（base64编码）
shareImgUrl:分享图片地址*/

//客户端返回的时候调用方法  backCallFun(param);
//客户端登录返回的时候调用方法  loginCallBack();
//获取文档高度  getDocHeight()
//设置图片的最大宽度
;function limitImg(){
	var $img = $("img");
	if($img.length>=1){
		$img.each(function(i,n){
			var $this = $(n);
			var w = $this.width();
			var maxWidth = document.body.offsetWidth;
			if(w && w>maxWidth){
			  $this.css("width","90%");
			  $this.css("height","");
			}
		});
	}
};

function clearWidth(){
	var $div = $("p,h1,h2");
	if($div.length<=0)return ;
	$div.each(function(i,n){
		var $this =$(n);
		$this.css("width","");
	});
};

//拼接尺寸后缀
function appendSuffix(w,h,imgpath){
	if(!imgpath){
		return imgpath;
	}
	var pre = imgpath.lastIndexOf(".");
	var prePath = imgpath.substring(0,pre);
	prePath = prePath+"_"+w+"x"+h+imgpath.substring(pre);
	return prePath;
}

function isOpenInApp(){
//	  if(navigator.userAgent.match(/android/i)){
//		  return true;
//	  }
//	  if(navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)){
//		  return true;
//	  }
//	  return false;
	try{
		window.AppJsObj.getSessionToken();
		IsAPP=true;
	}catch(e){
		IsAPP=false;
	}
}