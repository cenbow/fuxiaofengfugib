<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻详情数据统计表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="analy_info_detail_list.html" id="analysis_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_nickname" name="search_LIKE_nickname" value="${param.search_LIKE_nickname }" placeholder="编辑姓名" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="新闻标题" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
										<div class="input-group">
											<input id="search_EQ_columnsId" name="search_EQ_columnsId" value="${param.search_EQ_columnsId }" type="hidden" />
											<input name="columnsName" id="columnsName" type="text" placeholder="请选择所属栏目"  class="col-sm-12 form-control" />
											<span class="input-group-btn dropdown-toggle">
												<button class="btn btn-sm btn-primary" type="button">
													<span class="caret"></span>
												</button>
											</span>
											<ul class="dropdown-menu dropdown-menu-right" role="menu" style="width:100%;padding:0px;">
				                               <div class="dropdown-default" id="appcolumns_tree"></div>
				                            </ul>
										</div>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
				                            <div class="input-group">
					                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input type="text" id="onlineTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_onlineTime}"> 至 </c:if><fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="新闻发布时间"  class="form-control">
					                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
					                        </div>
	                        		</div>
                				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
				                            <div class="input-group">
					                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />">
					                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="统计时间"  class="form-control">
					                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
					                        </div>
	                        		</div>
                				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3 pull-right">
								<div class="form-group pull-right">
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
						<button class="btn btn-sm btn-danger" type="button" id="detailDownload"><i class="icon-reply"></i>导出</button>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="analy_info_detail_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input','common_treeview'], function(tableCurd,timeInput,common_treeview){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	var appColumns = ${appColumns};
	
	$("#analysis_FormId .dropdown-toggle,#analysis_FormId .dropdown-menu").bind("click",function(e){
		 e.cancelBubble = true;
		 e.stopPropagation();
		 $(this).next().show();
	 });
	 
	 $("body").bind("click",function(e){
		 $("#analysis_FormId .dropdown-toggle").next().hide();
	 });
	
	 common_treeview.treeview("appcolumns_tree",appColumns.data,function(dataParam){
		$("#search_EQ_columnsId").val(dataParam.id);
		$("#columnsName").val(dataParam.name);
		$("body").click();
	});
});
</script>