<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />

<c:if test="${r"${"}empty _model_ }">
<div class="main-content">
	<jsp:include page="${r"${"}pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${table.comment}列表|${configuration.pagePrefix}list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class='col-md-12 <c:if test="${r"${"}empty _model_ }">col-lg-8</c:if>'>
			        <#list table.columnMetadatas as entity>
	                    <#if entity.name?lower_case != 'id' && (configuration.viewColumnsList?size==0 || configuration.viewColumnsList?seq_contains(entity.name?lower_case))>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">${entity.common}</label>
	                        <div class="col-sm-9">
	                        <#if entity.dataType == 2>
	                        <#--日期-->
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" />">
	                        <#elseif entity.dataType == 1 || entity.dataType == 4>
	                        <#--数字-->
	                            <#if entity.options??>
	                                <c:choose>
	                                    <#list entity.options?keys as key>
	                                        <c:when test="${r"${"}item.${entity.propertyName} == ${key}}">
	                                            <span class="label label-info">${entity.options[key]}</span>
	                                        </c:when>
	                                    </#list>
	                                </c:choose>
	                            <#else>
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${r"${"}item.${entity.propertyName}}">
	                            </#if>
	                        <#else>
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${r"${"}item.${entity.propertyName}}">
	                        </#if>
	                        </div>
	                    </div>
	                    </#if>
	                </#list>
	                	<%-- <textarea readonly="" rows="10" class="form-control textarea limited" id="synopsis" name="synopsis" maxlength="600">textarea示例</textarea> --%>
			            <c:if test="${r"${"}empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='${configuration.pageMapping}/${configuration.pagePrefix}list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${r"${"}empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>