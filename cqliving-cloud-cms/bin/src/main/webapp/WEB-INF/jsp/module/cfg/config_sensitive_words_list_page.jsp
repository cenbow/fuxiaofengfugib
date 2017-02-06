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
                		<th>ID</th>
                		<c:if test="${not empty appList}">
                			<th>所属APP</th>
                		</c:if>
                		<th>敏感词</th>
                		<th>类型</th>
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
                		<td>${item.id}</td>
                		<c:if test="${not empty appList}">
                			<td>${item.appName}</td>
                		</c:if>
                    	<td>${item.name}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/sensitiveWords/config_sensitive_words_view.html">
									<a class="light-blue" href="javascript:void(0);" url="config_sensitive_words_view.html?id=${item.id}&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/sensitiveWords/config_sensitive_words_update.html">
									<a class="blue" href="javascript:void(0);" url="config_sensitive_words_update.html?id=${item.id}&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/sensitiveWords/config_sensitive_words_delete.html">
									<a class="red" href="javascript:;" id="deleteButton" url="config_sensitive_words_delete.html?id=${item.id }" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="config_sensitive_words_FormId" dataUrl="config_sensitive_words_list.html" />
	</div>
</div>