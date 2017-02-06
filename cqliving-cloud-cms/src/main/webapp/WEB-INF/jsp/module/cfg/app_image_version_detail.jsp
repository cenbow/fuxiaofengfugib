<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="LODING图管理|/module/appimgversion/app_image_version_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
	
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="${pageContext.request.contextPath}/module/appimgversion/app_image_version_${empty item.id ? 'add' : 'update'}.html">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <c:if test="${not empty appList}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="appId">所属APP</label>
                        <div class="col-sm-10">
                            <select name="appId" id="appId" class="form-control chosen-select">
								<c:forEach items="${appList}" var="app" varStatus="idx">
                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                    </c:if>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="title">标题</label>
                            <div class="col-sm-10">
                            <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="请输入标题"  value="${item.title}">
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="loadingUrl">广告图</label>
                            <div class="col-sm-10">
                            <input type="hidden" id="loadingUrl" name="loadingUrl" value="${item.loadingUrl}">
	                        <div class="col-sm-10">
	                   			<div id="img_upload" title="点击上传">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="loadingView">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.loadingUrl}">
		                                 <li>
		                                 	<a href="${item.loadingUrl}" data-rel="colorbox">
										 		<img alt="150x150" style="width:150px;height:150px;" src="${item.loadingUrl}">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
													</a>
												</div>
												<input type="text" name="linkUrl" class="form-control link_url" placeholder="请输入广告链接地址" value="${item.linkUrl}"/>
											</li>
											</c:if>
		                             </ul>
		                        </div>
	                        </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="type">客户端类型</label>
                            <div class="col-sm-10">
                            <select class="form-control" id="type" name="type" value="${item.type}">
                            	<c:forEach items="${allTypes}" var="type" varStatus="idx">
                                  <option value="${type.key}" <c:if test="${item.type eq type.key}">selected</c:if>>${type.value}</option>
                               </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-12" id="show-time-div">
                        <label class="col-sm-2 control-label no-padding-right" for="type">显示时长</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="showTime" name="showTime" maxlength="5" placeholder="显示时长,单位：秒"  value="${item.showTime}">
                        </div>
                    </div>
                    <c:if test="${empty _model_ }">
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="saveBtn" back_url="/module/appimgversion/app_image_version_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-default popup-cancel-btn" type="button">
								<i class="icon-undo bigger-110"></i>取消
							</button>
						</div>
					</div>
					</c:if>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	<c:if test="${empty _model_ }">
		</div><!-- /.page-content -->
		</div>
	</c:if>
</div>

<script type="text/javascript">
var appId = '${item.appId}';
if(!appId){
	appId = '${appId}';
}
var imgUrl = '${imageUrl}';
var startTimeOld = $.trim($("#startTime").val());
var endTimeOld = $.trim($("#endTime").val());
require(['/resource/business/config/app_image_version_detail.js'],function(imageVersionDetail){
	imageVersionDetail.init();
});
</script>