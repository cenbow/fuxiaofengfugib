<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<c:forEach items="${shops}" var="obj">
	<div class="bu_list_detail" dv="${obj.distance}" cc="${obj.collectCount}" rc="${obj.replyCount}" sid="${obj.id}">
		<div onclick="javascript: location.href='${request.contextPath}/shop/detail/${obj.id}.html'">
			<c:if test="${obj.topType eq 1}">
				<div class="bu_ding">置顶</div>
			</c:if>
			<img src="${obj.coverImg}" />
			<div class="bu_list_content">
				<div class="bu_list_title">${obj.name}</div>
				<div class="bu_list_info">${obj.address}<p>评论 ${obj.replyCount}</p></div>
			</div>
		</div>
		<div class="detail_line"></div>
	</div>
 </c:forEach>