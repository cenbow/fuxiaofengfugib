define(['validator.bootstrap','cloud.table.curd','myUploader','cqliving_dialog','common_colorbox','chosen'], function($,tableCurd,myUploader,cqliving_dialog,colorbox) {
	return {
		init: function(){
			$("#position").focus();
			tableCurd.bindSaveButton();
			$(".chosen-select").chosen({search_contains:true});
			colorbox.init();
			//图片初始化
			imgInit();
			//移除图片
			removeRemoveUpload();
			//表单校验
			formValidate();
		}
	};
	
	/**
	 * 图片上传控件初始化
	 */
	function imgInit(){
		myUploader.init({
			url:"/common/upload.html",
			containerId:"img_upload",
			thumbContainerId:"imgView",
			fileUrlPath:imageUrl,
			fileNumLimit:9,
			isSingle:false,
			success:function(file,response){
				var inp = '<input type="hidden" name="urls" value="'+imageUrl+response.data.filePath+'">';
				$('#imgView .upload-imgs:last').append(inp);
				$('#imgView').find("li:last").find('img').eq(0).attr('src',imgUrl+response.data.filePath);
			},
			error:function(file,reason){
				cqliving_dialog.alert(reason);
			}
		});
	}
	
	/**
	 * 删除上传的图片
	 */
	function removeRemoveUpload(){
		$("body").on("click",".icon-remove",function(){
	    	$(this).parents("li").remove();
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
                    name : {
                    required: true
                },
                    nature : {
                    required: true
                },
                    scale : {
                    required: true
                },
                    synopsis : {
                    required: true
                },
                    position : {
                    required: true
                },
                    description : {
                    required: true
                },
                    salary : {
                    required: true
                },
                    numberPeople : {
                    required: true,
                    number:true,
                    digits:true,
                    min:1
                },
                    workmode : {
                    required: true
                },
                    telephone : {
                    required: true
                },
                    address : {
                    required: true
                },
                    publicTime : {
                    required: true,
                    date:true
                },
                    education : {
                    required: true
                },
                    entLabel : {
                    required: true
                }
                /*,
                    sortNo : {
                    required: true,
                    number:true,
                    digits:true,
                    min:1
                }*/
            },
            messages: {
                appId : {
                    required: '请选择所属APP',
                    number:' '
                },
                name : {
                    required: '请输入企业名称'
                },
                nature : {
                    required: '请选择企业性质'
                },
                scale : {
                    required: '请选企业规模'
                },
                synopsis : {
                    required: '请输入企业简介'
                },
                position : {
                    required: '请输入招聘职位'
                },
                description : {
                    required: '请输入职位描述'
                },
                salary : {
                    required: '请选月薪'
                },
                numberPeople : {
                    required: '请输入招聘人数',
                    number:'请输入正整数',
                    digits:'请输入正整数',
                    min:'最小为1'
                },
                workmode : {
                    required: '请选工作性质'
                },
                telephone : {
                    required: '请输入联系电话'
                },
                address : {
                    required: '请输入联系地址'
                },
                publicTime : {
                    required: '请选择发布日期',
                    date:'请选择发布日期'
                },
                education : {
                    required: '请选学历'
                },
                entLabel : {
                    required: '请输入标签'
                }
                /*,
                sortNo : {
                    required: '请输入排序号',
                    number:'排序号是数字',
                    digits:'请输入正整数',
                    min:'最小为1'
                }*/
            }
        });
    }
});