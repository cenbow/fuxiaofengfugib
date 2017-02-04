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
			<div class="window1-2 animated bounceInDown delay1">
	            <span class="time">
	                <b>${time < 0 ? 0 : time}<em>s</em></b>
	                后开始游戏
	            </span>
	        </div>
	        <!--内容区-->
	    </div>
		<jsp:include page="common/hidden.jsp"></jsp:include>
	</body>
</html>