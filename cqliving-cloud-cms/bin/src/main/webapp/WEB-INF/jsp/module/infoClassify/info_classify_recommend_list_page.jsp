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
                		<th>新闻类型</th>
                		<th>发布时间</th>
                		<th>发布状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" icid="${item.id}" commid="${item.commentId}">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.id}</td>
                    	<td>${item.title}</td>
                		<td>${item.columnsName}</td>
                        <td>
                        	<span class="label label-info">${empty allContextTypes[item.contextType] ? "专题" : allContextTypes[item.contextType]}</span>
                        </td>
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.recommendStatus]}</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                            	<cqliving-security2:hasPermission name="/module/info/info_classify_recommed_edit.html">
	                            	<a class="blue" url="/module/info/info_classify_recommed_edit.html?commentId=${item.commentId}" forwordSaveParam="forwordSaveParam" href="javascript:;" data-rel="tooltip" data-original-title="编辑" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${item.recommendStatus eq status0}">
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish_to_column.html">
	                            		<a class="blue publish_btn" href="javascript:;" data-toggle="modal" data-target="#app_column_modal" data-rel="tooltip" data-original-title="发布到APP" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
                            	<c:if test="${item.recommendStatus eq status0}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_recommend_ignore.html">
	                            		<a class="red ignore_btn" href="javascript:;"  url="/module/infoClassify/info_classify_recommend_ignore.html?id=${item.commentId}" data-rel="tooltip" data-original-title="忽略" data-placement="top">
											<i class="icon-ban-circle bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="info_classify_FormId" dataUrl="info_classify_list_recommend.html" />
	</div>
</div>