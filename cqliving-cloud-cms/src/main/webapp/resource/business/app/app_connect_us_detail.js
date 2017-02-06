define(['validator.bootstrap','cloud.table.curd','cqliving_ajax','ZeroClipboard','cqliving_dialog','ueditor','chosen'], function($,tableCurd,cqliving_ajax,zeroClipboard,dialog){
	
	return {
		init: function(){
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//页面按钮绑定事件
			tableCurd.bindSaveButton();
			window.ZeroClipboard = zeroClipboard;
			//表单校验
			formValidate();
			//修改app
			changeApp();
			//加载联系我们的内容
			loadContent();
			//预览
			preview();
			
		}
	};
	
	/**
	 * 预览
	 */
	function preview(){
		$("#preview").on("click",function(){
			var content = ue.getContent();
			if(content){
				$(".overflow-visible").html(content);
				$(".preview-form-a").click();
				var $img = $(".overflow-visible").find("img");
				if($img.length>=1){
					$img.each(function(i,n){
						var $this = $(n);
						var w = $this.width();
						//获取预览框宽度
						//var maxWidth = document.getElementsByClassName("overflow-visible").offsetWidth;
						var maxWidth = $(".overflow-visible").width();
						//若预览框宽度未获取到或者图片宽度大于预览框宽度,将图片设置为90%宽度
						if((w && w>maxWidth) || !maxWidth){
						  $this.css("width","100%");
						  $this.css("height","auto");
						}
					});
				}
				var $map = $(".overflow-visible").find(".ueditor_baidumap");
				if($map.length>=1){
					$map.each(function(i,n){
						var $this = $(n);
						var w = $this.width();
						//获取预览框宽度
						var maxWidth = $(".overflow-visible").width();
						//若预览框宽度未获取到或者图片宽度大于预览框宽度,将图片设置为90%宽度
						if((w && w>maxWidth) || !maxWidth){
						  $this.css("width","100%");
						  $this.css("height","auto");
						}
					});
				}
			}else{
				dialog.error("请填写内容");
			}
		});
	}
	
	/**
	 * 表单校验
	 */
	function formValidate(){
		$("#form1").validate({
			rules: {
	                appId : {
	                required: true,
	                number:true
	            },
	                content : {
	                required: true
	            }
		    },
	        messages: {
	            appId : {
	                required: '请选择所属APP'
	            },
	            content : {
	                required: '请填写联系我们的内容'
	            }
	       }
        });
	}
	
	/**
	 * 修改app
	 */
	function changeApp(){
		$("body").on("change","#appId",function(){
			loadContent();
		});
	}
	
	/**
	 * 加载联系我们的内容
	 */
	function loadContent(){
		var appId = $("#appId").val();
		if(appId){
			var url='/module/app_connect_us/common/query_by_appId.html';
			var data = {};
			data['appId'] = appId;
			cqliving_ajax.ajaxOperate(url,null,data,function(data,status){
				if(data.code >= 0){
					var content = "";
					if(data.data && data.data.content){
						//设置内容 
						content = data.data.content;
						$("#content").val(content);
					}
					//第一次（初始化之前）将数据放到script标签
					var jEditor = $("script[id='content_editor']");
					jEditor.html(content);
					//初始化
					ue = initUeditor("content_editor");
					//绑定change事件
					ue.addListener('contentChange',function(){
						var content = ue.getContent();
						$("#content").val(content);
					});
					//jEditor.length == 0表示文本编辑器已经初始化完成
					if(jEditor.length == 0){
						//清空内容
						$("#content").val("");
						ue.execCommand('cleardoc');
						//设置内容
						if(content){
							ue.setContent(content);
						}
					}
				}
			});
			var backUrl = '/module/app_connect_us/to_add.html?appId='+appId;
			$("#commonSaveButton").attr("back_url",backUrl);
		}
		
	}
	
	/**
	 * 初始化百度编辑器
	 */
	function initUeditor(id){
		var ue = UE.getEditor(id,{  
			//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
			toolbars:[['source', '|', 'undo', 'redo', '|',
			           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			           'directionalityltr', 'directionalityrtl', 'indent', '|',
			           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
			           'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
			           'simpleupload', 'insertimage', 'emotion', 'scrawl','attachment', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
			           'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
			           'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
			           'print', 'preview', 'searchreplace', 'help', 'drafts']]
		});
		return ue;
	}
});