<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>编辑登陆名</th>
                		<th>编辑姓名</th>
                		<th>栏目名称</th>
                		<th>新闻标题</th>
                		<th>发布时间</th>
                		<th>当前浏览量</th>
                		<th>当前回复量</th>
                		<th>当前参与量</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td>${item.userName}</td>
                		<td>${item.nickname}</td>
                		<td>${item.columnsName}</td>
                		<td>${item.title}</td>
                		<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                		<td>${item.viewTotalCount}</td>
                		<td>${item.replyCount}</td>
                		<td>${item.activeCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="analysis_FormId" dataUrl="analy_info_detail_list.html" />
	</div>
</div>