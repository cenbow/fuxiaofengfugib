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
                		<th>客户端</th>
                		</c:if>
                		<th width="25%">参数名称</th>
                		<th>参数类型</th>
                		<th>长度限制</th>
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
						<c:if test="${not empty appList}">
                		<td>${item.appName}</td>
                		</c:if>
                    	<td width="25%">${item.name}</td>
                        <td><span class="label label-info">${allTypes[item.type] }</span></td>
                		<td>${item.length}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_collect_info_view.html">
								<a class="blue" href="javascript:void(0);" url="act_collect_info_view.html?id=${item.id}&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_collect_info_update.html">
								<a class="blue" href="javascript:void(0);" url="act_collect_info_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_collect_info_delete.html">
		                        <a class="red" href="javascript:;" id="deleteButton" url="act_collect_info_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="act_collect_info_FormId" dataUrl="act_collect_info_list.html" />
	</div>
</div>