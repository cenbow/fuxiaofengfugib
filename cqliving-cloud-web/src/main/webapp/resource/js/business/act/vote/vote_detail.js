define(['jquery','cqliving_ajax','cqalert','numberUtil'],function($,cqliving_ajax,cqalert,numberUtil){
	
	var init = {
			getVotePercent:getVotePercent,
			surplusVoteNum:surplusVoteNum,
			initBind:function(){
				
				$("#close_a").unbind("click").bind("click",function(){
					  $(this).closest(".share_top").remove();
				});
				//分享的页面不能投票
				var share = $("span[openUrl]").length;
				if(share>=1){
					//图片投票
					$("button[actVoteItemId]").unbind("click").bind("click",redirectLoadPage);
					//图片投票的多选和普通投票
					$(".btn_submit").unbind("click").bind("click",redirectLoadPage);
					return;
				}
				//图片投票
				$("button[actVoteItemId]").unbind("click").bind("click",imgVote);
				//图片投票的多选和普通投票
				$(".btn_submit").unbind("click").bind("click",selectVote);
			}
	};
	
	//跳转到app下载页
	function redirectLoadPage(){
		var loadUrl = $("span[openUrl]").eq(0).attr("openUrl");
		window.location.href=loadUrl;
	}
	
	function getVotePercent(){
		
		if($(".survey_vote_info").length<=0)return;
		
		var arr = [];
		$(".survey_vote_info").each(function(i,n){
			var $n = $(n);
			var $num = $n.find(".vote_num");
			var num = parseInt($num.text(),10);
			arr.push(num);
		});
		
		arr = numberUtil(arr);
		$(".survey_vote_info").each(function(i,n){
			var $n = $(n);
			var w = arr[i];
			var $num = $n.find(".vote_num span+span");
			var $voteLine = $n.find(".vote_line span");
			$num.text(w);
			$voteLine.css("width",w);
		});
		
		statisticTotal();
	};
	
	
    function imgVote(){
		
    	if(canVote){
			ZWY_ClOUD.alert(canVote);
			return;
		}
    	
		var itemIds = [];
		var $this = $(this);
		itemIds.push($this.attr("actVoteItemId"));
		
		saveUserVote(itemIds,$this,true);
	}
	
    function selectVote(){
		
    	if(canVote){
			ZWY_ClOUD.alert(canVote);
			return;
		}
    	
		var $this = $(this);
		var $input = $this.closest(".activity_content").find(":input:checked");
		var itemId=[];
		$input.each(function(i,n){
			itemId.push($(n).val());
		});
		
		if(itemId.length<=0){
			ZWY_ClOUD.alert("请选择投票项");
			return;
		}
		
		saveUserVote(itemId,$this);
	}
	
	function saveUserVote(itemIds,$this,isImage){
		
		var params = {};
		
		try{
			ZWY_ClOUD.getSessionToken("getToken");
		}catch(e){
		}
		setTimeout(function(){
			
			if(!sessionStr)return;
			sessionStr = eval("("+sessionStr+")");
			params.sessionCode = sessionStr.sessionId;
			params.token = sessionStr.token;
			
			var arrProp = "itemIds[]";
			params[arrProp] = itemIds;
			var $appId = $(":input[name=appId]");
			params.appId = $appId.val();
			var $input = $appId.siblings(":input[type=hidden]");
		    
			$input.each(function(i,n){
				var $n = $(n);
				var name = $n.attr("name");
				var value = $n.val();
				params[name] = value;
			});
			var url = "/act/user/vote.html";
			cqliving_ajax.ajaxOperate(url,"body",params,function(data,status){
				
				//获取还能投票数
				surplusVoteNum();
				if(data.code>=0){
					if(isImage){
						imageVoteCallBack($this);
					}else{
						voteCallBack($this);
					}
				}else if(data.code == -11111){
					//跳转到登录页
					window.AppJsObj.redirectLogin();
				}else{
					ZWY_ClOUD.alert(data.message);
				}
				
			});
		},500);
		
	}
	
	function imageVoteCallBack($this){
		
		var $vote_num = $this.closest("li").find(".vote_list_number_right span");
		var vote_num_text = $vote_num.text();
		var num = parseInt(vote_num_text,10) + 1;
		$vote_num.text(num);
	}
	
	function voteCallBack($this){
		
		var $actContent = $this.closest(".activity_content");
		
		if($this.hasClass("img_vote")){
			
			var $input = $actContent.find("li :input:checked");
			$input.each(function(i,n){
				var $n = $(n);
				var $vote_num = $n.closest("li").find(".vote_list_number_right span");
				var vote_num_text = $vote_num.text();
				var num = parseInt(vote_num_text,10) + 1;
				$vote_num.text(num);
			});
			
		}else{
			var $input = $actContent.find("li :input:checked");
			$input.each(function(i,n){
				var $n = $(n);
				var $vote_num = $n.closest("li").find(".vote_num span:first");
				var vote_num_text = $vote_num.text();
				var num = parseInt(vote_num_text,10);
				var newnum =num+1;
				vote_num_text = vote_num_text.replace(num,newnum);
				$vote_num.text(vote_num_text);
				
				//重新计算投票比例
				getVotePercent();
			});
		}
	}
	
	//计算总投票数
	function statisticTotal(){
		
		var $voteCount = $(".survey_vote_info").closest(".activity_content").find(".survey_vote_count");
		
		var total = 0;
		
		$(".survey_vote_info").each(function(i,n){
			var $n = $(n);
			var $num = $n.find(".vote_num");
			var num = parseInt($num.text(),10);
			total += num;
		});
		$voteCount.text("共"+total+"票");
	}
	
	function surplusVoteNum(){
		
		var voteClassifyId = $(":input[name=actVoteClassifyId]").val();
        var params = {};
		try{
			ZWY_ClOUD.getSessionToken("getToken");
			setTimeout(function(){
				if(!sessionStr)return;
				sessionStr = eval("("+sessionStr+")");
				params.sessionCode = sessionStr.sessionId;
				params.token = sessionStr.token;
				
				var url="/act/surpluse_vote.html?voteClassifyId="+voteClassifyId+"&psessionId="+params.sessionCode+"&ptoken="+params.token+"";
				var $nameMemo = $(".active_common .name_memo");
				if($nameMemo.length<=0){
					return;
				}
				cqliving_ajax.load($nameMemo,url,{},function(data){
					$nameMemo.html(data);
				});
			},500);
		}catch(e){params.sessionCode = "cqliving_custom_session_code";}
		
		modifyHeadJoinNum();
	}
	
	//修改头部的参加人数
	function modifyHeadJoinNum(){
		var actInfoListId = $(":input[name=actInfoListId]").val();
		var url="/act/vote_join_num.html";
		cqliving_ajax.load(".joinNum",url,{actInfoListId:actInfoListId},function(data){
		});
	}
	
	return init;
});