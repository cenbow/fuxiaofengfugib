<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
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
						<th>主键ID</th>
						<th>角色名称</th>
						<th>说明</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${pageInfo.pageResults}" var="item">
	                    <tr>
	                    	<td class="center" id="${item.id}">
								<label>
									<input type="checkbox" class="ace" />
									<span class="lbl"></span>
								</label>
							</td>
	                        <td>${item.id}</td>
	                        <td>${item.roleName}</td>
	                        <td>${item.descn}</td>
	                        <td>
	                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                            	<cqliving-security2:hasPermission name="/module/security/sys_role_view.html">
										<button class="btn btn-xs">查看</button>
		                            </cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/security/sys_role_update.html">
										<button class="btn btn-xs" onclick="javascript:location.href='/module/security/sys_role_update.html?id=${item.id }'">修改</button>
		                            </cqliving-security2:hasPermission>
									<button class="btn btn-xs">删除</button>
								</div>
	                        </td>
	                    </tr>
	                </c:forEach>
				</tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="sysRoleListFormId" dataUrl="/module/security/sys_role_list.html" />
	</div>
</div>