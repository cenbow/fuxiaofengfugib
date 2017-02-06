<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"></label>
			<div class="col-sm-9">
				<div id="imageUrlDiv" class="col-sm-4">
					<i class="icon-cloud-upload"></i>
					添加图片
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"></label>
			<div class="col-sm-9">
				<div class="col-sm-8" id="imageUrlList">
					<ul class="ace-thumbnails">
						<c:if test="${not empty url }">
							<li class="upload-imgs" style="width:80px;height:80px;">
								<a href="${url }" data-rel="colorbox" class="cboxElement">
									<img alt="80pxx80px" src="${url }" style="width:80px;height:80px;">
								</a>
								<div class="tools tools-top">
									<a href="javascript:;"><i class="icon-remove red"></i></a>
								</div>
								<input type="hidden" name="imageUrl" value="${url }">
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var imageUrl = '${imageUrl }';
require(['/resource/business/topic/topic_image.js'], function(obj){
	obj.init();
});
</script>