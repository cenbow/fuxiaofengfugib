<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="区县推荐管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="recommend_app_list.html" id="recommend_app_FormId">
	                    <div class="special-list">
	                    	<c:choose>
	                    		<c:when test="${empty appList}">
	                    			<input type="hidden" name="search_EQ_appId" id="recommend_app_appid" value="${search_EQ_appId}" />
	                    		</c:when>
		                    	<c:otherwise>
			                    	<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
				                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_appId" id="recommend_app_appid">
					                                <c:forEach items="${appList}" var="obj">
					                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
					                                </c:forEach>
					                            </select>
			                        		</div>
	                    				</div>
									</div>
								</c:otherwise>
							</c:choose>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
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
						<cqliving-security2:hasPermission name="/module/recommend_app/recommend_app_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/recommend_app/recommend_app_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
							<%-- 跳转页面的方式打开 <button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/recommend_app/recommend_app_add.html'"><i class="icon-plus"></i>新增</button> --%>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/recommend_app/recommend_app_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/recommend_app/recommend_app_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
						<%-- <button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button>
						<button class="btn btn-sm btn-warning" type="button"><i class="icon-download-alt"></i>导出</button>
						<button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
						<button class="btn btn-sm btn-danger" type="button"><i class="icon-reply"></i>撤稿</button> --%>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="recommend_app_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
	require(["/resource/business/config/recommendAppList.js"], function(obj) {
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>