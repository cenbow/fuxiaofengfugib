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
				
				   <th colspan="4">
						<form action="${request.contextPath}/jywtinf/statistics.html">
						  <!--   <input type="text" name="phone" value="" placeholder="请输入中奖电话号码"/> -->
							<input type="text" name="exchangeCode" value="${param.exchangeCode}" placeholder="请输入兑奖码核销奖品"/>
							<input type="submit" value="查询" />
						</form>
					</th>
					<th colspan="3">
						<form action="${request.contextPath}/jywtinf/statistics.html">
							<input type="text" name="dateStr" onFocus="WdatePicker({isShowClear:false,readOnly:true})" value="${empty dateStr ? param.dateStr : dateStr}" placeholder="点击选择日期"/>
							<input type="submit" value="查询" />
						</form>
					</th>
				</tr>
				<tr>
				   <th colspan="4">
						中奖数量：${fn:length(awardNumDaily)}
					</th>
					<th colspan="3">
						核销数量：${fn:length(verifyAwardNumDaily)}
					</th>
				</tr>
				<tr>
					<th>手机号</th>
					<th>奖品名称</th>
					<th>兑奖码</th>
					<th>状态</th>
					<th>中奖时间</th>
					<th>核销时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <c:forEach items="${awardList }" var="award">
			        <tr>
						<td class="val">${award.phone}</td>
						<th class="val">${award.awardName}</th>
						<td class="val">${award.exchangeCode}</td>
						<td class="val">${allstatus[award.takeStatus]}</td>
						<td class="val"> <fmt:formatDate value="${award.awardTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
						<td class="val"><fmt:formatDate value="${award.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="val">
						  <c:if test="${award.takeStatus eq 1}"><a href="${request.contextPath}/jywtinf/getaward.html?exchangeCode=${award.exchangeCode}">核销</a></c:if>
						</td>
				    </tr>
			    </c:forEach>
			</tbody>
		</table>
	</body>
</html>