<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="订单收支记录" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="order_list.html" id="order_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <div class="input-group">
				                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="请选择时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
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
						<div class="col-xs-12">							
						<div class="well">
							<cqliving-security2:hasPermission name="/module/order/order_export.html">
								<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/order/order_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
							</cqliving-security2:hasPermission>
						</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="order_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['/resource/business/order/order_list.js'],function(list){
	list.init();
});
</script>