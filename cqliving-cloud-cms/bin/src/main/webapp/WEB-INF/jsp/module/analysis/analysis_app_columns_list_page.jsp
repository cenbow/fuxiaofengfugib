<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>栏目编号</th>
                		<th>栏目名称</th>
                		<th>所属客户端</th>
                		<th>统计日期</th>
                		<th>用户类型</th>
                		<th>浏览量</th>
                		<th>评论数</th>
                		<th>收藏数</th>
                		<th>分享数</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td>${item.appColumnsId}</td>
                		<td>${item.appColumnsName}</td>
                		<td>${item.appName}</td>
                    	<td><fmt:formatDate value="${item.statisticsDate}" pattern="yyyy-MM-dd" /></td>
                        <td>
                        	<span class="label label-info">${allUserType[item.userType] }</span>
                        </td>
                		<td>${item.viewNum}</td>
                		<td>${item.commentNum}</td>
                		<td>${item.favoriteNum}</td>
                		<td>${item.shareNum}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<a class="blue" href="javascript:;" forwordSaveParam="forwordSaveParam" url="/statistics/app_column/common/app_column_echarts.html?appColumnsId=${item.appColumnsId }&appId=${item.appId}" data-rel="tooltip" data-original-title="图表" data-placement="top">
									图表
								</a>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="analysis_app_columns_FormId" dataUrl="/statistics/app_column/index.html" />
	</div>
</div>