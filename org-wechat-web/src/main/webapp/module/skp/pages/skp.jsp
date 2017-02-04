<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>北京SKP</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/skp.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap-skp">
			<img src="/module/skp/front/images/skp-img.jpg" />
			<dl>
				<dt><i></i>开幕回馈礼</dt>
				<dd><i></i>惊喜福袋礼</dd>
				<dd><i></i>银行刷卡礼</dd>
				<dd><i></i>品牌日十倍积分</dd>
				<dd><i></i>新会员招募礼</dd>
			</dl>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>