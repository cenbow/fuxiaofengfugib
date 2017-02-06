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
                		<th width="10%">商品</th>
                		<th width="10%">用户</th>
                		<th width="20%">内容</th>
                		<th>状态</th>
                		<th>创建时间</th>
                		<th>审核时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}" status="${item.status}" goodsId="${item.goodsId}"/>
								<span class="lbl"></span>
							</label>
						</td>
                		<c:if test="${not empty appList}">
                			<td>
		                		<c:forEach items="${appList}" var="app">
		                           <c:if test="${item.appId eq app.id}">${app.name}</c:if></option>
		                        </c:forEach>
                			</td>
                        </c:if>
                		<td  width="10%" style="word-wrap: break-word; word-break: break-all;">${item.shoppingGoodsName}</td>
                		<td width="10%" style="word-wrap: break-word; word-break: break-all;">${item.userName}</td>
                    	<td  width="20%" style="word-wrap: break-word; word-break: break-all;">${item.content}</td>
                    	<td>${allStatuss[item.status]}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                    	<td><fmt:formatDate value="${item.aduitTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/order_evaluate/order_evaluate_view.html">
									<a class="blue" href="javascript:void(0);" url="order_evaluate_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/order_evaluate/order_evaluate_update.html">
									<a class="blue" href="javascript:void(0);" url="order_evaluate_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/order_evaluate/order_evaluate_delete.html">
									<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="order_evaluate_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
										<i class="icon-trash bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <cqliving-security2:hasPermission name="/module/order_evaluate/auditing.html">
									<!--保存态数据才可以审核 -->
									<c:if test="${STATUS1 eq item.status}">
									<a class="blue auditing-one" eid="${item.id}" goodsId="${item.goodsId}" url="/module/order_evaluate/auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
										<i class="icon-edit bigger-130"></i>
									</a>
									</c:if>
	                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="order_evaluate_FormId" dataUrl="order_evaluate_list.html" />
	</div>
</div>