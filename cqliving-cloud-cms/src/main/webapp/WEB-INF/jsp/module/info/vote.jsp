<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="vote_modal_form" class="modal" tabindex="-1">
   
    <input type="hidden" name="id" value="${surveyInfoDto.id }"/>
    <input type="hidden" name="informationContentId" value="${surveyInfoDto.informationContentId }"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加投票</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
                        <div class="table-responsive">
							<table class="table table-striped table-bordered table-hover dataTable">
								<thead>
				                    <tr>
				                		<th>配置规则</th>
				                    </tr>
				                </thead>
								<tbody>
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 radio">
					                            <label>投票频次：</label>
						                            <label class="radio-2">
						                                <input type="radio" class="ace" name="limitRateType" value="1" id="limitRateType1">
						                                <span class="lbl">限制</span>
						                                <input type="text" class="input-sm" style="width: 50px;" name="limitRateTimes1" value="${surveyInfoDto.limitRateType eq 1 ? surveyInfoDto.limitRateTimes : ''}"/>
						                                <span class="lbl">次</span>
						                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="2" id="limitRateType2">
					                                <span class="lbl">每天限制</span>
					                                <input type="text" class="input-sm" style="width: 50px;" name="limitRateTimes2" value="${surveyInfoDto.limitRateType eq 2 ? surveyInfoDto.limitRateTimes : ''}"/>
					                                <span class="lbl">次</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="0" id="limitRateType0"><span class="lbl">无限制</span>
					                            </label>
					                        </div>
					                        <script type="text/javascript">
					                        document.getElementById("limitRateType${empty surveyInfoDto.limitRateType ? 1 : surveyInfoDto.limitRateType}").checked=true;
					                        </script>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 radio">
						                        <label>单项限制：</label>                        
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="1" id="limitSingleType1">
					                                <span class="lbl">限制</span>
					                                <input type="text" class="input-sm" style="width: 50px;" name="limitSingleTimes1" value="${surveyInfoDto.limitSingleType eq 1 ? surveyInfoDto.limitSingleTimes : ''}"/>
					                                <span class="lbl">次</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="2" id="limitSingleType2">
					                                <span class="lbl">每天限制</span>
					                                <input type="text" class="input-sm" style="width: 50px;" name="limitSingleTimes2" value="${surveyInfoDto.limitSingleType eq 2 ? surveyInfoDto.limitSingleTimes : ''}"/>
					                                <span class="lbl">次</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="0" id="limitSingleType0"><span class="lbl">无限制</span>
					                            </label>
					                        </div>
					                        
					                        <script type="text/javascript">
					                        document.getElementById("limitSingleType${empty surveyInfoDto.limitSingleType ? 1 : surveyInfoDto.limitSingleType}").checked=true;
					                        </script>
					                        
				                        </td>
				                    </tr>
				                    
				                    <tr class="hidden">
				                        <td>
				                            <div class="col-sm-12 radio">
						                        <label>投票规则：</label>                             
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRuleType" value="1" id="limitRuleType1" checked><span class="lbl">单选</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRuleType" value="2" id="limitRuleType2"><span class="lbl">多选</span>
					                            </label>
					                            <label class="radio-2">
					                                <span>限制项数：</span>
					                                <input type="text" class="input-sm" style="width: 50px;" name="limitRuleTimes" value="${surveyInfoDto.limitRuleTimes le -1 ? '' : surveyInfoDto.limitRuleTimes}"/>项(不填为不限制)
					                            </label>
					                        </div>
					                        
					                        <!-- <script type="text/javascript">
					                          document.getElementById("limitRuleType${empty surveyInfoDto.limitRuleType ? 1 : surveyInfoDto.limitRuleType}").checked=true;
					                        </script> -->
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 checkbox">
						                        <label>投票控制：</label>                              
					                            <label>
					                                <input type="checkbox" class="ace ace-checkbox-2" name="loggedStatus" value="0" id="loggedStatus"  ${surveyInfoDto.loggedStatus eq 0 ? 'checked' : ''}><span class="lbl">允许匿名投票</span>
					                            </label>
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 checkbox">
					                            <label>投票类型：</label>                         
					                            <label>
					                                <input type="checkbox" class="ace ace-checkbox-2" name="type" value="6" id="voteType6"  ${surveyInfoDto.surveyQuestionDtos[0].type eq 6 ? 'checked' : '' }><span class="lbl">图片投票</span>
					                            </label>
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12">
						                        <label>投票主题：</label>                          
					                            <input type="text" class="ace form-control limited" name="title" value="${surveyInfoDto.surveyQuestionDtos[0].question}">
					                        </div>
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>
				                            <div class="col-sm-12">
					                           <label> 投票项：</label>                          
							                   <a class="btn blue btn-primary" href="#modal-form-add-item" role="button" data-toggle="modal">
													<i class="icon-plus align-top"></i>
													增加投票项
												</a>
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                       <td>
				                          <div class="col-xs-12 col-sm-12 widget-span add_item_div">
												<div class="widget-box collapsed">
													<div class="widget-header">
														<h6>编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															投票项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初始投票数</h6>
														<div class="widget-toolbar"></div>
													</div>
												</div>
												
												<c:forEach items="${surveyInfoDto.surveyQuestionDtos}" var="ques">
												   <c:forEach items="${ques.surveyQuestionAnswers}" var="ans">
												      <div class="widget-box collapsed">
													    <div class="widget-header">
														    <h6>${ans.number}&nbsp;&nbsp;&nbsp;${ans.answer }&nbsp;&nbsp;&nbsp;${ans.initValue}票</h6>
														    <div class="widget-toolbar">
														        <a class="btn-sm edit_vote_item" href="javascript:;">编辑</a>
															    <a uuid="${ans.id }" href="javascript:;">
															    <i class="icon-remove"></i>删除</a>
														    </div>
													    </div>
													    
													    <input type="hidden" name="imageUrl" value="${ans.imageUrl }"/>
													    <input type="hidden" name="id" value="${ans.id }"/>
													    <input type="hidden" name="answer" value="${ans.answer}"/>
													    <input type="hidden" name="type" value="${ans.type }"/>
													    <input type="hidden" name="inputLimit" value="${ans.inputLimit }"/>
													    <input type="hidden" name="sortNo" value="${ans.sortNo }"/>
													    <input type="hidden" name="number" value="${ans.number }"/>
													    <input type="hidden" name="initValue" value="${ans.initValue }"/>
													    <input type="hidden" name="actValue" value="${ans.actValue }"/>
													    <input type="hidden" name="createTime" value="<fmt:formatDate value='${ans.createTime }' pattern='yyyy-HH-dd HH:mm:ss'/>"/>
													    
												     </div>
												   </c:forEach>
												</c:forEach>
											</div>
				                       </td>
				                    </tr>
				                </tbody>
							</table>
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

