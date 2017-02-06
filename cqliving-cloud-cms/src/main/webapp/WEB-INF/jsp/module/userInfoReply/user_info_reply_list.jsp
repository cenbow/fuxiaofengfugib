<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${navigation}|${action}" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" id="user_info_reply_FormId" action="${action}">
                         	<input type="hidden" id="sourceType" name="search_EQ_sourceType" value="${sourceType}"/>
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<input type="text" id="search_LIKE_replyUser" name="search_LIKE_replyUser" value="${param.search_LIKE_replyUser }" placeholder="评论用户" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
				                            <div class="input-group">
					                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
					                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
					                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="评论时间"  class="form-control">
					                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
					                        </div>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_content }" placeholder="标题" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="评论内容" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<select name="search_EQ_status" id="search_EQ_status" class="col-xs-12 form-control">
			                                <option value="">审核状态</option>
											<c:forEach items="${allStatuss}" var="obj">
												<c:if test="${obj.key lt statusDeleted}">
													<option value="${obj.key}"<c:if test="${param.search_EQ_status eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
												</c:if>
											</c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3"></div>
							<div class="col-xs-6 col-sm-3"></div>
							<div class="col-xs-6 col-sm-3"></div>
							<div class="col-xs-6 col-sm-3"></div>
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
								<c:if test="${sourceType eq SOURCETYPE1}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/news_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/news_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/news_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/news_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/news_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/news_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE2}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/wz_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/wz_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/wz_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/wz_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/wz_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/wz_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE3}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/shop_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/shop_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shop_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/shop_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shop_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/shop_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE4}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/shoot_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/shoot_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shoot_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/shoot_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shoot_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/shoot_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE5}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/joke_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/joke_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/joke_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/joke_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/joke_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/joke_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE6}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/act_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/act_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/act_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/act_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/act_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/act_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE7}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/topic_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/topic_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/topic_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/topic_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/topic_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/topic_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE10}">
									<cqliving-security2:hasPermission name="/module/user_info_reply/tourism_auditing_batch.html">
										<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" type="button" noAuditing="${noAuditing}" ><i class="icon-edit"></i>审核</button>
										<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/user_info_reply/tourism_auditing_batch.html">
											<i class="align-top bigger-125"></i>审核
										</a>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/tourism_export.html">
										<button class="btn btn-sm btn-primary" type="button" id="export" url="/module/user_info_reply/tourism_export.html"><i class="icon-download-alt bigger-130"></i>导出</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/user_info_reply/tourism_reply_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/user_info_reply/tourism_reply_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
									</cqliving-security2:hasPermission>
								</c:if>
						</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="user_info_reply_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<div id="modal-form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">评论审核</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
	                        <div class="col-sm-12">
	                        	<textarea class="form-control limited" rows="3" name="auditingContent" id="auditingContent" maxlength="100" placeholder="请输入审核描述">${item.auditingContent}</textarea>
	                        </div>
                    	</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-success btn-auditing" status="${pass}">
					<i class="icon-ok"></i>
					审核通过
				</button>
				<button class="btn btn-sm btn-warning btn-auditing" status="${noPass}">
					<i class="icon-ok icon-remove"></i>
					审核不通过
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/userInfoReply/user_info_reply_list.js'],function(reply_list){
	reply_list.init();
});
</script>