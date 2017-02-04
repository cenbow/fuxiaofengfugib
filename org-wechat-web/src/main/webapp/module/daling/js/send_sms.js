;$(function(){
	
	var _interval = "";
	
	$(".close_rule_btn").bind("touchend",function(){
		$(this).closest(".pup-block").remove();
	});
	
	$(".get_sms_btn").bind("touchend",function(){
		
		var $this = $(this);
		if($this.hasClass("no")){
			return;
		}
		var phone = $(":input[name=phone]").val();
		if(!phone){
			msgAlert($(":input[name=phone]").attr("placeholder"));
			return;
		}
		interval();
		$this.addClass("no");
		var url = "/musicale/"+$(":input[name=accId]").val()+"/sms_code.html";
		$.ajax({
		   url:url,
		   dataType:"json",
		   type:"post",
		   async:true,
           data:{phone:phone},	
           success:function(data,status){
        	   if(-22222 == data.code ){
        		   msgAlert(data.message);
        		   return;
        	   }
        	   if(-33333 == data.code){
        		   msgAlert(data.message);
        		   return;
        	   }
        	   msgAlert(data.message);
           },
           error:function(e){
        	   msgAlert(e);
           }
		});
	});
	
	$(".grab_btn").bind("touchend",function(){
		var $this = $(this);
		if(!checkForm())return;
		var accId = $(":input[name=accId]").val();
		var params = $("#form").serializeArray();
		var url = "/musicale/"+accId+"/verify_sms_code.html";
		 $.ajax({
			 url:url,
			 dataType:"json",
			 type:"post",
			 async:true,
			 data:params,
			 success:function(data,status){
				 if(data.code>=0){
					 url = "/musicale/"+accId+"/ticket_jsp.html";
					 $(".grab_btn").unbind("touchend");
					 window.location.href=url;
					 return;
				 }
				 if(-44444 == data.code){
					 msgAlert(data.message);
					 return;
				 }
				 if(-55555 == data.code){
					 msgAlert(data.message);
					 return;
				 }
				 msgAlert(data.message);
			 },
			 error:function(e){
				 msgAlert(e);
			 }
		 });
	});
	
	function interval(){
		
		var $btn = $(".get_sms_btn");
		var i = 60;
		_interval = window.setInterval(function(){
			i-=1;
			$btn.text(i+"s");
			if(0==i){
				$btn.removeClass("no");
				$btn.text("获取验证码");
				window.clearInterval(_interval);
			}
		},1000);
	}
	
	
	function checkForm(){
		var flag = true;
		$("#form :input[type=text]").each(function(i,n){
			var $this = $(n);
			if(!$this.val()){
				flag = false;
				var msg = $this.attr("placeholder");
				msgAlert(msg);
				return flag;
			}
		});
		return flag;
	}
});