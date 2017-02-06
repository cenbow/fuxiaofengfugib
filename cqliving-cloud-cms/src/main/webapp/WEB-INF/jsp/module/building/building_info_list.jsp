<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="楼房信息表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="building_info_list.html" id="building_info_FormId">
	                    <div class="special-list">
                        	<c:choose>
	                    		<c:when test="${empty appList}">
	                    			<input type="hidden" name="search_EQ_appId" id="search_EQ_appId" value="${search_EQ_appId}" />
	                    		</c:when>
		                    	<c:otherwise>
			                    	<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
				                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_appId" id="search_EQ_appId">
					                                <c:forEach items="${appList}" var="obj">
					                                	<option value="${obj.id}" <c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
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
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="楼盘名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <select class="chosen-select" data-placeholder="请选择区域" name="search_EQ_regionCode" id="search_EQ_regionCode">
			                            	<option value="">所有区域</option>
			                                <c:forEach items="${allRegion}" var="it">
			                                	<option value="${it.code}" <c:if test="${param.search_EQ_regionCode eq it.id}">selected="selected"</c:if>>${it.name}</option>
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
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>保存</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
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
				                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="创建时间"  class="form-control">
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
										<button class="btn btn-sm" type="button" id="resetButton" notinclude="select[name=search_EQ_appId]">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="/module/building/building_info_add.html">
							<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/building/building_info_add.html';"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/building/building_info_online/1.html">
							<button class="btn btn-sm btn-primary batchOnlineBtn" type="button" url="/module/building/building_info_online/1.html"><i class="icon-mail-forward"></i>批量发布</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/building/building_info_online/2.html">
							<button class="btn btn-sm btn-danger batchOnlineBtn" type="button" url="/module/building/building_info_online/2.html"><i class="icon-arrow-down"></i>批量下线</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/building/building_info_delete.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchBtn" url="/module/building/building_info_delete.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
						
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="building_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['/resource/business/building/building_info_list.js'], function(obj){
	obj.init();
});
</script>