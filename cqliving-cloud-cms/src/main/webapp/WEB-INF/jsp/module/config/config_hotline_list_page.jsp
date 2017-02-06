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
						<c:if test="${not empty appList}">
                			<th>所属APP</th>
                		</c:if>
                		<th>热线类型</th>
                		<th>热线名称</th>
                		<th>电话</th>
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
						<c:if test="${not empty appList}">
                			<td>${item.appName}</td>
                		</c:if>
                		<td>${item.typeName}</td>
                    	<td>${item.name}</td>
                    	<td>${item.phone}</td>
                		<td>
                			<input iid="${item.id}" type="text" url="/module/config_hotline/common/update_sort_no.html" class="only_num" value="${empty item.sortNo ? '1' : item.sortNo}" style="width: 42px;" maxlength="3" />
                			<input type="hidden" class="only_num_old" value="${empty item.sortNo ? '1' : item.sortNo}" />
                		</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/config_hotline/config_hotline_view.html">
								<a class="blue" href="javascript:void(0);" url="config_hotline_view.html?id=${item.id}&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/config_hotline/config_hotline_update.html">
								<a class="blue" href="javascript:void(0);" url="config_hotline_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/config_hotline/config_hotline_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="config_hotline_delete.html?id=${item.id}" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="config_hotline_FormId" dataUrl="config_hotline_list.html" />
	</div>
</div>