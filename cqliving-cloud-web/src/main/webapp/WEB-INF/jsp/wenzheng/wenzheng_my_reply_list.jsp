<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<title>我的问政</title>
</head>
<body>
	<div id="header">
		<div class="btn_back" onClick="location.href='wenzheng_my_list.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }'">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>我的问政</h1>
	</div>
	<div id="nav_mine">
		<ul>
			<li><a href="wenzheng_my_list.html?appId=${param.appId }&sessionId=${param.sessionId }&token=${param.token }">我的问政</a></li>
			<li class="active"><a>我的评论</a></li>
		</ul>
	</div>
	<div id="comment_tab">
		<ul class="tabs">
			<li <c:if test="${empty queryType || queryType eq 1 }"> class="active"</c:if>><a href="javascript:;" class="commentTab" data-type="1">发出的评论</a></li>
			<li <c:if test="${queryType eq 2 }"> class="active"</c:if>><a href="javascript:;" class="commentTab" data-type="2">收到的评论</a></li>
		</ul>
	</div>
	<div id="contentDiv" class="tab_content">
		<div class="none_status" style="display: none; font-size: 1em">暂无信息</div>
		<ul class="comment_list" custom_more="custom_more">
		</ul>
	</div>
	
	<div class="model_report">
		<div class="edit_bg"></div>
		<div class="select_list">
			<div class="select_list_title">
				举报<a><img src="${pageContext.request.contextPath}/resource/images/wz/close.png" /></a>
			</div>
			<ul class="select_list_report">
				<c:forEach var="reportType" items="${reportTypeList }">
					<li data="${reportType.code }">${reportType.name }</li>
				</c:forEach>
			</ul>
			<div class="btn btn_submit">提交</div>
		</div>
	</div>
	
	<div class="edit_comment">
		<div class="edit_bg" onclick="editBgOnClick()"></div>
		<div class="edit_content">
			<div class="btn_cancel" onclick="editBgOnClick()">取消</div>
			<div class="btn_send btn_active btn" onclick="replySendOnClick()">发送</div>
			<input type="hidden" name="commentId"/>
			<input type="hidden" name="sourceId"/>
			<textarea placeholder="请填写评论内容" name="content"></textarea>
		</div>
	</div>
	<script type="text/javascript">
		var paramsObj = {isAjaxPage: 1, queryType: '${queryType}', appId: '${param.appId}', token: '${param.token}', sessionId: '${param.sessionId}'},
			sessionObj = {},
			firstLoadPage = true;
		function setSessionToken(params){
			sessionObj = eval("("+params+")");
			paramsObj.sessionId = sessionObj.sessionId;
			paramsObj.token = sessionObj.token;
		}
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wenzheng_my_reply_list.js'], function(obj){
			obj.init();
		});
		require(['${pageContext.request.contextPath}/resource/js/business/wenzheng/wenzheng_report.js'], function(obj){
			obj.init();
		});
		
		function reloadCurrentPage(){
			try{
				ZWY_ClOUD.getSessionToken("setSessionToken");
			}catch(e){}
			setTimeout(function(){
				var url = 'wenzheng_my_reply_list.html?appId='+paramsObj.appId+'&sessionId='+paramsObj.sessionId+'&token='+paramsObj.token+'&queryType=' + paramsObj.queryType;
				location.href = url;
			}, 500);
		}
	</script>
</body>
</html>
