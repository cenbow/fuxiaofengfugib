<%@page contentType="text/html; charset=utf-8"%>
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="#">Home</a>
			</li>

			<li>
				<a href="#">Tables</a>
			</li>
			<li class="active">Simple &amp; Dynamic</li>
		</ul><!-- .breadcrumb -->

		<div class="nav-search" id="nav-search">
			<form class="form-search">
				<span class="input-icon">
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
					<i class="icon-search nav-search-icon"></i>
				</span>
			</form>
		</div><!-- #nav-search -->
	</div>

	<div class="page-content">
		<jsp:include page="index_main_table.jsp"></jsp:include>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-xs-12">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
							<div class="row">
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
										<label>Search: <input type="text"
											aria-controls="sample-table-2"></label>
									</div>
								</div>
							</div>
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
											<th>Domain</th>
											<th>Price</th>
											<th class="hidden-480">Clicks</th>
	
											<th>
												<i class="icon-time bigger-110 hidden-480"></i>
												Update
											</th>
											<th class="hidden-480">Status</th>
	
											<th></th>
										</tr>
									</thead>
	
									<tbody>
										<tr>
											<td class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</td>
	
											<td>
												<a href="#">app.com</a>
											</td>
											<td>$45</td>
											<td class="hidden-480">3,330</td>
											<td>Feb 12</td>
	
											<td class="hidden-480">
												<span class="label label-sm label-warning">Expiring</span>
											</td>
	
											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
													<a class="blue" href="#">
														<i class="icon-zoom-in bigger-130"></i>
													</a>
	
													<a class="green" href="#">
														<i class="icon-pencil bigger-130"></i>
													</a>
	
													<a class="red" href="#">
														<i class="icon-trash bigger-130"></i>
													</a>
												</div>
	
												<div class="visible-xs visible-sm hidden-md hidden-lg">
													<div class="inline position-relative">
														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
															<i class="icon-caret-down icon-only bigger-120"></i>
														</button>
	
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
															<li>
																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																	<span class="blue">
																		<i class="icon-zoom-in bigger-120"></i>
																	</span>
																</a>
															</li>
	
															<li>
																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																	<span class="green">
																		<i class="icon-edit bigger-120"></i>
																	</span>
																</a>
															</li>
	
															<li>
																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																	<span class="red">
																		<i class="icon-trash bigger-120"></i>
																	</span>
																</a>
															</li>
														</ul>
													</div>
												</div>
											</td>
										</tr>
	
										<tr>
											<td class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</td>
	
											<td>
												<a href="#">base.com</a>
											</td>
											<td>$35</td>
											<td class="hidden-480">2,595</td>
											<td>Feb 18</td>
	
											<td class="hidden-480">
												<span class="label label-sm label-success">Registered</span>
											</td>
	
											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
													<a class="blue" href="#">
														<i class="icon-zoom-in bigger-130"></i>
													</a>
	
													<a class="green" href="#">
														<i class="icon-pencil bigger-130"></i>
													</a>
	
													<a class="red" href="#">
														<i class="icon-trash bigger-130"></i>
													</a>
												</div>
	
												<div class="visible-xs visible-sm hidden-md hidden-lg">
													<div class="inline position-relative">
														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
															<i class="icon-caret-down icon-only bigger-120"></i>
														</button>
	
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
															<li>
																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																	<span class="blue">
																		<i class="icon-zoom-in bigger-120"></i>
																	</span>
																</a>
															</li>
	
															<li>
																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																	<span class="green">
																		<i class="icon-edit bigger-120"></i>
																	</span>
																</a>
															</li>
	
															<li>
																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																	<span class="red">
																		<i class="icon-trash bigger-120"></i>
																	</span>
																</a>
															</li>
														</ul>
													</div>
												</div>
											</td>
										</tr>
	
									</tbody>
								</table>
							</div>
							<div class="row">
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
							</div>
						</div>
					</div>
				</div>

			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.page-content -->
</div><!-- /.main-content -->


		<script type="text/javascript">
			window.jQuery || document.write("<script src='/resource/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<script src="/resource/assets/js/jquery.dataTables.min.js"></script>
		<script src="/resource/assets/js/jquery.dataTables.bootstrap.js"></script>

		<!-- ace scripts -->

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
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
