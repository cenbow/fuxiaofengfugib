<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="角色管理|/module/security/sys_role_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="查看" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">角色名称</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.roleName}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所属APP</label>
                        <div class="col-sm-8">
                            <input type="text" value="${app.name}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">角色类型</label>
                        <div class="col-sm-8">
                             <input type="text" value="${allTypes[item.type]}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">描述信息</label>
                        <div class="col-sm-8">
                            <input type="text" value="${item.descn}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    
		            <div class="form-group">
						<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/security/sys_role_list.html'">
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
