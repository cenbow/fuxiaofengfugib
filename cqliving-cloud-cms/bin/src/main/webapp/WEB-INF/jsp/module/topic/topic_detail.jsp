<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
        <form class="form-horizontal form" method="post" id="form1" action="/module/topic/topic_${empty item.id ? 'add' : 'update'}.html">
        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	            <c:if test="${not empty item}">
	            	<input type="hidden" name="id" value="${item.id}" />
	            </c:if>
	            <c:if test="${fn:length(appList) eq 1 }">
	            	<input type="hidden" value="${appId }" name="appId">
	            </c:if>
                   <c:if test="${fn:length(appList) ne 1 }">
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="appId">客户端<i class="text-danger">*</i></label>
                       <div class="col-sm-9">
						<select name="appId" id="appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
							<option value="">请选择所属APP</option>
							<c:forEach items="${appList}" var="it">
								<option value="${it.id }" <c:if test="${item.appId eq it.id || fn:length(appList) eq 1 }">selected</c:if>>${it.name}</option>
							</c:forEach>
						</select>
                       </div>
                   </div>
                   </c:if>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="name">话题名称<i class="text-danger">*</i></label>
                          <div class="col-sm-9">
                           <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入话题名称"  value="${item.name}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="appId">话题类型<i class="text-danger">*</i></label>
                       <div class="col-sm-9">
						<select name="types" id="topicTypes" class="form-control chosen-select" data-placeholder="请选择话题类型" multiple="multiple">
							<c:set var="tmpTypes" value="${fn:split(item.types, ',') }"/>
							<c:forEach var="it" items="${typeList }">
								<option value="${it.id }" 
								<c:forEach var="tmp" items="${tmpTypes }">
									<c:if test="${tmp eq it.id }">selected="selected"</c:if>									
								</c:forEach>
								>${it.name }</option>
							</c:forEach>
						</select>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="content">话题内容<i class="text-danger">*</i></label>
                          <div class="col-sm-9">
                          	<textarea rows="6" class="form-control" name="content" placeholder="请输入话题内容">${item.content}</textarea>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="listImageUrl">话题图片</label>
                       <div class="col-sm-9" id="imageUrlDiv">
							<i class="icon-cloud-upload"></i>
							添加图片
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="listImageUrl"></label>
                          <div class="col-sm-9" id="imageUrlList">
								<ul class="ace-thumbnails">
									<c:forEach var="it" items="${imageList }">
										<li class="upload-imgs" style="width:80px;height:80px;">
											<a href="${it.url }" data-rel="colorbox" class="cboxElement">
												<img alt="80pxx80px" src="${it.url }" style="width:80px;height:80px;">
											</a>
											<div class="tools tools-top">
												<a href="javascript:;"><i class="icon-remove red"></i></a>
											</div>
											<input type="hidden" name="imageUrl" value="${it.url }">
										</li>
									</c:forEach>
								</ul>
                       </div>
                   </div>
			</div>
        </form>
	</div>
</div>
<script type="text/javascript">
var imageUrl = '${imageUrl }';
require(['/resource/business/topic/topic_detail.js'], function(obj){
	obj.init();
});
</script>