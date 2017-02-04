<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<jsp:include page="common/include.jsp"></jsp:include>
		<title>金猴闹春</title>
		<script type="text/javascript">
			seajs.use("/module/ahjy/scripts/prepare.js?v=1", function(obj) {
		    	obj.init();
			});
		</script>
	</head>
	<body>
		<input type="hidden" id="aid" value="${activityId}" />
		<input type="hidden" id="time" value="${time}" />
		<div class="content">
			<!--内容区-->
			<div class="window1-1 animated bounceInDown delay1">
				<span class="time"> <b>${time}<em>s</em></b> 后开始游戏
				</span>
			</div>
			<div class="start-lnk animated bounceIn delay2">
				<a href="javascript:void(0);"><img src="/module/ahjy/front/images/start-btn.png" /></a>
			</div>
			<!--内容区-->
		</div>
		<jsp:include page="common/hidden.jsp"></jsp:include>
	</body>
</html>