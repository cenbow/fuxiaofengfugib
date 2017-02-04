<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/module/ahjy/pages/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<jsp:include page="/module/ahjy/pages/common/include.jsp"></jsp:include>
		<title>金猴闹春</title>
		<script type="text/javascript">
			seajs.use("/module/ahjy/scripts/luckyDraw/ld_win.js?v=1", function(obj) {
		    	obj.init();
			});
		</script>
	</head>
	<body>
		<div class="content nor-bd-bg">
			<form action="/ahjy/${accId}/luckyDraw.html" id="form1" method="post">
				
			</form>
	        <!--内容区-->
			<div class="window1-6 animated bounceInDown delay1"></div>
	        <div class="peace animated infinite">
	        	<img id="peach" src="/module/ahjy/front/images/peace.png" />
	        </div>
	        <!--内容区-->
	        
	        <audio id="chatAudio">
				<source src="/module/ahjy/front/sound/428.mp3" type="audio/mpeg">
	    	</audio>
	        
	    </div>
	</body>
</html>