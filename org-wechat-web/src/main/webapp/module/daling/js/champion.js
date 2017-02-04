;$(function(){

	$("#begin_vote").bind("click",function(){
		
		var url = "/begin_vote.html";
		var param = {voteStep:2};
		ajaxFn(url,param,beginCallBack);
	});
	
	
    $("#end_vote").bind("click",function(){
    	var url = "/end_vote.html";
		var param = {voteStep:2};
		ajaxFn(url,param,endCallBack);
	});
    
    function beginCallBack(data,status){
    	
    	var dateNow = data.data.dateNow;
    	window.clearInterval(window.interval);
    	timeCount(dateNow);
    }
    
    function endCallBack(){
    	location.href= location.href;
    }
    
    timeCount();
    function timeCount(dateStr){
    	
    	var dateInit = dateStr;
    	var sessionDate = $(":input[name=date_str]").val();
    	if(!dateInit){
    		dateInit = sessionDate;
    	}
        if(!dateInit)return;
        var date = new Date(dateInit);
        var dateJsNow = new Date();
                
        var countInit = (dateJsNow.getTime()/1000).toFixed(0) - (date.getTime()/1000).toFixed(0);
        
       window.interval =  setInterval(function(){
        	countInit +=1;
        	updateTimeCount(numToTime(countInit));
        },1000);
    }
    
    function numToTime(num){
    	
    	if(!num)return "";
    	
    	var h = 0,m=0;
    	if(num >=3600){
    		h = (num / 3600).toFixed(0);
    		num = num % 3600;
    	}
    	if(num >=60){
    		m = (num / 60).toFixed(0);
    		num = num % 60;
    	}
    	return h+"小时"+m+"分"+num+"秒";
    }
    
    function updateTimeCount(countNum){
    	
    	$("#time_count").text(countNum);
    	
    }
    
    function ajaxFn(url,param,callback){
    	$.ajax({
    		url:url,
    		type:"POST",
    		dataType:"json",
    		data:param,
    		async:true,
    		success:function(data,status){
    			
    			if($.isFunction(callback))callback(data,status);
    		},
    		error:function(e){
    			
    		}
    	});
    }
});
