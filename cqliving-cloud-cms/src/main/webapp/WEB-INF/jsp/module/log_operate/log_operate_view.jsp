<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="操作日志管理|/module/log_operate/log_operate_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class="col-md-12 col-lg-8">
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">模块</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.module}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">模块名称</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.moduleName}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">ACTION</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.action}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">ACTION名称</label>
                        <div class="col-sm-9">
                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.actionName}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">执行时长</label>
                        <div class="col-sm-9">
                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.executeMilliseconds}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">操作时间</label>
                        <div class="col-sm-9">
                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">操作者</label>
                        <div class="col-sm-9">
                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.operateUser}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">操作返回编码</label>
                        <div class="col-sm-9">
                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.operateResult}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">来源地址</label>
                        <div class="col-sm-9">
	                        <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.refer}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">TAG</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.tag}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12" style="display: none;">
                        <label class="col-sm-3 control-label no-padding-right">会话Id</label>
                        <div class="col-sm-9">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sessionId}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">操作信息</label>
                        <div class="col-sm-9">
                        <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.operateMessage}</textarea>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">传参</label>
                        <div class="col-sm-9">
                        <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.requestParameters}</textarea>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">客户端信息</label>
                        <div class="col-sm-9">
                        <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.clientInformations}</textarea>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12">
                        <label class="col-sm-3 control-label no-padding-right">描述</label>
                        <div class="col-sm-9">
                        <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.des}</textarea>
                        </div>
                    </div>
		            <div class="form-group">
						<div class="col-sm-12">
							<div class="pull-right">
							<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/log_operate/log_operate_list.html'">
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
