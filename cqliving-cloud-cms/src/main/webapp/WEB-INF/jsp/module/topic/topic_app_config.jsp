<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="话题配置" name="_breadcrumbs_1"/>
	</jsp:include>
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" role="form" action="/module/topic/topic_app_config_save.html" id="myForm">
					<div class="col-md-12 col-lg-8">
						<c:if test="${not empty appInfos}">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">所属APP</label>
								<div class="col-sm-9">
									<select class="chosen-select" data-placeholder="区县" name="appId" id="appId">
		                                <c:forEach items="${appInfos}" var="obj">
		                                	<option value="${obj.id}" <c:if test="${appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
		                                </c:forEach>
		                            </select>
								</div>
							</div>
						</c:if>
						<c:forEach items="${configMap}" var="obj">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">${obj.value.name}</label>
								<div class="col-sm-9 radio">
									<label class="radio-2">
		                                <input type="radio" class="ace" name="topicConfig_${obj.key}" value="0" <c:if test="${obj.value.value eq 0}">checked="checked"</c:if>><span class="lbl"> 否</span>
		                            </label>
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="topicConfig_${obj.key}" value="1" <c:if test="${obj.value.value eq 1}">checked="checked"</c:if>><span class="lbl"> 是</span>
		                            </label>
								</div>
							</div>
						</c:forEach>
						<div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">话题默认图片</label>
	                        <div class="col-sm-9" id="img_upload">
		                        <input type="hidden" name="defaultImage" value="" />
	                            <i class="icon-cloud-upload"></i>上传默认图片
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="img_thum">
	                           	<ul class="ace-thumbnails">
                               		<c:if test="${not empty defaultImage}">
                               			<li class="upload-imgs">
	                               			<a href="${defaultImage}" data-rel="colorbox">
	                                 			<img alt="150x150" src="${defaultImage}" style="width:150px;height:150px;" />
	                               			</a>
	                                 		<div class="tools tools-top"> 
							          			<a href="javascript:void(0);"><i class="icon-remove red"></i></a> 
							 		 		</div>
                              			</li>
                              		</c:if>
                           		</ul>
	                        </div>
	                    </div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<cqliving-security2:hasPermission name="/module/topic/topic_app_config_save.html">
										<button class="btn btn-success btn-sm" type="button" id="submitBtn">
											<i class="icon-save bigger-110"></i>保存设置
										</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var imgUrl = "${imageUrl}";
	
	require(['/resource/business/topic/topicAppConfig.js'], function(obj) {
		obj.init();
	});
</script>