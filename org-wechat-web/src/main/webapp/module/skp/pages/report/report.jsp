<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>昨日统计</title>
		<style type="text/css">
			table {
				border-collapse: collapse;			
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
		<table>
			<thead>
				<tr>
					<th colspan="4"><fmt:formatDate value="${date}" pattern="yyyy-MM-dd" /></th>
				</tr>
				<tr>
					<th>参与活动的新增用户数</th>
					<th>帮好友助力的用户数</th>
					<th>帮好友助力的次数</th>
					<th>新增加的主页数</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="val">${newUserNum}</td>
					<td class="val">${helpUserNum}</td>
					<td class="val">${helpNum}</td>
					<td class="val">${newPageNum}</td>
				</tr>
			</tbody>
		</table>
		<hr />
<!-- 		<table> -->
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th colspan="6"> -->
<%-- 						<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />&nbsp;新增用户 --%>
<!-- 					</th> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<th>序号</th> -->
<!-- 					<th>头像</th> -->
<!-- 					<th>昵称</th> -->
<!-- 					<th>性别</th> -->
<!-- 					<th>省份</th> -->
<!-- 					<th>城市</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach items="${newUserList}" var="obj" varStatus="i"> --%>
<!-- 					<tr> -->
<%-- 						<td>${i.count}</td> --%>
<%-- 						<td><img src="${obj.headimgurl}" alt="头像" /></td> --%>
<%-- 						<td>${obj.nickname}</td> --%>
<%-- 						<td>${obj.sex eq 1 ? "男" : "女"}</td> --%>
<%-- 						<td>${obj.province}</td> --%>
<%-- 						<td>${obj.city}</td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
		<table>
			<thead>
				<tr>
					<th colspan="6">
						<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />&nbsp;新增加主页的用户
					</th>
				</tr>
				<tr>
					<th>序号</th>
					<th>头像</th>
					<th>昵称</th>
					<th>性别</th>
					<th>省份</th>
					<th>城市</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${newPageUserList}" var="obj" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td><img src="${obj.headimgurl}" alt="头像" /></td>
						<td>${obj.nickname}</td>
						<td>${obj.sex eq 1 ? "男" : "女"}</td>
						<td>${obj.province}</td>
						<td>${obj.city}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>