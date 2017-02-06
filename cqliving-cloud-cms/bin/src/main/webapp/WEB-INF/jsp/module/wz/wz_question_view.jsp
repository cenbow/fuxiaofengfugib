<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="问政列表|wz_question_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="问政详情" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-10  version-view form-horizontal">
				<input type="hidden" id="questionId" value="${item.id }">
				<%-- 详细 --%>
		        <div class="detail_title">${item.title }</div>
		        <div class="detail_name">昵称：${item.creator }</div>
<!-- 		        收集信息 -->
				<c:forEach var="collectInfo" items="${collectList }">
					<div class="detail_name">${collectInfo.name }：${collectInfo.value }</div>
				</c:forEach>
		        <p class="detail_content">${item.content }</p>
		        <c:if test="${fn:length(imageList) > 0 }">
			        <div class="row-fluid">
						<ul class="ace-thumbnails">
							<c:forEach var="image" items="${imageList }">
								<li>
									<a href="${image.imageUrl }" data-rel="colorbox">
										<img alt="问政图片" src="${image.imageUrl }" />
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
		        </c:if>
		        	<!-- 转交情况 -->
		        <c:forEach var="it" items="${wzTransferList }" varStatus="i">
		        	<div class="widget-box">
						<div class="widget-header">
							<h4>第${i.index + 1 }次转交&nbsp;&nbsp;&nbsp;&nbsp;待处理部门：${it.wzUser.orgName}&nbsp;&nbsp;&nbsp;&nbsp;受理部门：${it.creator }</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<p>回复内容：${it.replayContent }</p>
								<p>备注：<c:if test="${empty it.description }">无</c:if>${it.description }</p>
							</div>
						</div>
					</div>
		        </c:forEach>
		        <div class="widget-box <c:if test="${item.status == 2 }">hide</c:if>" id="replyDiv">
					<div class="widget-header">
						<h4>回复情况</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<form method="POST">
								<input type="hidden" name="questionId" value="${item.id }">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="auditingDepartment"> 受理部门 ：</label>
									<div class="col-sm-9">
										<input type="text" name="auditingDepartment" placeholder="请输入受理部门" class="col-xs-11 col-sm-12" value="${sysUser.nickname }" disabled/>
									</div>
								</div>
								<hr />
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="auditingDepartment"> 回复内容：</label>
									<div class="col-sm-9">
										<%-- <textarea name="content" class="form-control limited col-xs-11 col-sm-12" rows="10" disabled maxlength="5000">${item.replyContent }</textarea> --%>
										<!-- <input type="hidden" name="content" id="replyContent"> -->
										<script id="reply-content" type="text/plain">${item.replyContent}</script>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-12 text-right">
				<div class="space-4"></div>
				<c:if test="${isCheck && item.status == 2 }">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_check.html">
						<button class="btn btn-primary checkBtn btn-sm" type="button" data-id="${item.id }"><i class="icon-edit bigger-110"></i>审核</button>
					</cqliving-security2:hasPermission>
				</c:if>
				<c:if test="${item.status == 3 || item.status == 5 || item.status == 6}">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_reply.html">
						<button class="btn btn-primary btn-sm" type="button" onclick="editButton(this)"><i class="icon-pencil bigger-110"></i>回复编辑</button>
						<button class="btn btn-primary hide btn-sm" type="button" id="replyButtonSubmit"><i class="icon-save bigger-110"></i>回复保存</button>
					</cqliving-security2:hasPermission>
				</c:if>
				<c:if test="${(fn:length(wzUserList) > 0 && item.status == 3)  || item.status == 6 }">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_transfer.html">
						<button class="btn btn-primary transferBtn btn-sm" type="button" data-id="${item.id }"><i class="icon-exchange bigger-110"></i>转交</button>
					</cqliving-security2:hasPermission>
				</c:if>
				<c:if test="${item.status == 5 }">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_publish.html">
						<button class="btn btn-success buttonPublish btn-sm" type="button" url="wz_question_publish.html?id=${item.id }"><i class="icon-mail-forward bigger-110"></i>发布</button>
					</cqliving-security2:hasPermission>
				</c:if>
				<!-- 新增 功能-->
				<c:if test="${item.status == 7 }">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_publish.html">
						<button class="btn btn-primary btn-sm" type="button" onclick="editButton(this)"><i class="icon-pencil bigger-110"></i>回复编辑</button>
						<button class="btn btn-primary hide btn-sm" type="button" id="replyPublishButtonSubmit"><i class="icon-save bigger-110"></i>回复保存</button>
					</cqliving-security2:hasPermission>
				</c:if>
				
				<c:if test="${!(!isAllowDel && item.status == 7)}">
					<cqliving-security2:hasPermission name="/module/wz/wz_question_publish.html">
						<button class="btn btn-danger btn-sm" id="deleteButton" type="button" url="wz_question_delete.html?id=${item.id }"><i class="icon-remove bigger-110"></i>删除</button>
					</cqliving-security2:hasPermission>
				</c:if>
				<button class="btn btn-sm btn-primary" type="button" onclick="javascript:void(0);" backRestoreParam="backRestoreParam" back_url="/module/wz/wz_question_list.html">
					<i class="icon-undo bigger-110"></i>返回
				</button>
			</div>
		</div>
	</div>
</div>

<div id="checkModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" value="${item.id }" name="questionId">
	        	<input type="hidden" value="1" name="checkStatus">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">审核确认</h3>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                	<textarea class="form-control col-sm-10" name="content" rows="3" required placeholder="请输入审核情况" maxlength="500"></textarea>
	                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm submitBtn" data-status="1"><i class="icon-ok bigger-110"></i>审核通过</button>
                    <button type="button" class="btn btn-warning submitBtn" data-status="-1"><i class="icon-remove bigger-110"></i>审核不通过</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<div id="transferModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post" id="transferForm">
	        	<input type="hidden" value="${item.id }" name="questionId">
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
                    <button type="button" class="btn btn-primary submitBtn"><i class="icon-ok bigger-110"></i>确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-remove bigger-110"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<script type="text/javascript">
var uEditor;
window.UEDITOR_HOME_URL = "/resource/components/ueditor/";
require(['/resource/business/wz/wzQuestionView.js'], function(obj){
	obj.init();
});
function editButton(me){
	$(me).remove();
	$('#replyButtonSubmit').removeClass('hide');
	$('#replyPublishButtonSubmit').removeClass('hide');
	$('#replyDiv').find('input').removeAttr('disabled');
	uEditor.setEnabled();
}
</script>