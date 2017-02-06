<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
			 <c:forEach items="${pageInfo.pageResults}" var="item">
				<div class="widget-box">
					<div class="widget-header">
						<h5>模板名称：${item.name } ------模板状态：${allStatuss[item.status]}------ 最后修改时间： <fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </h5>
						<div class="widget-toolbar">
						   <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							  <cqliving-security2:hasPermission name="/module/shopfare/shopfare_add.html">
								<a class="blue" forwordSaveParam="forwordSaveParam" href="javascript:;" url="shopfare_add.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
			                  </cqliving-security2:hasPermission>
							  <cqliving-security2:hasPermission name="/module/shopfare/shopfare_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="shopfare_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
			                  </cqliving-security2:hasPermission>
			                  <cqliving-security2:hasPermission name="/module/shopfare/shopfare_copy.html">
								<a class="blue" data-toggle="modal" forwordSaveParam="forwordSaveParam" href="javascript:;" url="shopfare_copy.html?id=${item.id }" data-rel="tooltip" data-original-title="复制" data-placement="top">
									<i class="icon-copy bigger-130"></i>
								</a>
							  </cqliving-security2:hasPermission>
							  <c:choose>
							    <c:when test="${88 eq item.status or 1 eq item.status}">
							      <cqliving-security2:hasPermission name="/module/shopfare/shopfare_online.html">
									<a class="blue online_btn" data-toggle="modal" href="javascript:void(0);" url="/module/shopfare/shopfare_online.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
										<i class="icon-mail-forward bigger-130"></i>
									</a>
								  </cqliving-security2:hasPermission>
							    </c:when>
							    <c:when test="${3 eq item.status}">
							        <cqliving-security2:hasPermission name="/module/shopfare/shopfare_offline.html">
					                    <a class="red offline_btn" href="javascript:void(0);" data-rel="tooltip" url="/module/shopfare/shopfare_offline.html?id=${item.id}" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
									  </cqliving-security2:hasPermission>
							    </c:when>
							  </c:choose>
								 <a href="javascript:;" data-action="collapse">
									<i class="icon-chevron-up"></i>
								</a>
							</div>
						</div>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<table class="table table-striped table-bordered table-hover">
								<thead class="thin-border-bottom">
									<tr>
				                		<th>运送到</th>
				                		<th>是否默认</th>
				                		<th>首件(件)</th>
				                		<th>运费</th>
				                		<th>续件(件)</th>
				                		<th>运费</th>
			                        </tr>
								</thead>
								<tbody>
									<c:forEach items="${item.tempDetails}" var="detail">
				                    <tr>
				                    	<td>${detail.regionNames}</td>
				                        <td>
				                        	<span class="label label-info">${allIsDefaults[detail.isDefault] }</span>
				                        </td>
				                		<td>${detail.baseFareCount}</td>
				                		<td>${detail.baseFare}</td>
				                		<td>${detail.addFareCount}</td>
				                		<td>${detail.addFare}</td>
				                    </tr>
				                </c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
		       </c:forEach>
			</div>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="shopfare_FormId" dataUrl="shopfare_list.html" />
	</div>
</div>

<script type="text/javascript">
   require(['cqliving_ajax','cqliving_dialog'],function(cqliving_ajax,cqliving_dialog){
	   
	   //上线和下线
	   $("#sample-table-2_wrapper .online_btn,#sample-table-2_wrapper .offline_btn").bind("click",function(){
		   var $this = $(this);
		   var url = $this.attr("url");
		   var pageNo = $this.closest(".ajax_pagination").find("ul.pagination li.active a").text();
		   cqliving_ajax.ajaxOperate(url,null,{},function(){
			    url = "shopfare_list.html";
				var paramForm = $("#shopfare_FormId");
				var params = [];
				if(paramForm.length > 0){
					params = paramForm.serializeArray();
				}
				//表示请求ajax分页
				var countOfCurrentPage = $this.closest(".ajax_pagination").find("select").val();
				params.push({"countOfCurrentPage":countOfCurrentPage,"pageNo":pageNo,"name" : 'p',"value" : 'y'});
				cqliving_ajax.load(("#table_content_page"),url,params,function(){});
		   });
	   });
   });
</script>

