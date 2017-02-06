<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="人才招聘表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="recruit_info_list.html" id="recruit_info_FormId">
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
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="企业名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_position" name="search_LIKE_position" value="${param.search_LIKE_position }" placeholder="招聘职位" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <div class="input-group">
				                            <input name="search_GTE_publicTime" type="hidden" value="<fmt:formatDate value="${search_GTE_publicTime}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_publicTime" type="hidden" value="<fmt:formatDate value="${search_LT_publicTime}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="publicTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_publicTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publicTime}"> 至 </c:if><fmt:formatDate value="${search_LT_publicTime}" pattern="yyyy-MM-dd" />" placeholder="发布日期"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">上线状态</option>
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>待上线</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>已下线</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
							<c:if test="${not empty appList}">
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
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
						<cqliving-security2:hasPermission name="/module/recruit_info/recruit_info_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/recruit_info/recruit_info_add.html?_model_=_model_" open-model="add" open-title="新增" open-width="750" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/recruit_info/online_batch.html">
				        	<button class="btn btn-sm btn-primary on-out-batch" status='${STATUS3}' type="button" title="已上线的数据将自动过滤 " oper="批量上线" url="/module/recruit_info/online_batch.html?status=${STATUS3}"><i class="icon-arrow-up"></i>批量上线</button>
				        </cqliving-security2:hasPermission>
				        <cqliving-security2:hasPermission name="/module/recruit_info/outline_batch.html">
				        	<button class="btn btn-sm btn-danger on-out-batch" status='${STATUS88}' type="button" title="待上线和已下线的数据将自动过滤 " oper="批量下线" url="/module/recruit_info/outline_batch.html?status=${STATUS88}"><i class="icon-arrow-down"></i>批量下线</button>
				        </cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/recruit_info/recruit_info_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/recruit_info/recruit_info_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="recruit_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
var STATUS1 = ${STATUS1};
var STATUS3 = ${STATUS3};
var STATUS88 = ${STATUS88};
require(['/resource/business/recruit_info/recruit_info_list.js'],function(list){
	list.init();
});
</script>