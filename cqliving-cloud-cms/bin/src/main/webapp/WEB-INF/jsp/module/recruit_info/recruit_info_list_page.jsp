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
                			<th>所属App</th>
                		</c:if>
                		<th>企业名称</th>
                		<th>招聘职位</th>
                		<th>职位月薪</th>
                		<th>招聘人数</th>
                		<th>工作性质</th>
                		<th>联系电话</th>
                		<th>联系地址</th>
                		<th>发布日期</th>
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
								<input type="checkbox" class="ace" id="${item.id}" status="${item.status}" />
								<span class="lbl"></span>
							</label>
						</td>
						<c:if test="${not empty appList}">
							<td>
                			<c:forEach items="${appList}" var="app" varStatus="idx">
                				<c:if test="${item.appId eq app.id }">${app.name}</c:if>
				            </c:forEach>
                			</td>
                		</c:if>
                    	<td>${item.name}</td>
                    	<td>${item.position}</td>
                    	<td>
	                    	<c:if test="${not empty item.salary}">
				                <c:forEach items="${salaryList}" var="obj">
		                    		<c:if test="${item.salary eq obj.code}">${obj.name}</c:if>
		                    	</c:forEach>
							</c:if>
                    	</td>
                    	<td>${item.numberPeople}</td>
                    	<td>
                    		<c:if test="${not empty item.workmode}">
				                <c:forEach items="${workmodeList}" var="obj">
		                    		<c:if test="${item.workmode eq obj.code}">${obj.name}</c:if>
		                    	</c:forEach>
							</c:if>
                    	</td>
                    	<td>${item.telephone}</td>
                    	<td>${item.address}</td>
                    	<td><fmt:formatDate value="${item.publicTime}" pattern="yyyy-MM-dd"/></td>
                        <td>
                        	<span class="label 
		               			<c:choose>
		                    		<c:when test="${item.status eq 1 }">label-info</c:when>
		                    		<c:when test="${item.status eq 3 }">label-success</c:when>
		                    		<c:when test="${item.status eq 88 }">label-danger</c:when>
		                    		<c:otherwise>label-info</c:otherwise>
		                    	</c:choose>
		                		">${allStatuss[item.status]}
	                		</span>
                        </td>
                        <td>
                			<input iid="${item.id}" type="text" url="/module/recruit_info/common/update_sort_no.html" class="only_num" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="3" />
                			<input type="hidden" class="only_num_old" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" />
                		</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/recruit_info/recruit_info_view.html">
								<a class="blue" href="javascript:void(0);" url="recruit_info_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" open-width="750" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/recruit_info/recruit_info_update.html">
								<a class="blue" href="javascript:void(0);" url="recruit_info_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" open-width="750" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/recruit_info/recruit_info_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="recruit_info_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/recruit_info/online.html">
	                            <c:if test="${(STATUS1 eq item.status) or (STATUS88 eq item.status)}">
									<a class="blue oper-one" href="javascript:void(0);" url="online.html?id=${item.id}&_model_=_model&status=${STATUS3}" data-rel="tooltip" data-original-title="上线" data-placement="top">
										<i class="icon-arrow-up bigger-130"></i>
									</a>
	                            </c:if>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/recruit_info/outline.html">
								<c:if test="${STATUS3 eq item.status}">
									<a class="red oper-one" href="javascript:void(0);" url="outline.html?id=${item.id}&_model_=_model&status=${STATUS88}" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
                            	</c:if>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="recruit_info_FormId" dataUrl="recruit_info_list.html" />
	</div>
</div>