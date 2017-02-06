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
                		<th>标题</th>
                		<th>栏目</th>
                		<th>栏目显示类型</th>
                		<th>发布状态</th>
                		<th>发布时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" icid="${item.id}" ss="${item.status}">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.id}</td>
                    	<td>${item.title}</td>
                		<td>${item.columnsName}</td>
                        <td>
                        	<span class="label label-info">${allListViewTypes[item.listViewType]}</span>
                        </td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq -1 or item.status eq 88}">
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
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                            	<cqliving-security2:hasPermission name="/module/info/info_add.html">
                            		<a class="blue" href="javascript:;" url="/module/info/info_add.html?id=${item.id}&back_url=/module/infoClassify/info_classify_list_copy.html" forwordSaveParam="forwordSaveParam" data-rel="tooltip" data-original-title="编辑" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
                            	<c:if test="${item.status eq statusOnline}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline.html">
	                            		<a class="red offline_btn" href="javascript:;"  url="info_classify_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	
                            	<c:if test="${fn:contains(statusSave,item.status)}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish.html">
                           				<a class="blue publish_btn" href="javascript:;" url="info_classify_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="info_classify_FormId" dataUrl="info_classify_list_copy.html" />
	</div>
</div>