<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<jsp:include page="common/include.jsp"></jsp:include>
		<title>金猴闹春</title>
		<script type="text/javascript">
			seajs.use("/module/ahjy/scripts/play.js?v=1", function(obj) {
		    	obj.init();
			});
		</script>
	</head>
	<body>
		<input type="hidden" id="aid" value="${activityId}" />
		<div class="content">
	        <!--内容区-->
			<div class="window1-3 animated bounceInDown delay1">
	            <div class="ico-phone animated shake infinite">
	            	<img src="/module/ahjy/front/images/phone.png" />
	            </div>
	        </div>
	        <!--内容区-->
	    </div>
	    <audio id="chatAudio">
			<source src="/module/ahjy/front/sound/5018.mp3" type="audio/mpeg">
    	</audio>
		<jsp:include page="common/hidden.jsp"></jsp:include>
	</body>
</html>