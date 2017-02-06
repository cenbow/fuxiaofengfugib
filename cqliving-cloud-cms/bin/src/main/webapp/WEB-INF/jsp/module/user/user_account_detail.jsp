<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="APP用户管理|/module/userAccount/user_account_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
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
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userName">登录账号</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="userName" name="userName" maxlength="100" placeholder="请输入登录账号"  value="${item.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="telephone">手机号</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="telephone" name="telephone" maxlength="11" placeholder="请输入手机号"  value="${item.telephone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="email">邮箱</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="email" name="email" maxlength="100" placeholder="请输入邮箱"  value="${item.email}">
                        </div>
                    </div>
                    <c:if test="${empty item.id}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="password">密码</label>
	                            <div class="col-sm-9">
	                            <input type="password" class="form-control" id="password" name="password" maxlength="20" placeholder="请输入密码" >
	                        </div>
	                    </div>
            		</c:if>
                    <c:if test="${not empty appList}">
                       	<div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">来源客户端</label>
							<div class="col-sm-9">
								<select name="appId" id="appId" class="form-control">
									<c:forEach items="${appList}" var="app" varStatus="idx">
	                                  <option value="${app.id}" 
	                                  	<c:if test="${app.id eq item.appId}">selected</c:if>
	                                  	<c:if test="${empty item.appId and idx.first}">selected</c:if>
	                                  >${app.name}</option>
	                               </c:forEach>
								 </select>
                  			</div>
						</div>
					</c:if>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">用户姓名</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入用户姓名"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="imgUrl">头像</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="imgUrl" name="imgUrl" value="${item.imgUrl}">
	                        <div class="col-sm-9">
	                   			<div id="img_upload" title="点击上传">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView">
		                           <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imgUrl}">
		                                 <li>
									 		<img alt="150x150" style="width:150px;height:150px;" src="${item.imgUrl}">
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
                        <label class="col-sm-3 control-label no-padding-right" for="speciality">个性签名</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="speciality" name="speciality" maxlength="200" placeholder="请输入个性签名"  value="${item.speciality}">
                        </div>
                    </div>
                	<div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/userAccount/user_account_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/userAccount/user_account_list.html'">
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
var imgHead = '${imageUrl}';
require(['/resource/business/user/user_account_detail.js'],function(account){
	account.init();
});
</script>