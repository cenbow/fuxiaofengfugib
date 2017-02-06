<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="栏目管理|/module/columns/app_columns_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="app_columns_list.html" id="app_columns_FormId" method="post">
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
							<div class="well">
							<%-- <cqliving-security2:hasPermission name="/module/columns/app_columns_add.html">
								<button class="btn btn-sm btn-success" id="addbtn" type="button" onclick="javascript:;"><i class="icon-plus"></i>新增一级栏目</button>
							</cqliving-security2:hasPermission> --%>
							<cqliving-security2:hasPermission name="/module/columns/send.html">
								<button class="btn btn-sm btn-success" id="sendbtn" type="button" onclick="javascript:;">发布栏目</button>
							</cqliving-security2:hasPermission>
						</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="app_columns_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script>
require(['/resource/business/config/app_columns_list.js'],function(columns_list){
	columns_list.init();
});
</script>