<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${not empty categoryList}">
   <div class="col-xs-6 col-sm-3">
		<div class="form-group">
			<div class="col-xs-12">
                 <select name="search_EQ_shoppingCategoryId" id="search_EQ_shoppingCategoryId" class="form-control">
                 	<option value="">所有分类</option>
					<c:forEach items="${categoryList}" var="category">
                        <option value="${category.id}" <c:if test="${(param.search_eq_shoppingCategoryId eq category.id)or (empty param.search_eq_shoppingCategoryId and idx.first)}">selected</c:if>>${category.name}</option>
                    </c:forEach>
				 </select>
           </div>
       </div>
	</div>
</c:if>