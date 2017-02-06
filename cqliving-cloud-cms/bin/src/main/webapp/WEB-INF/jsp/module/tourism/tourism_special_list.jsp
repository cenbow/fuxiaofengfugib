<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="专题子景点列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="tourism_special_list.html" id="tourism_info_FormId">
					
					    <input type="hidden" name="tourismId" value="${param.tourismId}">
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
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_regionCode" id="search_EQ_regionCode" class="form-control">
			                                <option value="">所有区域</option>
		                            		<c:forEach items="${regionList}" var="obj">
				                                <option value="${obj.code}" appid="${obj.appId}" <c:if test="${param.search_EQ_regionCode eq obj.code}"> selected="selected"</c:if>>${obj.name}</option>
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
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
                    		<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group pull-left">
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
						<cqliving-security2:hasPermission name="/module/tourism/tourism_special_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/tourism/tourism_special_delete_batch.html"><i class="icon-trash"></i>批量移除专题</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/tourism/tourism_special_publish_batch.html">
						   <button class="btn btn-sm btn-primary" id="publush_batch" type="button" url="/module/tourism/tourism_special_publish_batch.html" status="3"><i class="icon-mail-forward"></i>批量发布</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/tourism/tourism_special_offline_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="offline_batch" url="/module/tourism/tourism_special_offline_batch.html" status="88"><i class="icon-arrow-down"></i>批量下线</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/tourism/join_special.html">
						   <button class="btn btn-sm btn-primary" type="button" url="/module/tourism/join_special.html?specialId=${param.tourismId }&_model_=_model_" open-model="update" open-title="加入专题" data-placement="top"><i class="icon-tag"></i>加入专题</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="tourism_special_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
	require(["/resource/business/tourism/tourism_special_sub.js"], function(obj) {
		obj.init();
		
		$("body").tooltip({selector: "[data-rel=tooltip]"});
	});
</script>