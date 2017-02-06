<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th width="10%">操作人</th>
                		<th width="10%">模块名称</th>
                		<th width="10%">操作</th>
                		<th width="5%">执行时长（ms）</th>
                		<th width="10%">操作时间</th>
                		<th width="50%">操作说明</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td width="10%">${item.operateUser}</td>
                    	<td width="10%">${item.moduleName}</td>
                    	<td width="10%">${item.actionName}</td>
                		<td width="7%">${item.executeMilliseconds}</td>
                    	<td width="10%"><fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td width="53%" style="word-wrap: break-word; word-break: break-all;">
                    	【${item.operateUser}】做了【${item.moduleName}】的【${item.actionName}】操作<%-- ，传参：${item.requestParameters} --%>
                    	</td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="log_operate_FormId" dataUrl="log_operate_list.html" />
	</div>
</div>