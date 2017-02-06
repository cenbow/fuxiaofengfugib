<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<input type="hidden" class="isLastPage" value="${(fn:length(list) >= pageSize) ? false : true }" />
<c:forEach var="item" items="${list }">
	<li data-id="${item.replyId }">
		<div class="comment_list_head">
			<div class="list_head">
				<c:if test="${empty item.headerImg }">
					<img src="${request.contextPath}/front/detail/images/default_head.png"/>
				</c:if>
				<c:if test="${not empty item.headerImg }">
					<img src="${item.headerImg }" />
				</c:if>
			</div>
			<div class="list_info">
				<p class="info_name">${item.userName }</p>
				<p class="info_time">${item.commentTime }</p>
			</div>
		</div>
		<div class="comment_list_content">
			${item.content }
			<p>
				<c:choose>
					<c:when test="${queryType == 2 }">评论<font style="color: #CC6600;">${item.passiveReplyName }</font>的评论：</c:when>
					<c:when test="${not empty item.passiveReplyName && queryType != 2 }">评论<font style="color: #CC6600;">${item.passiveReplyName }</font>的评论：</c:when>
					<c:otherwise>问政标题：</c:otherwise>
				</c:choose>
				${item.sourceTitle }
			</p>
		</div>
		<div class="comment_list_btn">
			<c:choose>
				<c:when test="${queryType == 2 }">
					<div class="btn_reply" comment-id="${item.replyId }" source-id="${item.sourceId }" passive-reply-name="${item.passiveReplyName }" passive-reply-id="${item.userId }" onclick="replyOnclick(this)">回复</div>
					<div class="btn_report" commentId="${item.replyId }" onclick="clickReport(this);">举报</div>
				</c:when>
				<c:otherwise>
					<div class="btn_delete btn" data-id="${item.replyId }" onclick="delFn(${item.replyId})">删除</div>
				</c:otherwise>
			</c:choose>
		</div>
	</li>
</c:forEach>