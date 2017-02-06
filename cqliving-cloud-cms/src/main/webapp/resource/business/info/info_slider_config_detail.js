define(['cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', "chosen"],function(tableCurd, cq_ajax, cq_dialog){
	return {
		init: function(){
			tableCurd.initTableCrud();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});		
			//加载配置信息
			loadConfigInfo();			
			//修改app
			changeApp();
			//保存
			save();
		}
	};
	
	/**
	 * 修改app
	 */
	function changeApp(){
		$("body").on("change","#appId",function(){
			//加载配置信息
			loadConfigInfo();
		});
	}
	
	/**
	 * 加载配置信息
	 */
	function loadConfigInfo(){
		//释放保存按钮
		$("#saveBtn").removeAttr("disabled");
		
		var appId = $("#appId").val();
		var url='/module/info_slider_config/common/load_config_info.html';
		var data = {};
		if(appId){
			data['appId'] = appId;
		}
		cq_ajax.load($('#info-div'),url,data,function(){});
	}
	
	/**
	 * 保存
	 */
	function save(){
		$('#saveBtn').on('click',function(){
			var valInps = $(".val-input");
			if(valInps){
				$(this).attr("disabled","disabled");
				var flag = true;
				valInps.each(function(){
	    			var val = $(this).val();
	    			var reg = /^[1-9]+[0-9]*]*$/ ;
	    			if(!val){
	    				$(this).parent().find("span").text("请输入轮播图显示数量");
	    				flag = false;
	    			}else if(!reg.test(val)){
	    				$(this).parent().find("span").text("请输入大于0的整数");
	    				flag = false;
	    			}else{
	    				$(this).parent().find("span").text("");
	    			}
	    		}); 
				if(flag){
					//$("#form1").submit();
					url = $("#form1").attr('action');
					var jthis = $(this);
					var jform = jthis.parents("form");
					var params = jform.serializeArray();
					cq_ajax.ajaxOperate(url,jform,params,function(data,status){
						if(data.code >= 0){
							cq_dialog.success(data.message?data.message:"保存成功",function(){
								//保存按钮可用
								$("#saveBtn").removeAttr("disabled");
								//就停留在这个页面，不进行页面跳转
								/*if(jthis.attr("back_url")){
									var appId = $("#appId").val();
									var back_url = '/module/config_text/config_text_add.html?appId='+appId;
									tableCurd.backRestoreParam(back_url);
								}*/
							});
						}else{
							cq_dialog.error(data.message?data.message:"保存失败");
						}
					});
				}else{
					$("#saveBtn").removeAttr("disabled");
				}
			}
		});
	}
});