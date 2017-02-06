<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="用户${dataType eq 2 ? '栏目' : '商铺分类' }权限" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="${thisUri}" id="sys_user_data_FormId">
	                    <div class="special-list">
                        	
                           <c:if test="${not empty allApp }">
                             <div class="col-xs-6 col-sm-3">
	                        	<div class="form-group">
			                        <div class="col-xs-12">
			                           <select name="search_EQ_appId" class="chosen-select" data-placeholder="请选择客户端">
			                               <option value="">全部</option>
			                               <c:forEach items="${allApp }" var="app">
			                                     <option value="${app.id }">${app.name}</option>
			                               </c:forEach>
					                    </select>
			                        </div>
			                    </div>
			                  </div>
                        	</c:if>
                        	
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userName" name="search_LIKE_userName" value="${param.search_LIKE_userName }" placeholder="用户名" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group">
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
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="${addUri}">
							<!-- <button class="btn btn-sm btn-success" type="button" url="/module/security/sys_user_data/sys_user_data_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button> -->
							<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='${addUri}'"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="sys_user_data_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','chosen'], function(tableCurd){
	tableCurd.initTableCrud();
	$(".chosen-select").chosen({width:'300px',search_contains:true});
});
</script>