<div id="modal-form-add-item" class="modal" tabindex="-1" data-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加投票项</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				  <form id="vote_item_form">
				       
				    <input type="hidden" name="uuid"/>
				    <input type="hidden" name="imageUrl"/>
				    <input type="hidden" name="id" />
				    <input type="hidden" name="type" />
				    <input type="hidden" name="inputLimit" />
				    <input type="hidden" name="sortNo" />
				    <input type="hidden" name="actValue" />
				    <input type="hidden" name="createTime" />
				    
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="initCount">编号</label>
	                        <div class="col-sm-8">
	                            <input class="form-control" name="number" id="number" type="text" maxlength="10" placeholder="请输入投票项编号">
	                        </div>
	                    </div>
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="answer">投票项</label>
	                        <div class="col-sm-8">
	                            <input class="form-control" name="answer" id="answer" type="text"  maxlength="18" placeholder="请输入投票内容">
	                        </div>
						</div>
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="initValue">初始量</label>
	                        <div class="col-sm-7">
	                            <input class="form-control" name="initValue" id="initValue" type="text"  maxlength="10" placeholder="请输入初始投票量">
	                        </div>
	                        <span>票</span>
						</div>
						
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="vote_img_container">上传图片</label>
	                        <div class="col-sm-8" id="vote_img_container">
	                            <input type="hidden" class="form-control" name="vote_img_url" value="">
	                            <i class="icon-cloud-upload"></i>
									上传图片
	                        </div>
						</div>
						
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="vote_img_thum"></label>
	                        <div class="col-sm-8" id="vote_img_thum">
	                           <ul class="ace-thumbnails">
	                           </ul>
	                        </div>
						</div>
					</div>
				  </form>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
				<button class="btn btn-sm btn-success">
					<i class="icon-ok"></i>
					确定
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

