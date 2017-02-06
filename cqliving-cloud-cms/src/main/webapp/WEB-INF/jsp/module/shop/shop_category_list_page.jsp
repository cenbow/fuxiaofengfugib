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
                		<th>类型</th>
                		<th>分类名称</th>
                		<c:if test="${not empty typeId}">
	                		<th>排序号</th>
                		</c:if>
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
                		<td>${item.typeName}</td>
                    	<td>${item.name}</td>
                    	<c:if test="${not empty typeId}">
                    		<td><input type="text" class="only_num" value="${item.sortNo gt 100 ? '' : item.sortNo}" style="width: 42px;" maxlength="2" /></td>
                		</c:if>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
<%--                         	<cqliving-security2:hasPermission name="/module/shop_category/shop_category_view.html"> --%>
<%-- 								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='shop_category_view.html?id=${item.id }'">查看</button> --%>
<%--                             </cqliving-security2:hasPermission> --%>
							<cqliving-security2:hasPermission name="/module/shop_category/shop_category_update.html">
                            	<a class="blue" href="shop_category_update.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/shop_category/shop_category_delete.html">
                            	<a class="red" href="javascript:;" id="deleteButton" url="shop_category_delete.html?id=${item.id }" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="shop_category_FormId" dataUrl="shop_category_list.html" />
	</div>
</div>