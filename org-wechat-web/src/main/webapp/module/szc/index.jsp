<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<title>砂之船奥莱VIP粉丝群</title>
		<%-- ${allDistrictTitle[district]} --%>
		<!-- #debug -->
		<!-- /debug -->
	    <script src="${request.contextPath}/common/scripts/lib/jquery-1.11.3.min.js?v=v1" type="text/javascript" charset="utf-8"></script>
	   
	    <script type="text/javascript">
	        var accId = "${accId}";
	    	var szc = 
	    	{
	    		//聊天版本参数
	    		//0简单版   1完整版
	    		chat:1,
	    		//长贴版本  
	    		//0合肥     1南京
	    		longStick:0,
	    		//用户名
	    		userName:'${weixin_session_user.nickname}',
	    		//用户头像
	    		userHead:"${weixin_session_user.headimgurl}",
	    		title:["砂之船奥莱VIP粉丝群","#合肥头条#","朋友圈"]
	    	};
	    	var district = "${district}";
	    	
	    	if(district == 2){//2南京  
	    		szc.chat = 0;
	    	    szc.longStick = 1;
	    	    szc.title[1] = "#南京头条#";
	    	}
	    	if(district == 3){//3璧山
	    		szc.chat = 0;
	    	    szc.longStick = 2;
	    		szc.title[1] = "#重庆头条#";
	    	}
	    	
	    </script>
	<link href="${request.contextPath}/module/szc/bin-release/css/base.min.css?v=v5" type="text/css" rel="stylesheet" />
	<link href="${request.contextPath}/module/szc/bin-release/css/style.min.css?v=v5" type="text/css" rel="stylesheet" />
    <script src="${request.contextPath}/module/szc/bin-release/js/style.min.js?v=v8" type="text/javascript" charset="utf-8"></script>
