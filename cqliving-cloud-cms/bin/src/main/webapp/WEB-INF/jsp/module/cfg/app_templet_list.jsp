<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="栏目模板管理|/module/apptemplet/app_templet_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="app_templet_list.html" id="app_templet_FormId">
		                    <div class="special-list">
		                        <c:if test="${not empty appList}">
		                        	<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
					                            <select name="search_EQ_appId" id="search_EQ_appId" class="col-xs-12 form-control chosen-select">
					                            	<option value="">所有APP</option>
													<c:forEach items="${appList}" var="app" varStatus="idx">
					                                  <option value="${app.id}">${app.name}</option>
					                               </c:forEach>
												 </select>
			                        		</div>
	                    				</div>
									</div>
								</c:if>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                        	<input type="text" id="search_LIKE_templetCode" name="search_LIKE_templetCode" value="${param.search_LIKE_templetCode}" placeholder="模板CODE" class="col-xs-12 form-control" />
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
							<div class="col-xs-12">							
							<div class="well">
							<cqliving-security2:hasPermission name="/module/apptemplet/app_templet_add.html">
								<button class="btn btn-sm btn-success" id="addbtn" type="button" onclick="javascript:;"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/apptemplet/app_templet_delete_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/apptemplet/app_templet_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
							</cqliving-security2:hasPermission>
						</div>
						</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="app_templet_list_page.jsp"></jsp:include>
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
	$('body').tooltip({selector:'[data-rel=tooltip]'});
	$("body").on("click","#addbtn",function(){
		var appid=$('#search_EQ_appId').val();
		var url = '/module/apptemplet/app_templet_add.html';
		if(appid){
			url += '?appId='+appid;
		}
		window.location.href = url;
	});
});
</script>