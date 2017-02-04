$(function() {
	// 倒计时
	// setTimeout(arguments.callee, 1000);	
	// 掀裙子
	var _x1 = 0,
		_x2 = 0,
		_y1 = 0,
		_y2 = 0;
	$("#audio_btn").on("touchstart", function(e) {
		_x1 = 0, _x2 = 0, _y1 = 0, _y2 = 0;
		var _touch1 = e.originalEvent.targetTouches[0];
		_x1 = _touch1.pageX;
		_y1 = _touch1.pageY;
	});
	$("#audio_btn").on("touchend", function(e) {
		var _touch2 = e.originalEvent.changedTouches[0];
		_x2 = _touch2.pageX;
		_y2 = _touch2.pageY;
		if (Math.abs(_x1 - _x2) < 10 && Math.abs(_y1 - _y2) < 10) {
			// 
			if ($("#media")[0].paused) {
				$("#media")[0].play();
				$(this).addClass("rotate");
			} else {
				$("#media")[0].pause();
				$(this).removeClass("rotate");
			};
		}
	});


	$(document).on("touchend", ".door .btn01", function() {
		$(".open span").addClass("on")
		$('.open span').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
			$(".house-bak").addClass("on")
			$(".door").addClass("page1flipOutX animated")
			setTimeout(function() {
				$(".house-bak").fadeOut("slow");
				$('.door').remove();
				// $(".door").removeClass("page1flipOutX animated")
				setTimeout(function() {
					$(".video_exist").show();
					$("#media")[0].play();
					// 3秒消失
					setTimeout(function() {
						$(".house").fadeOut();

						$(".fullpage").show();
						$("#fullpage").css("height", $(window).height())
						$(".section").css("height", $(window).height())
						$('#fullpage').fullpage({
							scrollOverflow: true,
							paddingTop: 0,
							paddingBottom: 0,
							scrollOverflowOptions: {
								scrollbars: true,
								mouseWheel: true,
								hideScrollbars: false,
								fadeScrollbars: false,
								disableMouse: true
							},
							onLeave: function(index) {
								if (index == 1) {
									$(".sheet-top").hide();
									$(".sheet-bottom").hide();
									$(".hand-tip").hide();
								} else {
									$(".sheet-top").show();
									$(".sheet-bottom").show();
									$(".hand-tip").show();
								};
							},
							afterLoad: function(anchorLink, index) {
								if (index == 1) {
									$(".sheet-top").show();
									$(".sheet-bottom").show();
									$(".hand-tip").show();
								} else {
									$(".sheet-top").hide();
									$(".sheet-bottom").hide();
									$(".hand-tip").hide();
								};
							}
						});

						var sheetAn = null;
						sheetAn = setTimeout(function() {
							if ($(".sheet ul li").eq(2).css("visibility") == "hidden") {
								$(".sheet ul li").eq(0).css("visibility", "visible");
								$(".fp-scroller").css({
										transform: "translate(0px,-0px)"
									})
									// $('.fp-scroller').slimScroll({ scrollTo : 0 });
									// $(".fp-scroller").scrollTo(0,10,200,true);
								$(".sheet ul li").eq(0).css({
									opacity: 1,
									transform: "translateY(0px)"
								})

								$(".sheet ul li").eq(0).find("p").eq(1).addClass('animated fadeInLeft');
								setTimeout(function() {
									$(".sheet ul li").eq(1).css("visibility", "visible");
									var st = $(window).height() - $(".sheet ul li").eq(0).height() * 2 - 60 - ($(".sheet").height() - $(".sheet ul").height())
										// $('.fp-scroller').slimScroll({ scrollTo : st+"px" });
									$(".fp-scroller").css({
											transform: "translate(0px," + st + "px)"
										})
										// $(".sheet").scrollTop($(".sheet ul li").eq(0).height())
									$(".sheet ul li").eq(1).css({
										opacity: 1,
										transform: "translateY(0px)"
									})
									$(".sheet ul li").eq(1).find("p").eq(1).addClass('animated fadeInRight');
									setTimeout(function() {
										$(".sheet ul li").eq(2).css("visibility", "visible");
										var st = $(window).height() - $(".sheet ul li").eq(0).height() * 3 - 90 - ($(".sheet").height() - $(".sheet ul").height());
										// $('.fp-scroller').slimScroll({ scrollTo : st+"px" });
										$(".fp-scroller").css({
											transform: "translate(0px," + st + "px)"
										});
										// $(".sheet").scrollTop($(".sheet ul li").eq(0).height())
										$(".sheet ul li").eq(2).css({
											opacity: 1,
											transform: "translateY(0px)"
										})
										$(".sheet ul li").eq(2).find("p").eq(1).addClass('animated fadeInLeft');
									}, 3000)
								}, 3000)
							}
						}, 1000)
					}, 6000)
				}, 1000)
			}, 2500)
		});
	})



	var lightBg = null;
	var lightNum = 1;

	function light() {
		lightNum++;
		if (lightNum % 2 == 1) {
			$(".light img").css("visibility", "hidden");
		} else {
			$(".light img").css("visibility", "visible");
		};
		lightBg = setTimeout(function() {
			light()
		}, 500)
	}
	light()

	var audiosBg = null;
	var audiosNum = 1;

	function audios() {
		audiosNum++;
		if (audiosNum % 2 == 1) {
			$(".audio img").eq(0).hide();
			$(".audio img").eq(1).show();
		} else {
			$(".audio img").eq(0).show();
			$(".audio img").eq(1).hide();
		};
		audiosBg = setTimeout(function() {
			audios()
		}, 500)
	}
	audios()



	//定义游戏实例
	var game = new soya2d.Game({
		container: '.mylegend' //使用CSS selector指定容器，如果不指定，默认body
	});
	//定义场景
	var scene = new soya2d.Scene({
		onInit: function(game) {
			//资源加载完毕
			$(".loding").hide();
		}
	});
	//加载资源
	var loader = new soya2d.LoaderScene({
		textures: ['/module/daling/front/images/bgm.mp3', "/module/daling/front/images/house-bak.jpg", "/module/daling/front/images/door01l.png", "/module/daling/front/images/door01r.png", "/module/daling/front/images/door-bg.png", "/module/daling/front/images/house-bg.jpg", "/module/daling/front/images/house-audio.png", "/module/daling/front/images/house-audios.png", "/module/daling/front/images/house-cloud01.png", "/module/daling/front/images/house-cloud02.png", "/module/daling/front/images/house-cloud03.png", "/module/daling/front/images/house-cloud04.png", "/module/daling/front/images/house-cloud05.png", "/module/daling/front/images/house-light1.png", "/module/daling/front/images/house-light2.png", "/module/daling/front/images/house-p1.png", "/module/daling/front/images/house-p2.png", "/module/daling/front/images/house-p3.png", "/module/daling/front/images/house-p4.png", "/module/daling/front/images/sheet01.png", "/module/daling/front/images/sheet01s.png", "/module/daling/front/images/sheet02.png", "/module/daling/front/images/sheet02s.png", "/module/daling/front/images/sheet03.png", "/module/daling/front/images/sheet03s.png", "/module/daling/front/images/sheet-bg.jpg", "/module/daling/front/images/sheet-bt1.png", "/module/daling/front/images/sheet-bt2.png", "/module/daling/front/images/sheet-tp1.png"],
		nextScene: scene
	});
	//设置该实例的缩放模式
	game.view.scaleMode = soya2d.SCALEMODE_NOSCALE;
	//启动场景
	game.start(loader);


})