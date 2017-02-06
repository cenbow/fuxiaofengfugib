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
                		<th>编号</th>
                		<th>客户端名称</th>
                		<th>客户端LOGO</th>
                		<th>自定义域名</th>
                		<th>后台域名</th>
                		<th>管理员</th>
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
                    	<td>${item.name}</td>
                    	<td><img height="50 px;" width="50 px;" src="${item.logo}"/></td>
                    	<td>${item.appDomain}</td>
                    	<td>${item.cmsDomain}</td>
                    	<td>${item.username}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/app/app_info_view.html">
	                        		<a class="light-blue" href="app_info_view.html?id=${item.id }&weatherId=${item.weatherId}&qiniuId=${item.qiniuId}" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/app/app_info_update.html">
									<a class="blue" href="app_info_update.html?id=${item.id}&weatherId=${item.weatherId}&qiniuId=${item.qiniuId}" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${not empty item.userId}">
									<cqliving-security2:hasPermission name="/module/app/pwd_update.html">
										<a class="blue" href="pwd_update.html?userId=${item.userId}" data-rel="tooltip" data-original-title="账号编辑" data-placement="top">
											<i class="icon-lock bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
								<cqliving-security2:hasPermission name="/module/app/app_info_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="app_info_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="app_info_FormId" dataUrl="app_info_list.html" />
	</div>
</div>