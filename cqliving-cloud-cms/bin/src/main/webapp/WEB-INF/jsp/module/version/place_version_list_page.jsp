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
                		<th>主键</th>
                		<th>APP_ID</th>
                		<th>位置ID</th>
                		<th>客户端类型</th>
                		<th>版本号</th>
                		<th>发布时间</th>
                		<th>创建时间</th>
                		<th>创建人ID</th>
                		<th>创建人名称</th>
                		<th>状态</th>
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
                		<td>${item.appId}</td>
                		<td>${item.placeId}</td>
                    	<td>${item.type}</td>
                		<td>${item.versionNo}</td>
                    	<td><fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd" /></td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                		<td>${item.creatorId}</td>
                    	<td>${item.creator}</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/version/place_version_view.html">
								<button class="btn btn-xs btn-info" onclick="javascript:location.href='place_version_view.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/version/place_version_update.html">
								<button class="btn btn-xs btn-success" onclick="javascript:location.href='place_version_update.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/version/place_version_delete.html">
								<button class="btn btn-xs btn-danger" id="deleteButton" url="place_version_delete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="place_version_FormId" dataUrl="place_version_list.html" />
	</div>
</div>