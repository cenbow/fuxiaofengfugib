<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="举报管理|/module/userReport/user_report_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="user_report_list.html" id="user_report_FormId">
		                    <div class="special-list">
		                    <c:if test="${not empty appList}">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
		                            		<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
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
		                            		<select name="search_LIKE_reportCode" id="search_LIKE_reportCode" class="form-control">
	                            				<option value="">举报类型</option>
												<c:forEach items="${reportTypeList}" var="obj">
				                                  <option value="${obj.code}">${obj.name}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
		                            		<select name="search_EQ_sourceType" id="search_EQ_sourceType" class="form-control">
	                            				<option value="">举报来源</option>
												<c:forEach items="${allSourceTypes}" var="obj">
				                                  <option value="${obj.key}">${obj.value}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
		                            		<select name="search_EQ_operateType" id="search_EQ_operateType" class="form-control">
	                            				<option value="">是否评论举报</option>
												<c:forEach items="${allTypes}" var="obj">
				                                  <option value="${obj.key}">${obj.value}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
		                            		<select name="search_EQ_status" id="search_EQ_status" class="form-control">
	                            				<option value="">所有状态</option>
												<c:forEach items="${allStatuss}" var="obj">
													<c:if test="${STATUS99 ne obj.key}">
					                                  <option value="${obj.key}">${obj.value}</option>
					                                </c:if>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
		                            		<select name="search_EQ_auditingType" id="search_EQ_auditingType" class="form-control">
	                            				<option value="">所有审阅状态</option>
												<c:forEach items="${allAuditingTypes}" var="obj">
				                                  <option value="${obj.key}">${obj.value}</option>
				                                </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
								
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="举报人姓名" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group">
						                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd HH:mm" />">
						                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />">
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm:ss" />" placeholder="举报时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
								<c:if test="${empty appList}">
									<div class="col-xs-6 col-sm-3"></div>
								</c:if>
								<div class="col-xs-6 col-sm-3"></div>
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
	                    	<input type="hidden" id="noPass" value="${noPass }"/>
	                    	<input type="hidden" id="pass" value="${pass }"/>
	                    	<div class="col-md-12">					
								<div class="well">
									<cqliving-security2:hasPermission name="/module/userReport/user_report_add.html">
										<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/userReport/user_report_add.html'"><i class="icon-plus"></i>新增</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="user_report_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['/resource/business/user/user_report_list.js'],function(list){
	list.init();
});
</script>