<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th width="10%">用户名称</th>
                		<th width="10%">手机号</th>
                		<th width="25%">内容</th>
                		<th width="10%">状态</th>
                		<th width="10%">创建时间</th>
                		<th width="25%">回复内容</th>
                        <th width="10%">操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td width="10%">${item.name}</td>
                    	<td width="10%">${item.telephone}</td>
                    	<td width="25%">${item.content}</td>
                        <td width="10%"><span class="label 
                        <c:choose>
                    		<c:when test="${item.status eq 2}">label-warning</c:when>
                    		<c:when test="${item.status eq 3}">label-success</c:when>
                    		<c:otherwise>label-info</c:otherwise>
                    	</c:choose>
                        ">${allStatuss[item.status]}</span></td>
                    	<td width="10%"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                    	<td width="25%" style="word-wrap: break-word; word-break: break-all;">${item.replyContent}</td>
                        <td width="10%">
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/userFeedback/user_feedback_view.html">
								<a class="light-blue" href="javascript:void(0);" url="user_feedback_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <c:if test="${STATUS3 ne  item.status}">
								<cqliving-security2:hasPermission name="/module/userFeedback/reply.html">
									<a class="blue" href="javascript:void(0);" url="reply.html?id=${item.id }&_model_=_model" open-model="update" open-title="回复" data-rel="tooltip" data-original-title="回复" data-placement="top">
										<i class="icon-comment-alt bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
                            </c:if>
							<cqliving-security2:hasPermission name="/module/userFeedback/user_feedback_delete.html">
								<button class="btn btn-xs btn-danger" id="deleteButton" url="user_feedback_delete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="user_feedback_FormId" dataUrl="user_feedback_list.html" />
	</div>
</div>