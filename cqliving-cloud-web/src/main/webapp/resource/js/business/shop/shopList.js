define(["jquery", "cqliving_ajax", "scroll_base"], function($, cqlivingAjax, scroll_base) {
	return {
		init: function() {
			//UI 初始化
			uiInit();
			//获取定位
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r) {
				if (this.getStatus() == BMAP_STATUS_SUCCESS) {
					lat = r.point.lat;
					lng = r.point.lng;
				} else {
					console.log("获取位置失败："+ this.getStatus());
				}        
				//加载店铺数据
				loadData(true);
			},{enableHighAccuracy: true});
			
			//查询、排序数据
			$(".nav_order li[val], .nav_area li[rid], .nav_type li[tid]").click(function() {
				//顶置选中的选项
				$(this).closest(".nav_list").prev().find(".shop_condition_label").text(cutStr($(this).text()));
				if ($(this).hasClass("active")) {
					return;
				}
				//高亮选项，刷新数据
				$(this).addClass("active").siblings().removeClass("active");
				//加载数据
				loadData(true);
			});
			
			//搜索
			$("#search_btn").click(function() {
				loadData(true);
			});
		}
	};
	
	var canTrigger = false;
	//默认坐标：重庆
	var lat = 29.60625;
	var lng = 106.516739;
	
	/** 控制查询条件上最多显示4个字 */
	function cutStr(text) {
		if (text.length <= 4) {
			return text;
		}
		return text.substring(0, 3) + "...";
	}
	
	/** 加载列表数据 */
	function loadData(isFisrtPage) {
		var url = "/shop/loadData.html";
		var lastBusinessValue = "";
		var order = $(".nav_order li.active").attr("val");
		if (!isFisrtPage) {
			if ("d" == order) {
				lastBusinessValue = $(".bu_list_detail:last").attr("dv");
			} else if ("c" == order) {
				lastBusinessValue = $(".bu_list_detail:last").attr("cc");
			} else {
				lastBusinessValue = $(".bu_list_detail:last").attr("rc");
			}
		}
		var data = {
				"lat": lat,
				"lng": lng,
				"a": $("#app_id").val(),
				"t": $("#shop_type_id").val(),
				"o": order,
				"r": $(".nav_area li.active").attr("rid"),
				"c": $(".nav_type li.active").attr("tid"),
				"n": $("#search").is(":visible") ? $("#search_ipt").val() : "",
				"lb": lastBusinessValue,
				"li": isFisrtPage ? "" : $(".bu_list_detail:last").attr("sid")
		};
		cqlivingAjax.ajaxOperate(url, null, data, function(data, status) {
			if ($.trim(data)) {
				canTrigger = true;	//有数据，允许触发滚动事件
				if (isFisrtPage) {
					$(".bu_list").html(data);
				} else {
					$(".bu_list").append(data);
				}
				//绑定滚动分页事件
				var hh = scroll_base.getClientHeight() * 0.5;
				$(window).scroll(function () {
					if ((scroll_base.getScrollTop() + scroll_base.getClientHeight()) >= (scroll_base.getScrollHeight() - hh)) {
						if (!canTrigger) {
							return;
						}
						//没有数据后就不执行ajax请求
//						if($(".nodata").length>=1)  return;
//						//加载  正在加载的div
//						if(loadObj) $(loadObj).last().after(getLoading());
//						qzAjax.ajaxOperate(url,$("body"),datas,function(data,status){
//							//加载结束后删除   正在加载的div
//							if(loadObj) closeLoading();
//							callback ? callback(data,status) : "";
//						});
						canTrigger = false;	//阻止触发滚动事件
						loadData(false);
					}
				});
			} else {	//无数据
				if (isFisrtPage) {	
					$(".bu_list").html("");
				} else {
					//无更多数据时，取消绑定滚动事件
					$(window).unbind("scroll");
				}
			}
		}, {
			async: false,
			dataType: "html"
		}); 
	}
	
	/** UI 初始化 */
	function uiInit() {
		//导航筛选
		var show_flag_1=0;
		var show_flag_2=0;
		var show_flag_3=0;
		var show_flag_4=0;
		$("#nav ul li.nav_order").click(function(){
			if(show_flag_1==0 || show_flag_2==1 || show_flag_3==1){
				$(this).addClass("active");
				$("#nav ul li.nav_order a i").addClass("fa-sort-asc");
				$("#nav ul li.nav_area a i,#nav ul li.nav_type a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_area,#nav ul li.nav_type").removeClass("active");
				$("#nav ul li.nav_area .nav_list,#nav ul li.nav_type .nav_list").hide();
				$("#nav ul li.nav_order .nav_list").show();	
				show_flag_1=1;
				show_flag_2=0;
				show_flag_3=0;
				}
				else{
					$(this).removeClass("active");
					$("#nav ul li.nav_order a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_order .nav_list").hide();
					show_flag_1=0;
					}
			});
		$("#nav ul li.nav_area").click(function(){
			if(show_flag_1==1 || show_flag_2==0 || show_flag_3==1){
				$(this).addClass("active");
				$("#nav ul li.nav_area a i").addClass("fa-sort-asc");
				$("#nav ul li.nav_order a i,#nav ul li.nav_type a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_order,#nav ul li.nav_type").removeClass("active");
				$("#nav ul li.nav_order .nav_list,#nav ul li.nav_type .nav_list").hide();
				$("#nav ul li.nav_area .nav_list").show();	
				show_flag_1=0;
				show_flag_2=1;
				show_flag_3=0;
				}
				else{
					$(this).removeClass("active");
					$("#nav ul li.nav_area a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_area .nav_list").hide();
					show_flag_2=0;
					}
			});
		$("#nav ul li.nav_type").click(function(){
			if(show_flag_1==1 || show_flag_2==1 || show_flag_3==0){
				$(this).addClass("active");
				$("#nav ul li.nav_type a i").addClass("fa-sort-asc");
				$("#nav ul li.nav_order a i,#nav ul li.nav_area a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_order,#nav ul li.nav_area").removeClass("active");
				$("#nav ul li.nav_order .nav_list,#nav ul li.nav_area .nav_list").hide();
				$("#nav ul li.nav_type .nav_list").show();	
				show_flag_1=0;
				show_flag_2=0;
				show_flag_3=1;
				}
				else{
					$(this).removeClass("active");
					$("#nav ul li.nav_type a i").removeClass("fa-sort-asc");
					$("#nav ul li.nav_type .nav_list").hide();
					show_flag_3=0;
					}
			});
		$("#nav ul li.nav_search").click(function(){
			if(show_flag_1==1 || show_flag_2==1 || show_flag_3==1){
				$("#nav ul li.nav_order a i,#nav ul li.nav_type a i,#nav ul li.nav_area a i").removeClass("fa-sort-asc");
				$("#nav ul li.nav_order,#nav ul li.nav_type,#nav ul li.nav_area").removeClass("active");
				$("#nav ul li.nav_order .nav_list,#nav ul li.nav_type .nav_list,#nav ul li.nav_area .nav_list").hide();
				show_flag_1=0;
				show_flag_2=0;
				show_flag_3=0;
				}
				if(show_flag_4==0)
				{
				$("#search").show();
				$(".bu_list ").css("margin-top","78px");
				show_flag_4=1;
				}
				else{
					$("#search").hide();
				$(".bu_list ").css("margin-top","36px");
				show_flag_4=0;
					}
				
		});
		
			
		//获取浏览器高度
		var window_height=$(window).height();
		$(".nav_list_bg").height(window_height-36);
		
		//列表图比例
		var bu_pic_width = $(".bu_list_detail img").width();
		$(".bu_list_detail img").height(3 * bu_pic_width / 7);
	}
	
});