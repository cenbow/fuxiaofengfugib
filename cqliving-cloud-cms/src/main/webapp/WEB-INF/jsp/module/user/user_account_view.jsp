<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="APP用户管理|/module/userAccount/user_account_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <div class="col-md-12 col-lg-8">
		        	<c:if test="${not empty item.userName}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">登录账号</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.userName}"/>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty item.telephone}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">手机号</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.telephone}"/>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty item.email}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">邮箱</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.email}"/>
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">来源客户端</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">来源</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.source}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">注册时间</label>
                        <div class="col-sm-9">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.registTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">用户姓名</label>
                        <div class="col-sm-9">
                           <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    <c:if test="${not empty item.imgUrl}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">头像</label>
                        <div class="col-sm-9">
                        	<span class="ace-thumbnails">
                        		<c:if test="${not empty item.imgUrl }">
                            		<img src="${item.imgUrl}" alt="用户头像" style="max-height: 150px;max-width: 150px;"/>
                            	</c:if>
                            </span>
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">个性签名</label>
                        <div class="col-sm-9">
                            <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.speciality}</textarea>
                        </div>
                    </div>
		            <div class="form-group">
						<div class="col-sm-12">
							<div class="pull-right">
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