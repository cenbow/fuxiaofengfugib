<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>已删除</title>
</head>
<body id="index">
<div id="header">
	<div class="btn_back" onClick="window.history.go(-1)">
		<i class="fa fa-angle-left"></i>
	</div>
	<h1>对不起，该条信息已被删除</h1>
</div>
<div class="wrap_phone">
<img src="${request.contextPath}/front/detail/images/pic.png" class="status_pic"/>
<div class="err_infos">对不起，该条信息已被删除</div>
</body>
</html>
