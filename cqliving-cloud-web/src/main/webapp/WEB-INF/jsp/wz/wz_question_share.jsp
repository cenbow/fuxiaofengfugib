<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>问政详情</title>
</head>
<body id="index">
	<%@include file="wzSessionPage.jsp" %>
	<div id="header">
		<div class="btn_back" onClick="window.history.go(-1)">
			<i class="fa"></i>
		</div>
		<h1>问政分享详情</h1>
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
				<p>${question.replyContent }</p>
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
			<div class="section_detail">
				<span>评论</span><span class="section_detail_num">(${question.replyCount })</span>
			</div>
			<c:if test="${fn:length(replyList) == 0 }">
				<div class="none_status">暂无信息</div>
			</c:if>
			<ul class="comment_list">
				<%@include file="wz_question_reply_page.jsp" %>
			</ul>
		</div>
	</div>
</body>
</html>
