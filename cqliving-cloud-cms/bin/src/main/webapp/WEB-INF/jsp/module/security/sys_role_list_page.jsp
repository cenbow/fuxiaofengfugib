<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>主键ID</th>
                		<th>角色名称</th>
                		<th>所属APP</th>
                		<th>是否公共角色</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td>${item.id}</td>
                    	<td>${item.roleName}</td>
                		<td>
                		   <c:forEach items="${allApps}" var="app">
		                        <c:if test="${item.appId eq app.id}">
		                           ${app.name }
		                        </c:if>
		                    </c:forEach>
                		</td>
                		<td>${item.type eq 1 ? '是' : '否'}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/security/sys_role_view.html">
                                <a class="blue" href="sys_role_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/security/sys_role_update.html">
                                <a data-placement="top" data-original-title="编辑" data-rel="tooltip" href="sys_role_update.html?id=${item.id }" class="blue">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            
                            <c:if test="${item.type ne 1 && empty item.commonRoleCode}">
                               <cqliving-security2:hasPermission name="/module/security/sys_role_delete.html">
                                <a data-placement="top" data-original-title="删除" data-rel="tooltip" href="javascript:;" id="deleteButton" url="sys_role_delete.html?roleId=${item.id }" class="red">
									<i class="icon-trash bigger-130"></i>
								</a>
                                </cqliving-security2:hasPermission>
                            </c:if>
							
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="sys_role_FormId" dataUrl="sys_role_list.html" />
	</div>
</div>