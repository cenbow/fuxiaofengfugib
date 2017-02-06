<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
		<title>我的位置</title>
	</head>
	<body>
		<input type="hidden" id="app_name" value="${appInfo.name}" />
	</body>
	<script type="text/javascript">
		require(["/resource/js/business/shop/myPosition.js"], function(obj) {
			obj.init();
		});
		
		//客户端回调方法，打开百度地图
		function openBaiduMap(lat, lng) {
			var content = "我的位置";
			var title = "我的位置";
			var appName = $("#app_name").val();
			location.href = "http://api.map.baidu.com/marker?location=" + lat + "," + lng + "&title=" + title + "&content=" + content + "&output=html&src=" + appName;
		}
	</script>
</html>