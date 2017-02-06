<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	    <div class="col-xs-12 dataTables_wrapper">已相关的</div>
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
						<c:forEach items="${corrpageInfo.pageResults}" var="item">
		                    <tr>
		                    	<td>${item.title}</td>
		                    	<th>${item.listTitle}</th>
		                        <td>
		                        	<fmt:formatDate value="${item.onlineTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		                        </td>
		                        <td>
		                           <a href="javascript:;" role="button" class="btn btn-xs btn-info" infoclassifyid="${item.id}">取消相关</a>
		                        </td>
		                    </tr>
		                </c:forEach>
		                </tbody>
					</table>
				</div>
		     	<%-- <cqliving-frame:paginationAjax dataUrl="/module/info/had_correlation_modal.html" loadId="info_corr" property="corrpageInfo" paramFormId="had_info_corr_form"/> --%>
			</div>
		</div>
     </div>