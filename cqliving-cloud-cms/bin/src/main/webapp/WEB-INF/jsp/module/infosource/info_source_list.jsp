<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="资讯来源表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="info_source_list.html" id="info_source_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3 ${fn:length(allApps) le 1 ? 'hidden' : ''}">
								<div class="form-group">
									<div class="col-xs-12">
									    <select name="search_EQ_appId" class="chosen-select" id="search_EQ_appId">
			                               <c:forEach items="${allApps }" var="app">
			                                  <option value="${app.id }"  ${fn:length(allApps) le 1 or param.search_LIKE_appId eq app.id ? 'selected' : ''}>${app.name }</option>
			                               </c:forEach>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="名称" class="col-xs-12 form-control" />
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
						<cqliving-security2:hasPermission name="/module/info/info_source_add.html">
							<!-- <button class="btn btn-sm btn-success" type="button" url="/module/info/info_source_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button> -->
						    <button class="btn btn-sm btn-success" type="button" forwordSaveParam="forwordSaveParam"   url="/module/info/info_source_add.html"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/info/info_source_delete.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/info/common/info_source_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_source_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.table.curd','chosen'], function(tableCurd){
	tableCurd.initTableCrud();
	
	$("#resetButton").off("click").on("click",function(){
		var paramForm = $(this).parents("form");
		paramForm.find(":input").not(':button, :submit, :reset,select[name=search_EQ_appId]')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected');
	});
	
	$(".chosen-select").chosen({search_contains:true});
	$("#searchButton").click();
	
	
});
</script>