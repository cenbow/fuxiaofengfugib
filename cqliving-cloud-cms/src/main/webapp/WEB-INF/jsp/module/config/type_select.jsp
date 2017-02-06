<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${not empty typeList }">
<c:if test="${isList}">
	<div class="col-xs-6 col-sm-3">
		<div class="form-group">
			<div class="col-sm-12">
	            <select name="search_EQ_hotlineTypeId" id="search_EQ_hotlineTypeId" class="form-control">
	                <option value="">所有分类</option>
					<c:forEach items="${typeList}" var="type">
	                   <option value="${type.id}" <c:if test="${param.search_EQ_hotlineTypeId eq type.id}">selected</c:if>>${type.name}</option>
	                </c:forEach>
				</select>
	        </div>
		</div>
	</div>
</c:if>
<c:if test="${! isList}">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appId">所属分类</label>
		<div class="col-sm-9">
			<select name="hotlineTypeId" id="hotlineTypeId" class="form-control">
				<c:forEach items="${typeList}" var="type">
					<option value="${type.id}" <c:if test="${(item.hotlineTypeId eq type.id) or (empty type.id and idx.first)}">selected</c:if>>${type.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</c:if>
</c:if>