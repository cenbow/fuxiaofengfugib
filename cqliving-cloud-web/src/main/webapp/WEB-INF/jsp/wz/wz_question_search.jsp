<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>问政搜索</title>
</head>
<body id="index">
	<%@include file="wzSessionPage.jsp" %>
	<div id="header">
		<div class="btn_back" onClick="location.href='wz_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<input type="text" placeholder="搜索" id="search_text" value="${queryValue }"/><a href="javascript:;" class="btn_search btn" id="search_button">搜索</a>
	</div>
	<div class="wz_list wz_search">
		<div class="none_status" style="display:none;">暂无信息</div>
		<ul custom_more="custom_more">
		
		</ul>
		<c:if test="${fn:length(list) >= pageSize }">
			<div class="list_more btn" id="loadMoreBtn">点击加载更多</div>
		</c:if>
	</div>
	<script type="text/javascript">
	require(['${pageContext.request.contextPath}/resource/js/business/wz/wzQuestionSearch.js'], function(obj){
		obj.init();
	});
	</script>
</body>
</html>