<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
    String version = "1.1";
%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>好友助力</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/help.js?v1", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap clearfix">
			<p class="sszs"><i></i>时尚指数：<b id="friend_score">${obj.totalScore}</b></p>
			<div class="photo-box clearfix">
  				<div class="photo-con"><img src="${obj.photoUrl}?v=<%=version%>" /></div>
			</div>
			<ul class="clearfix btn-box">
  				<li><a id="btn_help" href="javascript:void(0);" class="w md-btn grey-btn">为好友增加时尚指数</a></li>
  				<li class="flex">
    				<p class="flex-1"><a id="btn_rank" href="javascript:void(0);" class="w md-btn blue-btn">时尚排行榜</a></p>
    				<p class="flex-1"><a id="btn_index" href="javascript:void(0);" class="w md-btn blue-btn">我要制作</a></p>
  				</li>
			</ul>
		</div>
		<!--公共遮罩层-->
		<div class="pop_layer" style="display: none;"></div>
		<!-- 已助力弹层 -->
		<div id="help_fail_popup" class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">对不起<br/>活动已结束！</p>
 			<p class="tc btn-p"><a href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<!-- 助力成功弹层 -->
		<div id="help_succ_popup" class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">已为好友时尚指数<br/>增加${helpScore}分</p>
 			<p class="tc btn-p"><a href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="help_score" value="${helpScore}" />
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="friend_id" value="${friendId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>