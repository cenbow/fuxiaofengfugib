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
                		<th>区域名称</th>
                		<th>排序号</th>
                		<c:if test="${TYPE2 eq type}">
                		<th>商情分类名称</th>
                		</c:if>
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
	                		<c:forEach items="${appList}" var="app">
	                           <c:if test="${app.id eq item.appId}">${app.name}</c:if>
	                        </c:forEach>
                		</td>
                		</c:if>
                    	<td>${item.name}</td>
                		<td>
	                		<input iid="${item.id}" type="text" url="/module/config_region/common/update_type_sort_no.html" class="only_num" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="3" />
	               			<input type="hidden" class="only_num_old" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" />
                		</td>
                		<c:if test="${TYPE2 eq type}">
                		<td>${item.typeName}</td>
                		</c:if>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                            <c:if test="${TYPE1 eq type}">
									<cqliving-security2:hasPermission name="/module/config_region/wz_config_region_update.html">
										<a class="blue" href="javascript:void(0);" url="wz_config_region_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
											<i class="icon-pencil bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/config_region/wz_config_region_delete.html">
										<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="wz_config_region_delete.html?id=${item.id}" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${TYPE2 eq type}">
									<cqliving-security2:hasPermission name="/module/config_region/shop_config_region_update.html">
										<a class="blue" href="javascript:void(0);" url="shop_config_region_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
											<i class="icon-pencil bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/config_region/shop_config_region_delete.html">
										<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="shop_config_region_delete.html?id=${item.id}" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${TYPE10 eq type}">
									<cqliving-security2:hasPermission name="/module/config_region/tourism_config_region_update.html">
										<a class="blue" href="javascript:void(0);" url="tourism_config_region_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
											<i class="icon-pencil bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/config_region/tourism_config_region_delete.html">
										<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="tourism_config_region_delete.html?id=${item.id}" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<c:if test="${TYPE1 eq type}">
     		<cqliving-frame:paginationAjax paramFormId="config_region_FormId" dataUrl="wz_config_region_list.html"/>
		</c:if>
		<c:if test="${TYPE2 eq type}">
     		<cqliving-frame:paginationAjax paramFormId="config_region_FormId" dataUrl="shop_config_region_list.html"/>
		</c:if>
		<c:if test="${TYPE10 eq type}">
     		<cqliving-frame:paginationAjax paramFormId="config_region_FormId" dataUrl="tourism_config_region_list.html"/>
		</c:if>
	</div>
</div>