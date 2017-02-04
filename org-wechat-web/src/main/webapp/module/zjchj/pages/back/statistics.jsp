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
	<meta id="viewport" name="viewport" content="widtd=device-widtd,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>数据统计</title>
	<meta name="Keywords" content="">
	<meta name="Description" content=""></head>

<style type="text/css">
	table {
		border-collapse: collapse;
		widtd: 100%;			
	}
	table, td, td {
		border: 1px solid gray;
	}
</style>
<body>
	<div>
		<table  width="90%"  cellspacing="1" cellpadding="0" border: 1px solid gray;>
		<tr>
			<td align="center">页面整体点击量</td>
			<td align="center">参与用户量</td>
			<td align="center">点亮菜品用户量</td>
		</tr>
		<tr>
			<td align="center">${totalCount}</td>
			<td align="center">${totalPeople}</td>
			<td align="center">${totalLightedPeople}</td>
		</tr>
	    </table>
	</div>
	<p>
	    <div>点亮菜品用户分布</div>
   		<div>
			<table  width="90%" cellspacing="1" cellpadding="0" border: 1px solid gray; >
				<tr>
					<c:forEach items="${distribution}" var="obj">
						<td align="center">点亮${obj.virtualNum}个</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach items="${distribution}" var="obj">
						<td align="center">${obj.actualNum}人</td>
					</c:forEach>
				</tr>
		    </table>
		</div>
	</p>
	<p>
	    <div>终极大奖统计</div>
   		<div>
			<table  width="90%" cellspacing="1" cellpadding="0" border: 1px solid gray; >
				<tr>
					<td align="center">奖品名称</td>
					<td align="center">达到抽奖资格用户数量</td>
					<td align="center">已中奖数量</td>
					<td align="center">已核销数量</td>
				</tr>
				<tr>
					<td align="center">5000元美食卡一张</td>
					<td align="center">${ec_0}</td>
					<td align="center">${ec_1}</td>
					<td align="center">${ec_2}</td>
				</tr>
		    </table>
		</div>
	</p>
	<p>
		<form method="post" action="${request.contextPath}/zjchj_in/statistics.html">
			开始日期：
			<input type="date" name="bd" value="${bd}" />
			结束日期：
			<input type="date" name="ed" value="${ed}" />
			<input type="submit" value="查询" />
		</form>
	</p>
	<p>
	    <div>中奖统计</div>
   		<div>
			<table  width="90%" cellspacing="1" cellpadding="0" border: 1px solid gray; >
				<tr>
					<td align="center">中奖等级</td>
					<td align="center">奖品名称</td>
					<td align="center">已抽奖数量</td>
					<td align="center">已中奖数量</td>
					<td align="center">已核销数量</td>
				</tr>
				<c:forEach items="${awards}" var="obj">
					<tr>
					    <td align="center">${levelMap[obj.level]}</td>
						<td align="center">${obj.name}</td>
						<td align="center">${obj.drawNum}</td>
						<td align="center">${obj.awardNum}</td>
						<td align="center">${obj.rewardNum}</td>
					</tr>
				</c:forEach>
		    </table>
		</div>
	</p>
	<p>
    	<div>菜品排行</div>
    	<div>
			<table  width="90%" cellspacing="1" cellpadding="0">
				<tr>
				    <td align="center">顺序</td>
					<td align="center">门店名称</td>
					<td align="center">菜品名称</td>
					<td align="center">消费次数</td>
					<td align="center">消费人数量</td>
				</tr>
				<c:forEach items="${billInfos}" var="obj" varStatus="i">
					<tr>
					    <td align="center">${i.count}</td>
						<td align="center">${obj.shopName}</td>
						<td align="center">${obj.itemsName}</td>
						<td align="center">${obj.consumeTime}</td>
						<td align="center">${obj.consumePeople}</td>
					</tr>
				</c:forEach>
		    </table>
		</div>
	</p>
</body>
</html>