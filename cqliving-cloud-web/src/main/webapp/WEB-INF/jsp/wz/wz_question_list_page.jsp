<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<input type="hidden" class="isLastPage" value="${(fn:length(list) >= pageSize) ? false : true }" />
<c:forEach var="item" items="${list }">
	<li data-id="${item.id }" view_count="${item.viewCount }" reply_count="${item.replyCount }">
		<a href="wz_question_view.html?id=${item.id }">
			<div class="wz_title">${item.title }</div>
			<div class="wz_info">
				<div class="wz_info_status 
					<c:choose>
						<c:when test="${item.status == '已转交' }">status_zhuanjiao</c:when>
						<c:when test="${item.status == '已受理' }">status_shouli</c:when>
						<c:otherwise>status_huifu</c:otherwise>
					</c:choose>
				">状态：<span>${item.status }</span></div>
				<div class="wz_info_view">阅：${item.viewCount } &nbsp;&nbsp;评：${item.replyCount }</div>
				<div class="wz_info_time">
					${item.createTime }
				</div>
			</div>
		</a>
	</li>
</c:forEach>