<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>我的问政</title>
</head>
<body>
	<%@include file="wzSessionPage.jsp" %>
	<div id="header">
		<div class="btn_back" onClick="location.href='wz_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>我的问政</h1>
	</div>
	<div id="nav_mine">
		<ul>
			<li class="active"><a>我的问政</a></li>
			<li><a href="wz_my_reply_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }">我的评论</a></li>
		</ul>
	</div>
	<div class="wz_list">
		<div class="none_status" style="display: none;">暂无问政信息</div>
		<ul custom_more="custom_more"></ul>
	</div>
	<div id="create" url="wz_question_add.html"></div>
	
	
	<div class="model_confirm" id="model_confirm" data-id="">
		<div class="edit_bg"></div>
		<div class="select_list">
			<div class="select_list_title">
				确认消息<a><img src="${pageContext.request.contextPath}/resource/images/wz/close.png" /></a>
			</div>
			<ul class="confirm_content">
				确定要删除吗？
			</ul>
			<div class="button" align="center">
				<div class="btn btn_sure" onclick="delSubmit()">提交</div>
				<div class="btn btn_cancel">取消</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		require(['${pageContext.request.contextPath}/resource/js/business/wz/wzMyQuestionList.js'], function(obj){
			obj.init();
		});
		require(['${pageContext.request.contextPath}/resource/js/business/wz/common/wzLoginAjax.js'], function(obj){
			obj.init();
		});
	</script>
</body>
</html>
