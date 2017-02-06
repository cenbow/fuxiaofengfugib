<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="App版本管理|/module/version/app_version_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <div class="col-md-12 col-lg-8">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">APP</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">版本名称</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">客户端类型</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allTypes[item.type]}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">客户端URL</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.url}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">显示版本号</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.viewVersion}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">版本号</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.vesionNo}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">最低支持版本号</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.minVersion}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">发布时间</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm" />"/>
                             <p class="form-control-static"></p>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">版本升级说明</label>
                        <div class="col-sm-9">
                             <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.updateContext}</textarea>
                        </div>
                    </div>
                    
		            <div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/version/app_version_list.html'">
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
