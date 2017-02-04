<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<title>统计</title>
		<style type="text/css">
			table {
				border-collapse: collapse;
				width: 100%;			
			}
			table, td, th {
				border: 1px solid gray;
			}
			.val {
				text-align: right;
			}
			img {
				width: 60px;
				height: 60px;
				border-radius: 50%;
				border: 1px solid #878686;
			}
		</style>
	</head>
	<body>
	   <br><br>
	   <table>
	      <thead>
	         <tr>
				<th colspan="7">参与人数：${userNum}</th>
			</tr>
	         <tr>
				<th>区域</th>
				<th width="300px">奖品名称</th>
				<th>中奖情况</th>
				<th>中奖人数</th>
			</tr>
	      </thead>
	      <tbody>
	         <c:forEach items="${userItems }" var="award">
		        <tr>
					<td class="val">${allDistrict[award.district]}</td>
					<th class="val" width="300px">${award.awardName}</th>
					<td class="val">${allTakeStatus[award.takeStatus]}</td>
					<td class="val">${award.userNum}</td>
			    </tr>
		    </c:forEach>
	      </tbody>
	   </table>
	   
		<table>
			<thead>
				<tr>
					<th>区域</th>
					<th>奖品名称</th>
					<th>奖品实际数</th>
					<th>奖品中奖数</th>
					<th>奖品实际剩余数</th>
					<th>奖品剩余预发布数</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${items }" var="award">
			        <tr>
						<td class="val">${allDistrict[award.district]}</td>
						<th class="val">${award.awardName}</th>
						<td class="val">${award.actualNum}</td>
						<td class="val">${award.verifyNum}</td>
						<td class="val">${award.surplusNum}</td>
						<td class="val">${award.virtualNum}</td>
				    </tr>
			    </c:forEach>
			</tbody>
		</table>
	</body>
</html>
