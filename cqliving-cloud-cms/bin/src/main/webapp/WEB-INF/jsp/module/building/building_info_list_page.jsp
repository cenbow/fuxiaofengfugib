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
                		<th>楼盘名称</th>
                		<th>楼盘地址</th>
                		<th>楼盘价格</th>
                		<th>咨询电话</th>
                		<th>楼盘类型</th>
                		<th>状态</th>
                		<th>排序号</th>
                		<th>发布者</th>
                		<th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}" status="${item.status }"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.name}</td>
                    	<td>${item.address}</td>
                    	<td>${item.viewPrice }</td>
                    	<td>${item.telephone }</td>
                    	<td>${item.buildingType }</td>
                    	<td>
                    		<span class="label  
                    	<c:choose>
                    		<c:when test="${item.status eq 3 }">label-success</c:when>
                    		<c:when test="${item.status eq 88 }">label-danger</c:when>
                    		<c:otherwise>label-primary</c:otherwise>
                    	</c:choose>
                    		">${allStatuss[item.status] }</span>
                    	</td>
                    	<td><input type="text" class="only_num" value="${item.sortNo eq defaultSortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="2" /></td>
                    	<td>${item.creator }</td>
                    	<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/building/building_info_view.html">
									<a class="blue" href="/module/building/building_info_view.html?id=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="building_info_FormId" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/building/building_info_update.html">
									<a class="blue" href="/module/building/building_info_update.html?id=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="building_info_FormId" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${item.status ne 3 }">
									<cqliving-security2:hasPermission name="/module/building/building_info_online/1.html">
										<a class="blue onlineBtn" href="javascript:;" url="/module/building/building_info_online/1.html?id=${item.id }" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status eq 3 }">
								<cqliving-security2:hasPermission name="/module/building/building_info_online/2.html">
									<a class="red onlineBtn" href="javascript:;" url="/module/building/building_info_online/2.html?id=${item.id }" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status ne 3 }">
								<cqliving-security2:hasPermission name="/module/building/building_info_delete.html">
									<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="building_info_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="building_info_FormId" dataUrl="building_info_list.html" />
	</div>
</div>