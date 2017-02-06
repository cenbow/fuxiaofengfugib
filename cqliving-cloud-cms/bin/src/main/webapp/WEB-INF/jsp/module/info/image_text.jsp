<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="image_text_modal_form" class="modal" tabindex="-1">
	<input type="hidden" name="id" value="${appResourse[0].id }"/>
    <input type="hidden" name="informationContentId" value="${appResourse[0].informationContentId }"/>
	<div class="modal-dialog" style="width:80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加图文</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
					    <textarea class="hidden" name="description">${appResourse[0].description}</textarea>
                        <script id="image_text_news_editor" type="text/plain">${appResourse[0].description}</script>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>

				<button class="btn btn-sm btn-success">
					<i class="icon-ok"></i>
					保存
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->


<script type="text/javascript">
  require(['cqliving_ajax','cqliving_dialog','ueditor'],function(cqliving_ajax,cqliving_dialog){
	  
	  var ue = UE.getEditor("image_text_news_editor",{'zIndex':1200});
	  
	  ue.ready(function() {
		    ue.setHeight(350);
		});
	  
	  ue.addListener('contentChange',function(){
			var content = ue.getContent();
			//var contentTx = ue.getContentTxt();
			$("#image_text_modal_form textarea[name=description]").val(content);
			//$("input[name=contentText]").val(contentTx);
		});
	  
	  
	  $("#image_text_modal_form").on("hide.bs.modal",function(){
		  ue.destroy();
	  });
	 
  });
</script>
