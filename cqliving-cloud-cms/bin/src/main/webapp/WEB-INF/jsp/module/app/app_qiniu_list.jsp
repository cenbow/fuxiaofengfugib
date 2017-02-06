<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="位置版本列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="app_qiniu_list.html" id="app_qiniu_FormId">
		                    <div class="form-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_appId" name="search_LIKE_appId" value="${param.search_LIKE_appId }" placeholder="APP_ID" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_bucketName" name="search_LIKE_bucketName" value="${param.search_LIKE_bucketName }" placeholder="七牛云服务资源名称" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_domainTest" name="search_LIKE_domainTest" value="${param.search_LIKE_domainTest }" placeholder="七牛提供的测试域名" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_domainCustom" name="search_LIKE_domainCustom" value="${param.search_LIKE_domainCustom }" placeholder="绑定的自定义域名" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
						                                <option value="">所有状态</option>
						                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>有效</option>
						                                	<option value="99"<c:if test="${param.search_EQ_status eq '99'}"> selected="selected"</c:if>>删除</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton">
												<i class="icon-search bigger-110"></i>查询
											</button>
											<button class="btn btn-sm" type="button" id="resetButton">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>
							<div class="clear"></div>							
							<div class="well">
							<cqliving-security2:hasPermission name="/module/app/app_qiniu_add.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/app/app_qiniu_add.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/app/app_qiniu_delete_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/app/app_qiniu_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
							</cqliving-security2:hasPermission>
							<button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button>
							<button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
							<button class="btn btn-sm btn-warning" type="button"><i class="icon-download"></i>批量下线</button>
							<button class="btn btn-sm btn-danger" type="button"><i class="icon-remove"></i>清空排序</button>
							<button class="btn btn-sm btn-danger" type="button"><i class="icon-reply"></i>撤稿</button>
						</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="app_qiniu_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd'], function(tableCurd){
	tableCurd.initTableCrud();
});
</script>