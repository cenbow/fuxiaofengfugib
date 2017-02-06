<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>需要登录才能操作</title>
</head>
<body>

<script type="text/javascript">
require([ '${pageContext.request.contextPath}/resource/js/business/wz/wz_login.js' ], function(obj) {
	obj.init();
});
</script>
</body>
</html>