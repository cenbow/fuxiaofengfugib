<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="../pages/common/taglibs.jsp"%>
<!doctype html>
<html>
	<head>
		<%
// 		    long version = new Date().getTime();
		    String version = "1.3";
		%>
		<script type="text/javascript">
		    var version = "<%=version%>";//版本号
		    var host = "${pageContext.request.requestURL}";
			var index = host.indexOf("/module");
			host = host.substr(0, index);
		</script>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>照片处理</title>
		<script type="text/javascript"  src="/module/skp/photo/drawingBoards/drawingBoardsRequire.js?v=<%=version%>"></script>
		<script type="text/javascript"  src="/module/skp/photo/com/require.js?v=<%=version%>"></script>
		<script type="text/javascript"  src="/module/skp/photo/photo/photo.js?v=<%=version%>"></script>
		<script  type="text/javascript" src="/module/skp/photo/make.js?v=<%=version%>"></script>
		<script type="text/javascript" src="/module/skp/front/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="/module/skp/front/js/jquery.SuperSlide.2.1.1.js"></script>
		<link rel="stylesheet" href="/module/skp/front/css/base.css?v=<%=version%>">
		<link rel="stylesheet" href="/module/skp/front/css/style.css?v=<%=version%>">
	</head>
	<body>
		<div class="wrap">
			<div class="btn-1 clearfix">
				<ul class="flex">
					<li class="flex-1"><a href="javascript:void(0);" class="file">拍摄照片<input type="file" name="cameraInput" id="cameraInput" capture="camera" accept="image/*" /></a></li>
					<li class="flex-1 tc"><a id="btn_rotate" href="javascript:void(0);" class="sm-btn grey-btn">照片旋转</a></li>
					<li class="flex-1"><a id="btn_tutorial" href="javascript:void(0);" class="fr sm-btn blue-btn">制作教程</a></li>
				</ul>
			</div>
	  		<div class="photo-box clearfix">
	  			<span id="btn_xk_prev" class="big-pre"></span>
		    	<div class="photo-con"><canvas id="mycanvas"></canvas></div>
		    	<span id="btn_xk_next" class="big-next"></span>
	    	</div>
		  	<div class="adorn-box clearfix picScroll-left"> 
		    	<!-- 下面是前/后按钮代码，如果不需要删除即可 --> 
		    	<a class="prev" href="javascript:void(0);"></a>
		    	<a class="next" href="javascript:void(0);"></a>
		    	<div class="adorn-con bd">
		      		<ul id="sp_list" class="picList">
		      			<c:forEach items="${decoList}" varStatus="i">
		      				<li>
		      					<img eid="sp_${i.count}" src="/module/skp/front/images/i_${i.count}.jpg?v=<%=version%>" />
		      					<c:if test="${i.count le 3}">
		      						<span class="jf"></span>
		      					</c:if>
		      				</li>
		      			</c:forEach>
		      		</ul>
		    	</div>
		  	</div>
		  	<p class="info">时尚值最高的前三名会获得由北京SKP提供的大奖<br />所以记得选取可以加分的饰品哦</p>
		  	<p class="ok-btn"><a id="make_png_btn" href="javascript:void(0);" class="w md-btn blue-btn">确定</a></p>
		  	<div id="frame_list" style="display: none;">
		  		<c:forEach items="${frameList}" var="obj" varStatus="s">
		  			<img src="${obj.url}" />
		  		</c:forEach>
		  	</div>
		  	<div id="deco_list" style="display: none;">
		  		<c:forEach items="${decoList}" var="obj" varStatus="s">
		  			<img eid="sp_${s.count}" src="${obj.url}" />
		  		</c:forEach>
		  	</div>
		</div>
		<!--遮罩层-->
		<div class="pop_layer" style="display: none;"></div>
		<!-- 弹层 -->
		<div class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">
 				照片已经保存成功<br />请在个人主页查看<br />&nbsp;
			</p>
 			<p class="tc img-p"><img src="/module/skp/front/images/code.jpg?v=<%=version%>"/></p>
 			<p class="tc text-p">
 				关注北京SKP官方微信<br/>查看每日幸运获奖用户<br/>
				快点 看看您是不是幸运儿
			</p>
 			<p class="tc btn-p">
 				<a id="follow_pop_btn" href="javascript:void(0);" class="sm-btn blue-btn">确定</a>
 			</p>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="acc_id" value="${accId}" />
		<input type="hidden" id="user_id" value="${userId}" />
	</body>
</html>