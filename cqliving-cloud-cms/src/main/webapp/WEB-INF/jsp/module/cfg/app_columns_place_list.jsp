<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="app_columns_place_list.html" id="app_columns_place_FormId">
                    		<div class="form-group">
		                        <label class="col-sm-1 control-label no-padding-right" for="search_EQ_appId">客户端_ID</label>
		                        <div class="col-sm-2">
		                            		<input type="text" id="search_LIKE_appId" name="search_LIKE_appId" value="${param.search_LIKE_appId }" placeholder="客户端_ID" class="col-xs-12" />
		                        </div>
	                        	
		                        <label class="col-sm-1 control-label no-padding-right" for="search_LIKE_placeName">位置名称</label>
		                        <div class="col-sm-2">
		                            	<input type="text" id="search_LIKE_placeName" name="search_LIKE_placeName" value="${param.search_LIKE_placeName }" placeholder="位置名称" class="col-xs-12" />
		                        </div>
                    		</div>
	                        	
		                    <div class="col-xs-6 col-sm-3">
								<div class="col-sm-offset-10 col-sm-2 text-right">
									<button class="btn btn-info btn-sm" type="button" id="searchButton">
										<i class="icon-search bigger-110"></i>查询
									</button>
									&nbsp;
									<button class="btn btn-sm" type="reset">
										<i class="icon-undo bigger-110"></i>重置
									</button>
								</div>
							</div>
							<div class="well">
								<cqliving-security2:hasPermission name="/module/place/app_columns_place_add.html">
						        <input type="button" class="btn btn-sm" value="新增" onclick="javascript:location.href='/module/place/app_columns_place_add.html'"></input>
						        </cqliving-security2:hasPermission>
							</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="app_columns_place_list_page.jsp"></jsp:include>
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