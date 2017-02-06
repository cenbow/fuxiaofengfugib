<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻推荐管理" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="info_classify_list_recommend.html" id="info_classify_FormId">
		                    <div class="special-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
				                            <select name="search_EQ_contextType" id="search_EQ_contextType" class="form-control">
				                                <option value="">所有类型</option>
				                                <c:forEach items="${allContextTypes}" var="obj">
				                                	<option value="${obj.key}"<c:if test="${param.search_EQ_contextType eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
				                                </c:forEach>
				                            </select>
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
										<div class="col-xs-12">
			                            	<select name="search_EQ_recommendStatus" id="search_EQ_recommendStatus" class="form-control">
				                                <option value="">所有状态</option>
				                                <c:forEach items="${allStatuss}" var="obj">
				                                	<option value="${obj.key}"<c:if test="${param.search_EQ_recommendStatus eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
				                                </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_sourceRecommendAppId" id="search_EQ_sourceRecommendAppId">
				                                <option value="">所有APP</option>
				                                <c:forEach items="${appList}" var="obj">
				                                	<option value="${obj.id}"<c:if test="${param.search_EQ_sourceRecommendAppId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
				                                </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <div class="input-group">
					                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" />">
					                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />">
					                            <input type="text" id="onlineTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publishTime}"> 至 </c:if><fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />" placeholder="上线时间"  class="form-control">
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
											<button class="btn btn-sm" type="button" id="resetButton">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>
	                    	<!-- <div class="col-xs-12">	 -->					
<!-- 							<div class="well"> -->
<%-- 								<cqliving-security2:hasPermission name="/module/info/info_add.html"> --%>
<!-- 									<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/info/info_add.html'"><i class="icon-plus"></i>新增</button> -->
<%-- 								</cqliving-security2:hasPermission> --%>
<%-- 								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish_batch.html"> --%>
<!-- 									<button class="btn btn-sm btn-primary" id="publish_batch_btn"><i class="icon-mail-forward"></i>批量发布</button> -->
<%-- 								</cqliving-security2:hasPermission> --%>
<!-- 								<button class="btn btn-sm btn-primary"><i class="icon-tag"></i>加入专题</button> -->
<%-- 								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline_batch.html"> --%>
<!-- 									<button class="btn btn-sm btn-warning" id="offline_batch_btn"><i class="icon-download"></i>批量下线</button> -->
<%-- 								</cqliving-security2:hasPermission> --%>
<%-- 								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_clear_sort_no.html"> --%>
<!-- 									<button class="btn btn-sm btn-danger" id="clear_sort_no_btn"><i class="icon-remove"></i>清空排序</button> -->
<%-- 								</cqliving-security2:hasPermission> --%>
<!-- 							</div> -->
							<!-- </div>	 -->
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_classify_recommend_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<div id="app_column_modal" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">发布到APP</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group">
	                        <label class="col-sm-2 control-label padding-right" for="appColumnId" style="text-align: right;">选择栏目</label>
	                        <div class="col-sm-10">
	                        	<div class="input-group">
		                        	<input type="hidden" id="icid" />
		                        	<input type="hidden" id="commid" />
		                        	<input type="hidden" id="appColumnId" name="appColumnId" />
									<input name="columnsName" type="text" placeholder="请选择所属栏目"  class="col-sm-10 form-control" />
		                            <span class="input-group-btn dropdown-toggle">
										<button class="btn btn-sm btn-primary" type="button">
											<span class="caret"></span>
										</button>
									</span>
		                            <ul class="dropdown-menu" role="menu" style="width:100%;padding:0px;">
		                               <div class="dropdown-default" id="appcolumns_tree"></div>
		                            </ul>
		                        </div>
	                        </div>
	                    </div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>取消
				</button>
				<button class="btn btn-sm btn-success" id="publish_ok_btn">
					<i class="icon-ok"></i>确认
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var appColumns = ${appColumns};

	require(["/resource/business/infoClassify/infoClassifyRecommendList.js"], function(obj) {
		obj.init();
		
		$("#app_column_modal .dropdown-toggle, #appcolumns_tree").bind("click", function(e) {
			e.cancelBubble = true;
			e.stopPropagation();
			$(this).next().show();
		});
		
		$("body").bind("click",function(e){
			$("#app_column_modal .dropdown-toggle").next().hide();
		});
		
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>