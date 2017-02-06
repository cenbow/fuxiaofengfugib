<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="App版本管理|/module/version/app_version_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        <div class="col-md-12 col-lg-8">
		            <c:if test="${not empty item.id}">
		            <input type="hidden" name="id" value="${item.id}" />
		            </c:if>
		            <c:if test="${not empty appList}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">APP</label>
	                        <div class="col-sm-9">
	                            <select name="appId" id="appId" class="form-control">
								   <c:forEach items="${appList}" var="app">
	                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
	                               </c:forEach>
								</select>
	                        </div>
	                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">版本名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入版本名称"  value="${item.name}" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="updateContext">版本升级说明</label>
                        <div class="col-sm-9">
							<textarea class="form-control" rows="5" cols="" id="updateContext" name="updateContext" placeholder="请输入版本升级说明" maxlength="500">${item.updateContext}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="vesionNo">版本号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="vesionNo" id="vesionNo" placeholder="请输入版本号" value="${item.vesionNo}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="viewVersion">显示版本号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="viewVersion" name="viewVersion" placeholder="请输入显示版本号"  value="${item.viewVersion}" maxlength="50"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="minVersion">最低支持版本号</label>
                        <div class="col-sm-9">
                            <input class="form-control" name="minVersion" id="minVersion" type="text" placeholder="请输入最低支持版本号" value="${item.minVersion}" maxlength="11"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="publishTime">发布时间</label>
                            <div class="col-sm-9">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm","timePickerIncrement": 1,"timePicker": true,"timePicker12Hour": false}' name="publishTime" id="publishTime" readonly="readonly" value="<fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="type">客户端类型</label>
                        <div class="col-sm-9 radio">
                            <select name="type" id="type" class="form-control">
								<c:forEach items="${allTypes}" var="obj" varStatus="">
									<c:if test="${TYPE0 ne obj.key}">
                                		<option value="${obj.key}" <c:if test="${obj.key eq item.type or (empty item.type and idx.first)}">selected</c:if>>${obj.value}</option>
                                	</c:if>
                                </c:forEach>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="url">客户端URL</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="url" name="url" placeholder="请输入客户端URL"  value="${item.url}" maxlength="127"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="url">上传客户端</label>
                        <div class="col-sm-9" style="position: relative;">
                        	<a href="javascript:;" id="video_pickfiles" class="btn btn-primary">
                                <i class="glyphicon glyphicon-plus"></i>
                                <span>选择文件</span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-12" id="success" style="display:none">
                        <div class="alert-success">
                                                                队列全部文件处理完毕
                        </div>
                    </div>
                    <div class="col-md-12 ">
                        <table style="margin-top:40px;display:none" class="table table-striped table-hover text-left">
                            <thead>
                              <tr>
                                <th class="col-md-3">文件名称</th>
                                <th class="col-md-2">文件大小</th>
                                <th class="col-md-7">详情</th>
                              </tr>
                            </thead>
                            <tbody id="fsUploadProgress">
                            </tbody>
                        </table>
                    </div>
                    
		            <div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/version/app_version_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/version/app_version_list.html'">
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

<script>
require(['/resource/business/config/app_version_detail.js'],function(app_version_detail){
	app_version_detail.init();
});
</script>