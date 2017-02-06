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
		<div class="btn_back" onClick="location.href='wz_my_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>我的问政</h1>
	</div>
	<div id="nav_mine">
		<ul>
			<li><a href="wz_my_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }">我的问政</a></li>
			<li class="active"><a>我的评论</a></li>
		</ul>
	</div>
	<div id="comment_tab">
		<ul class="tabs">
			<li class="active"><a href="javascript:;" class="commentTab" data-type="1">发出的评论</a></li>
			<li><a href="javascript:;" class="commentTab" data-type="2">收到的评论</a></li>
		</ul>
	</div>
	<div id="contentDiv" class="tab_content">
		<div class="none_status" style="display: none;">暂无信息</div>
		<ul class="comment_list" custom_more="custom_more">
		</ul>
	</div>
	
	<div class="edit_comment">
		<div class="edit_bg" onclick="editBgOnClick()"></div>
		<div class="edit_content">
			<div class="btn_cancel" onclick="editBgOnClick()">取消</div>
			<div class="btn_send btn_active btn" onclick="replySendOnClick()">发送</div>
			<input type="hidden" name="commentId"/>
			<input type="hidden" name="sourceId"/>
			<textarea placeholder="请填写评论内容" name="content"></textarea>
		</div>
	</div>
	
	<div class="model_report">
		<div class="edit_bg"></div>
		<div class="select_list">
			<div class="select_list_title">
				举报<a><img src="${pageContext.request.contextPath}/resource/images/wz/close.png" /></a>
			</div>
			<ul class="select_list_report">
				<c:forEach var="reportType" items="${reportTypeList }">
					<li data="${reportType.code }">${reportType.name }</li>
				</c:forEach>
			</ul>
			<div class="btn btn_submit">提交</div>
		</div>
	</div>
	
	
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
		require(['${pageContext.request.contextPath}/resource/js/business/wz/wzMyReplyList.js'], function(obj){
			obj.init();
		});
		require(['${pageContext.request.contextPath}/resource/js/business/wz/common/reply.js'], function(obj){
			obj.init();
		});
	</script>
</body>
</html>