</head>
	<body>
		<!-- 聊天输入框-->
		<div class="chat-input-container">
			<div class="chat-input">
				<img class="ico ico-1" src="/module/szc/bin-release/images/ico_0.png"/>
				<div class="text-container"></div>
				<img class="ico ico-2" src="/module/szc/bin-release/images/ico_1.png"/>
				<img class="ico ico-3" src="/module/szc/bin-release/images/ico_2.png"/>
			</div>
			<img class="chat-keyboard" src="/module/szc/bin-release/images/img_4.jpg" width="100%" style="display: none;"/>
			<img class="chat-hand" src="/module/szc/bin-release/images/ico_8.png" style="display: none;"/>
		</div>
		<!--/聊天输入框-->
		
		<!--聊天显示容器-->
		<div id="chat-container" class="clearfix">
		</div>
		<!--/聊天显示容器-->
		
		<audio id="news">
			<source src="/module/szc/bin-release/sound/news.mp3" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/news.ogg" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/news.wav" type="audio/mpeg">		  
		</audio>
		
		<audio id="type">
			<source src="/module/szc/bin-release/sound/type.mp3" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/type.ogg" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/type.wav" type="audio/mpeg">		  
		</audio>
		
		<audio id="unlock">
			<source src="/module/szc/bin-release/sound/unlock.mp3" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/unlock.ogg" type="audio/mpeg">
			<source src="/module/szc/bin-release/sound/unlock.wav" type="audio/mpeg">		  
		</audio>

		<!--加载界面-->
		<div class="loading">
			Loading...
		</div>

		<!--锁屏 style="display: none;" -->
		<div class="lock-screen">
			<div>
				<h1></h1>
				<p></p>
				<div class="information">
					<img src="/module/szc/bin-release/images/ico_6.png"/>
					<p>
						砂之船奥莱VIP粉丝群<span> 现在</span><br />
						你收到一条来自黄晓明的消息<br />
						<span>滑动来查看</span>
					</p>
				</div>
				<div class='prompt'>滑动来解锁</div>
				<div class='bottom'></div>
				<img src="/module/szc/bin-release/images/ico_7.png"/>
			</div>
		</div>
		<!--/锁屏-->

		<!--朋友圈-->
		<div class="circle-of-friends-web clearfix" style="display: none;">
			<div class="circle-of-friends-web-i">
				<div class="head"><img src="/module/szc/bin-release/images/head_2.jpg"/></div>
				<div class="detail">
					<div class="user-name">Baby—砂之船五星会员</div>
					<div class="information">
						<img src="/module/szc/bin-release/images/head_5.jpg"/>
						<p>砂之船奥莱重磅活动精彩来袭！</p>
					</div>
					<div class="inscription clearfix">
						<span>1分钟前</span>
						<img src="/module/szc/bin-release/images/ico_3.png"/>
					</div>
				</div>
			</div>
			
			<div class="circle-of-friends-web-i">
				<div class="head"><img class="headImg" src="/module/szc/bin-release/images/head_3.jpg"/></div>
				<div class="detail">
					<div class="user-name xyz-n">XYZ</div>
					<div class="information">
						<img src="/module/szc/bin-release/images/head_5.jpg"/>
						<p>砂之船奥莱重磅活动精彩来袭！</p>
					</div>
					<div class="inscription clearfix">
						<span>2分钟前</span>
						<img src="/module/szc/bin-release/images/ico_3.png"/>
					</div>
					<div id="praise" class="reply">
						<div class="praise" style="display: none;">
							<img src="/module/szc/bin-release/images/ico_4.png"/>
							<span id="praise-text"></span>
						</div>
						<div class="comment">
							<p id="comment-0" style="display: none;"><span>黄晓明：</span>果真是真爱粉！赞一个！</p>
							<p id="comment-1" style="display: none;"><span>Baby—砂之船五星会员：</span>转起来 <img src="/module/szc/bin-release/images/phiz_6.png"/><img src="/module/szc/bin-release/images/phiz_6.png"/><img src="/module/szc/bin-release/images/phiz_6.png"/></p>
							<p id="comment-2" style="display: none;"><span>麻麻：</span>我要换个新的包包，你看着办吧。</p>
							<p id="comment-3" style="display: none;"><span>砂之船奥莱客服小S：</span><img src="/module/szc/bin-release/images/phiz_7.jpg"/><img src="/module/szc/bin-release/images/phiz_7.jpg"/><img src="/module/szc/bin-release/images/phiz_7.jpg"/> 谢谢亲，一定要来哦！</p>
						</div>
					</div>
				</div>
			</div>
			
			<div class="circle-of-friends-web-i">
				<div class="head"><img src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="detail">
					<div class="user-name">黄晓明</div>
					<div class="information">
						<img src="/module/szc/bin-release/images/head_5.jpg"/>
						<p>砂之船奥莱重磅活动精彩来袭！</p>
					</div>
					<div class="inscription clearfix">
						<span>2分钟前</span>
						<img src="/module/szc/bin-release/images/ico_3.png"/>
					</div>
					<div class="reply">
						<div class="praise" style="display: none;">
							<img src="/module/szc/bin-release/images/ico_4.png"/>
							<span id="praise-text">Baby—砂之船五星会员, 砂之船奥莱客服小S</span>
						</div>
						<div class="comment">
							<p><span>Baby—砂之船五星会员：</span>期待呢~<img src="/module/szc/bin-release/images/phiz_8.jpg"/><img src="/module/szc/bin-release/images/phiz_8.jpg"/></p>
							<p><span>砂之船奥莱客服小S</span>晓明哥，大家都期待和你见面呢~~~</p>
							<p><span>黄晓明</span> 回复  <span>砂之船奥莱客服小S</span>:有机会会和大家见面的。<img src="/module/szc/bin-release/images/phiz_9.jpg"/><img src="/module/szc/bin-release/images/phiz_9.jpg"/><img src="/module/szc/bin-release/images/phiz_10.jpg"/></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/朋友圈-->
		
		<!--长贴-->
		<div class="long-stick" style="display: none;">
			<div id="long-stick-0">
				<h1>抢先关注:砂之船（合肥）奥菜秋季嘉年华精彩来袭！</h1>
				<div class="title-2">
					 09-06 <span class="place">#合肥头条#</span>
					<a class="share-Button">
						<img class="hand" src="/module/szc/bin-release/images/ico_8.png"/>
						<img class="ico" src="/module/szc/bin-release/images/ico_9.png"/>
						分享
					</a>
				</div>
				<img src="/module/szc/bin-release/images/poster_0.jpg"/>
				<img src="/module/szc/bin-release/images/poster_1.jpg"/>
				<img src="/module/szc/bin-release/images/poster_2.jpg"/>
				<img src="/module/szc/bin-release/images/poster_3.jpg"/>
				<img src="/module/szc/bin-release/images/poster_4.jpg"/>
				<img src="/module/szc/bin-release/images/poster_5.jpg"/>
				<img src="/module/szc/bin-release/images/poster_6.jpg"/>
				<img src="/module/szc/bin-release/images/poster_7.jpg"/>
				<p>
					阅读100000+    
					<span class="praise"><img src="/module/szc/bin-release/images/ico_5.jpg"/>910</span>
					<span class="complaint">投诉</span>
				</p>
			</div>
			<div id="long-stick-1">
				<h1>抢先关注：砂之船（南京）奥莱一周年庆钜惠全城！</h1>
				<div class="title-2">
					 09-06 <span class="place">砂之船（南京）奥莱</span>
					<a class="share-Button">
						<img class="hand" src="/module/szc/bin-release/images/ico_8.png"/>
						<img class="ico" src="/module/szc/bin-release/images/ico_9.png"/>
						分享
					</a>
				</div>
				<img src="/module/szc/bin-release/images/poster_16.jpg"/>
				<img src="/module/szc/bin-release/images/poster_17.jpg"/>
				<img src="/module/szc/bin-release/images/poster_18.jpg"/>
				<img src="/module/szc/bin-release/images/poster_19.jpg"/>
				<img src="/module/szc/bin-release/images/poster_20.jpg"/>
				<img src="/module/szc/bin-release/images/poster_21.jpg"/>
				<img src="/module/szc/bin-release/images/poster_22.jpg"/>
				<img src="/module/szc/bin-release/images/poster_23.jpg"/>
				<img src="/module/szc/bin-release/images/poster_24.jpg"/>
				<p>
					阅读100000+    
					<span class="praise"><img src="/module/szc/bin-release/images/ico_5.jpg"/>910</span>
					<span class="complaint">投诉</span>
				</p>
			</div>
			<div id="long-stick-2">
				<h1>【3周年庆】砂之船（璧山）奥莱，缤纷折扣，“闪”耀登场！</h1>
				<div class="title-2">
					09-12 <span class="place">#重庆头条#</span>
					<a class="share-Button">
						<img class="hand" src="/module/szc/bin-release/images/ico_8.png"/>
						<img class="ico" src="/module/szc/bin-release/images/ico_9.png"/>
						分享
					</a>
				</div>
				<img src="/module/szc/bin-release/images/poster_25.jpg"/>
				<img src="/module/szc/bin-release/images/poster_26.jpg"/>
				<img src="/module/szc/bin-release/images/poster_27.jpg"/>
				<img src="/module/szc/bin-release/images/poster_28.jpg"/>
				<img src="/module/szc/bin-release/images/poster_29.jpg"/>
				<img src="/module/szc/bin-release/images/poster_30.jpg"/>
				<img src="/module/szc/bin-release/images/poster_31.jpg"/>
				<img src="/module/szc/bin-release/images/poster_32.jpg"/>
				<img src="/module/szc/bin-release/images/poster_33.jpg"/>
				<img src="/module/szc/bin-release/images/poster_34.png"/>
				<img src="/module/szc/bin-release/images/poster_35.jpg"/>
				<img src="/module/szc/bin-release/images/poster_36.jpg"/>
				<img src="/module/szc/bin-release/images/poster_37.jpg"/>
				<img src="/module/szc/bin-release/images/poster_38.jpg"/>
				<img src="/module/szc/bin-release/images/poster_39.jpg"/>
				<img src="/module/szc/bin-release/images/poster_40.jpg"/>
				<img src="/module/szc/bin-release/images/poster_41.jpg"/>
				<img src="/module/szc/bin-release/images/poster_42.jpg"/>
				<img src="/module/szc/bin-release/images/poster_43.jpg"/>
				<img src="/module/szc/bin-release/images/poster_44.jpg"/>
				<img src="/module/szc/bin-release/images/poster_45.jpg"/>
				<img src="/module/szc/bin-release/images/poster_46.jpg"/>
				<img src="/module/szc/bin-release/images/poster_47.jpg"/>
				<img src="/module/szc/bin-release/images/poster_48.jpg"/>
				<p>
					阅读100000+    
					<span class="praise"><img src="/module/szc/bin-release/images/ico_5.jpg"/>910</span>
					<span class="complaint">投诉</span>
				</p>
			</div>
		</div>
		<!--/长贴-->
		
		<!--聊天模板-->
		<div class="chat-template" style="display: none;">
			
			<div class="tac">
				<p class="chat-system time-current">下午10:00</p>	
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text">大家最近都好么？好久不联系了哦！</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_1.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">砂之船奥莱客服小S</div>
					<div class="chat-container-text">哇，晓明哥来了！</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl"  sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_2.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">Baby—砂之船五星会员</div>
					<div class="chat-container-text">
						<img src="/module/szc/bin-release/images/phiz_1.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_1.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_1.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_0.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_0.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_0.jpg"/>
					</div>
				</div>
			</div>
			
			<!--右边文本聊天-->
			<div class="chat-container clearfix" sound='true' command='inputCharacter("晓明哥最近干嘛呢？是不是砂之船奥莱又有活动了呐？")'>
				<div class="fr">
					<div class="chat-container-l">
						<div class="chat-container-user-name xyz-n">XYZ</div>
						<div class="chat-container-text">晓明哥最近干嘛呢？是不是砂之船奥莱又有活动了呐？</div>
					</div>
					<div class="chat-container-r">
						<img class="chat-container-head headImg" src="/module/szc/bin-release/images/head_3.jpg"/>
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text">消息很灵通嘛！9月10日砂之船（合肥）奥特莱斯,我们不见不散~ @砂之船（合肥）奥莱</div>
				</div>
			</div>
			
			<!--左边文本聊天(图片)-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<img src="/module/szc/bin-release/images/img_0.png"/>
				</div>
			</div>
			
			<div class="tac">
				<p class="chat-system">砂之船（合肥）奥莱邀请 老狼 加入群聊</p>	
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_1.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">砂之船（合肥）奥莱</div>
					<div class="chat-container-text">
						各位VIP粉丝们，重磅消息来了！<br />
						9月10日，砂之船奥特莱斯秋季嘉年华精彩来袭！<br />
						9月10日黄晓明见面会~<br />
						9月15日老狼中秋音乐会~就在砂之船（合肥）奥特莱斯！@黄晓明 @老狼
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_4.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">老狼</div>
					<div class="chat-container-text">
						大家好，再过几天就要和大家在合肥见面了！2016年的中秋之夜，让我们一起在蜀西湖畔、在砂之船奥特莱斯High起来！<br />
						<img src="/module/szc/bin-release/images/phiz_2.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_2.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_2.jpg"/>
					</div>
				</div>
			</div>
			
			<!--左边文本聊天(图片)-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_4.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">老狼</div>
					<img src="/module/szc/bin-release/images/img_1.png"/>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text">狼哥给我留张票啊！我要点歌！！！虎口脱险一定要唱啊！</div>
				</div>
			</div>
			
			<!--右边文本聊天-->
			<div class="chat-container clearfix" sound='true' command='inputCharacter("我也要！我也要！[可怜][可怜]同桌的你！睡在我上铺的兄弟！")'>
				<div class="fr">
					<div class="chat-container-l">
						<div class="chat-container-user-name xyz-n">XYZ</div>
						<div class="chat-container-text">我也要！我也要！
							<img src="/module/szc/bin-release/images/phiz_3.jpg"/>
							<img src="/module/szc/bin-release/images/phiz_3.jpg"/>
							同桌的你！睡在我上铺的兄弟！</div>
					</div>
					<div class="chat-container-r">
						<img class="chat-container-head headImg" src="/module/szc/bin-release/images/head_3.jpg"/>
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_4.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">老狼</div>
					<div class="chat-container-text">
						统统都有！ 谁有抢票链接，快发上来！@黄晓明<br />
						<img src="/module/szc/bin-release/images/phiz_4.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_4.jpg"/>
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true' onclick="szc.showLongStick()">
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text circle-of-friends clearfix">
						<h1>砂之船奥莱重磅活动精彩来袭！</h1>
						<div>
							<p>一年一度的购物盛会，等你来参与！</p>
							<img src="/module/szc/bin-release/images/img_3.jpg"/>
						</div>
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true' command='pauseChat()'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text clearfix">
						<img src="/module/szc/bin-release/images/phiz_5.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_5.jpg"/>
						<img src="/module/szc/bin-release/images/phiz_5.jpg"/>
						快点开看吧，你想知道的都在里面了！
					</div>
				</div>
			</div>
			
			<!--左边文本聊天-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<div class="chat-container-text clearfix">
						谢谢大家的捧场，不发个红包这事都说不过去啊！
					</div>
				</div>
			</div>
			
			<!--左边文本聊天(红包)-->
			<div class="chat-container clearfix fl" sound='true'>
				<div class="chat-container-l"><img class="chat-container-head" src="/module/szc/bin-release/images/head_0.jpg"/></div>
				<div class="chat-container-r">
					<div class="chat-container-user-name">黄晓明</div>
					<a href="${request.contextPath}/szc/${accId}/luck_draw_jsp.html?district=${district}">
					 <img width="248" src="/module/szc/bin-release/images/img_2.png"/>
					</a>
				</div>
			</div>
		</div>
		<!--/聊天模板-->
		<div id="wx_config" style="display: none;">${config}</div>
	</body>
</html>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/szc/wechat_share.js?v=v2" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
  $(function(){
	  configShare();
  });
</script>

