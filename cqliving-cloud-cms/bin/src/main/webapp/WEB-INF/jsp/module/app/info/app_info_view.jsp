<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="客户端管理|/module/app/app_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view fieldset-form">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <fieldset>
			        <legend>客户端信息</legend>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">客户端名称</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">客户端后台名称</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.cmsName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">客户端LOGO</label>
                        <div class="col-sm-8">
                        	<c:if test="${not empty item.logo}">
                        		<span class="ace-thumbnails">
									<a href="${item.logo}" data-rel="colorbox">
								 		<img alt="客户端LOGO" style="width:150px;height:150px;" src="${item.logo}">
								 	</a>
								 </span>
							</c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">游客登录名</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.namePrefix}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所在区域</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.location}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">自定义域名</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appDomain}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">后台域名</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.cmsDomain}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">联系电话</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.contactPhone}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">传真</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.fax}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">地址</label>
                        <div class="col-sm-8">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.address}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">说明</label>
                        <div class="col-sm-8">
                        	<textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.description}</textarea>
                        </div>
                    </div>
                    </fieldset>
                    <fieldset>
		              	 <legend>七牛云信息</legend>
			             <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">资源名称</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${qiniu.bucketName}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">测试域名</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${qiniu.domainTest}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">自定义域名</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${qiniu.domainCustom}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-2 control-label no-padding-right">是否默认</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allIsDefault[qiniu.isDefault]}"/>
	                        </div>
	                    </div>
                    
                    </fieldset>
                    <fieldset>
			            <legend>获取天气信息</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">区域名称</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${weather.cityName}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">区域代码</label>
	                        <div class="col-sm-8">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${weather.cityPhoneticize}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                    	 <label class="col-sm-2 control-label no-padding-right">是否默认</label>
	                        <div class="col-sm-8 radio">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allIsDefaults[weather.isDefault]}"/>
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>短信下发配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">短信扩展码号</label>
	                        <div class="col-sm-8">
	                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.smsCode}"/>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="smsSignature">短信签名</label>
	                        <div class="col-sm-8">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.smsSignature}"/>
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>百度地图配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">百度地图Key</label>
	                        <div class="col-sm-8">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.baiduLbsKey}"/>
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>APP下载配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">下载市场地址</label>
	                        <div class="col-sm-8">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${appConfig.downLoadUrl}"/>
	                        </div>
	                    </div>
                    </fieldset>
		            <c:if test="${not empty smsTempList}">
                    <fieldset>
			            <legend>短信模板信息</legend>
				            <c:forEach items="${smsTempList}" var="obj" varStatus="idx">
			                    <div class="form-group">
			                        <label class="col-sm-2 control-label no-padding-right" for="cityName">
			                        ${smsTempTypes[obj.type]}
			                        </label>
			                        <div class="col-sm-8">
		                            	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.content}"/>
			                        </div>
			                    </div>
	                        </c:forEach>
                    </fieldset>
		            </c:if>
		            <div class="form-group col-xs-12">
						<div class="col-sm-12">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/app/app_info_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['common_colorbox'], function(colorbox) {
	//1、图片查看
	colorbox.init();
});
</script>