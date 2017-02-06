<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="话题表列表" name="_breadcrumbs_1" />
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<div class="row">
					<form class="form-horizontal" role="form" action="topic_list.html" id="topicFormId">
						<div class="special-list">
							<c:if test="${fn:length(appList) > 1 }">
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
											<c:forEach items="${appList}" var="it">
												<option value="${it.id }" <c:if test="${param.search_EQ_appId eq it.id}">selected</c:if>>${it.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							</c:if>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<input type="text" name="search_LIKE_name" id="search_LIKE_name" class="form-control" value="${search_LIKE_name }" placeholder="话题名称">
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_LIKE_types" id="search_LIKE_types" class="form-control chosen-select" data-placeholder="请选择话题分类">
											<option value="">所有分类</option>
											<c:forEach items="${typesList}" var="it">
												<option value="${it.id }" <c:if test="${param.search_LIKE_types eq it.id}">selected</c:if>>${it.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_status" id="search_EQ_status" class="form-control">
											<option value="">所有状态</option>
											<option value="-1" <c:if test="${param.search_EQ_status eq '-1'}"> selected="selected"</c:if>>审核不通过</option>
											<option value="1" <c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>待审核(草稿)</option>
											<option value="3" <c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已发布</option>
											<option value="88" <c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_isTop" id="search_EQ_isTop" class="form-control">
											<option value="">所有置顶状态</option>
											<option value="0" <c:if test="${param.search_EQ_isTop eq '0'}"> selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${param.search_EQ_isTop eq '1'}"> selected="selected"</c:if>>是</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_sourceType" id="search_EQ_sourceType" class="form-control">
											<option value="">所有来源</option>
											<option value="1" <c:if test="${param.search_EQ_sourceType eq '1'}"> selected="selected"</c:if>>APP用户</option>
											<option value="2" <c:if test="${param.search_EQ_sourceType eq '2'}"> selected="selected"</c:if>>后台用户</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_isAudit" id="search_EQ_isAudit" class="form-control">
											<option value="">所有审核状态</option>
											<option value="0" <c:if test="${param.search_EQ_isAudit eq '0'}"> selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${param.search_EQ_isAudit eq '1'}"> selected="selected"</c:if>>是</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="input-group">
											<input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
											<input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
											<input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />"placeholder="创建时间" class="form-control"> <span class="input-group-addon"><i class="icon-calendar"></i></span>
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
										<button class="btn btn-sm" type="button" id="resetButton" notinclude="select[name=search_EQ_appId]">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="well">
								<cqliving-security2:hasPermission name="/module/topic/topic_add.html">
									<button class="btn btn-sm btn-success" type="button" url="/module/topic/topic_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);">
										<i class="icon-plus"></i>创建话题
									</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/topic/topic_check.html">
									<button id="batchCheckButton" class="btn btn-sm btn-success" type="button" url="/module/topic/topic_check.html" onclick="javascript:void(0);">
										<i class="icon-edit"></i>批量审核
									</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/topic/topic_online.html">
									<button id="batchOnlineDownButton" class="btn btn-sm btn-danger" type="button" url="/module/topic/topic_online.html" onclick="javascript:void(0);">
										<i class="icon-arrow-down"></i>批量下线
									</button>
								</cqliving-security2:hasPermission>
								
							</div>
						</div>
					</form>
				</div>
				<div class="row" id="table_content_page">
					<jsp:include page="topic_list_page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	require([ '/resource/business/topic/topic_list.js' ], function(obj) {
		obj.init();
	});
</script>