define(['jquery','cqliving_ajax','cqalert'],function($,cqliving_ajax,cqalert){
	
	var appId = $(":input[name=appId]").val();
	
	init();
	function init(){
		//打擂正反方百分比
	    getArenaPercent();
	  //投票各种答案百分比
		getVotePercent();
	}
	
	function getArenaPercent(){
		$(".arena_content").each(function(i,n){
			var $thisArea = $(n);
			var left = parseInt($thisArea.find(".arena_content_line #arena_num_1").text(),10);
			var right = parseInt($thisArea.find(".arena_content_line #arena_num_2").text(),10);
			if(!left && left !=0)return;
			var arr = [left,right];
			arr = getPercent(arr);
			var per0 = parseFloat(arr[0]);
			var per1 = parseFloat(arr[1]);
			if(per0 == 0 && per1 == 0){
				per0 = per1 = 50;
			}
			per0 = per0 -1;
			per1 = per1 -1;
			$thisArea.find(".arena_content_line #arena_line_1").css("width",per0 + "%");
			$thisArea.find(".arena_content_line #arena_line_2").css("width",per1 + "%");
		});
	}
	
	function getVotePercent(){
		$(".survey_vote").each(function(i,n){
			var arr = [];
			var $this = $(n);
			$this.find("li").each(function(i,n){
				var $li = $(n);
				var text = $li.find(".vote_num").text();
				if(text){
					var num = parseInt(text,10);
					arr.push(num);
				}
			});
			arr = getPercent(arr);
			$this.find("li").each(function(i,n){
				var $li = $(n);
				var per = arr[i];
				$li.find(".vote_num span").text(per);
				per = parseFloat(arr[i])-1;
				$li.find(".vote_line span").css("width",per+"%");
			});
		});
	}
	
	if(!noev){
		//投票多选限制
		$(".survey_vote li :input").on("click tap",function(){
			var $this = $(this);
			var $ul = $(this).closest("ul");
			var limitruletype = $ul.attr("limitruletype");
			var limitruletimes = $ul.attr("limitruletimes");
			var checkedLen = $ul.find("li :input:checked").length;
			if($this.attr("type") == "checkbox" && limitruletype == 2 && limitruletimes>=1 && limitruletimes<checkedLen){
				$this.prop("checked",false);
				ZWY_ClOUD.alert("最多只能选择："+limitruletimes+"项");
			}
		});
		var params = {
				sessionCode:"",appId:appId,token:"",
				surveyId:"",questionId:"",content:"","answerIds[]":""
		};
		var property = "answerIds[]";
		//打擂提交
		$(".arena_content .btn_click").on("click tap",function(){
			if(share){
				location.href=loadUrl;
				return;
			}
			$(this).addClass("active");
			var $this = $(this);
			//答案ID
			var ansid = $this.attr("ansid");
			//调研ID
			var surveyId = $this.attr("arenaid");
			//问题ID
			var quesId = $this.attr("quesid");
			var answerIds = [];
			answerIds.push(ansid);
			params.surveyId = surveyId;
			params.questionId = quesId;
			params[property] = answerIds;
			saveSurveyInfo(params,arenaCallBack,$this);
		});
		
		//调研保存
		$(".survey_save").on("click tap",function(){
			if(share){
				location.href=loadUrl;
				return;
			}
			var $this = $(this);
			var arenaId = "",quesId="",ansid=[],content="";
			var $Pdiv = $this.closest(".activity_content");
			var $radiodiv = $Pdiv.find(".survey_radio");
			var $checkDiv = $Pdiv.find(".survey_checkbox");
			var $textareaDiv = $Pdiv.find(".survey_textarea");
	        if($radiodiv){
	        	arenaId = $radiodiv.attr("arenaid");
	        	quesId = $radiodiv.attr("quesid");
	        	$radiodiv.find(":input:checked").each(function(i,n){
	        		ansid.push($(n).val());
	        	});
			}else if($checkDiv){
				arenaId = $checkDiv.attr("arenaid");
	        	quesId = $checkDiv.attr("quesid");
	        	$checkDiv.find(":input:checked").each(function(i,n){
	        		ansid.push($(n).val());
	        	});
			}else if($textareaDiv){
				arenaId = $textareaDiv.attr("arenaid");
	        	quesId = $textareaDiv.attr("quesid");
	        	content = $textareaDiv.find("textarea").val();
	        	if(!content){
	        		 ZWY_ClOUD.alert("请输入答案");
	        		return;
	        	}
			}
	        if(!$textareaDiv && ansid.length<=0){
	        	 ZWY_ClOUD.alert("请选择答案");
	        	return;
	        }
			params.surveyId = arenaId;
			params.questionId = quesId;
			params[property] = ansid;
			saveSurveyInfo(params,surveyInfoCallBack,$this);
	        
		});
		
		//投票保存
		$(".survey_vote").next().on("click tap",function(){
			if(share){
				location.href=loadUrl;
				return;
			}
			var $this = $(this);
			var $vote = $this.prev(".survey_vote");
			var quesid = $vote.find("li").attr("quesid");
			var voteid = $vote.find("li").attr("voteid");
			var checkedInp = [];
			$vote.find("li :input:checked").each(function(i,n){
				checkedInp.push($(n).val());
			});
			if(checkedInp.length<=0){
				 ZWY_ClOUD.alert("请选择选项");
				return;
			}
			params.surveyId = voteid;
			params.questionId = quesid;
			params[property] = checkedInp;
			saveSurveyInfo(params,voteCallBack,$this);
		});
		
		//图片投票
		$(".vote_list li .vote_list_btn .btn_click").on("click tap",function(){
			if(share){
				location.href=loadUrl;
				return;
			}
		    var $this = $(this);
		    var $li = $this.closest("li");
		    var quesid = $li.attr("quesid");
			var voteid = $li.attr("voteid");
			var ansid = $li.attr("ansid");
			var checkedInp = [];
			 checkedInp.push(ansid);
			params.surveyId = voteid;
			params.questionId = quesid;
			params[property] = checkedInp;
			saveSurveyInfo(params,voteImgCallBack,$this);
		});
		
	}
	
	function addNum($obj,num){
		
		if(!$obj)return 0;
		var iniNum = parseInt($obj.text(),10);
		iniNum += parseInt(num,10);
		$obj.text(iniNum);
	}
	//打擂回调
	function arenaCallBack(data,status,$this){
		
		var ansId = data.data[0];
		var win = $("p[ansid="+ansId+"]").text();
		win = parseInt(win,10);
		win += 1;
		$("p[ansid="+ansId+"]").text(win);
		getArenaPercent();
		
	}
	
	function surveyInfoCallBack(data,status,$this){
	}
	
	function voteCallBack(data,status,$this){
		
		var ansid = data.data;
		if(ansid){
			for(var i=0,m=ansid.length;i<m;i++){
				var answerId = ansid[i];
				var $div = $("div[vote_ansid="+answerId+"]");
				var $span = $div.find("span");
				var ordinary = $div.text();
				ordinary = parseInt(ordinary,10);
				ordinary +=1;
				$("#temp_div").html($span);
				$div.html(ordinary+"票（"+$("#temp_div").html()+"）");
			}
			getVotePercent();
		}
	}
	
	function voteImgCallBack(data,status,$this){
		var $li = $this.closest("li");
		var num = parseInt($li.find(".vote_list_number_right span").text(),10);
		num +=1;
		$li.find(".vote_list_number_right span").text(num);
	}
	
	//保存user_survey_info表
	function saveSurveyInfo(params,callback,$this){
		
		try{
			ZWY_ClOUD.getSessionToken("getToken");
			
			if(sessionStr){
				setTimeout(function(){
					var sessionObj = eval("("+sessionStr+")");
					params.sessionCode = sessionObj.sessionId;
					params.token = sessionObj.token;
					
					var url="/info/save_survey_answer.html";
					cqliving_ajax.ajaxOperate(url,"",params,function(data,status){
						
						if(data.code>=0){
							if($.isFunction(callback))callback(data,status,$this);
						}else if(data.code == -1111111){
							//跳转到登录页
							window.AppJsObj.redirectLogin();
						}else{
							 ZWY_ClOUD.alert(data.message);
						}
					});
				},500);
			}
		}catch(e){}
	}
	
	//获取数组中每个数字的在总数中的百分比
    function getPercent(arr){
    	//强转成数字
    	var total = 0;
    	for(var i=0,m=arr.length;i<m;i++){
    		arr[i] = parseInt(arr[i],10);
    		total +=arr[i];
    	}
    	//计算
		var result = [];
		for(var i=0,m=arr.length;i<m;i++){
			var dataPer = "0%";
			if(total != 0){
			   dataPer = ((arr[i]/total).toFixed(6) * 100).toFixed(1) + "%";
			}
			result.push(dataPer);
    	}
		return result;
	}
});