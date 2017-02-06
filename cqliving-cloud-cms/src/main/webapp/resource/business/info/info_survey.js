define(["jquery",'cqliving_ajax','cqliving_dialog','myUploader','moment'],function($,cqliving_ajax,cqliving_dialog,myUploader,moment){
	
	  var questionUploaderOptions = {
				url:"/common/upload.html?imgsize=30",
				containerId:"question_img_container",
				thumbContainerId:"question_img_thum",
				success:function(file,response){
					
					var questImgs = $(":input[name=question_img_url]").val();
					if(questImgs)questImgs += ',';
					questImgs += imgUrl+response.data.filePath;
					$(":input[name=question_img_url]").val(questImgs);
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},isSingle:false
			};
		
		var answerUploaderOptions = {
				url:"/common/upload.html?imgsize=30",
				containerId:"answer_img_container",
				thumbContainerId:"answer_img_thum",
				success:function(file,response){
					
                    var answerImgs = $(":input[name=answer_img_url]").val();
					if(answerImgs)answerImgs += ',';
					answerImgs += imgUrl+response.data.filePath;
					$(":input[name=answer_img_url]").val(answerImgs);
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},isSingle:false
			};
		
		$("#add_original_layer").on("click",".survey_question_modal",function(){
			loadQuestionModal();
		});
		
		//加载问题弹出层
		function loadQuestionModal(params){
			
			var url = "common/survey_question.html";
			
			cqliving_ajax.load(".add_survey_question_modal",url,params,function(){
				$("#modal-form-add-question").modal("show");
				myUploader.init(questionUploaderOptions);
			});
			
		}
		
		$("#add_original_layer").on("click","#survey_modal_form .survey_item_div .add_answer_a",function(){
			
			var quesId = $(this).closest(".widget-box").attr("ques_id");
			
			loadAnswerModal({questionId:quesId});
		});
		
		//加载答案弹出层
        function loadAnswerModal(params){
			
			var url = "common/survey_answer.html";
			
			cqliving_ajax.load(".add_survey_answer_modal",url,params,function(){
				$("#modal-form-add-answer").modal("show");
				myUploader.init(answerUploaderOptions);
			});
			
		}
		
		/*$("#modal-form-add-question").on("show.bs.modal",function(){
			$("#question_img_container div").eq(1).css({left: '12px',width: '125px',height: '40px',bottom: 'auto',right: 'auto'});
			$("#answer_img_container div").eq(1).css({left: '12px',width: '125px',height: '40px',bottom: 'auto',right: 'auto'});
		});*/
		
		//问题保存
		$("#add_original_layer").on("click",".add_survey_question_modal .modal-footer button",function(){
			
			var index = $("#modal-form-add-question .modal-footer button").index(this);
			if(!index || index !=1)return;
			
			var question = $(":input[name=question]").val();
			var type = $(":input[name=ques_type]:checked").val();
			var typeName = $(":input[name=ques_type]:checked").next().text();
			var id = $("#modal-form-add-question").attr("quesid");
			var createTime = $("#modal-form-add-question :input[name=createTime]").val();
			
			if(!question || !type){
				cqliving_dialog.error("请完善问题信息");
				return;
			}
			
			var sortNo = $("#survey_modal_form .survey_item_div .widget-box").length;
			if(!sortNo)sortNo=0;
			sortNo +=1;
			
			var questImgs = $(":input[name=question_img_url]").val();
			var ques = {
					question:question,
					type : type,
					typeName:typeName,
					images:questImgs,
					limitCount:-1,
					id:id,
					sortNo:sortNo,
					createTime:createTime ? createTime : moment().format('YYYY-MM-DD HH:mm:ss')
			};
			//保存问题
			var url = "/module/info/common/save_survey_question.html";
			cqliving_ajax.ajaxOperate(url,"#modal-form-add-question",ques,function(data,status){
				
				if(data.code >=0){
					if(!ques.id){
						ques.id = data.data.id;
					}else{
						$("#survey_modal_form .survey_item_div .widget-box[ques_id="+ques.id+"]").remove();
					}
					$("#survey_modal_form .survey_item_div").append(getQuesHtml(ques));	
				}else{
					cqliving_dialog.error(  ques.id ? "修改问题失败" : "添加问题失败");
				}
				
			});
			
			
		});
		
		//答案保存
		$("#add_original_layer").on("click",".add_survey_answer_modal .modal-footer button",function(){
			
			var index = $("#modal-form-add-answer .modal-footer button").index(this);
			if(!index || index !=1)return;
			
			var answer = $(":input[name=answer]").val();
			var answerImgs = $(":input[name=answer_img_url]").val();
			var initValue = $(":input[name=answerinitValue]").val();
			var quesId = $("#modal-form-add-answer").attr("quesid");
            var answerid = $("#modal-form-add-answer").attr("answerid");
            var $type = $(":input[name=answerType]");
            var createTime = $("#modal-form-add-answer :input[name=createTime]").val();
            
            var type = 0;
            if($type.is(":checked")){
            	type = 1;
            }
            
            var sortNo = $("#survey_modal_form .survey_item_div .widget-box[ques_id="+quesId+"] .alert-info").length;
			if(!sortNo)sortNo=0;
			sortNo +=1;
			
			var answerO = {
					answer : answer,
					id : answerid,
					imageUrl:answerImgs,
					inputLimit:-1,
					sortNo:sortNo,
					questionId:quesId,
					initValue:initValue,
					type:type,
					createTime:createTime ? createTime : moment().format('YYYY-MM-DD HH:mm:ss')
			};
			
			if(!answer || !initValue){
				cqliving_dialog.error("请完善答案信息");
				return;
			}
			
			//保存答案
			var url = "/module/info/common/save_survey_answer.html";
			cqliving_ajax.ajaxOperate(url,"#modal-form-add-answer",answerO,function(data,status){
				
				if(data.code >=0){
					if(!answerO.id){
						answerO.id = data.data.id;
					}else{
						$("#survey_modal_form .survey_item_div .widget-box[ques_id="+quesId+"] .alert-info[answer_id="+answerO.id+"]").remove();
					}
					$("#survey_modal_form .survey_item_div .widget-box[ques_id="+answerO.questionId+"] .widget-main").append(getAnswerHtml(answerO));
				}else{
					cqliving_dialog.error(  answerO.id ? "修改答案失败" : "添加答案失败");
				}
				
			});
			
		});
		
		//问题删除
        $("#add_original_layer").on("click","#modal-form-add-question a[data-action]",function(){
        	var ques_id = $(this).closest(".widget-box").attr("ques_id");
        	var url = "common/del_survey_question.html";
        	cqliving_dialog.confirm("警告","确认要删除问题吗？删除后不能恢复",function(){
        		cqliving_ajax.ajaxOperate(url,"#modal-form-add-question",{questionId:ques_id},function(data,status){
            		if(data.code<0){
            			cqliving_dialog.error("删除问题失败");
            		}
            	});
        	});
		});
		//答案删除
        $("#add_original_layer").on("click",".answer_delete_a",function(){
        	
        	var $this = $(this);
        	var answerId = $this.closest(".alert-info").attr("answer_id");
        	var url = "common/del_survey_answer.html";
        	cqliving_dialog.confirm("警告","确认要删除答案吗？删除后不能恢复",function(){
        		cqliving_ajax.ajaxOperate(url,".answer_delete_a",{answerId:answerId},function(data,status){
            		if(data.code>=0){
            			$this.closest(".alert-info").remove();
            		}else{
            			cqliving_dialog.error("删除答案失败");
            		}
            	});
        	});
        	
		});

		//问题编辑
		$("#add_original_layer").on("click",".question_edit_a",function(){
			
			var ques_id = $(this).closest(".widget-box").attr("ques_id");
			loadQuestionModal({id:ques_id});
		});
		
		//答案编辑
        $("#add_original_layer").on("click",".answer_edit_a",function(){
			
        	var ques_id = $(this).closest(".widget-box").attr("ques_id");
        	var answerId = $(this).closest(".alert-info").attr("answer_id");
        	loadAnswerModal({id:answerId,questionId:ques_id});
        	
		});
		
		function getQuesHtml(ques){
			
			var html = "<div class=\"widget-box collapsed\" ques_id=\""+ques.id+"\" style=\"opacity: 1;\"><div class=\"widget-header\"><h5>";
				html += ques.question +"&nbsp;&nbsp;&nbsp;&nbsp;"+ ques.typeName;
				html += "	</h5><div class=\"widget-toolbar\"><a data-action=\"collapse\" href=\"#\"><i class=\"icon-chevron-up\"></i></a>";
				html += "<a href=\"javascript:;\" class=\"add_answer_a\"><i class=\"icon-plus\"></i>答案</a>";
				html += "<a href=\"javascript:;\" class=\"btn-sm question_edit_a\">编辑</a>";
				html += "    <a data-action=\"close\" href=\"#\"><i class=\"icon-remove\"></i>删除</a></div></div>";
				html += "<div class=\"widget-body\"><div class=\"widget-body-inner\"><div class=\"widget-main\">";
				html += "</div></div></div></div>";
			return html;
		}
		
		function getAnswerHtml(answer){
			
			var html = "<div answer_id=\""+answer.id+"\" class=\"alert alert-info\">"+answer.answer +"&nbsp;&nbsp;&nbsp;&nbsp;初始量"+answer.initValue;
			html +="<div class=\"widget-toolbar\"><a class=\"btn-sm answer_edit_a\" href=\"javascript:;\">编辑</a>";
			html += "<a href=\"javascript:;\" class=\"answer_delete_a\"><i class=\"icon-remove\"></i>删除</a></div></div>";
			
			return html;
		}
	  
  });