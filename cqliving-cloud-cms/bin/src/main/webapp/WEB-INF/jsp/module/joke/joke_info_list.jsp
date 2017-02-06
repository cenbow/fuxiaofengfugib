<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="段子管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="joke_info_list.html" id="joke_infoFormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="0"<c:if test="${param.search_EQ_status eq '0'}"> selected="selected"</c:if>>草稿</option>
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>待审核</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
<!--                         	<div class="col-xs-6 col-sm-3"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<div class="col-xs-12"> -->
<%-- 			                            <input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="内容" class="col-xs-12 form-control" /> --%>
<!-- 	                        		</div> -->
<!--                 				</div> -->
<!-- 							</div> -->
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <div class="input-group">
				                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="onlineTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_onlineTime}"> 至 </c:if><fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />" placeholder="上线时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
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
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_creator" name="search_LIKE_creator" value="${param.search_LIKE_creator }" placeholder="创建人" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
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
						<cqliving-security2:hasPermission name="/module/joke/joke_info_add.html">
							<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/joke/joke_info_add.html'"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/joke/joke_info_publish_batch.html">
							<button class="btn btn-sm btn-primary" type="button" id="publishBatchButton"><i class="icon-mail-forward"></i>批量发布</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/joke/joke_info_offline_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="offlineBatchButton"><i class="icon-arrow-down"></i>批量下线</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/joke/joke_info_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/joke/joke_info_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="joke_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
	require(["/resource/business/joke/jokeInfoList.js"], function(obj) {
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>