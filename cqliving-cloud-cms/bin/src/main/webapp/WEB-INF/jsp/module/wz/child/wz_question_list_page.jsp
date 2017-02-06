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
                		<th>事件类型</th>
                		<th>状态</th>
                		<th>处理结果</th>
                		<th>所属区域名称</th>
                		<th>标题</th>
                		<th>创建人</th>
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
                        <td>
                        	${allTypes[item.type] }
                        </td>
                        <td>
                        <span class="label 
	                        	<c:if test="${item.status == -1 }">label-danger</c:if>
	                        	<c:if test="${item.status == 2 }">label-yellow</c:if>
	                        	<c:if test="${item.status == 3 }">label-warning</c:if>
	                        	<c:if test="${item.status == 4 }">label-primary</c:if>
	                        	<c:if test="${item.status == 5 }">label-yellow</c:if>
	                        	<c:if test="${item.status == 6 }">label-danger</c:if>
	                        	<c:if test="${item.status == 7 }">label-success</c:if>
	                        	<c:if test="${!(item.status == 1 && item.status == 2 && item.status == 3 && item.status == 4 && item.status == 5 && item.status == 6 && item.status == 7) }">label-info</c:if>
                        	">${allStatuss[item.status] }</span>
                        </td>
                        <td>
                        	<c:if test="${not empty item.result }">${allResults[item.result] }</c:if>
                        </td>
                    	<td>${item.regionName}</td>
                    	<td>${item.title}</td>
                    	<td>${item.creator}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                            <cqliving-security2:hasPermission name="/module/wz/child/wz_question_view.html">
	                        		<a class="blue" href="javascript:void(0);" url="/module/wz/child/wz_question_view.html?id=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="wz_question_FormId" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${empty item.result}">
		                            <cqliving-security2:hasPermission name="/module/wz/child/wz_question_return.html">
		                        		<a class="red returnButton" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="驳回" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 1 || item.status == 2 }">
		                            <cqliving-security2:hasPermission name="/module/wz/child/wz_question_reply.html">
		                        		<a class="blue replyBtn" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="回复" data-placement="top">
											<i class="icon-comment-alt bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 2}">
		                            <cqliving-security2:hasPermission name="/module/wz/child/wz_question_submit.html">
		                        		<a class="blue submitButton" href="javascript:;" url="wz_question_submit.html?id=${item.id }" data-rel="tooltip" data-original-title="提交" data-placement="top">
											<i class="icon-arrow-top bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 3 && item.result == 3 }">
									<cqliving-security2:hasPermission name="/module/wz/child/wz_question_transfer.html">
		                        		<a class="blue transferBtn" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="转交" data-placement="top">
											<i class="icon-exchange bigger-130"></i>
										</a>
									</cqliving-security2:hasPermission>
								</c:if>
	                            <c:if test="${isLastPublish == false && item.status == 3 && item.result == 3 }">
		                            <cqliving-security2:hasPermission name="/module/wz/child/wz_question_publish.html">
		                        		<a class="blue buttonPublish" href="javascript:;" url="/module/wz/child/wz_question_publish.html?id=${item.id }" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${!(!isAllowDel && item.status == 3 && item.result == 4)}">
									<cqliving-security2:hasPermission name="/module/wz/child/wz_question_delete.html">
		                        		<a class="red" href="javascript:;" id="deleteButton" url="wz_question_delete.html?id=${item.id }" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="wz_question_FormId" dataUrl="wz_question_list.html" />
	</div>
</div>
