<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!-- 已设置相关的 -->
<div class="page-content row" >
    <form action="/module/info/common/had_correlation_modal.html" id="had_info_corr_form" class="hidden">
       <input type="text" name="search_EQ_id" value="${param.infoClassifyId }">
    </form>
    
    <div id="info_corr">
		<jsp:include page="/module/info/common/had_correlation_modal.html?search_EQ_id=${param.infoClassifyId }"></jsp:include>
    </div>
    
</div>

<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="/module/info/common/info_correlation_modal.html" id="info_correlation_modal_form">
	                    <input type="hidden" name="appId">
	                    <input type="hidden" name="infoClassifyId">
	                    <div class="form-list">
                        	<div class="col-xs-12 col-sm-12">
								<div class="form-group">
									<div class="col-sm-8">
			                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${not empty param.search_LIKE_keywords ? param.search_LIKE_keywords : param.search_LIKE_title}" placeholder="请输入搜索内容" class="col-xs-12" />
	                        		</div>
	                        		
	                        		<div class="col-sm-4">
										<button class="btn btn-primary btn-sm search_btn_info_corr" type="button">
											<i class="icon-search bigger-110"></i>查询
										</button>
									</div>
                   				</div>
							</div>
                    	</div>
	                </form>
				</div>
			</div><!-- /.row -->
			<div class="row" id="info_correlation_load">
		      <jsp:include page="/WEB-INF/jsp/module/infoClassify/info_correlation_modal_page.jsp"></jsp:include>
	       </div>
	       
		</div><!-- /.col -->
	</div><!-- /.row --><!-- /.main-content -->
</div><!-- /.page-content -->

<script type="text/javascript">
   require(['jquery'],function(){
	   $("#info_correlation_modal").on("hidden.bs.modal",function(){
			$("#info_correlation_modal .modal-body").children().remove();
		});
   });
</script>