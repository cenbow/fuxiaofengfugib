define(["jquery", "scroll_base", "cqalert"], function($, scroll_base, cqalert) {
	var baseUrl = '',
		paramsObj = {appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId};//参数
	return {
		initUrl: function(status, type, sort_column, sort_type){
			if(status != 0){
				paramsObj.status = status;
			}
			paramsObj.type = type;
			paramsObj.sortColumn = sort_column;
			paramsObj.sortType = sort_type;
			baseUrl = 'wz_question_list.html?appId=' + paramsObj.appId + '&sessionId=' + paramsObj.sessionId + '&token=' + paramsObj.token;
		},
		init: function(){
			//导航筛选
			var show_flag_1 = 0;
			var show_flag_2 = 0;
			$("#nav ul li.nav_category").click(function() {
				if (show_flag_1 == 0 || show_flag_2 == 1) {
					$(this).addClass("active");
					$("#nav ul li.nav_category a i").addClass("fa-sort-asc");
					$("#nav ul li.nav_status a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_status .nav_list").hide();
					$("#nav ul li.nav_category .nav_list").show();
					$("#nav ul li.nav_status").removeClass("active");
					show_flag_1 = 1;
					show_flag_2 = 0;
				} else {
					$(this).removeClass("active");
					$("#nav ul li.nav_category a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_category .nav_list").hide();
					show_flag_1 = 0;
				}
			});

			$("#nav ul li.nav_status").click(function() {
				if (show_flag_2 == 0 || show_flag_1 == 1) {
					$(this).addClass("active");
					$("#nav ul li.nav_status a i").addClass("fa-sort-asc");
					$("#nav ul li.nav_category a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_category .nav_list").hide();
					$("#nav ul li.nav_status .nav_list").show();
					$("#nav ul li.nav_category").removeClass("active");
					show_flag_1 = 0;
					show_flag_2 = 1;
				} else {
					$(this).removeClass("active");
					$("#nav ul li.nav_status a i").removeClass(
							"fa-sort-asc");
					$("#nav ul li.nav_status .nav_list").hide();
					show_flag_2 = 0;
				}
			});
			
			//排序事件
			$("#nav ul li.nav_order_by").click(function() {
				
				$("#nav ul li.nav_status a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_status .nav_list").hide();
				$("#nav ul li.nav_status").removeClass("active");
				
				$("#nav ul li.nav_category a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_category .nav_list").hide();
				$("#nav ul li.nav_category").removeClass("active");
				show_flag_1=0;
				show_flag_2=0;
				show_flag_3=0;
				show_flag_4=0;
				
				var me = $(this);
				if(me.data('column') == paramsObj.sortColumn){
					paramsObj.sortType = (paramsObj.sortType == 'asc') ? 'desc' : 'asc';
				}
				paramsObj.sortType = me.find('i').hasClass('fa-long-arrow-up') ? 'desc' : 'asc';
				paramsObj.sortColumn = me.data('column');
				
				if($('#nav ul li.nav_order_by.active').attr('data-column') != me.attr('data-column')){
					$("#nav ul li.nav_order_by").removeClass('active');
					$("#nav ul li.nav_order_by i").removeClass('fa-long-arrow-up');
				}
				if (paramsObj.sortType == 'asc') {
					me.addClass("active");
					me.find('i').addClass("fa-long-arrow-up");
				} else {
					me.find('i').removeClass("fa-long-arrow-up");
				}
				//执行查询
				if(paramsObj.status != '')
					baseUrl += '&status=' + paramsObj.status;
				if(paramsObj.type != '')
					baseUrl += '&type=' + paramsObj.type;
				
				paramsObj.lastId = null;
				loadData(true);
			});
			
			//类别 -- 选择
			$('.nav_category ul li').click(function(){
				var me = $(this);
				$('.nav_category .show').html(me.html());
				$('.nav_category ul li.active').removeClass('active');
				me.addClass('active');
				paramsObj.type = $(this).attr('value');
				queryFn();
			});
			//状态 -- 选择
			$('.nav_status ul li').click(function(){
				var me = $(this);
				$('.nav_status .show').html(me.html());
				$('.nav_status ul li.active').removeClass('active');
				me.addClass('active');
				paramsObj.status = $(this).attr('value');
				queryFn();
			});

			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
			
			loadData(true);
		}
	};
	//状态和类型查询
	function queryFn(){
		if(paramsObj.type != ''){
			baseUrl += '&type=' + paramsObj.type;
		}
		if(paramsObj.status != ''){
			baseUrl += '&status=' + paramsObj.status;
		}
		
		loadData(true);
	}
	
//	加载数据
	var canTrigger = false;
	function loadData(isFisrtPage){
		paramsObj.isAjaxPage = "1";
		var JLastObj = $("ul[custom_more] li:last");
		if (!isFisrtPage) {
			paramsObj.lastId = JLastObj.attr("data-id");
			if (paramsObj.sortColumn) {
				paramsObj.lastValue = JLastObj.attr(paramsObj.sortColumn);
			}
		}else{
			paramsObj.lastId = null;
			paramsObj.lastValue = null;
		}
		$.ajax({
			url: 'wz_question_list.html', 
			data: paramsObj, 
			dataType:"html",
			type:"post",
			async: true,
			success: function(data){
				if(isFisrtPage){
					$("ul[custom_more]").html(data);
					if($("ul[custom_more]").find('li').length == 0){
						$('.none_status').show();
					}else{
						$('.none_status').hide();
					}
				}else{
					$("ul[custom_more]").append(data);
				}
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
			},
     		error:function(){
     			$('.none_status').show();
     		}
		});
	}
});