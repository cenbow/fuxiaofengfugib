<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="blue bigger">加入专题</h4>
		</div>
		<div class="modal-body overflow-visible">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<form class="form-horizontal" role="form" action="/module/info/common/info_classify_modal.html" id="info_classify_modal_form">
				                    <input type="hidden" name="appId" value="${param.appId }"/>
				                    <div class="form-list">
			                        	<div class="col-xs-12 col-sm-12">
											<div class="form-group">
												<div class="col-sm-8">
						                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}" placeholder="专题标题或主题" class="col-xs-12" />
				                        		</div>
				                        		
				                        		<div class="col-sm-4">
													<button class="btn btn-primary btn-sm search_btn_join_special" type="button">
														<i class="icon-search bigger-110"></i>查询
													</button>
												</div>
			                   				</div>
										</div>
			                    	</div>
				                </form>
							</div>
						</div><!-- /.row -->
						<div class="row" id="info_classify_load">
					      <jsp:include page="/WEB-INF/jsp/module/infoClassify/info_classify_modal_page.jsp"></jsp:include>
				       </div>
				       
				       <div class="page-content info_theme_div">
				           <!-- 专题分类 -->
				       </div>
				       
					</div><!-- /.col -->
				</div><!-- /.row --><!-- /.main-content -->
			</div><!-- /.page-content -->
          </div>
		<div class="modal-footer">
			<button class="btn btn-sm btn-primary save_info_corre">
				<i class="icon-ok"></i>确认
			</button>
			<button class="btn btn-sm btn-danger" data-dismiss="modal">
				<i class="icon-remove"></i>取消
			</button>
		</div>
	</div>
</div>

<script type="text/javascript">
   require(['/resource/business/infoClassify/join_special_info.js?v=v1'],function(){
	   
	   $("#info_classify_modal").on("hidden.bs.modal",function(){
			$("#info_classify_modal").remove();
			$("#info_correlation_modal").before('<div id="info_classify_modal" class="modal" tabindex="-1"></div>');
		});
	   
   });
</script>