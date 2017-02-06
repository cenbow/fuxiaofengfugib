<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${allSourceTypes[sourceType] }推荐列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="recommend_info_list.html" id="recommend_info_FormId">
						<div class="hide"><input type="text"><!-- 解决表单只有一个text回车自动提交的问题 --></div>
	                    <div class="special-list">
                        	<c:if test="${fn:length(appList) > 1 }">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
			                               <c:forEach items="${appList}" var="it">
			                                  <option value="${it.id }" <c:if test="${param.search_EQ_appId eq it.id }">selected</c:if>  >${it.name}</option>
			                               </c:forEach>
				                        </select>
	                        		</div>
                				</div>
							</div>
							</c:if>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_sourceName" name="search_LIKE_sourceName" value="${param.search_LIKE_name }" placeholder="${allSourceTypes[sourceType] }标题" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>保存</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已发布</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>已下线</option>
			                            </select>
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
						<cqliving-security2:hasPermission name="/module/config/${sourceType }/recommend_info_publish.html">
							<button class="btn btn-sm btn-success" id="batchPublishBtn" type="button" url="/module/config/${sourceType }/recommend_info_publish.html"><i class="icon-plus"></i>批量发布</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/config/${sourceType }/recommend_info_delete.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/config/${sourceType }/recommend_info_delete.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="recommend_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
var sourceType = '${sourceType}';
require(['/resource/business/config/recommend_info_list.js'], function(obj){
	obj.init();
});
</script>