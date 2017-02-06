define(["jquery", 'cqliving_ajax', "scroll_base"], function($, cq_ajax, scroll_base) {
	var paramsObj = {};
	return {
		init: function(){
			try{
				ZWY_ClOUD.getSessionToken("setSessionToken");
			}catch(e){}
			
			//初始化参数
			paramsObj = $.extend(paramsObj, basicObj);
			setTimeout(function(){
				if(basicParamsStr){
					basicParamsStr = eval("("+basicParamsStr+")");
					basicObj.sessionCode = basicParamsStr.sessionId;
					basicObj.token = basicParamsStr.token;
					paramsObj = $.extend(paramsObj, basicObj);
				}
			},500);
			//加载数据
			loadData(true);
			//初始化页面
			initPage();
		}
	};
	
	/**
	 * 加载数据
	 */
	var canTrigger = false;
	function loadData(isFisrtPage){
		paramsObj.isAjaxPage = "1";
		var JLastObj = $(".act_list .bu_list_detail:last");
		if (!isFisrtPage) {
			paramsObj.lastId = JLastObj.attr("data-id");
			paramsObj.lastStartTimestamp = JLastObj.attr("data-starttime");
			paramsObj.lastSortNo = JLastObj.attr("data-sortno");
		}else{
			paramsObj.lastId = null;
			paramsObj.lastStartTimestamp = null;
			paramsObj.lastSortNo = null;
		}
		cq_ajax.ajaxOperate('act_list.html',null,paramsObj,function(data,status){
			if(isFisrtPage){
				$(".act_list").html(data);
				if($(".bu_list_detail").length == 0){
					$('#noDataDiv').show();
				}else{
					$('#noDataDiv').hide();
				}
			}else{
				$(".act_list").append(data);
			}
			
			//获取浏览器高度
			var window_height=$(window).height();
			$(".nav_list_bg").height(window_height-80);
			
			//列表图比例
			var bu_pic_width=$(".bu_list_detail img").width();
			$(".bu_list_detail img").height(3*bu_pic_width/7);
			
			canTrigger = $(".isLastPage:last").val() == "false";	//有数据，允许触发滚动事件
			var hh = scroll_base.getClientHeight() * 0.5;
			$(window).scroll(function () {
				if ((scroll_base.getScrollTop() + scroll_base.getClientHeight()) >= (scroll_base.getScrollHeight() - hh)) {
					if (!canTrigger) {
						return;
					}
					canTrigger = false;	//阻止触发滚动事件
					loadData(false);
				}
			});
		},{dataType:"html"});
	}

	
	/**
	 * 页面初始化
	 * 导航筛选
	 */
	function initPage(){
		$("#nav ul li.nav_order").click(function(){
			var me = $(this);
			if(me.hasClass('active')){
				me.removeClass("active");
				$("#nav ul li.nav_order a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_order .nav_list").hide();
			}else{
				me.addClass("active");
				$("#nav ul li.nav_order a i").addClass("fa-sort-asc");
				$("#nav ul li.nav_area a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_area").removeClass("active");
				$("#nav ul li.nav_area .nav_list").hide();
				$("#nav ul li.nav_order .nav_list").show();	
			}
		});
		$("#nav ul li.nav_area").click(function(){
			var me = $(this);
			if(me.hasClass('active')){
				me.removeClass("active");
				$("#nav ul li.nav_area a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_area .nav_list").hide();
			}else{
				me.addClass("active");
				$("#nav ul li.nav_area a i").addClass("fa-sort-asc");
				$("#nav ul li.nav_order a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_order").removeClass("active");
				$("#nav ul li.nav_order .nav_list").hide();
				$("#nav ul li.nav_area .nav_list").show();	
			}
		});
		//获取浏览器高度
		var window_height=$(window).height();
		$(".nav_list_bg").height(window_height-80);
		
		//列表图比例
		var bu_pic_width=$(".bu_list_detail img").width();
		$(".bu_list_detail img").height(3*bu_pic_width/7);
		
		$('#nav .nav_order li').click(function(){
			var me = $(this),
				val = me.html(),
				rangeType = me.data('val');
			me.closest('.nav_order').find('span').html(val);
			paramsObj.rangeType = rangeType;
			loadData(true);
		});
		$('#nav .nav_area li').click(function(){
			var me = $(this),
				val = me.html(),
				type = me.data('val');
			me.closest('.nav_area').find('span').html(val);
			paramsObj.type = type;
			loadData(true);
		});
	}
});