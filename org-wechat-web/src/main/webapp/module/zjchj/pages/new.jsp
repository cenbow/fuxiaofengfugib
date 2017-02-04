<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>正佳吃货节</title>
	<meta name="Keywords" content="">
	<meta name="Description" content=""></head>
	<script src="${request.contextPath}/module/zjchj/front/js/libs/jquery.1.8.2.min.js"></script>
	<script src="${request.contextPath}/module/zjchj/front/js/common.js"></script>
	<link href="${request.contextPath}/module/zjchj/front/css/animate.min.css" rel="stylesheet">
	<link href="${request.contextPath}/module/zjchj/front/css/common.css" rel="stylesheet">
	<link href="${request.contextPath}/module/zjchj/front/css/fn.css" rel="stylesheet">
<body>
	<div class="detail">
		<b class="return" onclick="javascript: location.href='${request.contextPath}/zjchj/${accId}/index.html?type=type'">
			<img src="${request.contextPath}/module/zjchj/front/images/rerutn.png" />
		</b>
		<h3><img src="${request.contextPath}/module/zjchj/front/images/detail-tit.png"></h3>
		<div class="new-thx">
			<img src="${request.contextPath}/module/zjchj/front/images/new-thx.png">
			<div class="text">
				<p>得正佳广场第一季<br>“吃货大汇终极吃货”抽奖机会，<br>我们将于现场抽取三名幸运吃货<br>并送出 <strong>¥5000</strong>美食卡大礼哦！</p>
				<p>活动时间：2016年12月18日<br>活动地点：正佳广场五楼中庭</p>
				<p>现场还有<strong>免费试吃</strong>和<strong>现场烹饪</strong>的机会。<br>请您一定不要错过哦！</p>
			</div>
		</div>
	</div>
	<!-- share -->
	<div id="wx_config" style="display: none;">${config}</div>
</body>
<script type="text/javascript">
	var accId = "${accId}";

	$(function(){
 		configShare();

		//返回首页
		$(".return").click(function() {
			location.href = "/zjchj/" + accId + "/index.html?type=type";
		});
	});
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/zjchj/js/wechat_share.js?v=v4" type="text/javascript" charset="utf-8"></script>
</html>