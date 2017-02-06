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
						<c:if test="${not empty appList}">
                			<th>所属APP</th>
                		</c:if>
                		<th>标题</th>
                		<th>客户端类型</th>
                		<th>上线状态</th>
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
						<c:if test="${not empty appList}">
                		<td>${item.appName}</td>
                		</c:if>
                    	<td>${item.title}</td>
                    	<td>${allTypes[item.type]}</td>
                    	<td>
                    	<span class="label 
                        	<c:choose>
                        		<c:when test="${item.useStatus eq USESTATUS0 }">label-danger</c:when>
                        		<c:when test="${item.useStatus eq USESTATUS1 }">label-success</c:when>
                        		<c:otherwise>label-info</c:otherwise>
                        	</c:choose>
                        	">${allUseStatus[item.useStatus]}</span>
                    	</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/appimgversion/app_image_version_view.html">
									<a class="light-blue" href="javascript:void(0);" url="app_image_version_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/appimgversion/app_image_version_update.html">
									<a class="blue" href="javascript:void(0);" url="app_image_version_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${USESTATUS0 eq item.useStatus}">
									<cqliving-security2:hasPermission name="/module/appimgversion/start_using.html">
										<a class="blue startUsing" href="javascript:void(0);" url="start_using.html?id=${item.id}&_model_=_model" data-rel="tooltip" data-original-title="上线" data-placement="top">
											<i class="icon-arrow-up bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${USESTATUS1 eq item.useStatus}">
									<cqliving-security2:hasPermission name="/module/appimgversion/non_use.html">
										<a class="red nonUse" href="javascript:void(0);" url="non_use.html?id=${item.id}&_model_=_model" data-rel="tooltip" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
                            	<cqliving-security2:hasPermission name="/module/appimgversion/app_image_version_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="app_image_version_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="app_image_version_FormId" dataUrl="app_image_version_list.html" />
	</div>
</div>