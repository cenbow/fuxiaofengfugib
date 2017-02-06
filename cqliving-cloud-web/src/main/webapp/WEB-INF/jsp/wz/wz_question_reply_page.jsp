<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<input type="hidden" class="isLastPage" value="${(fn:length(replyList) >= pageSize) ? false : true }" />
<c:set var="isSharePage" value="${empty pageType || pageType != 'share' }"/>
<c:forEach var="item" items="${replyList }">
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
			<c:if test="${not empty item.passiveReplyName }">&nbsp;对&nbsp;<font style="color: #CC6600;">${item.passiveReplyName }</font>&nbsp;说:</c:if>
			${item.content }
		</div>
		<c:if test="${isSharePage }">
			<div class="comment_list_btn">
				<div class="btn_reply" commentId="${item.replyId }" sourceId="${id }" onclick="replyOnclick(${item.replyId }, ${id })">回复</div>
				<div class="btn_report" commentId="${item.replyId }">举报</div>
			</div>
		</c:if>
	</li>
</c:forEach>