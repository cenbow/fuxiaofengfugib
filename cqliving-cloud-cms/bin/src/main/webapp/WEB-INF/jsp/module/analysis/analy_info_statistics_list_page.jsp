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
                		<th>新闻数量</th>
                		<th>浏览总量</th>
                		<th>浏览人数</th>
                		<th>评论总数量</th>
                		<th>评论总人数</th>
                		<th>参与活动数量</th>
                		<th>参与活动人数</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td>${item.userName}</td>
                		<td>${item.nickname}</td>
                		<td>${item.infoCount}</td>
                		<td>${item.viewTotalCount}</td>
                		<td>${item.viewUserCount}</td>
                		<td>${item.replyCount}</td>
                		<td>${item.replyUserCount}</td>
                		<td>${item.activeCount}</td>
                		<td>${item.activeUserCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="analysis_FormId" dataUrl="analy_info_statistics_list.html" />
	</div>
</div>