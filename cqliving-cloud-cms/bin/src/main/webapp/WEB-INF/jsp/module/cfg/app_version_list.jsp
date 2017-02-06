<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="App版本管理|/module/version/app_version_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="app_version_list.html" id="app_version_FormId">
						<c:if test="${not empty appList}">
		                    <div class="form-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
	                            			<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
	                            				<option value="">所有APP</option>
												<c:forEach items="${appList}" var="app">
				                                  <option value="${app.id}" <c:if test="${param.search_EQ_appId eq app.id}">selected</c:if>>${app.name}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group pull-left">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton"><i class="icon-search bigger-110"></i>查询</button>
											<button class="btn btn-sm" type="reset" id="resetButton" notinclude="select[name=search_EQ_appId]"><i class="icon-undo bigger-110"></i>重置</button>
										</div>
									</div>
								</div>
	                    	</div>
	                    	</c:if>
	                    	<c:if test="${empty appList}">
	                    		<button style="display: none;" type="button" id="searchButton"></button>
	                    	</c:if>
							<div class="col-xs-12">
								<div class="well">
									<cqliving-security2:hasPermission name="/module/version/app_version_add.html">
										<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/version/app_version_add.html'"><i class="icon-plus"></i>新增</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/version/delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="delete_batch.html"><i class="icon-remove"></i>删除</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
		                </form>
				</div>
				<div class="row" id="table_content_page">
					<jsp:include page="app_version_list_page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
require(['cloud.table.curd','chosen'], function(tableCurd){
	tableCurd.initTableCrud();
	//初始化APP选择列表框
	$(".chosen-select").chosen({search_contains:true});
	$('body').tooltip({selector:'[data-rel=tooltip]'});
});
</script>