<script type="text/javascript">
  
  
  var vote_answers = [];
  
  require(["jquery",'cqliving_ajax','cqliving_dialog','myUploader'],function($,cqliving_ajax,cqliving_dialog,myUploader){
	  
	  var vote_answer_data = [];
	  
	  $("#vote_modal_form .widget-box").each(function(i,n){
		  var $id = $(n).find(":input[name=id]");
		  var $answer  = $(n).find(":input[name=answer]");
		  var $type  = $(n).find(":input[name=type]");
		  var $inputLimit  = $(n).find(":input[name=inputLimit]");
		  var $sortNo  = $(n).find(":input[name=sortNo]");
		  var $number  = $(n).find(":input[name=number]");
		  var $initValue  = $(n).find(":input[name=initValue]");
		  var $createTime  = $(n).find(":input[name=createTime]");
		  var $actValue = $(n).find(":input[name=actValue]");
		  var $imgUrl = $(n).find(":input[name=imageUrl]");
		  
		  
		  if($id.length>=1){
			  
			  var data = {
					  uuid: cqliving_ajax.uuid(10,32),
					  survey_question_answer:{
						  id:$id.val(),
						  number:$number.val(),
						  initValue:$initValue.val(),
						  answer:$answer.val(),
						  inputLimit:$inputLimit.val(),
						  createTime:$createTime.val(),
						  type:$type.val(),
						  sortNo:$sortNo.val(),
						  actValue:$actValue.val() ? $actValue.val() : 0,
						  imageUrl:$imgUrl.val()
					  }
			  };
			 
			  vote_answer_data.push(data);
			  vote_answers.push(data.survey_question_answer);
			  $(n).find("a[uuid]").attr("uuid",data.uuid);
			  
		  }
		  
	  });
	  
	  $("#modal-form-add-item .icon-ok").parent().bind("click",function(){
		  
		  var num = $("#vote_item_form :input[name=number]").val();
		  var answerContent = $("#vote_item_form :input[name=answer]").val();
		  var initnum = $("#vote_item_form :input[name=initValue]").val();
		  
		  var createTime = $("#vote_item_form :input[name=createTime]").val();
		  var actValue = $("#vote_item_form :input[name=actValue]").val();
		  var id = $("#vote_item_form :input[name=id]").val();
		  
		  var sortNo = $("#vote_item_form :input[name=sortNo]").val();
		  
		  var uuid = $("#vote_item_form :input[name=uuid]").val();
		  
		  
		  num = parseInt(num,10);
		  initnum = parseInt(initnum,10);
		  if((!num && num != 0) || (!initnum && initnum != 0)){
			  cqliving_dialog.error("请输入数字");
			  return;
		  }
		  
		  if(!answerContent){
			  cqliving_dialog.error("请输入投票项");
			  return;
		  }
		  
		  var $img = $("#vote_img_thum ul li img");
		  
		  if($("#voteType6").is(":checked") && $img.length !=1){
			  cqliving_dialog.error("请上传图片");
			  return;
		  }
		  
		  var answer = {
				  id:id,
				  number:num,
				  initValue:initnum,
				  answer:answerContent,
				  inputLimit:-1,
				  createTime:createTime ? createTime : moment().format('YYYY-MM-DD HH:mm:ss'),
				  type:0,
				  sortNo:sortNo ? sortNo : 1,
				  imageUrl:$img.attr("src"),
				  actValue:actValue ? actValue : 0 
		  };
		  
		  if(uuid){//是编辑
			  //删除元素
			  var html = getHtml(uuid,answer);
			  html = $(html).html();
		      $("#vote_modal_form .add_item_div a[uuid="+uuid+"]").closest(".widget-box").html(html);
			  updateAnswer(uuid,answer);
		  
		  }else{
			  var sort = $("#vote_modal_form .add_item_div .widget-box").length;
			  if(!sort)sort = 0;
			  answer.sortNo = sort+1;
			  
			  uuid = cqliving_ajax.uuid(10,32);
			  var data = {
					  uuid:uuid,
					  survey_question_answer : answer
			  };
			  vote_answer_data.push(data);
			  vote_answers.push(data.survey_question_answer);
			  $(".add_item_div").append(getHtml(uuid,answer));
		  }
		  
		  resetItemForm();
	  });
	  
	  //删除操作
      $("#vote_modal_form .add_item_div").on("click","a[uuid]",function(){
    	  
    	  if(vote_answer_data.length<=1){
    		  cqliving_dialog.error("只有一个选项,不能删除");
    		  return;
    	  }
		  var uuid = $(this).attr("uuid");
		  var answerId = $(this).closest(".widget-box").find(":input[name=id]").val();
          cqliving_dialog.confirm("警告","确认要删除答案吗？删除后不能恢复",function(){
        	  deleteItem(uuid);
    		  if(answerId){
    			  delVote({answerId:answerId});
    		  }
		  });
	  });
	  
	  function delVote(params){
		  var url = "common/del_survey_answer.html";
		  cqliving_ajax.ajaxOperate(url,"",params,function(data,status){
		  });
	  }
	  
	  function getHtml(uuid,answer){
		  
		  var content = answer.answer;
		  if(content.length>=31)content = content.substring(0,30);
		  
		  var html = '<div class="widget-box collapsed">';
		      html +='<div class="widget-header"><h6>';
		      html += answer.number+'&nbsp;&nbsp;&nbsp;'+content+'&nbsp;&nbsp;&nbsp;'+answer.initValue+'票';
			  html +='</h6><div class="widget-toolbar">';
			  html +='<input type="hidden" name="id" value="'+answer.id+'"/>';
			  html +='<a class="btn-sm edit_vote_item" href="javascript:;">编辑</a>';
			  html += '<a href="javascript:;" uuid="'+uuid+'"><i class="icon-remove"></i>删除</a>';
			  html +='</div></div></div>';
		  return html;
	  }
	  
	  var answerUploaderOptions = {
				url:"/common/upload.html",
				containerId:"vote_img_container",
				thumbContainerId:"vote_img_thum",
				success:function(file,response){
					var fileUrl = imgUrl+response.data.filePath;
					//将图片的引用修改为上传后的图片路径
					var $img = $("#vote_img_thum ul li[id="+file.id+"] img").last();
					if($img.length<=0)$img = $("#vote_img_thum ul li img").last();
					$img.attr("src",fileUrl);
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},isSingle:true
			};
	 
	  var uploader = "";
	  $("#modal-form-add-item").on("show.bs.modal",function(){
		  
		  if(!uploader && $("#voteType6").is(":checked"))
		    uploader =  myUploader.init(answerUploaderOptions);
	  });
	 
	  
	  $("#voteType6").bind("click",function(){
		  var $this = $(this);
		  if($this.is(":checked")){
			  $("#vote_img_container,#vote_img_thum").closest(".form-group").show();
		  }else{
      		  $("#vote_img_container,#vote_img_thum").closest(".form-group").hide();
		  }
	  });
	  
	  if(!$("#voteType6").is(":checked")){
		  $("#vote_img_container,#vote_img_thum").closest(".form-group").hide();
	  }
	  
	  //投票的编辑
	  $("#vote_modal_form").on("click",".edit_vote_item",function(){
		  var $this = $(this);
		  var uuid = $this.next().attr("uuid");
		  var item = getVoteItem(uuid);
		  
		  var answer =  item.survey_question_answer;
		  
		  setVoteItemAnswer(answer,uuid);
		  
		  $("#modal-form-add-item").modal("show");
		  
	  });
	  
	  
	  function resetItemForm(){
		  
		  $("#vote_img_thum ul li").remove();
		  $("#vote_item_form").get(0).reset();
		  
		  $("#vote_item_form :input[name=uuid]").val("");
		  $("#vote_item_form :input[name=number]").val("");
		  $("#vote_item_form :input[name=answer]").val("");
		  $("#vote_item_form :input[name=initValue]").val("");
		  $("#vote_item_form :input[name=imageUrl]").val("");
		  $("#vote_item_form :input[name=id]").val("");
		  $("#vote_item_form :input[name=type]").val("");
		  $("#vote_item_form :input[name=sortNo]").val("");
		  $("#vote_item_form :input[name=actValue]").val("");
		  $("#vote_item_form :input[name=createTime]").val("");
		  
		  $("#modal-form-add-item").modal("hide");
		  
	  }
	  
	  function updateAnswer(uuid,newAnswer){
		  var newAnsData = {
		        uuid : uuid,
		        survey_question_answer : newAnswer
		  };
		  for(var i=0;i<vote_answer_data.length;i++){
			  var data  = vote_answer_data[i];
			  if(data.uuid == uuid){
				  vote_answer_data.splice(i,1,newAnsData);
				  vote_answers[i] = newAnswer;
				  break;
			  }
		  }
	  }
	  
	  
	  function deleteItem(uuid){
		  
		  for(var i=0;i<vote_answer_data.length;i++){
			  var data  = vote_answer_data[i];
			  
			  var submitAnswer = vote_answers[i];
			  
			  var sqa = data.survey_question_answer;
			  sqa.sortNo = i+1;
			  submitAnswer.sortNo = i + 1;
			  if(data.uuid == uuid){//移除
				  vote_answer_data.splice(i,1);
				  vote_answers.splice(i,1);
			  }
		  }
		  
		  //删除元素
		  $("#vote_modal_form .add_item_div a[uuid="+uuid+"]").closest(".widget-box").remove();
		  
	  }
	  
	  
	  function getVoteItem(uuid){
		  
		  var item = "";
		  for(var i=0;i<vote_answer_data.length;i++){
			  var data  = vote_answer_data[i];
			  if(data.uuid == uuid){
				  item = data;
				  break;
			  }
		  }
		  return item;
	  }
	  
	  function setVoteItemAnswer(answer,uuid){
		  
		  $("#vote_item_form :input[name=uuid]").val(uuid);
		  $("#vote_item_form :input[name=number]").val(answer.number);
		  $("#vote_item_form :input[name=answer]").val(answer.answer);
		  $("#vote_item_form :input[name=initValue]").val(answer.initValue);
		  $("#vote_item_form :input[name=imageUrl]").val(answer.imageUrl);
		  $("#vote_item_form :input[name=id]").val(answer.id);
		  $("#vote_item_form :input[name=type]").val(answer.type);
		  $("#vote_item_form :input[name=sortNo]").val(answer.sortNo);
		  $("#vote_item_form :input[name=actValue]").val(answer.actValue);
		  $("#vote_item_form :input[name=createTime]").val(answer.createTime);
		  
		  if(answer.imageUrl){
			  
			  var html = '<li class="upload-imgs" id="'+answer.id+'">';
                  html +=  '<img style="width:150px;height:150px;" src="'+answer.imageUrl+'" alt="150x150">';	
                  html += '<div class="tools tools-top"><a href="javascript:;"><i class="icon-remove red"></i></a></div></li>';
			  
			  $("#vote_img_thum ul").html(html);
		  }
		  
	  }
	  
  });
</script>
