<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />

<#assign isContainsDateSearch=0 />
<c:if test="${r"${"}empty _model_ }">
<div class="main-content">
	<jsp:include page="${r"${"}pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="${table.comment}列表|${configuration.pagePrefix}list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${r"${"}empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="${configuration.pageMapping}/${configuration.pagePrefix}${r"${"}empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${r"${"}empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${r"${"}not empty item}">
			            	<input type="hidden" name="id" value="${r"${"}item.id}" />
			            </c:if>
	                    
	                <#list table.columnMetadatas as entity>
	                    <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="${entity.propertyName}">${entity.common}<i class="text-danger">*</i></label>
	                        <#if entity.dataType == 2>
	                        <#--日期-->
	                            <#assign isContainsDateSearch=1 />
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="${entity.propertyName}" id="${entity.propertyName}" readonly="readonly" value="<fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        <#elseif entity.dataType == 1 || entity.dataType == 4 || entity.dataType == 5>
	                        <#--数字-->
	                            <#if entity.options??>
	                        <div class="col-sm-9 radio">
	                            <#--下拉框 -->
	                            <#--<select name="${entity.propertyName}" id="${entity.propertyName}" class="form-control">
	                                <#list entity.options?keys as key>
	                                    <option value="${key}"<c:if test="${r"${"}item.${entity.propertyName} eq ${key}}"> selected="selected"</c:if>>${entity.options[key]}</option>
	                                </#list>
	                            </select>-->
	                            <#--单选框 -->
	                            <#list entity.options?keys as key>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="${entity.propertyName}" value="${key}" id="${entity.propertyName}${key}"><span class="lbl"> ${entity.options[key]}</span>
	                            </label>
	                            </#list>
	                            <script type="text/javascript">
	                                document.getElementById("${entity.propertyName}${r"${"}empty item ? ${entity.options?keys[0]} : item.${entity.propertyName}}").checked=true;
	                            </script>
	                            <#else>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="${entity.propertyName}" id="${entity.propertyName}" type="text" value="${r"${"}item.${entity.propertyName}}" maxlength="${entity.length}" placeholder="请输入${entity.common}">
	                            </#if>
	                        <#else>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="${entity.propertyName}" name="${entity.propertyName}" maxlength="${entity.length}" placeholder="请输入${entity.common}"  value="${r"${"}item.${entity.propertyName}}">
	                        </#if>
	                        </div>
	                    </div>
	                    </#if>
	                </#list>
	                	<c:if test="${r"${"}empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-info btn-sm draft_save view_button" type="button" data-target="#info_view_modal" role="button" data-toggle="modal">
										<i class="icon-eye-open bigger-110"></i>预览
									</button>
									&nbsp;
									<button class="btn btn-primary btn-sm push_save" type="button">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
									&nbsp;
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="${configuration.pageMapping}/${configuration.pagePrefix}list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
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

<script type="text/javascript">
<#if isContainsDateSearch=1 >
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
</#if>
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                <#assign counter=0 />
                <#list table.columnMetadatas as entity>
                    <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
                        <#assign counter=counter+1 />
                    ${entity.propertyName} : {
                    required: true<#if entity.dataType == 2>,
                    date:true<#elseif entity.dataType == 1 || entity.dataType == 4>,
                    number:true</#if>
                }<#if entity_has_next && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?size>counter)>,</#if>
                    </#if>
                </#list>
            },
            messages: {
            <#assign counter=0 />
            <#list table.columnMetadatas as entity>
                <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
                    <#assign counter=counter+1 />
                ${entity.propertyName} : {
                    required: ' '<#if entity.dataType == 2>,
                    date:' '<#elseif entity.dataType == 1 || entity.dataType == 4>,
                    number:' '</#if>
                }<#if entity_has_next && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?size>counter)>,</#if>
                </#if>
            </#list>
            }
        });
    });
});
</script>