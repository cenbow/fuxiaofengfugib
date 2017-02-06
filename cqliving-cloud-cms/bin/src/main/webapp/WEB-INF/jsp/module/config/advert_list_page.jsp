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
                		<th>所属客户端</th>
                		</c:if>
                		<th>图片</th>
                		<th>广告链接地址</th>
                		<th>排序号</th>
                		<th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" name="ace" id="${item.id}" status="${item.status}"/>
								<span class="lbl"></span>
							</label>
						</td>
						<c:if test="${not empty appList}">
	                		<td>
		                		<c:forEach items="${appList}" var="app">
		                           <c:if test="${item.appId eq app.id}">${app.name}</c:if>
		                        </c:forEach>
	                		</td>
                		</c:if>
                		<td><img height="50 px;" width="50 px;" src="${item.imageUrl}"/></td>
                    	<td>${item.linkUrl}</td>
                		<td>
                			<input iid="${item.id}" type="text" url="/module/advert/common/update_sort_no.html" class="only_num" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="9" />
                			<input type="hidden" class="only_num_old" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" />
                		</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/advert/advert_view.html">
								<a class="blue" href="javascript:void(0);" url="advert_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/advert/advert_update.html">
								<a class="blue" href="javascript:void(0);" url="advert_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <cqliving-security2:hasPermission name="/module/advert/on_line.html">
						        <c:if test="${STATUS3 ne item.status}">
			                        <a class="blue on-out" href="javascript:;" tip="确认要上线么？" recommendId="${item.id}" url="on_line.html" data-rel="tooltip" data-original-title="上线" data-placement="top">
										<i class="icon-arrow-up bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
					        <cqliving-security2:hasPermission name="/module/advert/out_line.html">
						        <c:if test="${STATUS3 eq item.status}">
			                        <a class="red on-out" href="javascript:;" tip="确认要下线么？" recommendId="${item.id}" url="out_line.html" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/advert/advert_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="advert_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="advert_FormId" dataUrl="advert_list.html" />
	</div>
</div>