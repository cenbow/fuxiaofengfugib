<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="video_modal_form" class="modal" tabindex="-1">
	<input type="hidden" name="id" value="${appResourse[0].id }"/>
    <input type="hidden" name="informationContentId" value="${appResourse[0].informationContentId }"/>
    <input type="hidden" name="infoFileId" value="${appResourse[0].infoFileId}">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加视频</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				    <div class="form-group col-xs-12">
                        <label class="col-sm-1 control-label no-padding-right">名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="name" value="${appResourse[0].name}" placeholder="请输入文件名称"/>
                            <input type="hidden" class="form-control" name="fileUrl" value="${appResourse[0].fileUrl}"/>
                        </div>
                    </div>
					<!-- <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="content_audio_upload">
                            <i class="icon-cloud-upload"></i>
								上传视频
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="thelist">
                        </div>
                    </div> -->
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">输入视频地址</label>
                        <div class="col-sm-1">
                            <input type="checkbox" class="checkbox" name="v_check"/>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12 v_url_div hidden">
                        <label class="col-sm-2 control-label no-padding-right">输入视频地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="v_url" value="${appResourse[0].fileUrl}" placeholder="请输入音频地址"/>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12 up_v_div">
                        <div id="video_container" class="col-sm-offset-1 col-xs-3" style="position: relative;">
                            <a href="#" id="video_pickfiles" class="btn btn-primary">
                                <i class="glyphicon glyphicon-plus"></i>
                                <span>选择文件</span>
                            </a>
                         </div>
                    </div>
                    <div class="col-md-12 up_v_div" id="success" style="display:none">
                        <div class="alert-success">
                                                                队列全部文件处理完毕
                        </div>
                    </div>
                    <div class="col-md-12 up_v_div">
                        <table style="margin-top:40px;display:none" class="table table-striped table-hover text-left">
                            <thead>
                              <tr>
                                <th class="col-md-3">文件名称</th>
                                <th class="col-md-2">文件大小</th>
                                <th class="col-md-7">详情</th>
                              </tr>
                            </thead>
                            <tbody id="fsUploadProgress">
                            </tbody>
                        </table>
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
  require(['cqliving_ajax','cqliving_dialog','myQiNiu'],function(cqliving_ajax,cqliving_dialog,myQiniu){
	  
	  /* var audioUploader = chunkedUpload({
		  pickerId:"content_audio_upload",
		  echoId:"thelist",
		  success:function(data,status){
			  $("#video_modal_form :input[name=name]").val(data.data.fileName);
			  $("#video_modal_form :input[name=fileUrl]").val(data.data.fileUrl);
		  },
		  error:function(data,status){
			  cqliving_dialog.error(data.message);
		  },
		  beforeFileQueued:function(file){
			  $("#thelist").html("");
		  }
	  }); */
      var _appId = $("select[name=appId]").val();
	  if(!_appId)_appId = appId;
	  cqliving_ajax.ajaxOperate("common/qiniu_by_appid.html","#audio_modal_form",{appId:_appId},function(data,status){
		  
		  
		  if(!data.data){
			  cqliving_dialog.error("没有文件资源");
			  return;
		  }
		  
		 var qiniuDomain = data.data.domainCustom ? data.data.domainCustom : data.data.domainTest;
		  //,flv,avi,wmv,mpeg,mpg,mov,rmvb,mkv
		 var qiniuUp =  myQiniu({
			  browseBtn:"video_pickfiles",
			  tokenUrl:"/common/qiniu/normal_token.html?type=mp4&appId="+_appId,
			  domain:qiniuDomain,
			  container:"video_container",
			  dropEle:"video_container",
			  isSingle:true,
			  filters: {
				  mime_types : [
				                { title : "video files", extensions : "mp4" }
				              ]
			  },
			  success:function(up,file,info){
				  var res = $.parseJSON(info);
				  $("#video_modal_form :input[name=name]").val(file.name);
				  var domain = up.getOption('domain');
				    var url;
				    if (res.url) {
				        url = res.url;
				    } else {
				        url = domain + encodeURI(res.key);
				    }
				    $(":input[name=qiniuPersistentId]").val(res.persistentId);
				    $(":input[name=qiniuHash]").val(res.hash);
				    $(":input[name=qiniuKey]").val(res.key);
				    $(":input[name=qiniuDomain]").val(domain);
				    $(":input[name=originalName]").val(file.name);
				    $(":input[name=extensions]").val("mp4");
				    $("#video_modal_form :input[name=fileUrl],#video_modal_form :input[name=v_url]").val(url);
				    $(":input[name=videoStatus]").val(2);
			  }
		  });
	  });
	  
	  $("#video_modal_form button[data-dismiss]").bind("click",function(){
		   
		  $("#video_modal_form .progressContainer .progressCancel").click();
		  
	  });
	  
	  
	  $("input[name=v_check]").bind("click",function(){
		  var $this = $(this);
           if($this.is(":checked")){
        	   $(".v_url_div").removeClass("hidden");
        	   $(".up_v_div").addClass("hidden");
        	   $("#video_modal_form .progressContainer .progressCancel").click();
           }else{
        	   $(".v_url_div").addClass("hidden");
        	   $(".up_v_div").removeClass("hidden");
           }		  
	  });
	  
  });
</script>