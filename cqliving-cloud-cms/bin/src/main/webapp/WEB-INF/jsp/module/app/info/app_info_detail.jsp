<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
<%-- 	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include> --%>
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="客户端管理|/module/app/app_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail fieldset-form">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
		            <input type="hidden" id="imgHead" value="${imageUrl}" />
		            <c:if test="${empty item}">
			            <fieldset>
				            <legend>管理账户信息</legend>
				            <div class="form-group">
		                        <label class="col-sm-2 control-label no-padding-right">账号</label>
		                        <div class="col-sm-8">
		                            <input type="text" class="form-control" id="username" name="username" placeholder="请输入管理用户账号" maxlength="50">
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 control-label no-padding-right">密码</label>
		                        <div class="col-sm-8">
		                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入管理用户密码" maxlength="50">
		                        </div>
		                	</div>
				            <div class="form-group">
		                        <label class="col-sm-2 control-label no-padding-right">姓名</label>
		                        <div class="col-sm-8">
		                            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="请输姓名" maxlength="15">
		                        </div>
		                    </div>
	                    </fieldset>
		    		</c:if>
                    <fieldset>
			            <legend>客户端信息</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="name">客户端名称</label>
	                            <div class="col-sm-8">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入客户端名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="cmsName">客户端后台名称</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="cmsName" name="cmsName" placeholder="请输入客户端后台名称"  value="${item.cmsName}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="logo">客户端LOGO</label>
	                        <div class="col-sm-8">
	                   			<div id="img_upload" title="点击上传">
	                   				<i class="icon-cloud-upload"></i>
	                        	</div>
		                        <input type="hidden" id="imageUrl" name="logo" value="${item.logo}">
	                        	<div id="imgView">
	                             <ul class="ace-thumbnails">
	                             <c:if test="${not empty item.logo}">
	                                 <li>
	                                 	<a href="${item.logo}" data-rel="colorbox">
									 		<img alt="150x150" style="width:150px;height:150px;" src="${item.logo}">
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
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="namePrefix">游客登录名</label>
	                            <div class="col-sm-8">
	                            <input type="text" class="form-control" id="namePrefix" name="namePrefix" maxlength="50" placeholder="请输入游客登录名"  value="${item.namePrefix}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="location">所在区域</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="location" name="location" placeholder="请输入所在区域"  value="${item.location}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="appDomain">自定义域名</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="appDomain" name="appDomain" placeholder="请输入自定义域名"  value="${item.appDomain}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="cmsDomain">后台域名</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control col-sm-8" style="width:66% !important;" id="cmsDomain" name="cmsDomain" placeholder="请输入后台域名"  value="${empty item.cmsDomain ? null : fn:substring(item.cmsDomain, 0, fn:indexOf(item.cmsDomain, CMSEND))}" maxlength="50"><div class="col-sm-4">${CMSEND}</div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="contactPhone">联系电话</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="contactPhone" name="contactPhone" placeholder="请输入联系电话"  value="${item.contactPhone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="fax">传真</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="fax" name="fax" placeholder="请输入传真"  value="${item.fax}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="address">地址</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="address" name="address" placeholder="请输入地址"  value="${item.address}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="description">说明</label>
	                        <div class="col-sm-8">
	                            <textarea class="form-control" rows="5" cols="" id="description" name="description" placeholder="请输入说明" maxlength="500">${item.description}</textarea>
	                        </div>
	                    </div>
                    </fieldset>
                    <%-- <fieldset>
		              	 <legend>七牛云信息</legend>
		              	 <input type="hidden" name="qiniuId" value="${qiniu.id}" />
			             <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="bucketName">资源名称</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="bucketName" name="bucketName" placeholder="请输入资源名称"  value="${qiniu.bucketName}" maxlength="50" />
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="domainTest">测试域名</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="domainTest" name="domainTest" placeholder="请输入测试域名"  value="${qiniu.domainTest}" maxlength="255" />
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="domainCustom">自定义域名</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="domainCustom" name="domainCustom" placeholder="请输入自定义域名"  value="${qiniu.domainCustom}" maxlength="255">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-2 control-label no-padding-right" for="isDefault">是否默认</label>
	                        <div class="col-sm-8 radio">
	                        	<c:forEach items="${allIsDefault}" var="obj" varStatus="idx">
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="isDefault" value="${obj.key}" id="isDefault${obj.key}"><span class="lbl"> ${obj.value}</span>
		                            </label>
	                            </c:forEach>
	                            <script type="text/javascript">
	                                document.getElementById("isDefault${empty qiniu.isDefault ? 0 : qiniu.isDefault}").checked=true;
	                            </script>
	                        </div>
	                    </div>
                    
                    </fieldset> --%>
                    <fieldset>
			            <legend>获取天气信息</legend>
			            <input type="hidden" name="weatherId" value="${weather.id}" />
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="cityName">区域名称</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="cityName" name="cityName" placeholder="请输入区域名称"  value="${weather.cityName}" maxlength="12">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="cityPhoneticize">区域代码</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="cityPhoneticize" name="cityPhoneticize" placeholder="请输入区域代码"  value="${weather.cityPhoneticize}" maxlength="50">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-2 control-label no-padding-right" for="isDefaultWeather">是否默认</label>
	                        <div class="col-sm-8 radio">
	                        	<c:forEach items="${allIsDefaults}" var="obj" varStatus="idx">
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="isDefaultWeather" value="${obj.key}" id="isDefaultWeather${obj.key}"><span class="lbl"> ${obj.value}</span>
		                            </label>
	                            </c:forEach>
	                            <script type="text/javascript">
	                                document.getElementById("isDefaultWeather${empty weather.isDefault ? 0 : weather.isDefault}").checked=true;
	                            </script>
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>短信下发配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="smsCode">短信扩展码号</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="smsCode" name="smsCode" placeholder="请输入短信扩展码号"  value="${item.smsCode}" maxlength="4">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="smsSignature">短信签名</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="smsSignature" name="smsSignature" placeholder="请输入短信签名"  value="${item.smsSignature}" maxlength="10">
	                        </div>
	                    </div>
	                    
                    </fieldset>
                    <fieldset>
			            <legend>百度地图配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="baiduLbsKey">百度地图Key</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="baiduLbsKey" name="baiduLbsKey" placeholder="请输入短信扩展码号"  value="${item.baiduLbsKey}" maxlength="50">
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>APP下载配置</legend>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="baiduLbsKey">下载市场地址</label>
	                        <div class="col-sm-8">
	                            <input type="text" class="form-control" id="downLoadUrl" name="downLoadUrl" placeholder="请输入下载市场地址"  value="${appConfig.downLoadUrl}" maxlength="125">
	                        </div>
	                    </div>
                    </fieldset>
                    <fieldset>
			            <legend>短信模板信息</legend>
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="cityName">示例</label>
	                        <div class="col-sm-10 label-words">
	                        	欢迎注册，验证码是：#，30分钟内有效。
	                        </div>
	                    </div>
			            <c:if test="${empty smsTempList}">
				            <c:forEach items="${smsTempTypes}" var="obj" varStatus="idx">
			                    <div class="form-group">
			                        <label class="col-sm-2 control-label no-padding-right" >${obj.value}
			                        <input type="hidden" class="smsType" name="type" value="${obj.key}"/>
			                        </label>
			                        <div class="col-sm-8">
			                        	<textarea class="form-control smsContent" rows="3" cols="" name="content" placeholder="请输入50字以内的短信模板,验证码后面有且仅有一个#"></textarea>
			                        </div>
			                    </div>
	                        </c:forEach>
			            </c:if>
			            <c:if test="${not empty smsTempList}">
				            <c:forEach items="${smsTempList}" var="obj" varStatus="idx">
			                    <div class="form-group">
			                        <label class="col-sm-2 control-label no-padding-right" for="cityName">
			                        ${smsTempTypes[obj.type]}
			                        <input type="hidden" class="smsType" name="type" value="${obj.type}"/>
			                        </label>
			                        <div class="col-sm-8">
										<textarea class="form-control smsContent" rows="3" cols="" name="content" placeholder="请输入50字以内的短信模板,验证码后面有且仅有一个#">${obj.content}</textarea>
			                        </div>
			                    </div>
	                        </c:forEach>
			            </c:if>
			            <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">
	                        </label>
	                        <div class="col-sm-8">
	                        <span id="sms" style="color: #d16e6c;"></span>
	                        </div>
	                    </div>
                    </fieldset>
		            <div class="form-group col-xs-12">
						<div class="col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="saveBtn" back_url="/module/app/app_info_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:window.location.href='/module/app/app_info_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div>
	</div>
</div>

<script>
require(['/resource/business/app/app_detail.js'],function(app_detail){
	app_detail.init();
});
</script>
