<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
                    	  <label>
	                    	  <input type="checkbox" class="ace ace-checkbox-2" value=""/>
	                    	  <span class="lbl"></span>
                    	  </label>
						</th>
                		<th>标题</th>
                		<th>栏目</th>
                		<th>状态</th>
                		<th>创建者</th>
                		<th>发布时间</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center">
							<label>
								<input type="checkbox" class="ace ace-checkbox-2" name="infoClassifyId" value="${item.id}" appid="${item.appId }"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.title}</td>
                    	<td>${item.columnsName}</td>
                        <td>
                        	<span class="label label-info">${allStatuses[item.status] }</span>
                        </td>
                        <td>
                        	${item.creator }
                        </td>
                        <td><fmt:formatDate value="${item.onlineTime }" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax pageLength="5" paramFormId="join_info_modal_form" dataUrl="/module/info/join_info_modal.html" loadId="info_join_load"/>
	</div>
</div>