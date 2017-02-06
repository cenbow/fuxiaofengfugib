<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${configuration.pagePrefix}list.html">${table.comment}列表</a></li>
        <li><a href="${configuration.pagePrefix}add.html"><i class="fa fa-plus"></i> 增加${table.comment}</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <#assign counter=0 />
                    <#list table.columnMetadatas as entity>
                        <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
                            <#assign counter=counter+1 />
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">${entity.common}</label>

                        <div class="col-sm-8">
                            <#if entity.dataType == 2>
                            <#--日期-->
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#${entity.propertyName}");
                                        $(elm).daterangepicker({
                                            startDate: $(elm).prev().prev().val(),
                                            endDate: $(elm).prev().val(),
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true
                                        }, function(start, end){
                                            $(elm).find(".date-title").html(start.toDateStr() + " 至 " + end.toDateStr());
                                            $(elm).prev().prev().val(start.toDateStr());
                                            $(elm).prev().val(end.toDateStr());
                                        });
                                    });
                                });
                            </script>
                            <input name="begin${entity.propertyName?cap_first}" type="hidden" value="<fmt:formatDate value="${r"${"}begin${entity.propertyName?cap_first}}" pattern="yyyy-MM-dd" />">
                            <input name="end${entity.propertyName?cap_first}" type="hidden" value="<fmt:formatDate value="${r"${"}end${entity.propertyName?cap_first}}" pattern="yyyy-MM-dd" />">
                            <button class="btn btn-default daterange" id="${entity.propertyName}" type="button" data-original-title="" title=""><span class="date-title"><fmt:formatDate value="${r"${"}begin${entity.propertyName?cap_first}}" pattern="yyyy-MM-dd" /> 至 <fmt:formatDate value="${r"${"}end${entity.propertyName?cap_first}}" pattern="yyyy-MM-dd" /></span> <i class="fa fa-calendar"></i></button>
                            <#elseif entity.dataType == 1 || entity.dataType == 4>
                            <#--数字-->
                                <#if entity.options??>
                            <select name="${entity.propertyName}" id="${entity.propertyName}" class="form-control">
                                <option value="">所有</option>
                                <#list entity.options?keys as key>
                                <option value="${key}"<c:if test="${r"${"}param.${entity.propertyName} eq '${key}'}"> selected="selected"</c:if>>${entity.options[key]}</option>
                                </#list>
                            </select>
                                <#else>
                            <input class="form-control" name="${entity.propertyName}" id="${entity.propertyName}" type="text" value="${r"${"}param.${entity.propertyName}}">
                                </#if>
                            <#else>
                            <#--字符-->
                            <input class="form-control" name="${entity.propertyName}" id="${entity.propertyName}" type="text" value="${r"${"}param.${entity.propertyName}}">
                            </#if>
                        </div>
                        <#if entity_has_next==false ||  configuration.searchColumnsList?size==counter >
                        <div class="col-xs-12 col-sm-2 col-lg-1">
                            <button class="btn btn-default"><i class="fa fa-search"></i> 搜索</button>
                        </div>
                        </#if>
                    </div>
                        </#if>
                    </#list>
                </form>
            </div>
        </div>
    </div>

    <%--列表--%>
    <div class="panel panel-default">
        <div class="table-responsive panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <#list table.columnMetadatas as entity>
                            <#if configuration.listColumnsList?size==0 || configuration.listColumnsList?seq_contains(entity.name?lower_case)>
                        <th>${entity.common}</th>
                            </#if>
                        </#list>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${r"${"}pageInfo.pageResults}" var="item">
                    <tr>
                    <#list table.columnMetadatas as entity>
                        <#if configuration.listColumnsList?size==0 || configuration.listColumnsList?seq_contains(entity.name?lower_case)>
                        <#if entity.dataType == 2>
                        <#--日期-->
                        <td><fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" /></td>
                        <#elseif entity.dataType == 1 || entity.dataType == 4>
                        <#--数字-->
                            <#if entity.options??>
                        <td>
                            <c:choose>
                            <#list entity.options?keys as key>
                                <c:when test="${r"${"}item.${entity.propertyName} == ${key}}">
                                    <span class="label label-info">${entity.options[key]}</span>
                                </c:when>
                            </#list>
                            </c:choose>
                        </td>
                            <#else>
                        <td>${r"${"}item.${entity.propertyName}}</td>
                            </#if>
                        <#else>
                        <td>${r"${"}item.${entity.propertyName}}</td>
                        </#if>
                        </#if>
                    </#list>
                        <td>
                            <a href="${configuration.pagePrefix}view.html?id=${r"${"}item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="${configuration.pagePrefix}update.html?id=${r"${"}item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            <#--不需要删除方法<a href="del.html?id=${r"${"}item.id}" class="btn btn-default btn-sm"  onclick="return confirm('确认删除吗?\n 删除后的数据将不可恢复！');return false;" title="删除"><i class="fa fa-trash"></i> 删除</a>-->
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--分页--%>
            <cqliving-frame:pagination/>
        </div>
    </div>
</div>