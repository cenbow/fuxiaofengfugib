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
                		<th>名称</th>
                		<th>分类</th>
                		<th>所处位置</th>
                		<th>所属区域</th>
                		<th>状态</th>
                		<th>排序号</th>
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
                    	<td title="${item.name}">${myfn:cutString(item.name, 30)}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                    	<td>${item.place}</td>
                    	<td>${item.regionName}</td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq 88}">
		                        	<span class="label label-danger">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 1}">
		                        	<span class="label label-warning">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 3}">
		                        	<span class="label label-success">${allStatuss[item.status]}</span>
								</c:when>
								<c:otherwise>
		                        	<span class="label label-info">${allStatuss[item.status]}</span>
								</c:otherwise>
                        	</c:choose>
                        </td>
                        <td>
                			<input iid="${item.id}" type="text" url="/module/tourism/common/update_sort_no.html" class="only_num" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="3" />
                			<input type="hidden" class="only_num_old" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" />
                		</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="blue previewButton" href="javascript:void(0);" url="/module/tourism/common/tourism_info_view.html?id=${item.id }&_model_=_model_"  open-title="预览" data-rel="tooltip" data-original-title="预览" data-placement="top">
								<i class="icon-search bigger-130"></i>
							</a>
							<cqliving-security2:hasPermission name="/module/tourism/tourism_info_update.html">
								<a class="blue" href="javascript:;" url="tourism_info_update.html?id=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="tourism_info_FormId" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <c:if test="${item.status eq 1 or item.status eq 88 }">
							<cqliving-security2:hasPermission name="/module/tourism/tourism_info_publish.html">
								<a class="blue publishOrOfflineButton" href="javascript:;" url="tourism_info_publish.html?id=${item.id }" data-rel="tooltip" data-original-title="发布" data-placement="top">
									<i class="icon-mail-forward bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            </c:if>
                            <c:if test="${item.type eq 2 }">
							<cqliving-security2:hasPermission name="/module/tourism/tourism_special_list.html">
								<a class="blue" href="javascript:;" url="tourism_special_list.html?tourismId=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="tourism_info_FormId" data-rel="tooltip" data-original-title="子景点管理" data-placement="top">
									<i class="icon-link bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            </c:if>
                            <c:if test="${item.status eq 3 }">
							<cqliving-security2:hasPermission name="/module/tourism/tourism_info_offline.html">
								<a class="red publishOrOfflineButton" href="javascript:;" url="tourism_info_offline.html?id=${item.id }" data-rel="tooltip" data-original-title="下线" data-placement="top">
									<i class="icon-arrow-down bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            </c:if>
							<cqliving-security2:hasPermission name="/module/tourism/tourism_info_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="tourism_info_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="tourism_info_FormId" dataUrl="tourism_info_list.html" />
	</div>
</div>