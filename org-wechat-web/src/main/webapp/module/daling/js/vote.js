;$(function(){
	
	$(".close_btn").bind("touchend",function(){
		$(".pup-lsit,.pup-rule").hide();
	});
	
	$(".rule_img").bind("touchend",function(){
		$(".pup-rule").show();
	});
	
	$(".vote_btn").bind("touchend",function(){
		var $this = $(this);
		var $li = $this.closest("li");
		var code = $li.attr("code");
		var name = $li.attr("name");
		
		var accId = $(":input[name=accId]").val();
		var voteStep = $(":input[name=voteStep]").val();
		var url = "/musicale/"+accId+"/can_vote.html";
		$.ajax({
			url:url,
			dataType:"json",
			type:"post",
			async:true,
			data:{contestantCode:code,voteStep:voteStep},
			success:function(data,status){
				if(0<=data.code){
					voteConfirm(code,name);
					return;
				}
				if(-77777 == data.code ){
					$(".today_no_chance").show();
					return;
				}
				if(-88888 == data.code ){
					$(".share_vote").show();
					return;
				}
				if(-66666 == data.code ){
					msgAlert("投票已结束！");
					return;
				}
				msgAlert(data.message);
			},
			error:function(e){
				msgAlert(e);
			}
		});
	});
	
	
	function voteConfirm(code,name){
		
		var $pup = $(".vote_pup");
		var $p = $pup.find(".text p:first");
		$p.find("em:first").text(code+"号");
		$p.find("em:last").text(name);
		$pup.show();
		
		$(":input[name=code]").val(code);
	}
	
	$(".vote_pup .two a:first").bind("touchend",function(){
		
		$(".close").trigger("touchend");
		var code = $(":input[name=code]").val();
		vote(code);
	});
	
	function vote(code){
        
		var accId = $(":input[name=accId]").val();
		var voteStep = $(":input[name=voteStep]").val();
		var url = "/musicale/"+accId+"/vote.html";
		
		$.ajax({
			url:url,
			dataType:"json",
			type:"post",
			async:true,
			data:{contestantCode:code,voteStep:voteStep},
			success:function(data,status){
				if(0<=data.code){
					var url = location.href;
					location.href=url;
					return;
				}
				if(-77777 == data.code ){
					$(".today_no_chance").show();
					return;
				}
				if(-88888 == data.code ){
					$(".share_vote").show();
					return;
				}
				if(-66666 == data.code ){
					msgAlert("投票已结束！");
					return;
				}
				msgAlert(data.message);
			},
			error:function(e){
				msgAlert(e);
			}
		});
	}
	
})