<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />
<script>

    require(['validator.bootstrap'], function($){
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
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="${configuration.pagePrefix}list.html">${table.comment}列表</a></li>
        <li<c:if test="${r"${"}empty item}"> class="active"</c:if>><a href="${configuration.pagePrefix}add.html"><i class="fa fa-plus"></i> 增加${table.comment}</a></li>
        <c:if test="${r"${"}not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑${table.comment}</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${r"${"}not empty item}">
            <input type="hidden" name="id" value="${r"${"}item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    ${table.comment}
                </div>
                <div class="panel-body">
                <#list table.columnMetadatas as entity>
                    <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">${entity.common}</label>
                        <div class="col-sm-9">
                        <#if entity.dataType == 2>
                        <#--日期-->
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#${entity.propertyName}");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="input-prepend input-group">
                                <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                <input class="form-control" type="text" name="${entity.propertyName}" id="${entity.propertyName}" readonly="readonly" value="<fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" />">
                            </div>
                        <#elseif entity.dataType == 1 || entity.dataType == 4>
                        <#--数字-->
                            <#if entity.options??>
                            <#--下拉框 -->
                            <#--<select name="${entity.propertyName}" id="${entity.propertyName}" class="form-control">
                                <#list entity.options?keys as key>
                                    <option value="${key}"<c:if test="${r"${"}item.${entity.propertyName} eq ${key}}"> selected="selected"</c:if>>${entity.options[key]}</option>
                                </#list>
                            </select>-->
                            <#--单选框 -->
                            <#list entity.options?keys as key>
                            <label class="btn btn-default">
                                <input type="radio" name="${entity.propertyName}" value="${key}" id="${entity.propertyName}${key}"> ${entity.options[key]}
                            </label>
                            </#list>
                            <script type="text/javascript">
                                document.getElementById("${entity.propertyName}${r"${"}empty item ? ${entity.options?keys[0]} : item.${entity.propertyName}}").checked=true;
                            </script>
                            <#else>
                            <input class="form-control" name="${entity.propertyName}" id="${entity.propertyName}" type="text" value="${r"${"}item.${entity.propertyName}}">
                            </#if>
                        <#else>
                            <input type="text" class="form-control" id="${entity.propertyName}" name="${entity.propertyName}" placeholder="请输入${entity.common}"  value="${r"${"}item.${entity.propertyName}}">
                        </#if>

                        </div>
                    </div>
                    </#if>
                </#list>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary col-lg-1" name="submit" value="提交">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>