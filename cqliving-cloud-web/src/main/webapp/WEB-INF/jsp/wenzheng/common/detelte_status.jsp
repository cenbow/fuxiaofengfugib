<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<title>已删除</title>
	<style type="text/css">
		/*维护页面*/
		.wrap_phone {
			width: 90%;
			margin: 0 auto;
			text-align: center;
		}
		.err_info {
			font-size:1.6em;
			color: #aeadae;
			border-bottom: 1px solid #ddd;
			height: 4em;
			line-height: 6em;
		}
		.err_title {
			font-size: 6em;
			color: #3d3c3d;
			margin: .5em 0;
			font-weight: bold;
		}
		.wrap_phone img{ width:100%;}
		.err_memo {
			font-size: 1.6em;
			color: #545454;
			margin-bottom: 2em;
		}
		.wrap_phone img.status_pic{ width:26%; margin:40% 0 2em 0;}
		.err_infos{font-size:1.6em;
			color: #666;
			line-height:1.5em; text-align:center;}
	</style>
</head>
<body>
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
