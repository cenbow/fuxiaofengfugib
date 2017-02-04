<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>北京SKP</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/winners.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap2 clearfix">
			<h2 class="title clearfix"><i></i>中奖用户</h2>
			<p class="lh180">
				兑换时间：7月29日-9月5日（商场工作时间）<br/>
				兑换地点：北京市朝阳区大望桥东北角华贸中心内 北京SKP 4F卡务中心
			</p>
			<div class="clearfix mt30">
				<table class="grade_tabs" cellpadding="0" cellspacing="0" border="0">
  					<tr>
    					<th>日期</th>
					    <th>名次</th>
					    <th>头像</th>
					    <th>昵称</th>
					    <th>分数</th>
  					</tr>
  					<c:forEach items="${list1}" var="obj" varStatus="i">
	  					<tr>
	  						<c:if test="${i.count eq 1}">
							    <th rowspan="3" scope="row" class="spc1">
							    	<div><span>7月28日-8月3日(第一期)</span></div>
							    </th>
	  						</c:if>
						    <td>第${i.count}名</td>
						    <td><img src="${obj.headImgUrl}" /></td>
						    <td>${obj.nickName}</td>
						    <td>${obj.totalScore}分</td>
	  					</tr>
  					</c:forEach>
  					<c:forEach items="${list2}" var="obj" varStatus="i">
	  					<tr>
	  						<c:if test="${i.count eq 1}">
							    <th rowspan="3" scope="row" class="spc2">
							    	<div><span>8月4日-8月10日(第二期)</span></div>
							    </th>
	  						</c:if>
						    <td>第${i.count}名</td>
						    <td><img src="${obj.headImgUrl}" /></td>
						    <td>${obj.nickName}</td>
						    <td>${obj.totalScore}分</td>
	  					</tr>
  					</c:forEach>
  					<c:forEach items="${list3}" var="obj" varStatus="i">
	  					<tr>
	  						<c:if test="${i.count eq 1}">
							    <th rowspan="3" scope="row" class="spc1">
							    	<div><span>8月11日-8月17日(第三期)</span></div>
							    </th>
	  						</c:if>
						    <td>第${i.count}名</td>
						    <td><img src="${obj.headImgUrl}" /></td>
						    <td>${obj.nickName}</td>
						    <td>${obj.totalScore}分</td>
	  					</tr>
  					</c:forEach>
  					<c:forEach items="${list4}" var="obj" varStatus="i">
	  					<tr>
	  						<c:if test="${i.count eq 1}">
							    <th rowspan="3" scope="row" class="spc1">
							    	<div><span>8月18日-8月24日(第四期)</span></div>
							    </th>
	  						</c:if>
						    <td>第${i.count}名</td>
						    <td><img src="${obj.headImgUrl}" /></td>
						    <td>${obj.nickName}</td>
						    <td>${obj.totalScore}分</td>
	  					</tr>
  					</c:forEach>
				</table>
			</div>
			<div class="flex clearfix">
				<p class="flex-1">
					<a id="btn_back" href="javascript:void(0);" class="w blue-btn md-btn">返回</a>
				</p>
			</div>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>