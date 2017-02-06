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
<!--                 		<th>id</th> -->
                		<th>标题</th>
                		<th>创建人</th>
                		<th>发布区县</th>
                		<th>内容</th>
                		<th>发送时间</th>
<!--                 		<th>接收人类型</th> -->
<!--                 		<th>发送类型</th> -->
                		<th>状态</th>
<!--                 		<th>创建时间</th> -->
<!--                 		<th>创建人姓名</th> -->
<!--                 		<th>更新时间</th> -->
<!--                 		<th>更新人ID</th> -->
<!--                 		<th>更新人</th> -->
<!--                 		<th>接收客户端名称，多个用,分隔</th> -->
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
<%--                 		<td>${item.id}</td> --%>
                    	<td>${item.title}</td>
                    	<td>${item.creator}</td>
                		<td>${item.appName}</td>
                    	<td>${item.context}</td>
                    	<td><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%--                     	<td>${item.receiverType}</td> --%>
<!--                         <td> -->
<%--                         	<span class="label label-info">${allSendTypes[item.sendType] }</span> --%>
<!--                         </td> -->
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq 3}">
		                        	<span class="label label-success">${allStatuss[item.status]}</span>
								</c:when>
								<c:otherwise>
		                        	<span class="label label-info">${allStatuss[item.status]}</span>
								</c:otherwise>
                        	</c:choose>
                        </td>
<%--                     	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td> --%>
<%--                 		<td>${item.creatorId}</td> --%>
<%--                     	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td> --%>
<%--                 		<td>${item.updatorId}</td> --%>
<%--                     	<td>${item.updator}</td> --%>
<%--                     	<td>${item.receiverAppId}</td> --%>
                        <td>
	                        <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<c:if test="${item.sendTime gt dateNow}">
		                        	<cqliving-security2:hasPermission name="/module/message/message_info_send.html">
										<a class="blue send_btn" href="javascript:void(0);" url="/module/message/message_info_send.html?id=${item.id}" data-rel="tooltip" data-original-title="发送" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="message_info_FormId" dataUrl="message_info_list.html" />
	</div>
</div>