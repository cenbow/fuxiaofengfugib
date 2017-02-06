<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!-- 设置相关的 -->
<div class="col-xs-12">
	<div class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>详情标题</th>
                		<th>列表标题</th>
                		<th>发布时间</th>
                		<th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td>${item.title}</td>
                    	<td>${item.listTitle}</td>
                        <td>
                        	<fmt:formatDate value="${item.onlineTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                             <a href="javascript:;" role="button" class="btn btn-xs btn-info" infoclassifyid="${item.id}">设置相关</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax pageLength="5" paramFormId="info_correlation_modal_form" dataUrl="/module/info/common/info_correlation_modal.html" loadId="info_correlation_load"/>
	</div>
</div>