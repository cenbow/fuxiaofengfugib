<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pageMapping}/${names.domainClassName?uncap_first}" />

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th>
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
                    	<td class="center" id="${r"${"}item.id}">
							<label>
								<input type="checkbox" class="ace" id="${r"${"}item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
	                    <#list table.columnMetadatas as entity>
	                        <#if configuration.listColumnsList?size==0 || configuration.listColumnsList?seq_contains(entity.name?lower_case)>
		                        <#if entity.dataType == 2>
			                        <#--日期-->
                    	<td><fmt:formatDate value="${r"${"}item.${entity.propertyName}}" pattern="yyyy-MM-dd" /></td>
		                        <#elseif entity.dataType == 1 || entity.dataType == 4 || entity.dataType == 5>
		                        	<#--数字-->
		                            <#if entity.options??>
                        <td>
                        	<span class="label label-info">${r"${"}all${entity.propertyName?cap_first}s[item.${entity.propertyName}] }</span>
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
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="${configuration.pageMapping}/${configuration.pagePrefix}view.html">
								<a class="blue" href="javascript:void(0);" url="${configuration.pagePrefix}view.html?id=${r"${"}item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
								<%-- 跳转页面的方式打开 <a class="blue" href="${configuration.pagePrefix}view.html?id=${r"${"}item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top"> --%>
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="${configuration.pageMapping}/${configuration.pagePrefix}update.html">
								<a class="blue" href="javascript:void(0);" url="${configuration.pagePrefix}update.html?id=${r"${"}item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
								<%-- 跳转页面的方式打开 <a class="blue" href="${configuration.pagePrefix}update.html?id=${r"${"}item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top"> --%>
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="${configuration.pageMapping}/${configuration.pagePrefix}delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="${configuration.pagePrefix}delete.html?id=${r"${"}item.id }" data-original-title="删除" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
	                            <%--
	                            <a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="锁定/设置密码" data-placement="top">
									<i class="icon-lock bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="复制" data-placement="top">
									<i class="icon-copy bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="用户群/群组" data-placement="top">
									<i class="icon-group bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="用户/账户/账号" data-placement="top">
									<i class="icon-user bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="新增/添加" data-placement="top">
									<i class="icon-plus bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="相关" data-placement="top">
									<i class="icon-link bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="专题" data-placement="top">
									<i class="icon-tag bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="发布" data-placement="top">
									<i class="icon-mail-forward bigger-130"></i>
								</a>
	                            <a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
									<i class="icon-edit bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="回复" data-placement="top">
									<i class="icon-comment-alt bigger-130"></i>
								</a>
	                            <a class="red" href="javascript:void(0);" data-rel="tooltip" data-original-title="下线" data-placement="top">
									<i class="icon-arrow-down bigger-130"></i>
								</a>
								<a class="red" href="javascript:void(0);" data-rel="tooltip" data-original-title="禁用/忽略" data-placement="top">
									<i class="icon-ban-circle bigger-130"></i>
								</a>
								--%>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="${configuration.pagePrefix}FormId" dataUrl="${configuration.pagePrefix}list.html" />
	</div>
</div>