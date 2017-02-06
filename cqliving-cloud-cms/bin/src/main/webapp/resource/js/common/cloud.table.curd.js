;define(['cqliving_dialog','cqliving_ajax','toastr','jquery'],function(cqliving_dialog,cq_ajax,toastr){
	
	//定义默认的验证方法，用户可以传参修改
	var valid = function(form){
		if(!form)return false;
		return form.valid();
	}
	
	return {
		initTableCrud: function(){
			//1、查询按钮
			bindTableSearch();
			//2、重置按钮
			bindReset();
			//3、复选框全选
			bindTableSelecAll();
			//4、删除
			bindDeleteButton();
			//5、批量删除
			bindDeleteBatchButton();
			//6、保存
			bindSaveButton();
			//7、导出
			bindDownloadButton();
			//8、设置表格中按钮,鼠标移上去的提示
			initSetButtonTip();
			
			//9、新增，列表数据操作项中的“新增”按钮 add by yuwu 20160706
			bindOpenModelAddButton();
			//10、修改，列表数据操作项中的“修改”按钮 add by yuwu 20160706
			bindOpenModelUpdateButton();
			//11、查看，列表数据操作项中的“查看”按钮 add by yuwu 20160706
			bindOpenModelViewButton();
			
			//12、绑定列表查询事件
			bindEventSearch();
			
			//13、从列表页跳转到新增、修改、查看页面前保存当前列表页的参数（当从列表A页面跳转到B查看页面，当点击B页面的返回按钮再跳回A页面时，需要还原A页面跳转前的查询条件参数）
			bindForwordSaveParamButton();
		},
		//保存按钮提供单独的方法绑定
		bindSaveButton: function(){
			bindSaveButton();
			//14、绑定返回按钮（当从新增、修改、查看页面返回列表前，获取跳转前列表页的参数再跳转）
			bindBackRestoreParamButton();
		},
		//重置form提交前的验证方法
		setValid : function(valid_custom){
			valid = valid_custom;
		},
		//替换后面这种跳转地址方法，window.location.href = jthis.attr("back_url");
		backRestoreParam : function(back_url){
			backRestoreParam(back_url);
		},
		//单独提供绑定"返回"按钮方法
		bindBackRestoreParamButton : function(){
			bindBackRestoreParamButton();
		},
		//带上所有参数加载列表数据，包括页码
		reloadNotChangePageNo : function(){
			_reloadNotChangePageNo();
		}
	};
	
	//13、从列表页跳转到新增、修改、查看页面前保存当前列表页的参数（当从列表A页面跳转到B查看页面，当点击B页面的返回按钮再跳回A页面时，需要还原A页面跳转前的查询条件参数）
	function bindForwordSaveParamButton(){
		$(".main-content").off('click' ,'a[forwordSaveParam],button[forwordSaveParam]').on('click' ,'a[forwordSaveParam],button[forwordSaveParam]', function(){
			//点击按钮前需要保存的formId
			var saveFormId = $(this).attr("save-form-id");
			var paramForm = $("#" + saveFormId);
			if(paramForm.length == 0){
				//如果用户没有配置属性，则查找.page-content元素下的第一个form
				paramForm = $(this).parents(".page-content").find("form:first");
			}
			
			var tempParams = paramForm.serializeArray();
			tempParams = tempParams ? tempParams : [];
			var params = [];
			for(var i = 0 ; i < tempParams.length ; i++){
				//不为空的参数才存cookie
				if(tempParams[i].value){
					params.push(tempParams[i]);
				}
			}
			
			//获取每页分多少行
			var countOfCurrentPage = $(this).parents(".page-content").find(".ajax_pagination").find("select").val();
			//当前多少页
			var pageNo = $(this).parents(".page-content").find(".ajax_pagination").find("li.active a").text();
			//params = params + '&countOfCurrentPage=' + countOfCurrentPage;
			//params = params + '&pageNo=' + pageNo;
			params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
			params.push({"name" : 'pageNo',	"value" : pageNo });
			
			var tempUrl = window.location.pathname;
			var index = tempUrl.indexOf("?");
			if(index != -1){
				tempUrl = tempUrl.substring(0, index);
			}
			//跳转路径url必须配置全路径
			var url = $(this).attr("url");
			/*var tempUrl = url;
			var index = url.indexOf("?");
			if(index != -1){
				tempUrl = url.substring(0, index);
			}*/
			//保存跳转前的参数
			$.cookie(tempUrl, JSON.stringify(params),{path:"/"});
			window.location.href = url;
		});
	}
	
	//14、绑定返回按钮（当从新增、修改、查看页面返回列表前，获取跳转前列表页的参数再跳转）
	function bindBackRestoreParamButton(){
		$(".main-content").off('click' ,"a[backRestoreParam],button[backRestoreParam]").on('click' ,"a[backRestoreParam],button[backRestoreParam]", function(){
			var backUrl = $(this).attr("back_url");
			backRestoreParam(backUrl);
		});
	}
	
	function backRestoreParam(backUrl){
		//获取当前地址栏里面的链接
		var currHref = window.location.pathname;
		var index = currHref.indexOf("?");
		if(index != -1){
			currHref = currHref.substring(0, index);
		}
		//1、先根据当前链接获取跳转前的参数
		var restoreParams = $.cookie(currHref);
		if(!restoreParams){
			index = backUrl.indexOf("?");
			if(index != -1){
				currHref = backUrl.substring(0, index);
			}else{
				currHref = backUrl;
			}
			//2、再根据返回链接获取跳转前的参数
			restoreParams = $.cookie(currHref);
		}
		if(restoreParams){
			//用完后删除cookie
			$.cookie(currHref, null, { path: '/' });
			createAndSubmitForm(backUrl,$.parseJSON(restoreParams));
		}else{
			window.location.href = backUrl;
		}
	}
	
	//1、查询按钮
	function bindTableSearch(){
		$("#searchButton").on("click",function(){
			var paramForm = $(this).parents("form");
			var url = '';
			if(paramForm.length > 0){
				url = paramForm.attr("action");
			}else{
				cqliving_dialog.error("请求url不能为空");
				return;
			}
			var params = paramForm.serializeArray();
			params = params ? params : [];
			//表示请求ajax分页
			var countOfCurrentPage = $(this).parents(".page-content").find(".ajax_pagination").find("select").val();
			params.push({"name" : 'p',	"value" : 'y' });
			//查询时把每页多少条数据参数带上
			params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
			cq_ajax.load("#table_content_page",url,params,function(){});
		});
	}
	
	//2、重置按钮
	function bindReset(){
		//notinclude  不重置的名称,多个用逗号分隔
		$("#resetButton").on("click",function(){
			var paramForm = $(this).parents("form");
			var notInclude = ':button, :submit, :reset';
			var thisNotInclude = $(this).attr("notinclude");
			if(thisNotInclude){
				thisNotInclude = ","+thisNotInclude;
				notInclude += thisNotInclude;
			}
			paramForm.find(":input").not(notInclude)  
			 .val('').removeAttr('checked').removeAttr('selected');
		});
	}
	
	//3、复选框全选
	function bindTableSelecAll(){
		//全选
		$(".main-content").on('click' ,'table th input:checkbox', function(){
			$(this).closest('table').find('tr > td:first-child input:checkbox').prop("checked",$(this).prop("checked"));
		});
		//子项全选后“全选”被选中
		$(".main-content").on('click' ,'table td input:checkbox', function(){
			var jTable = $(this).closest('table');
			jTable.find('th input:checkbox').prop("checked",jTable.find('td input:checkbox').not("input:checked").length == 0);
		});
	}
	
	//4、删除
	function bindDeleteButton(){
		$(".main-content").on('click' ,'#deleteButton', function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			cqliving_dialog.confirm('操作确认','确定要删除该记录吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"删除成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"删除失败");
					}
				});
			});
		});
	}
    
	//5、批量删除
	function bindDeleteBatchButton(){
		$(".main-content").on('click' ,'#deleteBatchButton', function(){
			var jCheckedIds = $('input:checkbox[class=ace]:checked');
			if(jCheckedIds.length == 0){
				//如果没有选择商品则提示用户
				cqliving_dialog.error("请选择需要删除的记录");
			}else{
				var ids = [];
				jCheckedIds.each(function(i,t){
					var id = $(t).attr("id");
					if(id){
						ids.push(id);
					}
				});
				if(ids.length>0){
					var jThis = $(this);//操作按钮
					var url = jThis.attr("url");
					//弹出确认对话框
					cqliving_dialog.confirm('操作确认','确定要批量删除这些记录吗？',function(){
						cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
							if(data.code >= 0){
								cqliving_dialog.success(data.message?data.message:"批量删除成功",function(){
									$("#searchButton").trigger("click");
								});
							}else{
								cqliving_dialog.error(data.message?data.message:"批量删除失败");
							}
						});
					});
				}else{
					cqliving_dialog.error("请选择需要删除的记录");
				}
			}
		});
	}
	
	//6、保存按钮
	function bindSaveButton(){
		$('#commonSaveButton,.commonSaveButton').on('click', function(){
			var jthis = $(this);
			var jform = jthis.parents("form");
			
			if(!valid(jform))return;
			
			var params = jform.serializeArray();
			var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
			url = url ? url : location.href;
			cq_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					cqliving_dialog.success(data.message?data.message:"保存成功",function(){
						if(jthis.attr("back_url")){
							backRestoreParam(jthis.attr("back_url"));
							//window.location.href = jthis.attr("back_url");
						}
					});
				}else{
					cqliving_dialog.error(data.message?data.message:"保存失败");
				}
			});
		});
	}
	
	//7、导出
	function bindDownloadButton(){
		//导出明细
		$("#detailDownload,.detailDownload").on("click",function(){
			var paramForm = $(this).parents("form");
			if(paramForm.length == 0){
				cqliving_dialog.error("请求url不能为空");
				return;
			}
			var url = paramForm.attr("action");
			var formId = paramForm.attr("id");
			download(url,formId,{"isDownload":"true",exportFileName:""});
		});

		var download = function(url, searchFormId, paramData) {
			var params = $('#' + searchFormId).serializeArray();
			//判断传入是否有值
			if(paramData){
				$.each(paramData,function(name,value){
					params.push({
						"name" : name,
						"value" : value
					});
				});
			}
			createAndSubmitForm(url, params);
		};
	}
	
	//创建并提交form
	function createAndSubmitForm(url, objects) {
		var form = $('<form action="' + url + '" method="POST"></form>');
		$.each(objects, function(i, o) {
			if (o.value && o.value.length > 0) {
				form.append('<input type="hidden" name="' + o.name + '" value="'
						+ o.value + '" />');
			}
		});
		form.appendTo($('body')).submit();
		form.remove();
	};
	
	//8、设置表格中按钮,鼠标移上去的提示
	function initSetButtonTip(){
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	}
	
	/**
	 * @param {jquery对象} jthis :点击模态对话框上当前按钮的对象
	 * @param {jquery对象} jModel :当前弹出的模态对象框对象
	 * @param {object对象} param :自己传进去的参数，这里只是透传回来
	 */
	function updateCallBack(jthis,jModel,param){
		var jform = jthis.parents(".modal-content").find("form");
		if(!valid(jform))return;
		
		var params = jform.serializeArray();
		var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
		if(!url){
			cqliving_dialog.error("该操作的请求址不能为空");
		}else{
			cq_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					cqliving_dialog.success(data.message?data.message:"保存成功",function(){
						jModel.modal('hide');
						$("#searchButton").trigger("click");
					});
				}else{
					cqliving_dialog.error(data.message?data.message:"保存失败");
				}
			});
		}
	}
	
	//9、新增，列表数据操作项中的“新增”按钮 add by yuwu 20160706
	function bindOpenModelAddButton(){
		$(".main-content").on('click' ,"a[open-model='add'],button[open-model='add']", function(){
			var options={
				title:$(this).attr("open-title") ? $(this).attr("open-title"):'增加',
				content:"",//没有可以不填写
				url:$(this).attr("url"),
				width:$(this).attr("open-width") ? $(this).attr("open-width"):'700',
				buttons: {
					ok: {  
		                label: "确定",  
		                callback: function (jthis,jModel,param) {
		                	updateCallBack(jthis,jModel,param);
		                }
		            }
		        } 
			};
			cqliving_dialog.model_dialog(options);
		});
	}
	
	//10、修改，列表数据操作项中的“修改”按钮 add by yuwu 20160706
	function bindOpenModelUpdateButton(){
		$(".main-content").on('click' ,"a[open-model='update'],button[open-model='update']", function(){
			var options={
				title:$(this).attr("open-title") ? $(this).attr("open-title"):'修改',
				content:"",//没有可以不填写
				url:$(this).attr("url"),
				width:$(this).attr("open-width") ? $(this).attr("open-width"):'700',
				buttons: {
					ok: {  
		                label: "确定",  
		                callback: function (jthis,jModel,param) {
		                	updateCallBack(jthis,jModel,param);
		                }
		            }
		        } 
			};
			cqliving_dialog.model_dialog(options);
		});
	}
	
	//11、查看，列表数据操作项中的“查看”按钮 add by yuwu 20160706
	function bindOpenModelViewButton(){
		$(".main-content").on('click' ,"a[open-model='view']", function(){
			var options={
				title:$(this).attr("open-title") ? $(this).attr("open-title"):'详情',
				width:$(this).attr("open-width") ? $(this).attr("open-width"):'700',
				url:$(this).attr("url")
			};
			cqliving_dialog.model_dialog(options);
		});
	}
	
	/**
	 * 12、绑定列表查询事件
	 * 12.1、绑定下拉列表框选择后就执行事件
	 * 12.2、绑定时间选择框选择后就执行查询事件
	 * 12.3、输入框，接回车就是直接查询
	 */
	function bindEventSearch(){
		//1、绑定下拉列表框选择后就执行事件
		$(".page-content select").change(function() {
			$("#searchButton").trigger('click');
		});
		//2、绑定时间选择框选择后就执行查询事件
		$(".page-content input[time_options]").on("apply.daterangepicker", function() {
			$("#searchButton").trigger('click');
		});
		//3、输入框，接回车就是直接查询
		$('.page-content :text').not('input[time_options]').bind('keypress',function(event){
            if(event.keyCode == "13"){
            	$("#searchButton").trigger('click');
            }
        });
	}
	
	/**
	 * 刷新当前页面
	 * 参数和页码不变
	 * 例如用户查看第3也的数据，点击了删除后调用此方法
	 * @auto DeweiLi
	 */
	function _reloadNotChangePageNo(){
		//根据searchButton 查询按钮来定位
		var searchButton = $("#searchButton");
		var paramForm = searchButton.parents("form");
		var url = '';
		if(paramForm.length > 0){
			url = paramForm.attr("action");
		}else{
			cqliving_dialog.error("请求url不能为空");
			return;
		}
		var params = paramForm.serializeArray();
		params = params ? params : [];
		//表示请求ajax分页
		var countOfCurrentPage = searchButton.parents(".page-content").find(".ajax_pagination").find("select").val();
		params.push({"name" : 'p',	"value" : 'y' });
		//查询时把每页多少条数据参数带上
		params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
		//把当前页码参数带上
		var pageNo = searchButton.parents(".page-content").find('.dataTables_paginate>.pagination li.active>a').text();
		params.push({"name" : 'pageNo',	"value" : pageNo });
		cq_ajax.load("#table_content_page",url,params,function(){});
	}
});