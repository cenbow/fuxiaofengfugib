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
<%-- 		<b class="return"><img src="${request.contextPath}/module/zjchj/front/images/rerutn.png"></b> --%>
		<div class="bg-img">
			<img src="${request.contextPath}/module/zjchj/front/images/index-bg-my.jpg">
			<div class="index-text"><img src="${request.contextPath}/module/zjchj/front/images/index-text-my.png"></div>
			<div class="index-code-1"><img src="${request.contextPath}/module/zjchj/front/images/index-code-1.png"></div>
			<div class="index-code-2" style="left: 56%; top: 51.5%; width: 282px;">
				<img src="${request.contextPath}/module/zjchj/front/images/index-code-2.png">
			</div>
		</div>		
	</div>
<!-- 	<div class="no-data">弹窗提示!</div> -->
<!-- 	<div class="no-html"><img src="images/data-no.png" alt="">对不起，没有数据</div> -->
<!-- 	<div class="no-html"><img src="images/data-yes.png" alt="">成功</div> -->
<!-- 	<div class="loding"><img src="images/loading.png" alt=""></div> -->
	<!-- share -->
	<div id="wx_config" style="display: none;">${config}</div>
</body>
<script type="text/javascript">
	var accId = "${accId}";

	$(function() {
  		configShare();
	});
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/zjchj/js/wechat_share.js?v=v4" type="text/javascript" charset="utf-8"></script>
</html>