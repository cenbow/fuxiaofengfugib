<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="${configuration.pagePrefix}list.html">${table.comment}列表</a></li>
        <li><a href="${configuration.pagePrefix}add.html"><i class="fa fa-plus"></i> 增加${table.comment}</a></li>
        <li class="active"><a href="javascript:"> 查看${table.comment}</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    ${table.comment}
                </div>
                <div class="panel-body">
                <#list table.columnMetadatas as entity>
                    <#if entity.name?lower_case != 'id' && (configuration.viewColumnsList?size==0 || configuration.viewColumnsList?seq_contains(entity.name?lower_case))>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">${entity.common}</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                        <#if entity.dataType == 2>
                        <#--日期-->
                                <fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" />
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
                                ${r"${"}item.${entity.propertyName}}
                            </#if>
                        <#else>
                                ${r"${"}item.${entity.propertyName}}
                        </#if>
                            </p>
                        </div>
                    </div>
                    </#if>
                </#list>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                <div class="col-sm-10">
                    <p class="form-control-static"><a href="javascript:history.go(-1);" class="btn btn-primary">返回</a></p>
                </div>
            </div>
        </form>
    </div>
</div>