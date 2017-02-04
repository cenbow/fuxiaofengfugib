<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	table {
		border-collapse: collapse;
		width: 100%;			
	}
	table, td, th {
		border: 1px solid gray;
	}
</style>
</head>
<body>
<div style="text-align: center">

	<table align="center" cellspacing="1" cellpadding="0">
		<tr>
			<th align="center">页面整体点击量</th>
			<th align="center">参与用户量</th>
			<th align="center">参与抽奖用户量</th>
			<th align="center">参与抽奖人次</th>
		</tr>
		<tr>
			<td align="center">${viewNum }</td>
			<td align="center">${userNum }</td>
			<td align="center">${joinedUserNum }</td>
			<td align="center">${joinNum }</td>
		</tr>
	</table>
	    <!--  <p>
	 <input type="date" name="date" value="2016-09-15"><input type="submit" value="统计">
	 <p> -->
	<br>
	<table align="center" cellspacing="1" cellpadding="0">
		<tr>
			<th align="center">门票名称</th>
			<th align="center">中奖人次</th>
			<th align="center">未中奖人次</th>
			<th align="center">核销人次</th>

			<th align="center">中奖人数</th>
			<th align="center">未中奖人数</th>
			<th align="center">核销人数</th>
		</tr>
		
		<c:forEach items="${userTickets }" var="dto">
		  <tr>
			<th align="center">${dto.ticketName }</th>
			<th align="center">${dto.awardNum }</th>
			<th align="center">${dto.notAwardNum }</th>
			<th align="center">${dto.verifyNum }</th>
			<th align="center">${dto.awardUserNum }</th>
			<th align="center">${dto.notAwardUserNum }</th>
			<th align="center">${dto.verifyUserNum }</th>
		  </tr>
		</c:forEach>
	</table>
</div>
</body>
</html>