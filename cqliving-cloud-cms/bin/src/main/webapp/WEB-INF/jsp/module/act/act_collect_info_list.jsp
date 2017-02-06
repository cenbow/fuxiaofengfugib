<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
.modal-dialog{padding-top:150px; padding-bottom:150px; min-width:1000px;}
</style>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动信息收集管理|/module/act/act_collect_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="act_collect_info_list.html" id="act_collect_info_FormId">
		                    <div class="special-list">
								<c:if test="${not empty appList}">
									<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
		                            			<select name="search_EQ_appId" id="search_EQ_appId" class="col-xs-12 form-control chosen-select">
		                            				<option value="">所有APP</option>
													<c:forEach items="${appList}" var="app">
					                                  <option value="${app.id}" >${app.name}</option>
					                               </c:forEach>
												 </select>
			                        		</div>
	                    				</div>
									</div>
								</c:if>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
				                                <option value="">所有参数类型</option>
				                                <c:forEach items="${allTypes}" var="type">
				                                	<option value="${type.key}">${type.value}</option>
				                                </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="参数名称" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group pull-left">
										<div class="col-sm-12">
											<input type="text" style="display: none;"/>
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
	                    	<div class="col-md-12">					
								<div class="well">
									<cqliving-security2:hasPermission name="/module/act/act_collect_info_add.html">
										<button class="btn btn-sm btn-success" type="button" url="/module/act/act_collect_info_add.html?type=${TYPE1}&_model_=_model_" open-model="add" open-title="新增填空字段" onclick="javascript:void(0);"><i class="icon-plus"></i>添加填空字段</button>
										<button class="btn btn-sm btn-success" type="button" url="/module/act/act_collect_info_add.html?type=${TYPE2}&_model_=_model_" open-model="add" open-title="新增单选字段" onclick="javascript:void(0);"><i class="icon-plus"></i>添加单选字段</button>
										<button class="btn btn-sm btn-success" type="button" url="/module/act/act_collect_info_add.html?type=${TYPE3}&_model_=_model_" open-model="add" open-title="新增多选字段" onclick="javascript:void(0);"><i class="icon-plus"></i>添加多选字段</button>
										<button class="btn btn-sm btn-success" type="button" url="/module/act/act_collect_info_add.html?type=${TYPE4}&_model_=_model_" open-model="add" open-title="新增下拉字段" onclick="javascript:void(0);"><i class="icon-plus"></i>添加下拉字段</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/act/act_collect_info_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/act/act_collect_info_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>	
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="act_collect_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['/resource/business/act/act_collect_info_list.js'],function(act_collect){
	act_collect.init();
});
</script>