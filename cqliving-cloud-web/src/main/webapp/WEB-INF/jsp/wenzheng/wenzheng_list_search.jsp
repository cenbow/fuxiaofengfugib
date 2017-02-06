<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<title>问政搜索</title>
</head>
<body>
	<div id="header">
		<div class="btn_back" onClick="location.href='list.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<input type="text" placeholder="搜索" id="search_text" value="${queryValue }"/><a href="javascript:;" class="btn_search btn" id="search_button">搜索</a>
	</div>
	<div class="wz_list wz_search">
		<div class="none_status" style="display:none;">暂无信息</div>
		<ul custom_more="custom_more"></ul>
	</div>
	<script type="text/javascript">
	var paramsObj = {isAjaxPage: '1', isSearch: '1', appId: '${param.appId}', token: '${param.token}', sessionId: '${param.sessionId}'};
	require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wenzheng_list_search.js'], function(obj){
		obj.init();
	});
	</script>
</body>
</html>