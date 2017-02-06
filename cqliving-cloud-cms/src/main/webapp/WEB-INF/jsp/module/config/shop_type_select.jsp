<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- 编辑页面字段 -->
<c:if test="${!isList}">
<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="shopTypeId">商情分类<i class="text-danger">*</i></label>
	<div class="col-sm-9">
		<select name="shopTypeId" id="shopTypeId" class="form-control">
			<c:if test="${not empty typeList}">
				<c:forEach items="${typeList}" var="type">
					<option value="${type.id}" <c:if test="${(shopTypeId eq type.id) or (empty shopTypeId and idx.first)}">selected</c:if>>${type.name}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
</div>
</c:if>
<!-- 列表查询条件 -->
<c:if test="${isList}">
<div class="col-xs-6 col-sm-3">
	<div class="form-group">
		<div class="col-sm-12">
            <select name="search_EQ_shopTypeId" id="search_EQ_shopTypeId" class="form-control">
                <option value="">所有分类</option>
				<c:if test="${not empty typeList}">
					<c:forEach items="${typeList}" var="type">
	                   <option value="${type.id}" <c:if test="${param.search_EQ_shopTypeId eq type.id}">selected</c:if>>${type.name}</option>
	                </c:forEach>
                </c:if>
			</select>
        </div>
	</div>
</div>
</c:if>