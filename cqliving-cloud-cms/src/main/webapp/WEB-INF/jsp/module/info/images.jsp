<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="images_modal_form" class="modal" tabindex="-1">
    
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加多图</h4>
				<input type="hidden" name="informationContentId" value="${appResourse[0].informationContentId }"/>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="form-group col-xs-12">
                        <div class="" id="content_img_upload">
                            <i class="icon-cloud-upload"></i>
								上传新闻图片
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-xs-1 col-sm-1 col-md-1 control-label"></label>
                        <div class="col-sm-12" id="conent_images_thum">
							<ul class="ace-thumbnails">
                              <c:forEach items="${appResourse}" var="ar">
								<li>
								  <input type="text" name="imginput" style="position:relative" value="${ar.sortNo}"/>
								  <div class="topInput">
								   <a href="${ar.fileUrl}" data-rel="colorbox">
								    <img alt="150x150" src="${ar.fileUrl}" style="height:150px;">
								   </a>
								    <textarea placeholder="请输入图片描述" style="width:150px;">${ar.description}</textarea>
									<div class="tools tools-top">
									    
									   <a href="javascript:;"><i class="icon-remove red"></i></a>
                                       <a href="javascript:;"><i class="icon-copy red"></i></a> 
                                       <a href="javascript:;"><input type="file" name="upload_file_${ar.id }" id="upload_file_${ar.id}"><label>文件上传</label></a>
									</div>
									<input type="hidden" name="id" value="${ar.id}">
							       <input type="hidden" name="fileUrl" value="${ar.fileUrl }">
							       <input type="hidden" name="name" value="${ar.name }">
							       </div>
								</li>
						      </c:forEach>
							</ul>
                        </div>
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

  require(['myUploader','cqliving_ajax','common_colorbox'],function(myUploader,cqliving_ajax,common_colorbox){
	  
	  common_colorbox.init();
	  
	//新闻内容多图上传
		var infoContentOptions = {
				url:"/common/upload.html?imgsize=50",
				containerId:"content_img_upload",
				thumbContainerId:"conent_images_thum",
				success:function(file,response){
					var imgPath = imgUrl+response.data.filePath;
					
					var html = '<input name="fileUrl" type="hidden" value="'+imgPath+'"/>';
					html +='<input type="hidden" name="name" value="'+response.data.fileRealName+'">';
					var $li = $("#images_modal_form .ace-thumbnails li[id='"+file.id+"']").last();
					$li.append(html);
					$li.find("img").attr("src",imgPath);
					
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},
				isSingle:false,text : true,copy:true,
				upload:true,fileUrlPath:imgUrl,sort:true
			};
	
		myUploader.init(infoContentOptions);
  });
</script>

