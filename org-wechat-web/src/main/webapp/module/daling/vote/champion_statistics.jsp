<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大宁音乐季</title>
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

	<input name="date_str" type="hidden" value="${dalingmusicale_time_key}"/>
    <div><a href="javascript:;" id="begin_vote">投票开始</a></div>
    <div>计时：<span id="time_count">0</span>...</div>
    <div><a href="javascript:;" id="end_vote">投票结束</a></div>

    <p>
	<table align="center" cellspacing="1" cellpadding="0">
		<tr>
			<th align="center">页面整体点击量</th>
			<th align="center">参与用户量</th>
			<th align="center">参与投票用户 </th>
		</tr>
		<tr>
			<td align="center">${viewNum}</td>
			<td align="center">${userNum}</td>
			<td align="center">${joinedUserNum}</td>
		</tr>
	</table>
	<br>
	 <p>
	 <p>
	<table align="center" cellspacing="1" cellpadding="0">
		<tr>
			<th align="center">选手编号</th>
			<th align="center">选手名称</th>

			<th align="center">总票数量</th>
		</tr>
		<c:forEach items="${contestants }" var="compet">
		   <tr>
			    <th align="center">${compet.code }</th>
				<th align="center">${compet.name }</th>
				<th align="center">${compet.voteNum }</th>
		   </tr>
		</c:forEach>
	</table>
</div>
</body>

<script src="/module/daling/front/js/libs/jquery.1.8.2.min.js"></script>
<script type="text/javascript" src="/module/daling/js/champion.js"></script>

</html>