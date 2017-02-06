<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div id="audio_modal_form" class="modal" tabindex="-1">
	<input type="hidden" name="id" value="${appResourse[0].id }"/>
    <input type="hidden" name="informationContentId" value="${appResourse[0].informationContentId }"/>
    <input type="hidden" name="infoFileId" value="${appResourse[0].infoFileId}">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加音频</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="name" value="${appResourse[0].name}" placeholder="请输入文件名称"/>
                            <input type="hidden" class="form-control" name="fileUrl" value="${appResourse[0].fileUrl}"/>
                        </div>
                    </div>
					<!-- <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div href="javascript:;" class="col-sm-8" id="content_audio_upload">
                            <i class="icon-cloud-upload"></i>
								上传音频
                        </div>
                    </div> 
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="thelist">
                        </div>
                    </div>  -->
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">输入音频地址</label>
                        <div class="col-sm-1">
                            <input type="checkbox" class="checkbox" name="a_check"/>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12 a_url_div hidden">
                        <label class="col-sm-2 control-label no-padding-right">输入音频地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="a_url" value="${appResourse[0].fileUrl}" placeholder="请输入音频地址"/>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12 a_up_div">
                        <div id="audio_container" class="col-sm-offset-1 col-xs-3" style="position: relative;">
                            <a href="javascript:;" id="audio_pickfiles" class="btn btn-primary">
                                <i class="glyphicon glyphicon-plus"></i>
                                <span>选择文件</span>
                            </a>
                         </div>
                    </div>
                    <div class="col-md-12 a_up_div" id="success" style="display:none">
                        <div class="alert-success">
                                                                队列全部文件处理完毕
                        </div>
                    </div>
                    <div class="col-md-12 a_up_div">
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
	  
	  
	  var _appId = $("select[name=appId]").val();
	  
	  if(!_appId)_appId=appId;
	  
	  cqliving_ajax.ajaxOperate("common/qiniu_by_appid.html","#audio_modal_form",{appId:_appId},function(data,status){
		  
		  if(!data.data){
			  cqliving_dialog.error("没有文件资源");
			  return;
		  }
		  
		 var qiniuDomain = data.data.domainCustom ? data.data.domainCustom : data.data.domainTest;
		  
		   var  qiniuUp =  myQiniu({
			  browseBtn:"audio_pickfiles",
			  tokenUrl:"/common/qiniu/normal_token.html?type=mp3&appId="+_appId,
			  domain:qiniuDomain,
			  container:"audio_container",
			  dropEle:"audio_container",
			  isSingle:true,
			  filters: {
				  mime_types : [
				                { title : "audio files", extensions : "mp3" }
				              ]
			  },
			  success:function(up,file,info){
				  var res = $.parseJSON(info);
				  $("#audio_modal_form :input[name=name]").val(file.name);
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
				    $(":input[name=extensions]").val("mp3");
				  $("#audio_modal_form :input[name=fileUrl],#audio_modal_form :input[name=a_url]").val(url);
				  $(":input[name=videoStatus]").val(2);
			  }
		  });
	  });
	  
	  
	  $("#audio_modal_form button[data-dismiss]").bind("click",function(){
		   
		  $("#audio_modal_form .progressContainer .progressCancel").click();
		  
	  });
	  
	  $("input[name=a_check]").bind("click",function(){
		  var $this = $(this);
           if($this.is(":checked")){
        	   $(".a_url_div").removeClass("hidden");
        	   $(".a_up_div").addClass("hidden");
        	   $("#audio_modal_form .progressContainer .progressCancel").click();
           }else{
        	   $(".a_url_div").addClass("hidden");
        	   $(".a_up_div").removeClass("hidden");
           }		  
	  });
	  
	  /* var audioUploader = chunkedUpload({
		  pickerId:"content_audio_upload",
		  echoId:"thelist",
		  success:function(data,status){
			  $("#audio_modal_form :input[name=name]").val(data.data.fileName);
			  $("#audio_modal_form :input[name=fileUrl]").val(data.data.fileUrl);
		  },
		  error:function(data,status){
			  cqliving_dialog.error(data.message);
		  },
		  beforeFileQueued:function(file){
			  $("#thelist").html("");
		  }
	  }); */
  });
</script>
