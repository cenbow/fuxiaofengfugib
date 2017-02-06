<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<style>
#model{position:absolute; width:100%; height:100%; top:0; left:0;}
#model-bg{ width:100%; height:100%; background:#000; opacity:0.5; position:absolute; z-index:1;}
#tishi{ width:80%; height:auto; background:#f7f7f7; border-radius:20px; margin:30% auto; position:relative; z-index:11;}
.model-title{ color:#000; font-size:17px; width:100%; text-align:center; height:42px; line-height:42px;}
.model-content{ width:90%; margin:0 5%; font-size:13px; color:#333; line-height:1.6em;}
#model-btn{ width:100%; border-top:1px solid #ccc; text-align:center; font-size:17px; color:#007aff; line-height:45px; height:45px; display:block; cursor:pointer; margin-top:10px;}
</style>
<title></title>

<script type="text/javascript">
	function toLife(){
		window.location.href = '${lifeUrl}';
	}
</script>

</head>
<body>
	<div id="model">
		<div id="model-bg"></div>
		<div id="tishi">
			<div class="model-title">提示</div>
			<c:if test="${not empty errorMessage }">
				<div class="model-content">温馨提示：${errorMessage }</div>
				<div id="model-btn" onclick="javascript:(function(){document.getElementById('tishi').style.display='none'})();">知道了</div>
			</c:if>
			<c:if test="${empty errorMessage }">
				<div class="model-content">温馨提示：若出现“该用户不存在或用户资料不全”的提示信息，可能为缴费单位未出账单，请过段时间再查询。此缴费单位只支持建行卡</div>
				<div id="model-btn" onclick="toLife();">知道了</div>
			</c:if>
		</div>
	</div>
</body>
</html>