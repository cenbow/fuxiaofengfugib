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
                		<th>ID</th>
                		<c:if test="${not empty appList}">
                			<th>所属APP</th>
                		</c:if>
                		<th>客户端类型</th>
                		<th>版本名称</th>
                		<th>显示版本号</th>
                		<th>版本号</th>
                		<th>最低支持版本号</th>
                		<th>发布时间</th>
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
                		<td>${item.id}</td>
                		<c:if test="${not empty appList}">
                			<td>${item.appName}</td>
                		</c:if>
                        <td><span class="label label-info">
                        	${allTypes[item.type]}
                        </span></td>
                    	<td>${item.name}</td>
                    	<td>${item.viewVersion}</td>
                		<td>${item.vesionNo}</td>
                		<td>${item.minVersion}</td>
                    	<td><fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/version/app_version_view.html">
									<a class="light-blue" href="app_version_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${not empty item.maxId}">
									<cqliving-security2:hasPermission name="/module/version/app_version_update.html">
										<a class="blue" href="app_version_update.html?id=${item.id}" data-rel="tooltip" data-original-title="修改" data-placement="top">
											<i class="icon-pencil bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<cqliving-security2:hasPermission name="/module/version/app_version_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="app_version_delete.html?id=${item.id }" data-rel="tooltip" data-original-title="删除" data-placement="top">
										<i class="icon-trash bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="app_version_FormId" dataUrl="app_version_list.html" />
	</div>
</div>