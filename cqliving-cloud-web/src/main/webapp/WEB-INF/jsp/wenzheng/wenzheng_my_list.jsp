<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<title>我的问政</title>
</head>
<body>
	<div id="header">
		<div class="btn_back" onClick="location.href='list.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>我的问政</h1>
	</div>
	<div id="nav_mine">
		<ul>
			<li class="active"><a>我的问政</a></li>
			<li><a href="wenzheng_my_reply_list.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }">我的评论</a></li>
		</ul>
	</div>
	<div class="wz_list">
		<div class="none_status" style="display: none; font-size: 1em">暂无问政信息</div>
		<ul custom_more="custom_more"></ul>
	</div>
	<div id="create" url="add/${param.appId }.html"></div>
	
	<script type="text/javascript">
		var paramsObj = {appId: '${param.appId}', token: '${param.token}', sessionId: '${param.sessionId}'};
		var sessionObj = "";
		function setSessionToken(params){
			sessionObj = eval("("+params+")");
			paramsObj.sessionId = sessionObj.sessionId;
			paramsObj.token = sessionObj.token;
		}
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wenzheng_my_list.js'], function(obj){
			obj.init();
		});
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wzLoginAjax.js'], function(obj){
			obj.init();
		});
	</script>
</body>
</html>
