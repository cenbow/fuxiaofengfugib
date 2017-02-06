<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="热线列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<div class="row">
					<form class="form-horizontal" role="form" action="config_hotline_list.html" id="config_hotline_FormId">
	                    <div class="special-list">
                        	<c:if test="${not empty appList}">
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
							</c:if>
                        	<c:if test="${empty appList}">
                        		<input type="hidden" id="search_EQ_appId" value="${appId}"/>
							</c:if>
							<div id="type-div">
	                		</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="热线名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="电话" class="col-xs-12 form-control" />
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
						<cqliving-security2:hasPermission name="/module/config_hotline/config_hotline_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/config_hotline/config_hotline_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/config_hotline/config_hotline_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/config_hotline/config_hotline_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="config_hotline_list_page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/config/config_hotline_list.js'],function(list){
	list.init();
});
</script>