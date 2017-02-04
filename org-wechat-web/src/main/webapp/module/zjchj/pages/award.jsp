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
	<div class="index">
		<b class="return" onclick="javascript: history.back(-1);"><img src="${request.contextPath}/module/zjchj/front/images/rerutn.png"></b>
		<%-- <div id="bag_btn" class="bag"><img src="${request.contextPath}/module/zjchj/front/images/bag1.png"></div> --%>
		<div class="bg-img">
			<img src="${request.contextPath}/module/zjchj/front/images/index-bg-my.jpg">
			<div class="index-text"><img src="${request.contextPath}/module/zjchj/front/images/index-text-my.png"></div>
			<div class="index-my">
				<c:choose>
					<c:when test="${not empty award}">
						恭喜您中了${award.name}！<br>在吃货的路上越走越远吧!
					</c:when>
					<c:otherwise>
						很遗憾！<br>您未中奖!
					</c:otherwise>
				</c:choose>
			</div>
			<div class="my-btn">
				<a href="${request.contextPath}/zjchj/${accId}/index.html?type=type"><img src="${request.contextPath}/module/zjchj/front/images/btn-continue.png"></a>
				<a href="${request.contextPath}/zjchj/${accId}/detail/my.html"><img src="${request.contextPath}/module/zjchj/front/images/btn-see.png"></a>
			</div>
		</div>		
	</div>
	<div class="no-data">弹窗提示!</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-no.png" alt="">对不起，没有数据</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-yes.png" alt="">成功</div>
	<div class="loding"><img src="${request.contextPath}/module/zjchj/front/images/loading.png" alt=""></div>
	<!-- share -->
	<div id="wx_config" style="display: none;">${config}</div>
</body>
<script type="text/javascript">
	var accId = "${accId}";

	$(function(){
		configShare();
		
		//锦囊
		$("#bag_btn").click(function() {
			//去活动规则
			location.href = "/zjchj/" + accId + "/detail/rule.html";
		});
	});
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/zjchj/js/wechat_share.js?v=v4" type="text/javascript" charset="utf-8"></script>
</html>