define(['cloud.table.curd','cloud.time.input', 'cqliving_dialog', 'cqliving_ajax', 'chosen'], function(tableCurd,timeInput, cq_dialog, cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			
			//审核
			$('.checkButton').on('click', function(){
				checkFn($(this), null);
			});
			//批量审核
			$('#batchCheckButton').on('click', batchCheckFn);
			//发布
			$('body').on('click', '.publishButton', publishFn)
			//下线
			$('body').on('click', '.onlineDownButton', onlineDownFn)
			//上线
			$('body').on('click', '.onlineUpButton', onlineUpFn)
			//批量下线
			$('#batchOnlineDownButton').on('click', batchOnlineDownFn);
			
			//置顶、推荐到首页
			$('body').on('click', '.topAndRecommendButton', topAndRecommendFn);
			//取消置顶、取消推荐到首页
			$('body').on('click', '.cancelTopAndRecommendBtn', cancelTopAndRecommendFn);
			//绑定改变app事件
			$('#search_EQ_appId').on('change', changeApp);
		}
	};

	/**
	 * 操作成功
	 */
	function operateSuccess(data, jModel) {
		if(data.code >=0){
			if(jModel){
				jModel.modal('hide');
			}
			cq_dialog.success('操作成功！', function(){
				$('#searchButton').click();
			});
		}else{
			cq_dialog.error(data.message ? data.message : '操作失败！');
		}
	}
	
	/**
	 * 批量审核
	 */
	function batchCheckFn(){
		var jThis = $(this),
			jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			cq_dialog.alert("请选择需要审核的记录");
		}else{
			var ids = [];
			var validateStatus = true;
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					if($(t).data('status') == '1' && $(t).data('sourcetype') == '1'){//是待审核状态且是app发布的话题
						ids.push(id);
					}else{
						validateStatus = false;
					}
				}
			});
			if(validateStatus && ids.length > 0){
				checkFn(jThis, ids);
			}else{
				cq_dialog.alert("请选择需要审核的记录");
			}
		}
	}
	/**
	 * 审核
	 */
	function checkFn(me, ids){
		cq_dialog.model_dialog({
			title: '审核确认',
			content: '请确认审核',
			buttons:{
				aduit_pass: {
					param: {ids: ids, status: 3, url: me.attr('url')},
					callback: doCheck
				},
				aduit_reject: {
					param: {ids: ids, status: -1, url: me.attr('url')},
					callback: doCheck
				}
			}
		});
	}
	function doCheck(jthis, jModel, params){
		var par = {status: params.status};
		if(params.ids){
			par.id = params.ids.join(',');
		}
		cq_ajax.ajaxOperate(params.url, jthis, par, function(data){
			operateSuccess(data, jModel)
		});
	}
	
	/**
	 * 发布
	 */
	function publishFn(){
		var  me = $(this);
		cq_dialog.confirm('确认发布', '确定要发布所选吗？', function(){
			cq_ajax.ajaxOperate(me.attr('url'), me, {}, function(data){
				operateSuccess(data, null);
			});
		});
	}
	
	/**
	 * 下线
	 */
	function onlineDownFn(){
		var me = $(this);
		cq_dialog.confirm('确认信息', '确定要下线吗？', function(){
			doOnline(me, 88, null);
		});
	}
	/**
	 * 上线
	 */
	function onlineUpFn(){
		var me = $(this);
		cq_dialog.confirm('确认信息', '确定要上线吗？', function(){
			doOnline(me, 3, null);
		});
	}
	/**
	 * 批量下线
	 */
	function batchOnlineDownFn(){
		var jThis = $(this),
			jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			cq_dialog.alert("请选择需要下线的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					if($(t).data('status') == '3'){//只对发布的数据操作
						ids.push(id);
					}
				}
			});
			if(ids.length > 0){
				cq_dialog.confirm('确认信息', '确定要下线吗？', function(){
					doOnline(jThis, 88, ids);
				});
			}else{
				cq_dialog.alert("请选择需要下线的记录");
			}
		}
	}
	function doOnline(jThis, status, ids){
		var par = {status: status};
		if(ids){
			par.id = ids.join(',')
		}
		cq_ajax.ajaxOperate(jThis.attr('url'), jThis, par, function(data){
			operateSuccess(data, null)
		});
	}

	/**
	 * 置顶、推荐到首页
	 */
	function topAndRecommendFn(){
		var me = $(this);
		cq_dialog.model_dialog({
			title: me.data('original-title'),
			url: me.attr('modelurl'),
			buttons:{
				ok: {
					callback: function(jThis, jModel, params){
						var imageUrlObj = $(jModel).find('.modal-body').find('input[name=imageUrl]');
						var imageUrl = "";
						if(imageUrlObj.length > 0){
							imageUrl =imageUrlObj.val();
						}
						if(imageUrl == ''){
							cq_dialog.error('请上传图片');
							return ;
						}
						cq_ajax.ajaxOperate(me.attr('url'), jThis, {imageUrl: imageUrl, status: 1}, function(data){
							operateSuccess(data, jModel)
						});
					}
				}
			}
		});
	}
	/**
	 * 取消置顶、取消推荐
	 */
	function cancelTopAndRecommendFn(){
		var jThis = $(this);
		cq_dialog.confirm('确认信息', '确定要'+jThis.data('original-title')+'吗？', function(){
			cq_ajax.ajaxOperate(jThis.attr('url'), jThis, {status: 0}, function(data){
				operateSuccess(data, null)
			});
		});
	}
	
	/**
	 * 改变app 获得分类信息
	 */
	function changeApp(){
		var me = $(this),
			appId = me.val(),
			typesObj = $('#search_LIKE_types'),
			url = '/module/config/7/common/getByApp.html';
		if(appId == ''){
			typesObj.html('');
			typesObj.trigger("chosen:updated");
			return ;
		}
		cq_ajax.ajaxOperate(url, null, {appId:appId}, function(data){
			if(data.code >= 0){
				var html = '<option value="">所有分类</option>';
				$.each(data.data, function(i, d){
					html += '<option value="'+d.id+'">'+d.name+'</option>';
				});
				typesObj.html(html);
				typesObj.trigger("chosen:updated");
			}else{
				cq_dialog.error(data.message ? data.message : '获取分类信息失败');
			}
		});
	}
});