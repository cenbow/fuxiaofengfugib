<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />

<#assign isContainsDateSearch=0 />
<div class="main-content">
	<jsp:include page="${r"${"}pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${table.comment}列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="${configuration.pagePrefix}list.html" id="${configuration.pagePrefix}FormId">
	                    <#assign counter=0 />
	                    <div class="special-list">
                    <#list table.columnMetadatas as entity>
                        <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                        	<#--日期-->
			                            <#if entity.dataType == 2>
			                            	<#assign isContainsDateSearch=1 />
			                            <div class="input-group">
				                            <input name="search_GTE_${entity.propertyName}" type="hidden" value="<fmt:formatDate value="${r"${"}search_GTE_${entity.propertyName}}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_${entity.propertyName}" type="hidden" value="<fmt:formatDate value="${r"${"}search_LT_${entity.propertyName}}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="${entity.propertyName}" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${r"${"}search_GTE_${entity.propertyName}}" pattern="yyyy-MM-dd" /><c:if test="${r"${"}not empty search_GTE_${entity.propertyName}}"> 至 </c:if><fmt:formatDate value="${r"${"}search_LT_${entity.propertyName}}" pattern="yyyy-MM-dd" />" placeholder="${entity.common}"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
			                            <#--数字-->
			                            <#elseif entity.dataType == 1 || entity.dataType == 4 || entity.dataType == 5>
			                                <#if entity.options??>
		                            	<select name="search_EQ_${entity.propertyName}" id="search_EQ_${entity.propertyName}" class="form-control">
			                                <option value="">所有</option>
			                                <#list entity.options?keys as key>
			                                <option value="${key}"<c:if test="${r"${"}param.search_EQ_${entity.propertyName} eq '${key}'}"> selected="selected"</c:if>>${entity.options[key]}</option>
			                                </#list>
			                            </select>
			                                <#else>
			                            <input type="text" id="search_LIKE_${entity.propertyName}" name="search_LIKE_${entity.propertyName}" value="${r"${"}param.search_LIKE_${entity.propertyName} }" placeholder="${entity.common}" class="col-xs-12 form-control" />
			                                </#if>
			                            <#--字符-->
			                            <#else>
			                            <input type="text" id="search_LIKE_${entity.propertyName}" name="search_LIKE_${entity.propertyName}" value="${r"${"}param.search_LIKE_${entity.propertyName} }" placeholder="${entity.common}" class="col-xs-12 form-control" />
			                            </#if>
	                        		</div>
                				</div>
							</div>
                        </#if>
                    </#list>
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
                    		<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group">
									<div class="col-sm-12">
										<button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="${configuration.pageMapping}/${configuration.pagePrefix}add.html">
							<button class="btn btn-sm btn-success" type="button" url="${configuration.pageMapping}/${configuration.pagePrefix}add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
							<%-- 跳转页面的方式打开 <button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='${configuration.pageMapping}/${configuration.pagePrefix}add.html'"><i class="icon-plus"></i>新增</button> --%>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="${configuration.pageMapping}/${configuration.pagePrefix}delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="${configuration.pageMapping}/${configuration.pagePrefix}delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
						<%-- <button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button>
						<button class="btn btn-sm btn-warning" type="button"><i class="icon-download-alt"></i>导出</button>
						<button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
						<button class="btn btn-sm btn-danger" type="button"><i class="icon-reply"></i>撤稿</button> --%>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="${configuration.pagePrefix}list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
<#if isContainsDateSearch=0 >
require(['cloud.table.curd'], function(tableCurd){
	tableCurd.initTableCrud();
});
</#if>
<#if isContainsDateSearch=1 >
require(['cloud.table.curd','cloud.time.input'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
});
</#if>
</script>