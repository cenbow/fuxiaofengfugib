;$(function(){
	
	$(".my_ticket").bind("touchend",function(){
		$(".pup").show();
	});
	
	$(".rule_btn").bind("touchend",function(){
		$(".rule_pup").show();
	});
	
	$(".close").bind("touchend",function(){
		$(".pup-block").hide();
	});
	
	$("li:not(.over)").bind("touchend",function(){
		$(this).addClass("on").siblings().removeClass("on");
	});
	
	$(".grab_btn").bind("touchend",function(){
		var musicaleId = $("li.on").attr("ticketId");
		if(!musicaleId){
			msgAlert("请选择门票");
			return;
		}
		var accId  = $(":input[name=accId]").val();
        var url = "/musicale/"+accId+"/grab_ticket.html";
        $.ajax({
        	url:url,
        	dataType:"json",
        	type:"post",
        	data:{musicaleId:musicaleId},
        	async:true,
        	success:function(data,status){
        		var code = data.code;
        		if(-12112 == code){
        			$(".share_chance").show();
        			return;
        		}
        		if(-22112 == code){
        			$(".no_chance").show();
        			return;
        		}
        		if(-24112 == code){
        			$(".no_succ").show();
        			return;
        		}
        		if(-23112 == code){
        			$(".this_ticket_over").show();
        			return;
        		}
        		if(-24113 == code){
        			$(".no_succ_no_share").show();
        			return;
        		}
        		if(0 <= code){
        			$(".ticket_succ").show();
        			return;
        		}
        		msgAlert(data.message);
        	},
        	error:function(e){
        		msgAlert(e);
        	}
        });
	});
});