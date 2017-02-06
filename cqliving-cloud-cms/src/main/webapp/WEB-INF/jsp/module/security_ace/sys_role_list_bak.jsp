<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" role="form" action="/module/security/sys_role_list.html" id="sysRoleListFormId">
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right" for="form-field-1">新闻标题</label>
						<div class="col-sm-2">
							<input type="text" id="form-field-1" placeholder="" class="col-xs-12" />
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="form-field-2">栏目</label>
						<div class="col-sm-2">
							<input type="text" id="form-field-2" placeholder="" class="col-xs-12" />
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="form-field-select-1">状态</label>
						<div class="col-sm-2">
							<select class="form-control" id="form-field-select-1">
								<option value="">全部</option>
								<option value="">已发布</option>
								<option value="">未发布</option>
							</select>
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="form-field-select-2">推送状态</label>
						<div class="col-sm-2">
							<select class="form-control" id="form-field-select-2">
								<option value="">全部</option>
								<option value="">已推送</option>
								<option value="">未推送</option>
							</select>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right" for="form-field-select-3">新闻类型</label>
						<div class="col-sm-2">
							<select class="form-control" id="form-field-select-3">
								<option value="">全部</option>
								<option value="">纯文本</option>
								<option value="">非原创</option>
								<option value="">原创</option>
								<option value="">多图</option>
								<option value="">外链</option>
							</select>
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="form-field-select-4">数据类型</label>
						<div class="col-sm-2">
							<select class="form-control" id="form-field-select-4">
								<option value="">全部</option>
								<option value="">视频</option>
								<option value="">多图</option>
								<option value="">音频</option>
								<option value="">投票</option>
								<option value="">调研</option>
								<option value="">打擂</option>
							</select>
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="form-field-3">创建人</label>
						<div class="col-sm-2">
							<input type="text" id="form-field-3" placeholder="" class="col-xs-12" />
						</div>
						<label class="col-sm-1 control-label no-padding-right" for="id-date-range-picker-1">发布时间</label>
						<div class="col-sm-2">
							<input readonly="readonly" class="form-control" type="text" name="date-range-picker" id="id-date-range-picker-1">
						</div>
					</div>
					<!--
					<div class="clearfix form-actions">
						<div class="col-md-offset-9 col-md-3">
							<button class="btn btn-info btn-sm" type="button">
								<i class="icon-search bigger-110"></i>查询
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn btn-sm" type="reset">
								<i class="icon-undo bigger-110"></i>重置
							</button>
						</div>
					</div>-->
					<div class="well form-actions right">
						<button class="btn btn-info btn-sm" type="button">
							<i class="icon-search bigger-110"></i>查询
						</button>
						&nbsp;
						<button class="btn btn-sm" type="reset">
							<i class="icon-undo bigger-110"></i>重置
						</button>
					</div>
					<div class="well">
						<button class="btn btn-sm">批量发布</button>
						&nbsp;
						<button class="btn btn-sm">批量下线</button>
						&nbsp;
						<button class="btn btn-sm">清空排序</button>
						&nbsp;
						<button class="btn btn-sm">撤稿</button>
						&nbsp;
						<button class="btn btn-sm">加入专题</button>
					</div>
				</form>
			</div>
		</div><!-- /.row -->
		<div class="row">
			<div class="col-xs-12">
				<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
					<!-- <div class="row">
						<div class="col-sm-6">
							<div id="sample-table-2_length" class="dataTables_length">
								<label>Display 
									<select size="1" name="sample-table-2_length" aria-controls="sample-table-2">
										<option value="10" selected="selected">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select> records
								</label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="dataTables_filter" id="sample-table-2_filter">
								<label>Search: <input type="text" aria-controls="sample-table-2"></label>
							</div>
						</div>
					</div> -->
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
									<th>主键ID</th>
									<th>角色名称</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${pageInfo.pageResults}" var="item">
				                    <tr>
				                    	<td class="center" id="${item.id}">
											<label>
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</td>
				                        <td>${item.id}</td>
				                        <td>${item.roleName}</td>
				                        <td>
				                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
				                            	<cqliving-security2:hasPermission name="/module/security/sys_role_view.html">
					                           		<a class="blue" href="sys_role_view.html?id=${item.id}">
														<i class="icon-zoom-in bigger-130"></i>
													</a>
													<button class="btn btn-xs">查看</button>
					                            </cqliving-security2:hasPermission>
												<cqliving-security2:hasPermission name="/module/security/sys_role_view.html">
					                           		<a class="green" href="sys_role_update.html?id=${item.id}">
														<i class="icon-pencil bigger-130"></i>
													</a>
													<button class="btn btn-xs">修改</button>
					                            </cqliving-security2:hasPermission>
	
												<a class="red" href="#">
													<i class="icon-trash bigger-130"></i>
												</a>
												<button class="btn btn-xs">删除</button>
											</div>
				                        </td>
				                    </tr>
				                </c:forEach>
							</tbody>
						</table>
					</div>
					<!-- <div class="row">
						<div class="col-sm-3">
							<div class="dataTables_info" id="sample-table-2_info">Showing
								1 to 10 of 51 entries</div>
						</div>
						<div class="col-sm-3">
							<div id="sample-table-2_length" class="dataTables_length">
								<label>Display 
									<select size="1" name="sample-table-2_length" aria-controls="sample-table-2">
										<option value="10" selected="selected">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select> records
								</label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="dataTables_paginate paging_bootstrap">
								<ul class="pagination">
									<li class="prev disabled"><a href="#"><i class="icon-double-angle-left"></i></a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li class="next"><a href="#"><i class="icon-double-angle-right"></i></a></li>
								</ul>
							</div>
						</div>
					</div> -->
					<%--分页--%>
            		<cqliving-frame:paginationAjax paramFormId="sysRoleListFormId" dataUrl="/module/security/sys_role_list.html" />
            		<%-- <cqliving-frame:pagination/> --%>
				</div>
			</div>
		</div>
	</div><!-- /.col -->
