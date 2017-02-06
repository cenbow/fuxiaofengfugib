<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<input type="hidden" class="isLastPage" value="${(fn:length(list) >= 10) ? false : true }" />
<c:forEach var="item" items="${list }">
	<div class="bu_list_detail" data-id="${item.id }" data-sortno="${item.sortNo }" data-starttime="${item.startTimestamp }">
		<c:if test="${item.overdue }">
			<div class="active_end"></div>
		</c:if>
		<div onClick="window.location.href='${item.url }'">
			<img src="${item.imageUrl }" />
			<div class="bu_list_content">
				<div class="bu_list_title">${item.title }</div>
				<div class="bu_list_info">
					<img src="${request.contextPath}/front/detail/images/time_1.png" /><span>活动时间：${item.startTime } 至 ${item.endTime }</span>
					<p>${allTypes[item.showType] }</p>
				</div>
			</div>
		</div>
		<div class="detail_line"></div>
	</div>
</c:forEach>