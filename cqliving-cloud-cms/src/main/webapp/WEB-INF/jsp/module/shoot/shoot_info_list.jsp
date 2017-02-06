<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="随手拍表管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="shoot_info_list.html" id="shoot_info_FormId">
		                    <div class="special-list">
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
				                                <option value="">所有状态</option>
			                                	<option value="2"<c:if test="${param.search_EQ_status eq '2'}"> selected="selected"</c:if>>待审核</option>
			                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>上线</option>
			                                	<option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
				                            </select>
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <div class="input-group">
					                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
					                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
					                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="创建时间"  class="form-control">
					                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
					                        </div>
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="内容" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_loginName" name="search_LIKE_loginName" value="${param.search_LIKE_loginName }" placeholder="登录名" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <input type="text" id="search_LIKE_nickname" name="search_LIKE_nickname" value="${param.search_LIKE_nickname}" placeholder="昵称" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3 btn-search">
<!-- 	                    		<div class="col-xs-6 col-sm-12"> -->
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
	                    	<div class="col-md-12">				
								<div class="well">
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_online_batch.html">
										<button class="btn btn-sm btn-primary" type="button" id="onlineBatchButton"><i class="icon-arrow-up"></i>批量上线</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_offline_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="offlineBatchButton"><i class="icon-arrow-down"></i>批量下线</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shoot/shoot_info_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>	
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shoot_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
	require(["/resource/business/shoot/shootInfoList.js"], function(obj) {
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>