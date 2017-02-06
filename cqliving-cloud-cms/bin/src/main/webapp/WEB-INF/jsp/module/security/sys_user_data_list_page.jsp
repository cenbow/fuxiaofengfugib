<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>权限编号</th>
                		<th>用户编号</th>
                		<th>用户名</th>
                		<th>${dataType eq 2 ? '栏目' : '商铺分类' }</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td>${item.id}</td>
                		<td>${item.userId}</td>
                		<td>${item.userName}</td>
                		<td>${item.valueName}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<cqliving-security2:hasPermission name="${addUri}">
								<%-- <a class="blue" href="javascript:void(0);" url="sys_user_data_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top"> --%>
								<a class="blue" href="${addUri}?userId=${item.userId }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <cqliving-security2:hasPermission name="${deleteUri}">
								<a id="deleteButton" class="red" href="javascript:;" url="${deleteUri}?userId=${item.userId }" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="sys_user_data_FormId" />
	</div>
</div>