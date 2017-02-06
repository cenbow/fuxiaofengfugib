<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<input type="hidden" class="isLastPage" value="${(fn:length(list) >= pageSize) ? false : true }" />
<c:forEach var="item" items="${list }">
	<li data-id="${item.id }">
		<a href="javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:'${item.id }', sourceId: '${item.id }', detailViewType: '2', sourceType: '2', commentType: '0', url: '${webPath}wenzheng/view/${item.id}.html', shareUrl: '${webPath}wenzheng/share/${item.id }.html', title: '${myfn:base64(item.title) }', synopsis: '${myfn:base64(item.content) }', shareImgUrl: 'http://images.cqliving.com/images/icon/${param.appId }.png'}))">
			<div class="wz_title">${item.title }</div>
			<div class="wz_info">
				<div class="wz_info_status 
					<c:choose>
						<c:when test="${item.status == '已转交' || item.status == '已下线'}">status_zhuanjiao</c:when>
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
		<div class="wz_list_btn">
			<c:if test="${item.status == '待审核' }">
				<div class="btn_edit btn" onclick="location.href='add/${param.appId }.html?id=${item.id}'">修改</div>
			</c:if>
			<c:if test="${item.status == '待审核' || item.status == '已拒绝'  || item.status == '已下线' }">
				<div class="btn_delete btn" data-id="${item.id }" onclick="delOnClick(${item.id })">删除</div>
			</c:if>
		</div>
	</li>
</c:forEach>