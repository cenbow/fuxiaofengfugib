/**
 * Created by Administrator on 2015/3/13.
 */
//设置加载配置
(function(){

    var require_config = {
        urlArgs: "v=",
        baseUrl: '/module/skp/photo/com/',
        paths:
        {
            jquery: "../../front/js/jquery-1.11.3.min",
            wx: "http://res.wx.qq.com/open/js/jweixin-1.0.0"
        }
    };
    require_config.urlArgs += version;
    require_config = drawingBoards.require(require_config, "../");
    require_config = photo.require(require_config, "../");
    requirejs.config(require_config);
})();

require(
    ["wx", "jquery", "photo", "drawingBoards"],function (wx) {

        document.getElementById("file_UserPic");

        //初始化绘图板
        var _mycanvas = document.getElementById("mycanvas");
        var _db = new drawingBoards.DrawingBoards(_mycanvas);
        var width = $(window).width() * 0.715;
        _db.setSize(width, 700 / 500 * width);
        //_db.setSize(240, 320);

        //初始化相片处理器
        var _photo = new photo.Photo(_db);

        //用户选择了图片
        var  _cameraInput = document.getElementById("cameraInput");
        var _fileReader = new FileReader();
        var _image = new Image();

//        _photo.addAdorn("1", "/front/images/i_1.jpg", -1, -1, true);//添加饰品
//        _photo.addAdorn("2", "/front/images/i_1.jpg", 10, 10, true);//添加饰品

        _cameraInput.addEventListener("change", onCameraInputChangeHandler);//监听选择的文件改变时
        _fileReader.addEventListener("load", onFileReaderLoadHandler);//监听文件加载完成是
        _image.addEventListener("load", onImageLoadHandler);//图片加载完成

        /**
         * 用户选择了图片
         * */
        function onCameraInputChangeHandler() {

            _fileReader.readAsDataURL(_cameraInput.files[0]);
        }

        /**
         * 文件加载完成时
         * */
        function onFileReaderLoadHandler(event){

            _image.src = event.target.result;
        }

        /**
         * 图片加载完成
         * */
        function onImageLoadHandler(){

            _photo.setPhoto(_image);
        }
        
        //-----------------------华丽的分割线-----------------------------
        var accId;
        
        $(function() {
        	$(".picScroll-left").slide({
        			mainCell:".bd ul",
        			autoPage:false,
        			effect:"left",
        			scroll:1,
        			vis:5,
        			pnLoop:true,
        			trigger:"click"
        	});
        	//弹出层高度居中
        	$(".pop_con").each(function(){
        		$(this).css("margin-top", -$(this).height() / 2);
        	});
        	
        	accId = $("#acc_id").val();
        });
        
        //合成图片
        $("#make_png_btn").click(function() {
        	var foto = _photo.getBitmap();
        	var obj = _photo.getAdorns();
        	var sps = "";
        	for (x in obj) {
        		if (x.indexOf("sp") >= 0) {
        			if (sps == "") {
        				sps += x;
        			} else {
        				sps += "," + x;
        			}
        		}
        	}
        	//上传图片，保存相框、饰品分数
        	var url = "/skp/" + accId + "/mk.html";
        	var data = {
        			"foto": foto,
        			"xk": "xk_" + currXk,
        			"sps": sps
        	};
        	$.post(url, data, function(data) {
        		if (data.success) {
        			if (data.data.isFirstTime) {
        				//首次拍照完成显示弹层
        				$(".pop_layer, .pop_con").show();
        			} else {
        				//去个人主页
        				location.href = "/skp/" + accId + "/my.html?src=mk";
        			}
        		} else {
        			console.log(data.message);
        			$(".pop_layer").hide();
        		}
        	}, "json");
        	//遮罩层
        	$(".pop_layer").show();
        });
        
        //添加/移除饰品
        $("#sp_list img").click(function() {
        	var eid = $(this).attr("eid");
        	if (_photo.isHave(eid)) {	//移除
        		_photo.remAdorn(eid);
        	} else {	//添加
        		var src = $("#deco_list img[eid=" + eid + "]").attr("src");
        		_photo.addAdorn(eid, src, -1, -1, true);
        	}
        });
        
        //添加默认图片
        _image.src = "/module/skp/front/images/photo.jpg";
        //添加第一个相框
        _photo.addAdorn("xk", "/module/skp/front/images/xk_1.png", 0, 0, false, true);
        
        var currXk = 1;		//当前相框
        var xkCount = $("#frame_list img").length;	//相框数
        
        //下一个相框
        $("#btn_xk_next").click(function() {
        	if (++currXk > xkCount) {
        		currXk = 1;
        	}
        	_photo.addAdorn("xk", "/module/skp/front/images/xk_" + currXk + ".png", 0, 0, false, true);
        });
        
        //上一个相框
        $("#btn_xk_prev").click(function() {
        	if (--currXk <= 0) {
        		currXk = xkCount;
        	}
        	_photo.addAdorn("xk", "/module/skp/front/images/xk_" + currXk + ".png", 0, 0, false, true);
        });
        
        //关注弹层确定
        $("#follow_pop_btn").click(function() {
        	//转到个人主页
        	location.href = "/skp/" + accId + "/my.html?src=mk";
        });
        
        //图片旋转
        $("#btn_rotate").click(function() {
        	_photo.setPhotoAngle( _photo.getPhotoAngle() + 90);
        });
        
        //制作教程
        $("#btn_tutorial").click(function() {
        	location.href = "/skp/" + accId + "/tu.html";
        });
    
        //分享
        wx.config($.parseJSON($("#wx_config").html()));
        var userId = $("#user_id").val();
        var shareTitle;
		var shareDesc;
		var shareLink;
		
		//查询用户是否已经拍过时尚大片
		var url = "/skp/" + accId + "/ho.html";
		$.post(url, {}, function(data) {
			if (data.success) {
				shareTitle = "北京SKP提示你，快为好友的时尚大片助力吧！";
				shareDesc = "北京SKP美妆世界盛大开幕，助力好友为TA加油吧！";
				shareLink = host + "/skp/" + accId + "/fd/" + userId + ".html";
			} else {
				shareTitle = "定制您的时尚大片，北京SKP惊喜送不停！";
				shareDesc = "北京SKP美妆世界盛大开幕，快来拍摄时尚大片吧！";
				shareLink = host + "/skp/" + accId + "/idx.html";
			}
		});
		var shareImgUrl = host + "/module/skp/front/images/share.jpg";
		wx.ready(function() {
			//TODO tt 分享给朋友
			wx.onMenuShareAppMessage({
				title : shareTitle,
				desc : shareDesc,
				link : shareLink,
				imgUrl : shareImgUrl,
				trigger : function(res) {},
				success : function(res) {
					//关闭分享提示弹层
					$(".pop_layer, .pop-tip").hide();
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			
			//TODO tt 分享到朋友圈
			wx.onMenuShareTimeline({
				title : shareTitle,
				link : shareLink,
				imgUrl : shareImgUrl,
				trigger : function(res) {},
				success : function(res) {
					//关闭分享提示弹层
					$(".pop_layer, .pop-tip").hide();
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			wx.error(function(res) {});
		});
    }
);