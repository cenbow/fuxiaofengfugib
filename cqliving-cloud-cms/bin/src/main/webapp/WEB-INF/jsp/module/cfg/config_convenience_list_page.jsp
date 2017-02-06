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
                		<th>客户端</th>
                		<th>便民类型</th>
                		<th>便民名称</th>
                		<th>便民链接</th>
                		<th>排序</th>
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
                		<td>${item.appName}</td>
                		<td>${item.convenienceTypeName}</td>
                    	<td>${item.name}</td>
                    	<td><c:out value="${item.linkUrl}"/></td>
                    	<td><input type="text" class="sortNo" style="width:60px;" value="${item.sortNo}" maxlength="9" data-id="${item.id }"></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="blue" href="javascript:void(0);" url="/module/cfg/common/config_convenience_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
								<cqliving-security2:hasPermission name="/module/cfg/config_convenience_update.html">
									<a class="blue" href="javascript:void(0);" url="/module/cfg/config_convenience_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/cfg/config_convenience_delete.html">
									<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="/module/cfg/config_convenience_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="config_convenienceFormId" dataUrl="config_convenience_list.html" />
	</div>
</div>