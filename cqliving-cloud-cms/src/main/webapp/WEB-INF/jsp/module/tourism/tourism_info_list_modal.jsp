<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="page-content">
	<div class="row">
		<div class="">
			<!-- PAGE CONTENT BEGINS -->
			<div class="">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="join_special.html" id="tourism_info_modal_form">
				    <input type="hidden" name="specialId" value="${specialId}">
                    <div class="special-list">
                    	
                       	<div class="col-xs-4 col-sm-4">
							<div class="form-group">
								<div class="col-xs-12">
		                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="名称" class="col-xs-12 form-control" />
                        		</div>
               				</div>
						</div>
                       	
                       	<div class="col-xs-4 col-sm-4">
							<div class="form-group">
								<div class="col-xs-12">
	                            	<select name="search_EQ_regionCode" id="search_EQ_regionCode" class="form-control">
		                                <option value="">所有区域</option>
	                            		<c:forEach items="${regionList}" var="obj">
			                                <option value="${obj.code}" appid="${obj.appId}" <c:if test="${param.search_EQ_regionCode eq obj.code}"> selected="selected"</c:if>>${obj.name}</option>
	                            		</c:forEach>
		                            </select>
                        		</div>
               				</div>
						</div>
                   		<div class="col-xs-4 col-sm-4 btn-search">
							<div class="form-group pull-left">
								<div class="col-sm-12">
									<button class="btn btn-primary btn-sm" type="button" id="modal_search_btn">
										<i class="icon-search bigger-110"></i>查询
									</button>
									<button class="btn btn-sm" type="button" id="modal_reset_btn">
										<i class="icon-undo bigger-110"></i>重置
									</button>
								</div>
							</div>
						</div>
                   	</div>
                </form>
			</div><!-- /.row -->
			<div class="row" id="tourism_modal_id">
				<jsp:include page="tourism_info_list_modal_page.jsp"></jsp:include>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row --><!-- /.main-content -->
</div><!-- /.page-content -->

<script type="text/javascript">
	require(["cqliving_ajax","cqliving_dialog"], function(cqliving_ajax,cqliving_dialog) {
		
		$("#modal_search_btn").bind("click",function(){
			
			var paramForm = $(this).closest("form");
			var url = '';
			if(paramForm.length > 0){
				url = paramForm.attr("action");
			}else{
				cqliving_dialog.error("请求url不能为空");
				return;
			}
			var params = paramForm.serializeArray();
			params = params ? params : [];
			//表示请求ajax分页
			var countOfCurrentPage = $(this).closest("#tourism_modal_id").find(".ajax_pagination").find("select").val();
			params.push({"name" : 'p',	"value" : 'y' });
			//查询时把每页多少条数据参数带上
			params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
			cqliving_ajax.load("#tourism_modal_id",url,params,function(){});
		});
		
		$("#modal_reset_btn").on("click",function(){
			var paramForm = $(this).closest("form");
			paramForm.find(":input").not(':button, :submit, :reset')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected');
		});
		
		//全选
		$("#tourism_modal_id").on('click' ,'table th input:checkbox', function(){
			$(this).closest('table').find('tr > td:first-child input:checkbox').prop("checked",$(this).prop("checked"));
		});
		//子项全选后“全选”被选中
		$("#tourism_modal_id").on('click' ,'table td input:checkbox', function(){
			var jTable = $(this).closest('table');
			jTable.find('th input:checkbox').prop("checked",jTable.find('td input:checkbox').not("input:checked").length == 0);
		});
		
		$(".modal.in").on("click","button[bind_name=ok]",function(){
			
			var jCheckedIds = $('#tourism_modal_id tbody input:checkbox[class=ace]:checked');
			if(jCheckedIds.length == 0){
				//如果没有选择商品则提示用户
				cqliving_dialog.error("请选择要加入专题的子景点");
				return;
			}else{
				var ids = [];
				jCheckedIds.each(function(i,t){
					var id = $(t).val();
					if(id){
						ids.push(id);
					}
				});
				if(ids.length>0){
					var jThis = $(this);//操作按钮
					var url = "/module/tourism/common/join_special.html";
					var tourismId = $(":input[name=tourismId]").val();
					cqliving_ajax.ajaxOperate(url,jThis,{"refIds[]":ids,tourismId:tourismId},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"加入专题成功",function(){
								$("button[data-dismiss]").click();
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"加入专题失败");
						}
					});
				}else{
					cqliving_dialog.error("请选择要加入专题的子景点");
				}
			}
		});
	});
</script>