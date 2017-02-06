<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">	
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="操作日志管理|/module/log_operate/log_operate_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="log_operate_list.html" id="log_operate_FormId">
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
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <div class="input-group">
					                            <input name="search_GTE_operateTime" type="hidden" value="<fmt:formatDate value="${search_GTE_operateTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input name="search_LT_operateTime" type="hidden" value="<fmt:formatDate value="${search_LT_operateTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input type="text" id="operateTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_operateTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_operateTime}"> 至 </c:if><fmt:formatDate value="${search_LT_operateTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="操作时间"  class="form-control">
					                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
					                        </div>
		                        		</div>
	                   				</div>
								</div>								
								
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_operateUser" name="search_LIKE_operateUser" value="${param.search_LIKE_moduleName }" placeholder="操作人" class="form-control" />
		                        		</div>
                    				</div>
								</div>
								
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_moduleName" name="search_LIKE_moduleName" value="${param.search_LIKE_moduleName }" placeholder="模块名称" class="form-control" />
		                        		</div>
                    				</div>
								</div>
								
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_actionName" name="search_LIKE_actionName" value="${param.search_LIKE_actionName }" placeholder="操作" class="form-control" />
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
	                    	<div class="col-md-12">					
								<div class="well">
								</div>
							</div>	
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="log_operate_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input','chosen'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	//初始化APP选择列表框
	$(".chosen-select").chosen({search_contains:true});
	$('body').tooltip({selector:'[data-rel=tooltip]'});
});
</script>