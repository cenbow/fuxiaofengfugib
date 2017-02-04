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
		<title>北京SKP</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/awards.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap2 clearfix" style="padding:4%;">
			<c:forEach items="${list}" var="obj">
		  		<ul class="dj_list clearfix">
		    		<li class="jp"><img src="${obj.url}?v=<%=version%>"/></li>
		    		<li class="dh">
		    			<a batchid="${obj.batchId}" isreward="${obj.isReward}" href="javascript:void(0);" class="${obj.isReward eq 0 ? 'blue-btn' : 'grey-btn'} md-btn">
		    				${obj.isReward eq 0 ? '确认兑换' : '已经兑换'}
		    			</a>
		    		</li>
		  		</ul>
			</c:forEach>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>