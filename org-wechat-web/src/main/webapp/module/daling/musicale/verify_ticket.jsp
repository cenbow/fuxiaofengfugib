<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
</head>
<body>
    <div><H1>核销页面</H1></div>
    <div>
    
    门票名称：${userTicketDto.ticketName}
    <br><br>
    用户手机：${userTicketDto.phone}
    <br>
    中奖时间：${userTicketDto.grabTime}
    <br>
    核销状态：${allTakeStatus[userTicketDto.takeStatus]}
    <br>
    <br>
    <input type="hidden" value="${userTicketDto.verifyCode}" name="verifyCode">
    <div>
       <c:if test="${userTicketDto.takeStatus eq 1 }">
         <input type="text" value="${param.securityCode}" name="securityCode" placeholder="请输入核销的验证码">
         <br>
          <a href="javascript:void(0)" id="verify">点击核销</a>
          <br>
                    核销请确认票已发放给用户！
       </c:if>
    </div>
</div>
</body>
<script src="/module/daling/front/js/libs/jquery.1.8.2.min.js"></script>
<script type="text/javascript" src="/module/daling/front/js/libs/zepto.js"></script>
</html>

<script type="text/javascript">
$(function(){
	$("#verify").bind("touchend",function(){
		var verifyCode = $(":input[name=verifyCode]").val();
		var securityCode = $(":input[name=securityCode]").val();
		
		if(!securityCode){
			alert("请输入验证码！");
			return;
		}
		var url = "/verify_ticket.html";
		$.ajax({
			url:url,
			dataType:"json",
			type:"post",
			async:true,
			data:{verifyCode:verifyCode,securityCode:securityCode},
			success:function(data,status){
				alert(data.message);
				location.href=location.href;
			},
			error:function(e){
				alert(e);
			}
		});
	});
});
</script>
