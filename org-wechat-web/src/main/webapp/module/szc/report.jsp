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
		<script src="${request.contextPath}/common/scripts/lib/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="${request.contextPath}/common/scripts/My97DatePicker/WdatePicker.js"></script>
		
	</head>
	<body>
	   <br><br>
	   <a href="${request.contextPath}/count_award.html" target="_blank">查看统计页面</a>
		<table>
			<thead>
				<tr>
				   <th colspan="7">
						<form action="${request.contextPath}/statistics.html">
						  <!--   <input type="text" name="phone" value="" placeholder="请输入中奖电话号码"/> -->
							<input type="text" name="convertCode" value="${param.convertCode}" placeholder="请输入兑奖码核销奖品"/>
							<input type="submit" value="查询" />
						</form>
					</th>
					<%-- <th colspan="4">
						<form action="${request.contextPath}/statistics.html">
							<input type="text" name="dateStr" onFocus="WdatePicker({isShowClear:false,readOnly:true})" value="${empty dateStr ? param.dateStr : dateStr}" placeholder="点击选择日期"/>
							<select name="district">
							  <c:forEach items="${allDistrict}" var="mapp">
							    <option value="${mapp.key }"  ${param.district eq mapp.key ? 'selected' : '' }>${mapp.value }</option>
							  </c:forEach>
							</select>
							<input type="submit" value="查询" />
						</form>
					</th> --%>
				</tr>
				<%-- <tr>
				   <th colspan="3">
						中奖数量：${awardNumDaily}
					</th>
					<th colspan="4">
						核销数量：${verifyAwardNumDaily}
					</th>
				</tr> --%>
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
			    <c:forEach items="${szcUserAwards }" var="award">
			        <tr>
						<td class="val">${award.phone}</td>
						<th class="val">${award.awardName}</th>
						<td class="val">${award.convertCode}</td>
						<td class="val">${allstatus[award.takeStatus]}</td>
						<td class="val"> <fmt:formatDate value="${award.awardTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
						<td class="val"><fmt:formatDate value="${award.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="val">
						  <c:if test="${award.takeStatus eq 1}"><a url="${request.contextPath}/verify_convert_code.html?convertCode=${award.convertCode}" href="javascript:;">核销</a></c:if>
						</td>
				    </tr>
			    </c:forEach>
			</tbody>
		</table>
	</body>
</html>

<script type="text/javascript">
  $(function(){
	  
	  $("a[url]").bind("click",function(){
		  
		  var url = $(this).attr("url");
		  $.post(url,{},function(result){
			  var data = JSON.parse(result);
               if(data.code >=0){
            	   alert("核销成功");
               }else{
            	   alert(data.message);
               }
			   window.location.reload();
		  });
		  
	  });
	  
  })
</script>
