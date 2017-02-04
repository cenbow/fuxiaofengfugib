<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		
		<script type="text/javascript" src="${request.contextPath}/module/jywth/My97DatePicker/WdatePicker.js"></script>
		
	</head>
	<body>
	   <br><br>
		<table>
			<thead>
				<tr>
					<th colspan="7">
						<form action="${request.contextPath}/ahjy_export/statistics.html">
							开始时间：<input type="text" name="beginTime" onFocus="WdatePicker({isShowClear:false,readOnly:true})" value="${param.beginTime}" placeholder="点击选择开始日期"/>
							结束时间：<input type="text" name="endTime" onFocus="WdatePicker({isShowClear:false,readOnly:true})" value="${param.endTime}" placeholder="点击选择结束日期"/>
							<input type="submit" value="查询" />
						</form>
					</th>
				</tr>
				<c:forEach items="${statisticsData.activityTotalCount }" var="total">
					<tr>
					   <th colspan="1">
							总数统计：
						</th>
					   <th colspan="2">
							活动参与数量：${total.joinNum}
						</th>
						<th colspan="2">
							用户中奖数量：${total.awardNum}
						</th>
						<th colspan="2">
							用户领奖数量：${total.takeNum}
						</th>
					</tr>
				</c:forEach>
				
				<c:forEach items="${statisticsData.activityDailyCount }" var="daily">
					<tr>
					   <th colspan="2">
							活动参与数量：${daily.joinNum}
						</th>
						<th colspan="2">
							用户中奖数量：${daily.awardNum}
						</th>
						<th colspan="1">
							用户领奖数量：${daily.takeNum}
						</th>
						<th colspan="2">
							日期：<fmt:formatDate value="${daily.beginTime}" pattern="yyyy-MM-dd"/>
						</th>
					</tr>
				</c:forEach>
				<tr>
					<th>用户Id</th>
					<th>用户昵称</th>
					<th>活动ID</th>
					<th>奖品code->名称</th>
					<th>领奖时间</th>
					<th>是否中奖</th>
					<th>是否领奖</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${statisticsData.activityDetailList}" var="award">
			        <tr>
						<td class="val">${award.userId}</td>
						<th class="val">${award.nickname}</th>
						<td class="val">${award.activityId}</td>
						<td class="val">${award.code}->${award.name}</td>
						<td class="val"> <fmt:formatDate value="${award.awardGainTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
						<td class="val">${isGetStatus[award.isGetAward] }</td>
						<td class="val">${isAwardStatus[award.isAward] }</td>
				    </tr>
			    </c:forEach>
			</tbody>
		</table>
	</body>
</html>