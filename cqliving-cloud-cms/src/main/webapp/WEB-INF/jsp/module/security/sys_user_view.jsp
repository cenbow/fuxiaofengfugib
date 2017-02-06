<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="系统用户管理|/module/security/sys_user_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="查看" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">登录用户名</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.username}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">拥有的角色</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                               <c:forEach items="${item.role}" var="sr" varStatus="vs">
                                 ${sr.roleName }
                                 <c:if test="${!vs.last }">,</c:if>
                               </c:forEach>
                            </p>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">用户类型</label>
                        <div class="col-sm-8">
                            <input type="text" value="${allUsertypes[item.usertype]}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所属APP</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <c:forEach items="${allApps }" var="app">
                                   ${item.appId eq app.id ? app.name : '' }
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">状态</label>
                        <div class="col-sm-8">
                            <input type="text" value="${allStatuss[item.status]}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-8">
                            <input type="text" value="<fmt:formatDate value='${item.createDate}' pattern='yyyy-MM-dd HH:mm:ss' />" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <%-- <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">过期时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.expiredDate}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">解锁时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.unlockDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </p>
                        </div>
                    </div> --%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">头像</label>
                        <div class="col-sm-8">
                            <c:if test="${not empty  item.imgUrl}">
                            	<div class="ace-thumbnails">
		                           	<a href="${item.imgUrl }" data-rel="colorbox">
		                             	<img class="form-control-static" src="${item.imgUrl }" style="max-width:150px;max-height: 150px;">
		                             </a>
	                             </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">手机</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.mobile}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">QQ</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.qqCode}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">职位</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.position}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">描述</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.descn}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
		            <div class="form-group">
		            	<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/security/sys_user_list.html'">
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
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>