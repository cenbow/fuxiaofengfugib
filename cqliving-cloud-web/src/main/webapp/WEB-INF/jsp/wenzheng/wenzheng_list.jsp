<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<title>问政</title>
</head>
<body id="index">
	<div id="header">
		<div class="btn_back">
			<i class="fa"></i>
		</div>
		<div class="head_search" onClick="window.location.href='search.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }'">
			<img src="${contextPath}/resource/images/wz/search.png" /><span>输入想找的问题</span>
		</div>
		<div class="head_mine btn"  id="myWenzhengBtn" onClick="toMyWz()">我的问政</div>
	</div>
	<input id="queryValue" type="hidden" name="queryValue" value="${queryValue }">
	<div id="nav">
		<ul>
			<li class="nav_category"><a><span class="show"><c:if test="${empty allTypes[type] }">所有类别</c:if><c:if test="${not empty allTypes[type] }">${allTypes[type] }</c:if></span><i class="fa fa-sort-desc"></i></a>
				<div class="nav_list">
					<div class="nav_list_bg"></div>
					<ul>
						<li value="" <c:if test="${empty type }"> class="active"</c:if>>所有类别</li>
						<c:forEach var="item" items="${allTypes }">
							<li value=${item.key } <c:if test="${type == item.key }"> class="active"</c:if>>${item.value }</li>
						</c:forEach>
					</ul>
				</div>
			</li>
			<li class="nav_status"><a><span class="show">
				<c:choose>
					<c:when test="${status == 1 }">已回复</c:when>
					<c:when test="${status == 2 }">已受理</c:when>
					<c:when test="${status == 3 }">已转交</c:when>
					<c:otherwise>所有状态</c:otherwise>
				</c:choose>
				</span><i class="fa fa-sort-desc"></i></a>
				<div class="nav_list">
					<div class="nav_list_bg"></div>
					<ul>
						<li value="" <c:if test="${empty status}"> class="active"</c:if>>所有状态</li>
						<li value="1" <c:if test="${status == 1 }"> class="active"</c:if>>已回复</li>
						<li value="2" <c:if test="${status == 2 }"> class="active"</c:if>>已受理</li>
						<li value="3" <c:if test="${status == 3 }"> class="active"</c:if>>已转交</li>
					</ul>
				</div>
			</li>
			<li class="nav_order_by <c:if test="${sortColumn == 'reply_count' }">active</c:if>" data-column="reply_count"><a>评论<i class="fa <c:if test="${sortColumn == 'reply_count' && sortType == 'asc' }">fa-long-arrow-up</c:if><c:if test="${!(sortColumn == 'reply_count' && sortType == 'asc') }">fa-long-arrow-down</c:if>"></i></a></li>
			<li class="nav_order_by <c:if test="${sortColumn == 'view_count' }">active</c:if>" data-column="view_count"><a>浏览<i class="fa <c:if test="${sortColumn == 'view_count' && sortType == 'asc' }">fa-long-arrow-up</c:if><c:if test="${!(sortColumn == 'view_count' && sortType == 'asc') }">fa-long-arrow-down</c:if>"></i></a></li>
		</ul>
	</div>

	<div class="wz_list" id="questionlistDiv">
		<div class="none_status" style="display:none;">暂无信息</div>
		<ul custom_more="custom_more">
		</ul>
	</div>
	<div id="create" url="add/${appId }.html"></div>
	<script type="text/javascript">
		var paramsObj = {appId: '${appId}', sessionId: '${sessionId}', token: '${token}'};
		var sessionObj = "";
		function setSessionToken(params){
			sessionObj = eval("("+params+")");
			paramsObj.sessionId = sessionObj.sessionId;
			paramsObj.token = sessionObj.token;
		}
		function pullDownRefresh(){//not use
			window.location.href = widnow.location.href;
		}
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wenzheng_list.js'], function(obj){
			obj.init();
			obj.initUrl('${status}', '${type}', '${sortColumn}', '${sortType}');
		});
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wzLoginAjax.js'], function(obj){
			obj.init();
		});
		function toMyWz(){
			try{
				ZWY_ClOUD.getSessionToken("setSessionToken");
			}catch(e){}
			
			setTimeout(function(){
				var url = '/wenzheng/wenzheng_my_list.html?appId=' + paramsObj.appId + '&sessionId=' + paramsObj.sessionId + '&token=' + paramsObj.token;
				window.location.href = url;
			}, 500);
		}
	</script>
</body>
</html>