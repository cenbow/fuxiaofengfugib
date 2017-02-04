<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>核销</title>
	<meta name="Keywords" content="">
	<meta name="Description" content=""></head>

<style type="text/css">
	table {
		border-collapse: collapse;
		width: 100%;			
	}
	table, td, th {
		border: 1px solid gray;
	}

</style>
<body>
	<div>
		<form action="${request.contextPath}/zjchj_in/query.html" method="post">
			核销码 <input type="text" name="rewardPwd" style="border: 1px solid gray;	width: 80px; line-height:25px;" /> <input type="submit" value="查询" />
		</form>
	</div>
	<br>
	<c:if test="${not empty award}">
		<div>
	     	<div style=" line-height:25px;">核销码：${award.rewardPwd}</div>
	     	<div style=" line-height:25px;">奖品名称：${award.awardName}</div>
	     	<div style=" line-height:25px;">奖品等级：${allLevels[award.level]}</div>
	     	<div style=" line-height:25px;">中奖时间：<fmt:formatDate value="${award.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	     	<div style=" line-height:25px;">核销状态：${allIsRewards[award.isReward]}</div>
	     	<c:if test="${award.isReward eq 1}">
		     	<div style=" line-height:25px;">核销时间：<fmt:formatDate value="${award.rewardTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
		     	<div style=" line-height:25px;">核销人：${award.backUserName}</div>
	     	</c:if>
	   	</div>
	   	<c:if test="${award.isReward eq 0}">
	   		<form action="${request.contextPath}/zjchj_in/reward.html" method="post">
				<input type="hidden" name="id" value="${award.id}" />
	   			<input type="hidden" name="rewardPwd" value="${award.rewardPwd}" />
	    		<div style=" line-height:25px;">操作：<input type="submit" value="核销" /></div>
	    	</form>
	    </c:if>
	</c:if>
</body>
</html>
