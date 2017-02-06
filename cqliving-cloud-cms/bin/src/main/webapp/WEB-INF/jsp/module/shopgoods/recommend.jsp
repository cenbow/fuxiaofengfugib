<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品管理|/module/shop/shop_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="推荐到首页" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/shopgoods/${type eq TYPE1 ? 'recommemdIndex' : 'recommendCarousel'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		            	<input type="hidden" name="shoppingGoodsId" value="${item.shoppingGoodsId}"/>
		            	<input type="hidden" name="type" value="${type}"/>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><i class="text-danger">*</i>&nbsp;推荐图</label>
                            <div class="col-sm-9">
                            	<input type="hidden" id="imageUrl" name="imageUrl" value="${item.imageUrl}">
                            	<div class="col-sm-10">
		                   			<div id="img_upload" title="点击上传">
			                   			<i class="icon-cloud-upload"></i>
			                        </div>
			                        <div id="loadingView">
			                             <ul class="ace-thumbnails">
			                             <c:if test="${not empty item.imageUrl}">
			                                 <li>
			                                 	<a href="${item.imageUrl}" data-rel="colorbox">
											 		<img alt="150x150" style="width:150px;height:150px;" src="${item.imageUrl}">
											 	</a>
												<div class="tools tools-top">
													<a href="javascript:;">
														<i class="icon-remove red"></i>
														</a>
													</div>
												</li>
												</c:if>
			                             </ul>
			                        </div>
		                        </div>
	                        </div>
	                    </div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>
<script type="text/javascript">
var type = '${type}';
var TYPE1 = '${TYPE1}';
var TYPE2 = '${TYPE2}';
var imgUrl = '${imageUrl}';
var imgThumb = "${_imgConfig_[15]}";
var imgSize = "${_imgConfigSize_[15].thumb[0]}";
if(type==TYPE2){
	imgThumb = "${_imgConfig_[16]}";
	imgSize = "${_imgConfigSize_[16].thumb[0]}";
}
require(['/resource/business/shop/recommend.js'], function(recommend){
	recommend.init();
});
</script>