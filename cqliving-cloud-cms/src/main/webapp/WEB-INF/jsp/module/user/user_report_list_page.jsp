<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th width="20%">举报人姓名</th>
                		<th>举报类型</th>
                		<th>举报来源类型</th>
                		<th>状态</th>
                		<th>审阅状态</th>
                		<th>举报时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td width="20%">${item.name}</td>
                    	<td>
<%-- 	                    	<c:forEach items="${reportTypeList}" var="obj"> --%>
<%-- 	                    		<c:if test="${item.reportCode eq obj.code}"> ${obj.name}</c:if> --%>
<%-- 	                    	</c:forEach> --%>
	                    	<c:if test="${not empty item.reportCode}">
						         <c:forEach items="${fn:split(item.reportCode, ',')}" var="code" >
						                <c:forEach items="${reportTypeList}" var="obj">
				                    		<c:if test="${code eq obj.code}">${obj.name}&nbsp;</c:if>
				                    	</c:forEach>
						         </c:forEach>
							</c:if>
                    	</td>
                        <td>
                        	<span class="label label-info">${allSourceTypes[item.sourceType]}<c:if test="${TYPE1 eq item.operateType}">评论</c:if></span>
                        </td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status]}</span>
                        </td>
                        <td>
                        	<span class="label 
                        	<c:choose>
                        		<c:when test="${item.auditingType eq 0 }">label-danger</c:when>
                        		<c:when test="${item.auditingType eq 1 }">label-success</c:when>
                        		<c:otherwise>label-info</c:otherwise>
                        	</c:choose>
                        	">${allAuditingTypes[item.auditingType]}</span>
                        </td>
                        <td>
                        	<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/userReport/user_report_view.html">
                            	<%-- <a class="light-blue" href="user_report_view.html?id=${item.id}&sourceType=${item.sourceType}&type=${item.operateType}" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a> --%>
								<a class="light-blue" href="javascript:void(0);" url="user_report_view.html?id=${item.id}&sourceType=${item.sourceType}&type=${item.operateType}&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <c:if test="${AUDITINGTYPE0 eq item.auditingType}">
	                        	<cqliving-security2:hasPermission name="/module/userReport/auditing.html">
	                            	<%-- <a class="blue" href="auditing.html?id=${item.id}&sourceType=${item.sourceType}&type=${item.operateType}" data-rel="tooltip" data-original-title="审核" data-placement="top">
										<i class="icon-edit bigger-130"></i>
									</a> --%>
									<a class="light-blue" href="javascript:void(0);" url="auditing.html?id=${item.id}&sourceType=${item.sourceType}&type=${item.operateType}&_model_=_model_" open-model="aduit" open-title="审核" data-rel="tooltip" data-original-title="审核" data-placement="top">
										<i class="icon-edit bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="user_report_FormId" dataUrl="user_report_list.html" />
	</div>
</div>