</div><!-- /.row --><!-- /.main-content -->
	<!-- <script type="text/javascript">
		window.jQuery || document.write("<script src='/resource/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
	</script> -->

	<!-- <![endif]-->

	<script src="/resource/assets/js/jquery.dataTables.min.js"></script>
	<script src="/resource/assets/js/jquery.dataTables.bootstrap.js"></script>

	<!-- ace scripts -->

	<!-- inline scripts related to this page -->
	
	<script type="text/javascript">
		/* $(function() {
			var doPaginationAjax = function(paramData,currObj){
				var url = "/module/security/sys_role_list.html";
				var paramFormId = "sysRoleListFormId";
				var paramForm = paramFormId ? $("#" + paramFormId) : $(currObj).parents(".row").find("form");
				if(!url){
					if(paramForm.length > 0){
						url = paramForm.attr("action");
					}else{
						alert("请求url不能为空");
						return;
					}
				}
				var params = paramForm.serializeArray();
				//判断传入是否有值
				if(paramData){	
				    $.each(paramData,function(name,value){		
				        params.push({"name" : name,	"value" : value });	
				    });
				}
				$(".page-content").load(url, params, function(){
				
				});
			}
			
			$(".ajax_pagination .pagination a[pageNo]").on("click",function(){
				var countOfCurrentPage = $(this).parents(".ajax_pagination").find("select").val();
				var paramData = {"pageNo":$(this).attr("pageNo"),"countOfCurrentPage":countOfCurrentPage};
				doPaginationAjax(paramData,$(this));
			});
			
			$(".ajax_pagination select").change(function () {
				var countOfCurrentPage = $(this).parents(".ajax_pagination").find("select").val();
				var paramData = {"countOfCurrentPage":countOfCurrentPage};
				doPaginationAjax(paramData,$(this));
		    });
		}); */
		
		jQuery(function($) {
			/* var oTable1 = $('#sample-table-2').dataTable( {
			"aoColumns": [
		      { "bSortable": false },
		      null, null,null, null, null,
			  { "bSortable": false }
			] } ); */
			
			
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
		
		
			$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('table')
				var off1 = $parent.offset();
				var w1 = $parent.width();
		
				var off2 = $source.offset();
				var w2 = $source.width();
		
				if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
				return 'left';
			}
		})
	</script>