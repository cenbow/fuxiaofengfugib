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
                		<th>标题</th>
                		<th>发布人</th>
                		<th>发布区县</th>
                		<th>内容</th>
                		<th>发送时间</th>
                		<th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.rId}">
							<label>
								<input type="checkbox" class="ace" id="${item.rId}" readStatus="${item.readStatus}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.title}</td>
                    	<td>${item.creator}</td>
                		<td>${item.appName}</td>
                    	<td>${item.context}</td>
                    	<td><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.readStatus]}</span>
                        </td>
                        <td>
	                        <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                            <cqliving-security2:hasPermission name="/module/message/letter_detail_view.html">
									<a class="light-blue" href="letter_detail_view.html?id=${item.rId}" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <cqliving-security2:hasPermission name="/module/message_receive/message_receive_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="/module/message_receive/message_receive_delete.html?id=${item.rId}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="letter_lable_FormId" dataUrl="letter_list.html" />
	</div>
</div>