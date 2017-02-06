<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="栏目模板管理|/module/apptemplet/app_templet_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        <div class="col-md-12 col-lg-8">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <c:if test="${not empty appList}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端</label>
	                        <div class="col-sm-9">
	                            <select name="appId" id="appId" class="form-control">
									<c:forEach items="${appList}" var="app" varStatus="idx">
	                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="type">模板类型</label>
                        <div class="col-sm-9">
                            <select name="type" id="type" class="form-control">
								<c:forEach items="${allTypes}" var="obj" varStatus="idx">
                                  <option value="${obj.key}" <c:if test="${obj.key eq item.type or (empty item.type and idx.first)}">selected</c:if>>${obj.value}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="templetCode">模板CODE</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="templetCode" name="templetCode" maxlength="100" placeholder="请输入模板CODE"  value="${item.templetCode}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">模板名称</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入模板名称"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl">模板图片</label>
                            <div class="col-sm-9">
                            <input type="hidden" id="imageUrl" name="imageUrl" value="${item.imageUrl}">
	                        <div class="col-sm-9">
	                   			<div id="img_upload" title="点击上传">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imageUrl}">
		                                 <li>
		                                 	<a href="${item.imageUrl}" data-rel="colorbox">
										 		<img alt="150x150" style="width:150px;height:150px;" src="${item.imageUrl}">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
													</a>
												</div>
										 </li>
									 </c:if>
		                             </ul>
		                        </div>
	                        </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="templetDesc">模板描述</label>
                            <div class="col-sm-9">
                            <textarea class="form-control" rows="5" cols="" id="templetDesc" name="templetDesc" placeholder="请输入模板描述" maxlength="500">${item.templetDesc}</textarea>
                        </div>
                    </div>
                	<div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/apptemplet/app_templet_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/apptemplet/app_templet_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
var imageUrl = '${imageUrl}';
require(['/resource/business/config/app_templet_detail.js'],function(app_templet_detail){
	app_templet_detail.init();
});
</script>