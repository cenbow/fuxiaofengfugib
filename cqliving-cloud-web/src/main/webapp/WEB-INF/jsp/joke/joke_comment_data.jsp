<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:forEach items="${dataList}" var="obj">
	<li replyid="${obj.id}">
		<div class="comment_list_head">
			<div class="list_head">
				<c:choose>
   					<c:when test="${not empty obj.imgUrl}">
	    				<img src="${obj.imgUrl}"/>
   					</c:when>
   					<c:otherwise>
	    				<img src="${request.contextPath}/front/detail/images/default_head.png"/>
   					</c:otherwise>
   				</c:choose>
			</div>
			<div class="list_info">
				<p class="info_name">
					<c:choose>
	   					<c:when test="${obj.type eq 1}">${obj.anonymousName}</c:when>
	   					<c:otherwise>${obj.nickname}</c:otherwise>
	   				</c:choose>
				</p>
				<p class="info_time">${obj.createTimeStr}</p>
			</div>
			<div class="list_btn">
				<div class="list_btn_zan list_btn_zan_r btn_click">
					<span>${obj.praise}</span><a></a>
				</div>
			</div>
		</div>
		<div class="comment_list_content">${obj.content}</div>
	</li>
</c:forEach>