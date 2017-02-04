<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<%
    String version = "1.1";
%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>我的主页</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/tutorial.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap2 clearfix">
			<ul class="photo-compare clearfix">
				<li><strong>制作前</strong><img src="/module/skp/front/images/photo1.png?v=<%=version%>" /></li>
				<li><strong>制作后</strong><img src="/module/skp/front/images/photo2.png?v=<%=version%>" /></li>
			</ul>
			<div class="clearfix step">
				<p><span>第一步：</span><em>上传或拍摄你的照片</em></p>
				<p><span>第二步：</span><em>在三个相框模板中选择一个你喜欢的进行制作</em></p>
				<p><span>第三步：</span><em>选择你喜欢的饰品添加到照片上（每个饰品有着不同的时尚指数，时尚指数决定着你最后的排名）</em></p>
				<p><span>第四步：</span><em>确认生成你的时尚大片（转发你的时尚大片，让好友为你助力增加你</em></p> 
			</div>
			<div class="flex"><p class="flex-1"><a id="btn_back_to_mk" href="javascript:void(0);" class="w blue-btn md-btn">返回</a></p></div>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>