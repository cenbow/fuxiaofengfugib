<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="子帐号列表|/module/wz/wz_user_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="子帐号新增" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/wz/<c:if test="${not empty item}">wz_user_update.html</c:if><c:if test="${empty item}">wz_user_add.html</c:if>">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"><span class="highlight">*</span>登录用户名</label>
                        <div class="col-sm-9">
                            <input type="text" maxlength="10" class="form-control" id="username" name="username" placeholder="请输入登录用户名"  value="${item.username}" onblur="javascript:checkDup();" ${empty item ? ' ' : ' disabled'} >
                            <span class="help-block" >
                                <strong id="nameSpan"></strong>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"><span class="highlight">*</span>机构名称</label>
                        <div class="col-sm-9">
                            <input type="text" maxlength="10" class="form-control" id="orgName" name="orgName" placeholder="请输入机构名称"  value="${wzUser.orgName}">
                            <span class="help-block" >
                                <strong id="nameSpan"></strong>
                            </span>
                        </div>
                    </div>
                    <c:if test="${empty item}">
                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label"><span class="highlight">*</span>登录密码</label>
                            <div class="col-sm-9">
                                <input name="password" type="password" class="form-control" id="password" placeholder="请输入登录用户密码" maxlength="30">
    
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label"><span class="highlight">*</span>再次输入登录密码</label>
                            <div class="col-sm-9">
                                <input name="password2" type="password" class="form-control" id="password2" placeholder="请输入登录用户密码" maxlength="30">
    
                            </div>
                        </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">电子邮件</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="email" name="email" placeholder="请输入电子邮件"  value="${item.email}">

                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">手机号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入手机号"  value="${item.mobile}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">QQ</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="qqCode" name="qqCode" placeholder="请输入QQ号"  value="${item.qqCode}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">职位</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="position" name="position" placeholder="请输入职位"  value="${item.position}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">头像</label>
                        <div class="col-sm-9" id="img_upload">
                            <i class="icon-cloud-upload"></i>
								上传头像	
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                        <div class="col-sm-9" id="img_thum">
                        	<c:if test="${not empty item.imgUrl}">
	                        	<ul class="ace-thumbnails">
	                        		<li id="WU_FILE_0" class="upload-imgs">
	                        			<a href="${item.imgUrl}" data-rel="colorbox" class="cboxElement">
	                        				<img alt="150x150" src="${item.imgUrl}" style="width:150px;height:150px;">
	                       				</a>
	                       				<div class="tools tools-top">
	                       					<a href="javascript:;"><i class="icon-remove red"></i></a>
	                       				</div>
	                       				<input type="hidden" class="form-control" name="imgUrl" value="${item.imgUrl}">
	                     			</li>
	                        	</ul>
                        	</c:if>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述</label>
                        <div class="col-sm-9">
                          <textarea name="descn" rows="5" class="form-control" id="descn" placeholder="请输入描述信息" maxlength="80">${item.descn}</textarea>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9 radio">
                        
                            <label class="radio-2">
								<input type="radio" class="ace" name="status" id="status0" value="0">
								<span class="lbl">禁用</span>
							</label>
                            <label class="radio-2">
								<input type="radio" class="ace" name="status" id="status1" value="1">
								<span class="lbl">启用</span>
							</label>
                            
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
                            </script>

                        </div>
                    </div>
	                    
                	<div class="form-group">
                		<div class="col-sm-11">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="sub_form1">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/wz/wz_user_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
var imageUrl = '${imageUrl}';
function checkDup(){
	var userName = $(":input[name=username]").val();
	if(!userName){return;}
	
	$.ajax({
		type : "POST",
		url : "/module/security/common/check_name.html",
		data : $('#form1').serialize(),
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if(typeof data !== Object){
				data = eval("("+data+")");
			}
			if(data.code < 0 )
			{
				$('#nameSpan').css('color','red');
				$('#nameSpan').html(data.message);
				$("#sub_form1").addClass("disabled");
				$(this).addClass('red');
			}else{
				$('#nameSpan').css('color','green');
				$('#nameSpan').html(data.message);
				$("#sub_form1").removeClass("disabled");
			};
		}
	});
};
require(['validator.bootstrap','cloud.table.curd', 'myUploader', 'common_colorbox', "cqliving_dialog", "cqliving_ajax"], function($,tableCurd, myUploader, colorbox, cqliving_dialog, cq_ajax){
	tableCurd.bindSaveButton();
	colorbox.init();
	
	myUploader.init({
		url:"/common/upload.html",
		containerId:"img_upload",
		thumbContainerId:"img_thum",
		fileUrlPath: imageUrl,
		success:function(file,response){
			$('.ace-thumbnails .upload-imgs').append('<input type="hidden" class="form-control" name="imgUrl" value="'+imageUrl+response.data.filePath+'">');
		},
		error:function(file,reason){
			cqliving_dialog.alert(reason);
		},isSingle:true
	});
	
	$('#sub_form1').click(function(){
		var myForm = $('#form1'),
			me = $(this);
		if(myForm.valid()){
			cq_ajax.ajaxOperate(myForm.attr('action'),me,myForm.serialize(),function(data,status){
				if(data.code >= 0){
					cqliving_dialog.success('保存成功', function(){location.href='/module/wz/wz_user_list.html';});
				}else{
					cqliving_dialog.error(data.message?data.message:"保存失败");
				}
			});
		}
	});
	
    $(function(){
        $("#form1").validate({
        	rules: {
        		username: {
        			required: true
        		},
        		password: {
        			required: true
        		},
        		password2: {
        			required: true,
        			equalTo: '#password'
        		},
	        	usertype: {
	        		required: true,
	        		number: true
	        	},
        		orgName: {
        			required: true
        		}
        		
        	},
        	message: {
        		username: {
        			required: ''
        		},
        		password: {
        			required: ''
        		},
        		password2: {
        			equalTo: '两次密码不一致'
        		},
	        	usertype: {
	        		required: ''
	        	},
	    		orgName: {
	    			required: ''
	    		}
        	}
        });
        //
    });
});
</script>