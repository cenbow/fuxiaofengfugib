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
                		<th>模板名称</th>
                		<th>模板CODE</th>
                		<th>宽</th>
                		<th>高</th>
                		<th>备注说明</th>
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
                		<td>
                		<c:forEach items="${appList}" var="app" varStatus="idx">
				        	<c:if test="${app.id eq item.appId}">${app.name}</c:if>
				        </c:forEach>
                		</td>
						</c:if>
                    	<td>${item.name}</td>
                    	<td>${item.templetCode}</td>
                		<td>${item.width}</td>
                		<td>${item.hight}</td>
                    	<td>${item.context}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/infoeTmpleteImageConfig/info_templete_image_config_view.html">
									<a class="light-blue" href="info_templete_image_config_view.html?id=${item.id}" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/infoeTmpleteImageConfig/info_templete_image_config_update.html">
									<a class="blue" href="info_templete_image_config_update.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/infoeTmpleteImageConfig/info_templete_image_config_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="info_templete_image_config_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="info_templete_image_config_FormId" dataUrl="info_templete_image_config_list.html" />
	</div>
</div>