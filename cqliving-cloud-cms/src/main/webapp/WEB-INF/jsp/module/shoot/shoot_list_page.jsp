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
                		<th>id</th>
                		<th>作品标题</th>
                		<th>类型</th>
                		<th>学校名称</th>
                		<th>状态</th>
                		<th>手机号</th>
                		<th>上传时间</th>
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
                    	<%-- <td><a data-target="#modal_2" data-toggle="modal" data-remote="true" href="/module/shoot/shoot_view.html?id=${item.id}">${item.title}</a></td> --%>
                    	<td><a aaaa="#modal_2" url="/module/shoot/shoot_update.html?id=${item.id}" href="javascript:void(0)">${item.title}</a></td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                    	<td>${item.schoolName}</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                    	<td>${item.phone}</td>
                    	<td><fmt:formatDate value="${item.uploadTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/shoot/shoot_view.html">
								<a class="blue" data-toggle="modal" href="shoot_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/shoot/shoot_update.html">
								<a class="blue" data-toggle="modal" href="shoot_update.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/shoot/shoot_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="shoot_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
	                            <%--
	                            <a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="锁定/设置密码" data-placement="top">
									<i class="icon-lock bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="复制" data-placement="top">
									<i class="icon-copy bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="用户群/群组" data-placement="top">
									<i class="icon-group bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="用户/账户/账号" data-placement="top">
									<i class="icon-user bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="新增/添加" data-placement="top">
									<i class="icon-plus bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="相关" data-placement="top">
									<i class="icon-link bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="专题" data-placement="top">
									<i class="icon-tag bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="发布" data-placement="top">
									<i class="icon-mail-forward bigger-130"></i>
								</a>
	                            <a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
									<i class="icon-edit bigger-130"></i>
								</a>
								<a class="blue" data-toggle="modal" href="javascript:;" data-rel="tooltip" data-original-title="回复" data-placement="top">
									<i class="icon-comment-alt bigger-130"></i>
								</a>
	                            <a class="red" href="javascript:void(0);" data-rel="tooltip" data-original-title="下线" data-placement="top">
									<i class="icon-arrow-down bigger-130"></i>
								</a>
								<a class="red" href="javascript:void(0);" data-rel="tooltip" data-original-title="禁用/忽略" data-placement="top">
									<i class="icon-ban-circle bigger-130"></i>
								</a>
								--%>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
		
		<!-- 模态框（Modal） -->


		<!-- <div id="modal_2" class="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">适合少量内容</h4>
					</div>
					<div class="modal-body overflow-visible">
						<div class="">
							<div class="row">
								<div class="col-xs-12">
									PAGE CONTENT BEGINS
									<form class="form-horizontal form" method="post" id="" cansumbit="yes">
										<div class="col-md-12">
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">只读输入框</label>
												<div class="col-sm-9">
													<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="只读的输入框">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="columnsId">带按钮的选框<i class="text-danger">*</i></label>
												<div class="col-sm-9">
													<div class="input-group">
														<input class="col-xs-12 form-control" type="text" placeholder="请点击选择">
														<span class="input-group-btn">
															<button class="btn btn-sm btn-primary" type="button">
																操作
																<span class="caret"></span>
															</button>
														</span>
													</div>
													<div class="red alert_appcolumns"></div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right">下拉<i class="text-danger">*</i></label>
												<div class="col-sm-9">
													<select name="" class="form-control select-bigger">
														<option value="">下拉框</option>
														<option value="-1">审核不通过</option>
														<option value="0">草稿</option>
														<option value="1">保存</option>
														<option value="3">正常上线</option>
														<option value="88">下线</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="plViewType">多项选择<i class="text-danger">*</i></label>
												<div class="col-sm-9 checkbox">
													<label class="checkbox-2">
														<input name="form-field-checkbox" type="checkbox" class="ace">
														<span class="lbl">选项一</span>
													</label>
													<label class="checkbox-2">
														<input name="form-field-checkbox" type="checkbox" class="ace">
														<span class="lbl">选项二</span>
													</label>
													<label class="checkbox-2">
														<input name="form-field-checkbox" type="checkbox" class="ace">
														<span class="lbl">选项三</span>
													</label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="plViewType">单项选择<i class="text-danger">*</i></label>
												<div class="col-sm-9 radio">
													<label class="radio-2">
														<input class="ace" type="radio" name="classfieViewStatus" value="0" id=""/>
														<span class="lbl">显示</span>
													</label>
													<label class="radio-2">
														<input class="ace" type="radio" name="classfieViewStatus" value="1" id=""/>
														<span class="lbl">不显示</span>
													</label>
												</div>
											</div>
											<div class="form-group has-error">
												<label class="col-sm-3 control-label no-padding-right" for="title">错误输入框<i class="text-danger">*</i></label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="请输入文字"  value="">
													<small class="help-block">输入信息有误</small>
												</div>
											</div>
										</div>
									</form>
								</div>/.col
							</div>/.row --><!-- /.main-content
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-sm btn-danger" data-dismiss="modal">
							<i class="icon-remove"></i>取消
						</button>
					</div>
				</div>
			</div>
		</div> -->
		<script type="text/javascript">
		require(['bootstrap'], function($){
			/* $("#modal_2").modal({  
			    remote: "/module/shoot/shoot_view.html?id=210"  
			});  */
			
			/* $("#modal_2 .modal-body").load('/module/shoot/shoot_view.html?id=210',function(){  
			    $("#modal_2").modal("show");  
			}) */
		});
		
		</script>
     	<cqliving-frame:paginationAjax paramFormId="shoot_FormId" dataUrl="shoot_list.html" />
	</div>
</div>