<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动管理|/module/act/act_infolist.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="act_infolist.html" id="act_infoFormId">
		                    <div class="special-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}" placeholder="标题" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group">
						                            <input name="search_GTE_startTime" type="hidden" value="<c:if test="${not empty search_GTE_startTime}"><fmt:formatDate value="${ search_GTE_startTime}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty search_GTE_startTime && not empty param.search_GTE_startTime }">${ param.search_GTE_startTime}</c:if>">
						                            <input name="search_LT_endTime" type="hidden" value="<c:if test="${not empty search_LT_endTime}"><fmt:formatDate value="${ search_LT_endTime}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty search_LT_endTime && not empty param.search_LT_endTime }">${ param.search_LT_endTime}</c:if>">
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD"}' readonly="true" value="<fmt:formatDate value="${search_GTE_startTime}" pattern="yyyy-MM-dd" /><c:if test="${empty search_GTE_startTime && not empty param.search_GTE_startTime }">${ param.search_GTE_startTime}</c:if><c:if test="${not empty search_GTE_startTime || not empty param.search_GTE_startTime}"> 至 </c:if><fmt:formatDate value="${search_LT_endTime}" pattern="yyyy-MM-dd" /><c:if test="${empty search_LT_endTime && not empty param.search_LT_endTime }">${ param.search_LT_endTime}</c:if>" placeholder="活动开始/结束时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
				                                <option value="">所有状态</option>
			                                	<c:forEach items="${allStatuss}" var="obj" varStatus="idx">
				                                  <c:if test="${obj.key ne STATUS99}">
				                                  	<option value="${obj.key}" <c:if test="${param.search_EQ_status eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
				                                  </c:if>
				                               </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_LIKE_actTypes" id="search_LIKE_actTypes" class="form-control">
				                                <option value="">所有活动类型</option>
			                                	<c:forEach items="${allTypes}" var="obj" varStatus="idx">
				                                  <option value="${obj.key}"<c:if test="${param.search_LIKE_actTypes eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
				                               </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>								
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
			                            	<select name="search_LIKE_showTypes" id="search_LIKE_showTypes" class="form-control">
				                                <option value="">所有前台显示类型</option>
			                                	<c:forEach items="${allShowTypesStr}" var="obj" varStatus="idx">
				                                  <option value="${obj.key}"<c:if test="${param.search_LIKE_showTypes eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
				                               </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>								
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton">
												<i class="icon-search bigger-110"></i>查询
											</button>
											<button class="btn btn-sm" type="button" id="resetButton">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>		
	                    	<div class="col-sm-12">				
								<div class="well">
									<cqliving-security2:hasPermission name="/module/act/act_infoadd.html">
										<button class="btn btn-sm btn-success" type="button" href="javascript:void(0);" url="/module/act/act_infoadd.html" forwordSaveParam="forwordSaveParam" save-form-id="act_infoFormId"><i class="icon-plus"></i>新增</button>
									</cqliving-security2:hasPermission>
									<%-- 上线下线 --%>
							        <cqliving-security2:hasPermission name="/module/act/on_line_batch.html">
							        	<button class="btn btn-sm btn-primary on-out-batch" type="button" title="已发布的数据将自动过滤 " oper="1" url="/module/act/on_line_batch.html"><i class="icon-arrow-up"></i>批量上线</button>
							        </cqliving-security2:hasPermission>
							        <cqliving-security2:hasPermission name="/module/act/out_line_batch.html">
							        	<button class="btn btn-sm btn-danger on-out-batch" type="button" title="保存和已下线的数据将自动过滤 " oper="2" url="/module/act/out_line_batch.html"><i class="icon-arrow-down"></i>批量下线</button>
							        </cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/act/act_infodelete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/act/act_infodelete_batch.html"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/act/clear_sort_no.html">
										<button class="btn btn-sm btn-danger" url="/module/act/clear_sort_no.html" type="button" id="clear_sort_no_btn"><i class="icon-remove"></i>清空排序</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="act_infolist_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
var STATUS1 = ${STATUS1};
var STATUS3 = ${STATUS3} ; 
var STATUS88 = ${STATUS88} ;
require(['/resource/business/act/act_info_list.js'],function(act_info){
	act_info.init();
});
/* require(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	
	$('body').tooltip({selector:'[data-rel=tooltip]'});
	
	$(function(){
	//上下线
	$('body').on('click','.on-out',function(){
		var jThis = $(this);
		var url = jThis.attr("url");
		var tip = jThis.attr('tip');
		cqliving_dialog.confirm('操作确认',tip,function(){
			cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
				if(data.code >= 0){
					cqliving_dialog.success(data.message?data.message:"操作成功",function(){
						$("#searchButton").trigger("click");
					});
				}else{
					cqliving_dialog.error(data.message?data.message:"操作失败");
				}
			});
		});
	});
	
	//批量上下线
	$('body').on('click' ,'.on-out-batch', function(){
		var jCheckedIds = $('input:checkbox[name=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.alert("请选择需要操作的记录");
		}else{
			var ids = [];
			var oper = $(this).attr('oper');
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				var status = $(t).attr('status');
				//上线
				if(id && oper==1){
					if(!(status==STATUS3)){
						ids.push(id);
					}
				}else if(id && oper==2){
					if(!(status == STATUS88 || status == STATUS1)){
						ids.push(id);
					}
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认',oper==1 ? "确定要批量上线吗？":"确定要批量下线吗？",function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"批量操作成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"批量操作失败");
						}
					});
				});
			}else{
				cqliving_dialog.alert("请选择需要操作的记录");
			}
		}
	});
	
	//批量上下线
	$('body').on('click' ,'#clear_sort_no_btn', function(){
		var jCheckedIds = $('.check-box-ace');
		var ids = [];
		var id;
		jCheckedIds.each(function(i,t){
			id = $(t).attr("id");
			if(id){
				ids.push(id);
			}
		});
		if(ids.length>0){
			var jThis = $(this);//操作按钮
			var url = jThis.attr("url");
			//弹出确认对话框
			cqliving_dialog.confirm('操作确认',"确定要清空排序号吗？",function(){
				cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
					if(data.code >= 0){
						$("#searchButton").trigger("click");
					}else{
						cqliving_dialog.error(data.message?data.message:"清空排序号失败");
					}
				});
			});
		}else{
			cqliving_dialog.alert("无需要清空的记录");
		}
	});
	
	//修改排序号
	$('body').on('change','.only_num',function(){
		var jThis = $(this);
		var url = jThis.attr("url");
		var val = $(this).val();
		try{
			var re = /^[1-9][0-9]*$/ ;
	        var result=  re.test(val);
			if(!result){
				cqliving_dialog.error("排序号只能输入正整数");
				$(this).val('');
				return;
			}
		}catch(e){
			cqliving_dialog.error("排序号只能输入正整数");
			$(this).val('');
			return;
		}
		var id = $(this).attr("iid");
		cq_ajax.ajaxOperate(url,jThis,{"id":id,"sortNo":val},function(data,status){
			if(data.code >= 0){
				$("#searchButton").trigger("click");
			}else{
				cqliving_dialog.error(data.message?data.message:"操作失败");
			}
		});
	});
		
	});
}); */
</script>