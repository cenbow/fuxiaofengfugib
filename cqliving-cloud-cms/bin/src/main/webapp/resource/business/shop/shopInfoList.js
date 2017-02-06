define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax"], function(tableCurd, timeInput, cqDialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//批量下线
			$("#offlineBatchButton").click(offlineBatch);
			//上线
			$("#table_content_page").on("click", ".online_btn", online);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			//控制分类和区域下拉选项
			handleCategoryAndRegion();
			//切换分类
			$("#search_EQ_typeId").change(handleCategoryAndRegion);
			//置顶
			$("#table_content_page").on("click", ".top_btn", top);
			//取消置顶
			$("#table_content_page").on("click", ".untop_btn", untop);
			//审核
			$('.main-content').on('click', '.audit_btn', auditBtn);
			//批量审核
			$('#auditBatchButton').click(auditBatch);
			//查看审核不通过的原因
			$('.main-content').on('click', '.auditReject', viewAuditDesc);
		}
	};
	
	/** 控制分类和区域下拉选项 */
	function handleCategoryAndRegion() {
		var typeId = $("#search_EQ_typeId").val();
		//先清空
		$("#search_EQ_categoryId option:gt(0)").remove();
		$("#search_EQ_regionCode option:gt(0)").remove();
		$("#search_EQ_categoryId, #search_EQ_regionCode").val("");
		if (typeId) {
			//加入对应的选项
			$("#search_EQ_categoryId").append($("#category_option option[typeid=" + typeId + "]").clone());
			$("#search_EQ_regionCode").append($("#region_option option[typeid=" + typeId + "]").clone());
		}
	}
	
	/** 批量下线 */
	function offlineBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[sid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要下线的店铺");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("sid"));
		});
		cqDialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate("/module/shop/shop_info_offline_batch.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
		return false;
	}
	
	/** 上线 */
	function online() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要上线吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
	/** 下线 */
	function offline() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
	/** 置顶 */
	function top() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要置顶吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
	/** 取消置顶 */
	function untop() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要取消置顶吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	/**  审核*/
	function auditBtn(){
		var me = $(this),
			url = me.attr('url'),
			isAudit = me.data('status'),
			auditDesc = me.data('reject-desc');
		
		var noPoint = isAudit || isAudit == 'true';//true：表示有实际的坐标地址
//		if(isAudit || isAudit == 'true'){
		auditWin(url, auditDesc, noPoint);
//		}else{
//			cqDialog.error('缺少实际的坐标地址，请先编辑');
//		}
	}
	/** 审核弹窗 */
	function auditWin(url, auditDesc, noPoint, ids){
		var title = '';
		if(ids){
			title = '批量';
		}
		cqDialog.model_dialog({
			title: title + '审核',
			content: '<textarea class="form-control" name="content" rows="5" maxlength="500" placeholder="请输入审核不通过的原因">'+auditDesc+'</textarea>',
			buttons: {
				aduit_pass: {
					callback: function (jthis,jModel,param) {
						if(!noPoint){
							cqDialog.error('缺少实际的坐标地址，不可以通过审核');
							return ;
						}
						var content = $(jModel).find('textarea[name=content]').val();
						doAudit(jthis, jModel, 'pass', content, url, ids);
					}
				},
				aduit_reject: {
					callback: function (jthis,jModel,param) {
						var content = $(jModel).find('textarea[name=content]').val();
						if(content == ''){
							cqDialog.error('请输入不通过的原因');
							return ;
						}
						doAudit(jthis, jModel, 'reject', content, url, ids);
					}
				}
			}
		});
	}
	/** 审核状态保存 */
	function doAudit(jthis, jModel, status, content, url, ids){
		cq_ajax.ajaxOperate(url, jthis, {content: content, status: status, ids: ids}, function(data, status) {
			if (data.code >= 0) {
				jModel.modal('hide');
				cqDialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		});
	}
	/** 批量审核*/
	function auditBatch(){
		var me = $(this),
			url = me.attr('url');
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqDialog.error("请选择需要审核的记录");
		}else{
			var ids = [];
			var noPoint = false;
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id"),
					isAudit = $(t).closest('tr').find('.audit_btn').data('status');
				if(!noPoint){
					noPoint = isAudit || isAudit == 'true';
				}
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				auditWin(url, '', noPoint, ids);
			}else{
				cqDialog.error("请选择需要审核的记录");
			}
		}
	}
	/** 查看未通过原因*/
	function viewAuditDesc(){
		var me = $(this),
			html = me.find('p').html();
		cqDialog.model_dialog({
			title: '审核未通过原因',
			content: html
		});
	}
	
});