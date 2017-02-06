<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>答题</title>
</head>
<body>
<div id="header_dj">
	<a href="javascript:doBack();"></a>
	<h3>答题</h3>
</div>
<div id="answer">
	<div class="answerType">${actInfo.title }</div>
	<ul class="answerType_list">
		<c:forEach var="it" items="${classifyList }">
			<li>
				<img src="${request.contextPath}/front/detail/images/pics.png" />
				<a>
					<div class="type_list_title">${it.title }</div>
					<div class="btn_click answer_list_btn" onclick="start(${it.id}, '${appId }', '${sessionId }', '${token }')">开始答题</div>
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
</body>
<script type="text/javascript">
	require([ '/resource/js/business/act/answer/answerClassifyList.js' ], function(obj) {
		obj.init();
	});
	function doBack(){
		window.AppJsObj.answerGoBack();
	}
</script>

</html>
