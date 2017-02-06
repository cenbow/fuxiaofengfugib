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
                		<th>商品名称</th>
                		<th>销量</th>
                		<th>库存</th>
                		<th>分类</th>
                		<th>上架时间</th>
                		<th>状态</th>
                		<th>是否推荐到首页</th>
                		<th>是否推荐到轮播</th>
                		<th class="sort-td">排序</th>
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
                		<td>${item.name}</td>
                		<td>${item.salesVolume}</td>
                		<td>${item.stock}</td>
                		<td>${item.categoryName}</td>
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd" /></td>
                    	<td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allIsRecommemdIndexs[item.isRecommemdIndex] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allIsRecommendCarousels[item.isRecommendCarousel] }</span>
                        </td>
                        <td class="sort-td">
                        	<input iid="${item.id}" type="text" url="/module/shopgoods/common/update_sort_no.html" class="only_num" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="9" />
                			<input type="hidden" class="only_num_old" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" />
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<cqliving-security2:hasPermission name="/module/shopgoods/shop_goods_add.html">
								<a class="blue" href="javascript:void(0);" url="shop_goods_add.html?id=${item.id }" forwordSaveParam="forwordSaveParam" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            
                            <cqliving-security2:hasPermission name="/module/shopgoods/on_line.html">
						        <c:if test="${STATUS3 ne item.status}">
			                        <a class="blue on-out" href="javascript:;" tip="确认要上线么？" goodsId="${item.id}" url="on_line.html" data-rel="tooltip" data-original-title="上线" data-placement="top">
										<i class="icon-arrow-up bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
					        <cqliving-security2:hasPermission name="/module/shopgoods/out_line.html">
						        <c:if test="${STATUS3 eq item.status}">
			                        <a class="red on-out" href="javascript:;" tip="确认要下线么？" goodsId="${item.id}" url="out_line.html" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
                            <cqliving-security2:hasPermission name="/module/shopgoods/recommemdIndex.html">
						        <c:if test="${ISRECOMMEMDINDEX0 eq item.isRecommemdIndex}">
			                        <%-- <a class="blue recommemd" href="javascript:;" tip="确认要推荐到首页么？" goodsId="${item.id}" url="recommemdIndex.html" data-rel="tooltip" data-original-title="推荐到首页" data-placement="top">
										<i class="icon-share bigger-130"></i>
									</a> --%>
									<a class="blue recommend" href="javascript:void(0);" url="recommemdIndex.html?id=${item.id}&type=${TYPE1}&_model_=_model" open-model="update" open-title="推荐到首页" data-rel="tooltip" data-original-title="推荐到首页" data-placement="top">
										<i class="icon-level-up bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
					        <cqliving-security2:hasPermission name="/module/shopgoods/recommendCarousel.html">
						        <c:if test="${ISRECOMMENDCAROUSEL0 eq item.isRecommendCarousel}">
			                        <%-- <a class="blue recommemd" href="javascript:;" tip="确认要推荐到轮播么？" goodsId="${item.id}" url="recommendCarousel.html" data-rel="tooltip" data-original-title="推荐到轮播" data-placement="top">
										<i class="icon-share bigger-130"></i>
									</a> --%>
									<a class="blue recommend" href="javascript:void(0);" url="recommendCarousel.html?id=${item.id}&type=${TYPE2}&_model_=_model" open-model="update" open-title="推荐到轮播" data-rel="tooltip" data-original-title="推荐到轮播" data-placement="top">
										<i class="icon-level-up bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
                            
							<cqliving-security2:hasPermission name="/module/shopgoods/shop_goods_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="shop_goods_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="shop_goods_FormId" dataUrl="shop_goods_list.html" />
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/shopping/shop_goods_list_page.js'], function(list){
	list.init();
});
</script>
