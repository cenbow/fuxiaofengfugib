<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/module/ahjy/pages/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<jsp:include page="/module/ahjy/pages/common/include.jsp"></jsp:include>
		<title>金猴闹春</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="content nor-bd-bg">
        <!--内容区-->
        	<div class="window1-9 animated bounceInDown delay1">
        		<img src="/module/ahjy/front/images/window1-9-1.png" />
        	</div>
			 <div class="window1-9-2 animated bounceInDown delay1">
	        	<input type="text" maxlength="11" value="" name="phone" id="phone"/>
	        	<span id="tip" style="color: red;">${errorMessage}</span>
	        </div>
	        <div class="window1-9-3 animated bounceInDown delay1">
        		<img src="/module/ahjy/front/images/window1-9-3.png" />
        	</div>
	        <div class="sure-lnk animated bounceIn delay2">
	        	<a href="javascript:void(0);" id="okBtn"><img src="/module/ahjy/front/images/sure-btn.png" /></a>
	        </div>
	        <!--内容区-->
	    </div>
	</body>
</html>
<script type="text/javascript">
	$("body").on("click","#okBtn",function(){
		//debugger;
		var phone=$("#phone").val();
		if(phone){
			 var reg = /^1[3|4|5|8|7]{1}[0-9]{9}$/;
			 if (!reg.test(phone)) {
				 $("#tip").text("手机号码不正确！");
			 }else{
				 $("#tip").text("");
				 var url = "${pageContext.request.contextPath}/ahjy/${accId}/receive/"+phone+".html";
				 $("#okBtn").unbind("click");
				 window.location.href=url;
			 }
		}else{
			$("#tip").text("请输入手机号码！");
		}
	});
</script>