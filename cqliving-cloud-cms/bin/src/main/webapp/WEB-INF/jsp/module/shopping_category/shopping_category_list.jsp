<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品分类管理|/module/shopping_category/shopping_category_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="shopping_category_list.html" id="shopping_category_FormId" method="post">
						<c:if test="${not empty appList}">
		                    <div class="form-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
											<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
												<c:forEach items="${appList}" var="app">
				                                  <option value="${app.id}" <c:if test="${(param.search_EQ_appId eq app.id)or (empty param.search_EQ_appId and idx.first)}">selected</c:if>>${app.name}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
	                    	</div>
							<div class="clear"></div>
							</c:if>
							<button style="display: none;" type="submit" id="submitBtn"></button>
		                </form>
					</div>
				</div>
				<div class="row" id="table_content_page">
					<jsp:include page="shopping_category_list_page.jsp"></jsp:include>
				</div>
				<form style="display: none;" id="detail-form" method="post">
					<input type="hidden" name="appId" id="detail-appId"/>
					<input type="hidden" name="parentId" id="detail-parentId"/>
					<input type="hidden" name="level" id="detail-level"/>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/shopping/shopping_category_list.js'], function(list){
	list.init();
});
</script>