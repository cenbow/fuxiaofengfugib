<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="复制新闻查询" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="info_classify_list_copy.html" id="info_classify_FormId">
							<input type="text" style="display: none;"/>
		                    <div class="special-list">
		                    	<c:choose>
		                    		<c:when test="${empty appList}">
		                    			<input type="hidden" name="search_EQ_appId" id="info_classify_appid" value="${search_EQ_appId}" />
		                    		</c:when>
			                    	<c:otherwise>
				                    	<div class="col-xs-6 col-sm-3">
											<div class="form-group">
												<div class="col-sm-12">
					                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_appId" id="info_classify_appid">
						                                <c:forEach items="${appList}" var="obj">
						                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
						                                </c:forEach>
						                            </select>
				                        		</div>
		                    				</div>
										</div>
									</c:otherwise>
								</c:choose>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
				                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="新闻标题" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								<c:if test="${not empty appList}">
		                    		<div class="col-xs-6 col-sm-3"></div>
		                    		<div class="col-xs-6 col-sm-3"></div>
								</c:if>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group <c:if test="${empty appList}">pull-left</c:if>">
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
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="offline_batch_btn"><i class="icon-arrow-down"></i>批量下线</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_del_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="del_batch_btn"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>	
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_classify_copy_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
	require(["/resource/business/infoClassify/infoClassifyCopyList.js"], function(obj) {
		obj.init();
		
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>