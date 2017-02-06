<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>问政详情</title>
</head>
<body>
	<%@include file="wzSessionPage.jsp" %>
	<div id="header">
		<div class="btn_back" onClick="window.history.go(-1)">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>详情</h1>
	</div>
	<div class="wrap">
		<div class="section">
			<div class="detail_title">${question.title }</div>
			<div class="detail_infos">
				<p>提问人：${question.creator }</p>
				<p>受理时间：<fmt:formatDate value="${question.auditingTime }" pattern="yyyy-MM-dd HH:mm"/></p>
				<p>受理部门：${question.auditingDepartment }</p>
				<p>回复时间：<fmt:formatDate value="${question.replyTime }" pattern="yyyy-MM-dd HH:mm"/></p>
			</div>
			<div class="detail_content">
				<p>${question.content }</p>
				<c:forEach var="img" items="${imageList }">
					<div style="margin:5px;"><img src="${img.imageUrl }"></div>
				</c:forEach>
			</div>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>受理部门回复</span>
			</div>
			<div class="detail_content">
				<p>
					<c:if test="${question.status == -1 }">
						${question.rejectContent }
					</c:if>
					<c:if test="${question.status != -1 }">
						${question.replyContent }
					</c:if>
				</p>
			</div>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>事件进度</span>
			</div>
			<ul>
				<c:if test="${question.status == 7 }">
					<li class="speed_info speed_over">
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/PIN-2.png" />已回复<span><fmt:formatDate value="${question.replyTime }" pattern="yyyy-MM-dd"/></span>
						</div>
						<div class="speed_line"></div>
					</li>
				</c:if>
				<c:if test="${question.status > 3  }">
					<li class="speed_info">
						<c:if test="${question.status == 7 }">
							<div class="speed_line"></div>
						</c:if>
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/dot.png" /> 已转交相关部门<span><fmt:formatDate value="${question.transferTime }" pattern="yyyy-MM-dd"/></span>
						</div>
						<div class="speed_line"></div>
					</li>
				</c:if>
				<c:if test="${question.status >= 3 }">
					<li class="speed_info">
						<c:if test="${question.status > 3  }">
							<div class="speed_line"></div>
						</c:if>
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/dot.png" />已受理<span><fmt:formatDate value="${question.auditingTime }" pattern="yyyy-MM-dd"/></span>
						</div>
					</li>
				</c:if>
			</ul>
		</div>
		<div class="section">
			<div class="section_detail" id="section_detail_div">
				<span>评论</span><span class="section_detail_num">(${question.replyCount })</span>
			</div>
			<div class="none_status" style="display: none;">暂无信息</div>
			<ul class="comment_list" custom_more="custom_more"></ul>
		</div>
	</div>
	<div id="footer">
		<div class="footer_input" data-id="${question.id }" onclick="footerInputOnclick(${question.id })">
			<img src="${pageContext.request.contextPath}/resource/images/wz/edit.png" /><span>写评论</span>
		</div>
		<div class="footer_share" onclick="window.AppJsObj.share('${question.title}', '${shareContent}', 'http://static.yingyonghui.com/icon/128/3704767.png', 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}/wz/home/wz_question_share.html?id=${question.id}');">
			<img src="${pageContext.request.contextPath}/resource/images/wz/share.png" />
			<p>分享</p>
		</div>
		<div class="footer_comment">
			<a href="#section_detail_div">
			<img src="${pageContext.request.contextPath}/resource/images/wz/comment.png" />
			<p>评论</p>
			</a>
		</div>
	</div>
	
	<div class="edit_comment">
		<div class="edit_bg " onclick="editBgOnClick()"></div>
		<div class="edit_content">
			<div class="btn_cancel" onclick="editBgOnClick()">取消</div>
			<div class="btn_send btn_active btn" onclick="replySendOnClick()">发送</div>
			<input type="hidden" name="commentId"/>
			<input type="hidden" name="sourceId" value="${question.id }"/>
			<textarea placeholder="请填写评论内容" name="content"></textarea>
		</div>
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
	<script type="text/javascript">
	require([ '${pageContext.request.contextPath}/resource/js/business/wz/common/reply.js' ], function(obj) {
		obj.init();
	});
	
	require([ '${pageContext.request.contextPath}/resource/js/business/wz/wzQuestionReplyList.js' ], function(obj) {
		obj.init('${question.id}');
	});

	</script>
</body>
</html>
