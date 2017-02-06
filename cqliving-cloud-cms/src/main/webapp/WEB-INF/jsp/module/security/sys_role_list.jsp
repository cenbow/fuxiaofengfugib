<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="角色管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="sys_role_list.html" id="sys_role_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_roleName" name="search_LIKE_roleName" value="${param.search_LIKE_roleName }" placeholder="角色名称" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<c:if test="${empty session_user_obj.appId }">
                        	   <div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
										    <select name="search_EQ_appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
				                               <option value="">所有APP</option>
				                               <c:forEach items="${allApps}" var="app">
				                                  <option value="${app.id }" <c:if test="${param.search_EQ_appId eq app.id }">selected</c:if>  >${app.name}</option>
				                               </c:forEach>
					                        </select>
		                        		</div>
                    				</div>
							  </div>
                        	</c:if>
                        	
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control ">
			                                <option value="">角色类型</option>
		                                	<option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>是公共角色</option>
		                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>不是公共角色</option>
			                            </select>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group pull-left">
									<div class="col-sm-12">
										<button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton" notinclude="select[name=search_EQ_appId]">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="clear"></div>
						<div class="col-xs-12">									
							<div class="well">
							<cqliving-security2:hasPermission name="/module/security/sys_role_add.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/security/sys_role_add.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							</div>
						</div>
		        	</form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="sys_role_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','chosen'], function(tableCurd){
	tableCurd.initTableCrud();
	//初始化APP选择列表框
	$(".chosen-select").chosen({search_contains:true});
});
</script>