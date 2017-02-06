<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                		<th>登录用户名</th>
                		<th>回复机构名称</th>
                		<th>状态</th>
                		<th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.sysUser.username}</td>
                    	<td>${item.orgName}</td>
                    	<td><span class="label label-success">${allStatuss[item.sysUser.status]}</span></td>
                    	<td><fmt:formatDate value="${item.sysUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/wz/wz_user_view.html">
	                        		<a class="light-blue" href="wz_user_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/wz/wz_user_update.html">
	                        		<a class="blue" href="wz_user_update.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/wz/wz_user_modify_password.html">
	                        		<a class="blue resetPassButton" href="javascript:;" data-id="${item.id }" data-orgName="${item.orgName }" data-rel="tooltip" data-original-title="重置密码" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="wz_user_FormId" dataUrl="wz_user_list.html" />
	</div>
</div>