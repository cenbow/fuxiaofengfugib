<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="系统用户管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="sys_user_list.html" id="sys_user_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_username" name="search_LIKE_username" value="${param.search_LIKE_username }" placeholder="登录用户名" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_mobile" name="search_LIKE_mobile" value="${param.search_LIKE_mobile }" placeholder="手机号" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
							
							<div class="col-xs-6 col-sm-3  ${not empty session_user_obj.appId ? 'hidden' : ''}">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_appId" id="search_EQ_appId" class="form-control">
			                                <option value="">所属APP</option>
			                               <c:forEach items="${allApps }" var="app">
			                                  <option value="${app.id}"  ${param.search_EQ_appId eq app.id or session_user_obj.appId eq app.id ? 'selected' : '' }>${app.name }</option>
			                               </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
							
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
		                                	<option value="0"<c:if test="${param.search_EQ_status eq '0'}"> selected="selected"</c:if>>禁用</option>
		                                	<option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>启用</option>
		                                	<option value="2"<c:if test="${param.search_EQ_status eq '2'}"> selected="selected"</c:if>>锁定</option>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<div class="input-group">
				                            <input name="search_GTE_createDate" type="hidden" value="<fmt:formatDate value="${search_GTE_createDate}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_createDate" type="hidden" value="<fmt:formatDate value="${search_LT_createDate}" pattern="yyyy-MM-dd" />">
				                            <input placeholder="创建时间" type="text" id="createDate" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" 
				                            value="<fmt:formatDate value="${search_GTE_createDate}" pattern="yyyy-MM-dd" />${not empty search_GTE_publishTime ? '至' : ''}<fmt:formatDate value="${search_LT_createDate}" pattern="yyyy-MM-dd" />"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3 btn-search pull-right">
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
						<div class="clear"></div>
						<div class="col-xs-12">									
							<div class="well">
							<cqliving-security2:hasPermission name="/module/security/sys_user_add.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/security/sys_user_add.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							</div>
						</div>
		        	</form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="sys_user_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input','chosen'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	$(".chosen-select").chosen({search_contains:true});
});

</script>