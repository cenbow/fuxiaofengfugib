<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
    String version = "1.2";
%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>我的主页</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/my.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap clearfix">
			<p class="sszs"><i></i>时尚指数：<b>${obj.totalScore}</b></p>
			<div class="photo-box clearfix">
  				<div class="photo-con"><img src="${obj.photoUrl}?v=<%=version%>"/></div>
			</div>
			<p class="info">
				长按即可保存照片<br />
				邀请好友助力增加您的时尚值<br />
				时尚值最高的前三名可获得由北京SKP提供的大奖
			</p>
			<ul class="clearfix btn-box">
  				<li><a id="btn_invite_help" href="javascript:void(0);" class="w md-btn grey-btn">邀请好友助力</a></li>
  				<li class="flex">
    				<p class="flex-1"><a id="btn_lucy_test" href="javascript:void(0);" class="w md-btn blue-btn">每日幸运抽奖</a></p>
    				<p class="flex-1"><a id="btn_rank" href="javascript:void(0);" class="w md-btn blue-btn">时尚排行榜</a></p>
  				</li>
			</ul>
		</div>
		<!--公共遮罩层-->
		<div class="pop_layer" style="display: none;"></div>
		<!-- 分享弹层 -->
		<div id="share_popup" class="pop-tip" style="display: none;">
			<p class="arrow"></p>
			<p class="tip-text">分享您的时尚大片<br/>让你的好友来为你助力吧</p>
		</div>
		<!-- 幸运测试弹层 -->
		<div id="lucy_popup" class="pop_con" style="display: none;">
			<span id="btn_close_lucy" class="close"></span>
 			<p class="tc img-p"><img src="/module/skp/front/images/code.jpg?v=<%=version%>" /></p>
 			<p class="tc text-p">关注北京SKP官方微信<br/>查看每日幸运获奖用户<br/>快点 看看您是不是幸运儿</p>
 			<p class="tc btn-p"><a id="btn_go_rank" href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="src" value="${src}" />
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>