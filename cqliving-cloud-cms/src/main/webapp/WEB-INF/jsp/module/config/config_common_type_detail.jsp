<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${allSourceTypes[sourceType] }列表|/module/config/${sourceType }/config_common_type_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/config/${sourceType }/config_common_type_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		        		<input type="hidden" name="sourceType" value="${sourceType }">
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    <c:if test="${empty item }">
		                    <div class="form-group <c:if test="${fn:length(appList) < 2 }">hide</c:if>">
								<label class="col-sm-3 control-label no-padding-right" for="appId">客户端<i class="text-danger">*</i></label>
								<div class="col-sm-9">
									<select name="appId" id="appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
										<option>请选择所属APP</option>
				                        <c:forEach items="${appList}" var="it">
				                           <option value="${it.id }" <c:if test="${item.appId eq it.id || fn:length(appList) eq 1 }">selected</c:if>  >${it.name}</option>
				                        </c:forEach>
			                        </select>
								</div>
							</div>
						</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">分类名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入分类名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div>
	                   <div class="form-group <c:if test="${sourceType ne 8 }">hide</c:if>">
	                       <label class="col-sm-3 control-label no-padding-right" for="iconMinUrl">分类图标</label>
							<div class="col-sm-9">
								<div id="imageUrlDiv" class="col-sm-4">
									<i class="icon-cloud-upload"></i>
									添加图片
								</div>
								<div class="col-sm-8" id="imageUrlList">
								<c:if test="${not empty item.imageUrl }">
									<ul class="ace-thumbnails">
										<li id="WU_FILE_0" class="upload-imgs" style="width:80px;height:80px;">
											<a href="${item.imageUrl }" data-rel="colorbox" id="COLORBOX_WU_FILE_0" class="cboxElement">
												<img alt="80pxx80px" src="${item.imageUrl }" style="width:80px;height:80px;">
											</a>
											<div class="tools tools-top">
												<a href="javascript:;"><i class="icon-remove red"></i></a>
											</div>
											<input type="hidden" name="imageUrl" id="imageUrl" value="${item.imageUrl }">
										</li>
									</ul>
								</c:if>
								</div>
							</div>
	                   </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/config/${sourceType }/config_common_type_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/config/${sourceType }/config_common_type_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>

<script type="text/javascript">
var imageUrl = '${imageUrl}';
require(["/resource/business/config/config_common_type_detail.js"], function(obj) {
	obj.init();
});
</script>