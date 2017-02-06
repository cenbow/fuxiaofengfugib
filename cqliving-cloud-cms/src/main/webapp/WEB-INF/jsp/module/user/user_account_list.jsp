<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="APP用户管理|/module/userAccount/user_account_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="user_account_list.html" id="user_account_FormId">
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
				                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="用户姓名" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <input type="text" id="search_LIKE_userName" name="search_LIKE_userName" value="${param.search_LIKE_userName}" placeholder="登录账号" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <input type="text" id="search_LIKE_telephone" name="search_LIKE_telephone" value="${param.search_LIKE_telephone}" placeholder="手机号" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
				                                <option value="">用户类型</option>
												<c:forEach items="${allTypes}" var="obj" varStatus="idx">
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
						                                	<option value="0"<c:if test="${param.search_EQ_status eq '0'}"> selected="selected"</c:if>>正常</option>
						                                	<option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>禁用</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group">
						                            <input name="search_GTE_registTime" type="hidden" value="<fmt:formatDate value="${search_GTE_registTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_registTime" type="hidden" value="<fmt:formatDate value="${search_LT_registTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="registTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_registTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_registTime}"> 至 </c:if><fmt:formatDate value="${search_LT_registTime}" pattern="yyyy-MM-dd" />" placeholder="注册时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group">
						                            <input name="search_GTE_lastLoginTime" type="hidden" value="<fmt:formatDate value="${search_GTE_lastLoginTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_lastLoginTime" type="hidden" value="<fmt:formatDate value="${search_LT_lastLoginTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="lastLoginTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_lastLoginTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_lastLoginTime}"> 至 </c:if><fmt:formatDate value="${search_LT_lastLoginTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="最后登录时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3"></div>
								<c:if test="${empty appList}">
									<div class="col-xs-6 col-sm-3"></div>
								</c:if>
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
							<cqliving-security2:hasPermission name="/module/userAccount/user_account_add.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/userAccount/user_account_add.html'"><i class="icon-plus"></i>新增马甲用户</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/userAccount/start_using_batch.html">
								<button class="btn btn-sm btn-primary operBtn" tip="批量启用" type="button" id="startUsingBatch" url="/module/userAccount/start_using_batch.html"><i class="icon-ok"></i>批量启用</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/userAccount/user_account_delete_batch.html">
								<button class="btn btn-sm btn-danger operBtn" tip="批量禁用" type="button" id="forbiddenBatch" url="/module/userAccount/user_account_delete_batch.html"><i class="icon-ban-circle"></i>批量禁用</button>
							</cqliving-security2:hasPermission>
						</div>
						</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="user_account_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['/resource/business/user/user_account_list.js'],function(account_list){
	account_list.init();
});
</script>