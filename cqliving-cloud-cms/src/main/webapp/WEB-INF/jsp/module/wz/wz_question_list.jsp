<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="问政列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="wz_question_list.html" id="wz_question_FormId">
		                    <div class="special-list">
		                    	<c:if test="${fn:length(appList) > 1 }">
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
											<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
												<option value="">所有APP</option>
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
										<div class="col-sm-12">
					                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
						                                <option value="">所有事件类型</option>
						                                	<option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>建言献策</option>
						                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>投诉举报</option>
						                                	<option value="3"<c:if test="${param.search_EQ_type eq '3'}"> selected="selected"</c:if>>咨询求助</option>
						                                	<option value="4"<c:if test="${param.search_EQ_type eq '4'}"> selected="selected"</c:if>>其他</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
						                                <option value="">所有状态</option>
						                                	<option value="-1"<c:if test="${param.search_EQ_status eq '-1'}"> selected="selected"</c:if>>审核不通过</option>
						                                	<option value="2"<c:if test="${param.search_EQ_status eq '2'}"> selected="selected"</c:if>>未审核</option>
						                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>审核通过待处理</option>
						                                	<option value="4"<c:if test="${param.search_EQ_status eq '4'}"> selected="selected"</c:if>>转交中</option>
						                                	<option value="5"<c:if test="${param.search_EQ_status eq '5'}"> selected="selected"</c:if>>已处理待发布</option>
						                                	<option value="6"<c:if test="${param.search_EQ_status eq '6'}"> selected="selected"</c:if>>已驳回</option>
						                                	<option value="7"<c:if test="${param.search_EQ_status eq '7'}"> selected="selected"</c:if>>已发布</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_regionName" name="search_LIKE_regionName" value="${param.search_LIKE_regionName }" placeholder="所属区域名称" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="标题" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group">
						                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${empty search_GTE_startTime && not empty param.search_GTE_startTime }">${ param.search_GTE_startTime}</c:if>">
						                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" /><c:if test="${empty search_LT_endTime && not empty param.search_LT_endTime }">${ param.search_LT_endTime}</c:if>">
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="创建时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_creatorPhone" name="search_LIKE_creatorPhone" value="${param.search_LIKE_creatorPhone }" placeholder="手机号" class="col-xs-12 form-control" />
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
	                    	<div class="col-sm-12">						
								<div class="well">
									<cqliving-security2:hasPermission name="/module/wz/wz_question_publish_batch.html">
										<button type="button" class="btn btn-sm btn-primary" id="batchPublish" url="/module/wz/wz_question_publish_batch.html"><i class="icon-mail-forward"></i>批量发布</button>
									</cqliving-security2:hasPermission>
									<c:if test="${isCheck }">
										<cqliving-security2:hasPermission name="/module/wz/wz_question_check_batch.html">
											<button type="button" class="btn btn-sm btn-primary" id="checkBatchBtn"><i class="icon-edit"></i>批量审核</button>
										</cqliving-security2:hasPermission>
									</c:if>
									<cqliving-security2:hasPermission name="/module/wz/wz_question_delete_batch.html">
										<button type="button" class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/wz/wz_question_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/wz/wz_question_excel.html">
										<button type="button" class="btn btn-sm btn-primary" type="button" id="detailDownload" url="/module/wz/wz_question_excel.html"><i class="fa fa-cloud-download"></i>EXCEL导出</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>	
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="wz_question_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<div id="checkModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content" style="width: 700px;">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" value="" name="questionId">
	        	<input type="hidden" value="1" name="checkStatus">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">审核确认</h3>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                	<!-- 请确认审核？ -->
	                	<textarea class="form-control col-sm-10" name="content" rows="3" required placeholder="请输入审核情况" maxlength="500"></textarea>
	                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm submitBtn" data-status="1"><i class="icon-ok bigger-110"></i>审核通过</button>
                    <button type="button" class="btn btn-warning btn-sm submitBtn" data-status="-1"><i class="icon-remove bigger-110"></i>审核不通过</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<div id="transferModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post" id="transferForm">
	        	<input type="hidden" value="" name="questionId">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">转交确认</h3>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"> 受理人： </label>
						<div class="col-sm-9">
							<select name="currentUserId" class="col-xs-11 col-sm-10" data-placeholder="请选择受理人" id="selectAuditingSelect">
								<option value="">请选择受理人</option>
	                            <c:forEach items="${wzUserList }" var="user">
	                            	<option value="${user.id }">${user.orgName }</option>
	                            </c:forEach>
                            </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="auditingDepartment"> 受理部门 ：</label>
						<div class="col-sm-9">
							<input type="text" id="auditingDepartment" name="auditingDepartment" placeholder="请输入受理部门" class="col-xs-11 col-sm-10" value="${sysUser.nickname }"/>
						</div>
						<span class="help-inline col-xs-12 col-sm-7">
							<span class="middle highlight">*请谨慎填写，手机前端显示</span>
						</span>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="description"> 备注： </label>
						<div class="col-sm-9">
							<textarea name="description" id="description" placeholder="请输入备注" rows="4" class="col-xs-11 col-sm-10" ></textarea>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm submitBtn"><i class="icon-ok bigger-110"></i>确认</button>
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><i class="icon-remove bigger-110"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<div id="replyModal" class="modal fade">
	<div class="modal-dialog" style="width: 800px;">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" value="" name="questionId">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">回复信息</h3>
	            </div>
	            <div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right"> 受理部门 ：</label>
						<div class="col-sm-10">
							<input type="text" name="auditingDepartment" placeholder="请输入受理部门" class="col-xs-12 col-sm-12" value="${sysUser.nickname }"/>
						</div>
						<span class="help-inline col-xs-12 col-sm-7">
							<span class="middle highlight">*请谨慎填写，手机前端显示</span>
						</span>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right"> 内容： </label>
						<div class="col-sm-10">
							<!-- <textarea name="content" placeholder="请输入回复内容" rows="20" class="col-xs-12 col-sm-12"  maxlength="5000"></textarea> -->
							<!-- <input type="hidden" name="content" id="replyContent"> -->
							<script id="reply-content" type="text/plain"></script>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm submitBtn"><i class="icon-ok bigger-110"></i>确认</button>
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><i class="icon-remove bigger-110"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<div id="returnModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" value="" name="questionId">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">驳回理由</h3>
	            </div>
	            <div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right">驳回理由： </label>
						<div class="col-sm-10">
							<textarea name="content" placeholder="请输入驳回理由" rows="10" class="col-xs-12 col-sm-12" ></textarea>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm submitBtn"><i class="icon-ok bigger-110"></i>确认</button>
                    <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><i class="icon-remove bigger-110"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>
<script type="text/javascript">
var uEditor;
window.UEDITOR_HOME_URL = "/resource/components/ueditor/";
require(['/resource/business/wz/wzQuestionList.js'], function(obj){
	obj.init();
	$('body').tooltip({selector:'[data-rel=tooltip]'});
});
</script>