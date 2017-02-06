<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻列表图模板管理|/module/infoeTmpleteImageConfig/info_templete_image_config_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <div class="col-md-12 col-lg-8">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">所属APP</label>
                        <div class="col-sm-9">
                       		<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板名称</label>
                        <div class="col-sm-9">
                       		<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板CODE</label>
                        <div class="col-sm-9">
                       		<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.templetCode}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">压缩类型</label>
                        <div class="col-sm-9">
                            <c:forEach items="${allLimitTypes}" var="type">
                            	<c:if test="${type.key eq item.limitType}">
	                        		<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${type.value}"/>
                            	</c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">宽</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.width}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">高</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.hight}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">列表图片类型</label>
                        <div class="col-sm-9">
                           <c:forEach items="${allListTypes}" var="type">
                              <c:if test="${type.key eq item.listType}">
                              	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${type.value}"/>
                              </c:if>
                           </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">备注说明</label>
                        <div class="col-sm-9">
                             <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.context}</textarea>
                        </div>
                    </div>
		            <div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
							<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/infoeTmpleteImageConfig/info_templete_image_config_list.html'">
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
