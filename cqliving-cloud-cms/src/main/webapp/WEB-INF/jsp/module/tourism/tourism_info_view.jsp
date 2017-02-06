<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- 预览弹层 -->
<div id="info_view_modal" class="modal" tabindex="-1">
	<div class="modal-dialog preview-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<a href="${viewUrl}" target="_blank">链接地址</a>
				<h4 class="light-grey bigger">预览</h4>
			</div>
			<div class="modal-body overflow-visible">
				<iframe src="${viewUrl}" id="iframe1" frameborder="0" scrolling="auto" style="width:100%;height:100%;"></iframe>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					关闭
				</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
   require(['bootstrap'],function(){
	   $("#info_view_modal .preview-dialog .modal-body").css("overflow","visible");
	   $("#info_view_modal").modal("show");
	   $("#info_view_modal").on("hidden.bs.modal",function(){
		   $("#info_view_modal").remove();
	   });
   });
</script>