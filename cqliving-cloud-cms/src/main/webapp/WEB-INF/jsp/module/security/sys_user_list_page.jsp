<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<!-- <th class="center">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th> -->
                		<th>主键ID</th>
                		<th>登录用户名</th>
                		<th>拥有的角色</th>
                		<th>用户类型</th>
                		<th>所属APP</th>
                		<th>状态</th>
                		<th>创建时间</th>
                		<th>手机</th>
                		<th>QQ</th>
                		<th>职位</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<%-- <td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td> --%>
                		<td>${item.id}</td>
                    	<td>${item.username}</td>
                    	<td>
                    	   <c:forEach items="${item.role}" var="rl" varStatus="vs">
                             ${rl.roleName }
                             <c:if test="${!vs.last }">,</c:if>
                           </c:forEach>
                    	</td>
                        <td>
                        	<span class="label label-info">${allUsertypes[item.usertype] }</span>
                        </td>
                		<td>
                		   <c:forEach items="${allApps }" var="app">
                		         ${app.id eq item.appId ? app.name : '' }
                		   </c:forEach>
                		</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                    	<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td>${item.mobile}</td>
                    	<td>${item.qqCode}</td>
                    	<td>${item.position}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/security/sys_user_view.html">
                        		<a class="blue" href="sys_user_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/security/sys_user_update.html">
								<a data-placement="top" data-original-title="编辑" data-rel="tooltip" href="sys_user_update.html?id=${item.id}" class="blue">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/security/sys_user_pass.html">
								<a data-placement="top" data-original-title="重置密码" data-rel="tooltip" href="sys_user_pass.html?id=${item.id }" class="blue">
									<i class="icon-lock bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="sys_user_FormId" dataUrl="sys_user_list.html" />
	</div>
</div>