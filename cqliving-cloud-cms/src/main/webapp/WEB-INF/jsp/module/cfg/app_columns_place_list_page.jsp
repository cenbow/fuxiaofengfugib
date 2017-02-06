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
                		<th>客户端_ID</th>
                		<th>位置CODE</th>
                		<th>位置名称</th>
                		<th>位置描述</th>
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
                		<td>${item.appId}</td>
                    	<td>${item.placeCode}</td>
                    	<td>${item.placeName}</td>
                    	<td>${item.placeDesc}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/place/app_columns_place_view.html">
								<button class="btn btn-xs" onclick="javascript:location.href='app_columns_place_view.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/place/app_columns_place_update.html">
								<button class="btn btn-xs" onclick="javascript:location.href='app_columns_place_update.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/place/app_columns_place_delete.html">
								<button class="btn btn-xs" id="deleteButton" url="app_columns_place_delete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="app_columns_place_FormId" dataUrl="app_columns_place_list.html" />
	</div>
</div>