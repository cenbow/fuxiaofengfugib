<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="通知发布" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="message_info_list.html" id="message_info_FormId">
		                    <div class="special-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
				                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="标题" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
			                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
				                                <option value="">所有状态</option>
												<c:forEach items="${allStatuss}" var="obj">
													<c:if test="${obj.key lt statusDeleted}">
														<option value="${obj.key}"<c:if test="${param.search_EQ_status eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
													</c:if>
												</c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group pull-left">
										<div class="col-sm-12">
											<input type="text" style="display: none;"/>
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
							<div class="clear"></div>							
							<div class="well">
								<cqliving-security2:hasPermission name="/module/message/message_info_add.html">
									<button class="btn btn-sm btn-success" type="button" url="/module/message/message_info_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
<!-- 									<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/message/message_info_add.html'"><i class="icon-plus"></i>新增</button> -->
								</cqliving-security2:hasPermission>
<%-- 								<cqliving-security2:hasPermission name="/module/message/message_info_delete_batch.html"> --%>
<!-- 									<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/message/message_info_delete_batch.html"><i class="icon-remove"></i>批量删除</button> -->
<%-- 								</cqliving-security2:hasPermission> --%>
<!-- 								<button class="btn btn-sm btn-primary"><i class="icon-mail-forward"></i>批量发布</button> -->
<!-- 								<button class="btn btn-sm btn-primary"><i class="icon-tag"></i>加入专题</button> -->
<!-- 								<button class="btn btn-sm btn-warning"><i class="icon-download"></i>批量下线</button> -->
<!-- 								<button class="btn btn-sm btn-danger"><i class="icon-remove"></i>清空排序</button> -->
<!-- 								<button class="btn btn-sm btn-danger"><i class="icon-reply"></i>撤稿</button> -->
							</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="message_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
	require(["/resource/business/message/messageInfoList.js"], function(obj) {
		obj.init();
		$("body").tooltip({selector:"[data-rel=tooltip]"});
	});
</script>