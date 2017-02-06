<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<title>活动内容</title>
<style type="text/css">
	/*处理富文本中的无图模式*/
	.active_common_1 .detail_pic_none{
		height: 100px !important;
	} 
	/*处理页面上的无图模式*/
	.detail_pic_none {
		height: 100px;
	}
</style>
</head>
<body>
	<div class="vote_content">
		<div class="active_common active_common_1">
			<div class="active_common_name">
				<span class="name_title"><span>活动内容</span></span>
			</div>
			<p>
				<imgrp:img appName="${appName }" replace="${not empty param.noimg ? 1 : 0}" content="${content }"/>
			</p>
		</div>
	</div>
	<script type="text/javascript">
	require([ '/resource/js/common/noImgView.js' ], function(obj) {
		obj.init();
	});
	</script>
</body>
</html>