<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="短信统计" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					 <form class="form-horizontal" role="form" action="sms_statistics.html" id="sms_statistics_FormId">
	                     <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										总短信数：${sendTotal}
			                            <!-- <input type="text" id="search_LIKE_nickname" name="search_LIKE_nickname" value="${param.search_LIKE_nickname }" placeholder="编辑姓名" class="col-xs-12 form-control" /> -->
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										已使用：${sendTotal - overage}
			                            <%-- <div class="input-group">
				                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="onlineTime" time_options='{"format":"YYYY-MM-DD"}' readonly="true" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_onlineTime}"> 至 </c:if><fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />" placeholder="统计时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div> --%>
	                        		</div>
                				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
										未使用：${overage}
										<!-- <button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton">
											<i class="icon-undo bigger-110"></i>重置
										</button> -->
									</div>
								</div>
							</div>
                    	</div>
						<div class="col-xs-12">							
						<div class="well">
<!-- 							<button class="btn btn-sm btn-danger" type="button" id="detailDownload"><i class="icon-reply"></i>导出</button> -->
						</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="sms_statistics_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
});
</script>