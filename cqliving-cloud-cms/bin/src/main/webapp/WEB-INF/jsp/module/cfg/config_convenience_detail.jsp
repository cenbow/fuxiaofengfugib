<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<%-- 详细 --%>
        <form class="form-horizontal form" method="post" id="form1" action="/module/cfg/config_convenience_${empty item.id ? 'add' : 'update'}.html">
        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	            <c:if test="${not empty item}">
	            	<input type="hidden" id="updateId" name="id" value="${item.id}" />
	            </c:if>
                   <div class="form-group <c:if test="${fn:length(appList) eq 1 }">hide</c:if>">
                       <label class="col-sm-3 control-label no-padding-right" for="appId">客户端_ID<i class="text-danger">*</i></label>
                       <div class="col-sm-9">
                           <select name="appId" id="appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
							<option value="">请选择所属APP</option>
                              <c:forEach items="${appList}" var="it">
                                 <option value="${it.id }" <c:if test="${item.appId eq it.id || fn:length(appList) eq 1 }">selected</c:if>>${it.name}</option>
                              </c:forEach>
                        </select>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="convenienceTypeId">便民类型</label>
                       <div class="col-sm-9">
                           <select name="convenienceTypeId" id="convenienceTypeId" class="form-control" data-placeholder="请选择类型">
								<option value="">无</option>
								<c:forEach items="${typeList}" var="it">
                                 <option value="${it.id }" <c:if test="${item.convenienceTypeId eq it.id}">selected</c:if>>${it.name}</option>
                              </c:forEach>
	                        </select>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="name">便民名称<i class="text-danger">*</i></label>
                           <div class="col-sm-9">
                           <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入便民名称"  value="${item.name}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="linkUrl">便民链接<i class="text-danger">*</i></label>
                           <div class="col-sm-9">
                           <input type="text" class="form-control" id="linkUrl" name="linkUrl" maxlength="255" placeholder="请输入便民链接"  value="${item.linkUrl}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
                       <div class="col-sm-9">
                           <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="iconMinUrl">便民图标48*48px图标<i class="text-danger">*</i></label>
						<div class="col-sm-9">
							<div id="iconMinUrlDiv" class="col-sm-4">
								<i class="icon-cloud-upload"></i>
								添加图片
							</div>
							<div class="col-sm-8" id="iconMinUrlList">
							<c:if test="${not empty item.iconMinUrl }">
								<ul class="ace-thumbnails">
									<li id="WU_FILE_0" class="upload-imgs" style="width:80px;height:80px;">
										<a href="${item.iconMinUrl }" data-rel="colorbox" id="COLORBOX_WU_FILE_0" class="cboxElement">
											<img alt="80pxx80px" src="${item.iconMinUrl }" style="width:80px;height:80px;">
										</a>
										<div class="tools tools-top">
											<a href="javascript:;"><i class="icon-remove red"></i></a>
										</div>
										<input type="hidden" name="iconMinUrl" id="iconMinUrl" value="${item.iconMinUrl }">
									</li>
								</ul>
							</c:if>
							</div>
						</div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="iconMaxUrl">便民图标72*72px图标<i class="text-danger">*</i></label>
                           <div class="col-sm-9">
							<div id="iconMaxUrlDiv" class="col-sm-4">
								<i class="icon-cloud-upload"></i>
								添加图片
							</div>
							<div class="col-sm-8" id="iconMaxUrlList">
							<c:if test="${not empty item.iconMaxUrl }">
								<ul class="ace-thumbnails">
									<li id="WU_FILE_1" class="upload-imgs" style="width:80px;height:80px;">
										<a href="${item.iconMaxUrl }" data-rel="colorbox" id="COLORBOX_WU_FILE_1" class="cboxElement">
											<img alt="80pxx80px" src="${item.iconMaxUrl }" style="width:80px;height:80px;">
										</a>
										<div class="tools tools-top">
											<a href="javascript:;"><i class="icon-remove red"></i></a>
										</div>
										<input type="hidden" name="iconMaxUrl" id="iconMaxUrl" value="${item.iconMaxUrl }">
									</li>
								</ul>
							</c:if>
							</div>
						</div>
                   </div>
			</div>
        </form>
	</div>
</div>

<script type="text/javascript">
var imageUrl = '${imageUrl}';
require(['/resource/business/config/config_convenience_detail.js'], function(obj){
	obj.init();
});
</script>