define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//列表页面提示处理
			$('body').tooltip({selector:'[data-rel=tooltip]'});
			
			//预览
			$("#table_content_page").on("click", ".preview_btn", preview);
			
			/**
			 * 导出
			 */
			$('body').on("click","#export",function(){
				var url = $(this).attr("url");
				var param = $("#user_info_reply_FormId").serialize();
				url += "?"+param;
				window.location.href=url;
			});
			
			var ids = [] ,sourceIds = [];
			var auditingUrl="";
			/**
			 * 批量审核按钮
			 */
			$('body').on("click",".auditing-btn",function(){
				auditingUrl="";
				$("#auditingContent").val('');
				ids = [],sourceIds = [];
				var noAuditing = $(this).attr("noAuditing");
				var jCheckedIds = $('input:checkbox[class=ace]:checked');
				if(jCheckedIds.length == 0){
					cqliving_dialog.alert("请选择需要审核的记录");
				}else{
					jCheckedIds.each(function(i,t){
						var id = $(t).attr("id");
						var sourceId = $(t).attr("sourceId");
						var status = $(this).attr('status');
						if(id&&status==noAuditing){
							ids.push(id);
							sourceIds.push(sourceId);
						}
					});
					if(ids.length>0){
						$('.auditing-btn-a').trigger("click");
					}else{
						cqliving_dialog.alert("请选择状态为待审核的记录");
					}
				}
			});
			
			/**
			 * 单个审核按钮
			 */
			$('body').on("click","#auditing-one",function(){
				auditingUrl = $(this).attr("url");
				$("#auditingContent").val('');
				ids = [],sourceIds = [];
				var noAuditing = $(".auditing-btn").attr("noAuditing");
				var $checkbox = $(this).closest("tr").find(".ace");
				var id = $checkbox.attr("id");
				var sourceId = $checkbox.attr("sourceId");
				if(id){
					ids.push(id);
					sourceIds.push(sourceId);
				}
				$('.auditing-btn-a').trigger("click");
			});
			
			/**
			 * 审核通过/不通过按钮
			 */
			$('body').on("click",".btn-auditing",function(){
				var auditingContent = $.trim($("#auditingContent").val());
				var status = $(this).attr("status");
				var sourceType = $("#sourceType").val();
				var url = $('.auditing-btn-a').attr("url");
				url = auditingUrl?auditingUrl:url;
				url += "?status="+status+"&auditingContent="+auditingContent+"&sourceType="+sourceType;
				cq_ajax.ajaxOperate(url,null,{"ids":ids,"sourceIds":sourceIds},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"操作成功",function(){
							$("#searchButton").trigger("click");
							$("#modal-form").modal("hide");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"操作失败");
						$("#modal-form").modal("hide");
					}
				});
				var status = $(this).attr('status');
			});
			
		}
	};
	
	/** 预览 */
	function preview() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cq_ajax.ajaxOperate(url, "body", {}, function(data, status) {
			$("body").append(data);
		}, {dataType: "html"});
	}
});