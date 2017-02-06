<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="系统用户管理|/module/security/sys_user_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="${not empty item ? '修改' : '新增' }" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            
		            <input type="hidden" name="id" value="${item.id}" />
		            
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="username">登录用户名</label>
	                            <div class="col-sm-8">
	                            <input type="text" class="form-control" id="username" name="username" maxlength="30" placeholder="请输入登录用户名"  value="${item.username}" onblur="javascript:checkDup();" ${empty item ? ' ' : 'readonly disabled'}>
	                            <span class="help-block" >
		                                <strong id="nameSpan"></strong>
		                        </span>
	                        </div>
	                    </div>
                     <c:if test="${empty item}">
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="password">登录密码</label>
	                            <div class="col-sm-8">
	                            <input type="password" class="form-control" id="password" name="password" maxlength="50" placeholder="请输入登录密码"  value="">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="password">确认登录密码</label>
	                            <div class="col-sm-8">
	                            <input type="password" class="form-control" id="password2" name="password2" maxlength="50" placeholder="请确认登录密码"  value="">
	                        </div>
	                    </div>
                    </c:if>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="nickname">操作员姓名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="nickname" name="nickname" maxlength="30" placeholder="请输入操作员姓名"  value="${item.nickname}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12  ${empty session_user_obj.appId ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right" for="usertype">用户类型</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="usertype" value="1" id="usertype1" ${item.usertype eq 3 ? 'disabled' : '' }><span class="lbl"> 管理员</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="usertype" value="2" id="usertype2" ${item.usertype eq 3 ? 'disabled' : '' }><span class="lbl"> 操作员</span>
                            </label>
                           <label class="radio-2">
                                <input type="radio" class="ace" name="usertype" value="3" id="usertype3" ${item.usertype eq 3 ? 'disabled' : '' }><span class="lbl">区县管理员</span>
                            </label>
                            <c:if test="${item.usertype eq 3 }">
                               <input type="hidden" class="ace" name="usertype" value="${item.usertype }">
                            </c:if>
                            <script type="text/javascript">
                                document.getElementById("usertype${empty item ? 2 : item.usertype}").checked=true;
                            </script>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12 app_form_div hidden">
                        <label class="col-sm-2 control-label no-padding-right" for="appId">所属APP</label>
                        <div class="col-sm-8">
                            <select name="appId"  class="form-control chosen-select  tag-input-style" data-placeholder="请选择所属的App">
                                 <c:forEach items="${allApps }" var="app" varStatus="vs">
                                   <option value="${app.id }" ${app.id eq item.appId or session_user_obj.appId eq app.id ? 'selected':''}>${app.name }</option>
                                 </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">所属角色</label>
                        <div class="col-sm-8">
                            <select name="roleIds"  class="form-control chosen-select tag-input-style" multiple="" data-placeholder="请选择所属的角色">
                                    <c:forEach items="${roles }" var="role">
                                    <option value="${role.id }" 
                                        <c:forEach items="${item.role }" var="mr">
                                            <c:if test="${role.id eq mr.id}">selected</c:if>
                                        </c:forEach>
                                    >${role.roleName }</option>
                                    </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="email">电子邮件</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="email" name="email" maxlength="50" placeholder="请输入电子邮件"  value="${item.email}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="0" id="status0"><span class="lbl"> 禁用</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="1" id="status1"><span class="lbl"> 启用</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="2" id="status2"><span class="lbl"> 锁定</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
                    
                    <%-- <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="expiredDate">过期时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker":true,"timePickerIncrement":1}' name="expiredDate" id="expiredDate" readonly="readonly" value="<fmt:formatDate value="${empty item.expiredDate ? now : item.expiredDate}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div> --%>
                    <%-- <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="unlockDate">解锁时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker":true,"timePickerIncrement":1}' name="unlockDate" id="unlockDate" readonly="readonly" value="<fmt:formatDate value="${empty item.unlockDate ? now : item.unlockDate}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div> --%>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="mobile">手机</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="mobile" name="mobile" maxlength="20" placeholder="请输入手机"  value="${item.mobile}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="qqCode">QQ</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="qqCode" name="qqCode" maxlength="30" placeholder="请输入QQ"  value="${item.qqCode}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="position">职位</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="position" name="position" maxlength="20" placeholder="请输入职位"  value="${item.position}">
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="descn">描述</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" id="descn" name="descn" maxlength="1000" placeholder="请输入描述">${item.descn}</textarea>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="imgUrl">头像</label>
                            <div class="col-sm-8" id="img_upload">
                            <input type="hidden" class="form-control" id="imgUrl" name="imgUrl" maxlength="255" placeholder="请上传头像"  value="${item.imgUrl}">
                            <i class="icon-cloud-upload"></i>
							上传头像	
                           </div>
                    </div>
                   
                   <div class="form-group col-xs-12">
                       <label class="col-sm-2 control-label no-padding-right"></label>
                       <div class="col-sm-8 control-label" id="img_thum">
                         <c:if test="${not empty item.imgUrl }">
                          <ul class="ace-thumbnails">
                             <li>
                               <a href="${item.imgUrl }" data-rel="colorbox">
                                <img alt="" src="${item.imgUrl }" style="width:150px;height:150px;"/>
                               </a>
                             </li>
                          </ul>
                          </c:if>
                       </div>
                   </div>
                    
                	<div class="form-group col-xs-12">
                		<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/security/sys_user_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/security/sys_user_list.html'">
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

var sessionAppid = "${session_user_obj.appId}";

var userRoles = '${userRoles}';
if(userRoles){
	userRoles = eval("("+userRoles+")");
}
function checkDup()
{
	var userName = $(":input[name=username]").val();
	if(!userName)return;
	
	$.ajax({
		type : "POST",
		url : "common/check_name.html",
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

var imgUrl = "${imageUrl}";

require(['validator.bootstrap','cloud.time.input','cloud.table.curd','cqliving_dialog','cqliving_ajax','myUploader','common_colorbox','chosen'], function($,timeInput,tableCurd,cqliving_dialog,cqliving_ajax,myUploader,common_colorbox){
	timeInput.initTimeInput();
	tableCurd.bindSaveButton();
	common_colorbox.init();
	myUploader.init({
		url:"/common/upload.html",
		containerId:"img_upload",
		thumbContainerId:"img_thum",
		success:function(file,response){
			$(":input[name=imgUrl]").val(imgUrl+response.data.filePath);
			$("#img_thum img").attr("src",imgUrl+response.data.filePath);
		},
		error:function(file,reason){
			cqliving_dialog.alert(reason);
		},isSingle:true,fileUrlPath:imgUrl
	});
	
	$(".chosen-select").chosen({width:"311px",search_contains:true});
	
	$(":input[name=usertype]").bind("click",function(){
		var val = $(this).val();
		
		if(val == 2 && !sessionAppid){
			$(".app_form_div").removeClass("hidden");
		}
		if(val == 1){
			$("select[name=appId]").val("");
		}
		
		if(val != 1){
			var appId = $("select[name=appId]").val();
			getRoleByAppId(appId,null);
		}else{
			getRoleByAppId(null,val);
			$(".app_form_div").addClass("hidden");
		}
		
	});
	
	$(":input[name=usertype]:checked").click();
	
	$("select[name=appId]").unbind("change").bind("change",function(){
		
		var appId = $(this).val();
		
		if(!appId) appId = $(this).find("option").attr("value");
		
		getRoleByAppId(appId,null);
	});
	
	function getRoleByAppId(appId,usertype){
		
		cqliving_ajax.ajaxOperate('/module/security/common/sysrole.html',"",{appId:appId,usertype:usertype},function(data,status){
			var list = data.data;
			
			if(list){
				var html = "";
				for(var i in list){
					var role = list[i];
					if(userRoles){
						html +="<option value='"+role.id+"'  ";
						for(var j=0,m=userRoles.length;j<m;j++){
							var urole = userRoles[j];
							if(urole.id == role.id){
								html += "selected";
							}
						}
						html += ">"+role.roleName+"<option>";
					}else{
						html +="<option value='"+role.id+"'>"+role.roleName+"<option>";
					}
				}
				$("select[name=roleIds]").html(html);
				$("select[name=roleIds]").trigger("chosen:updated");
			}else{
				$("select[name=roleIds]").html("");
				$("select[name=roleIds]").trigger("chosen:updated");
			}
		});
	}
	
    $(function(){
        $("#form1").validate({
            rules: {
                    username : {
                    required: true,
                    english:true
                },
                    nickname : {
                    required: true
                },
                    password : {
                    required: true
                },
                password2 : {
                	required: true,
                	equalTo:"#password"
                },
                    usertype : {
                    required: true
                },
                    appId : {
                    required: true,
                    number:true
                },
                    email : {
                    email:true
                },
                    status : {
                    required: true
                },
                    imgUrl : {
                    required: true
                },
                    qqCode : {
                    number:true
                },
                roleIds:{
                	required:true
                }
            },
            messages: {
                username : {
                    required: ' ',
                    english:' '
                },
                nickname : {
                    required: ' '
                },
                password : {
                    required: ' '
                },
                password2 : {
                	required: ' ',
                	equalTo:' '
                },
                usertype : {
                    required: ' '
                },
                appId : {
                    required: ' ',
                    number:' '
                },
                email : {
                    email:' '
                },
                status : {
                    required: ' '
                },
                imgUrl : {
                    required: ' '
                },
                qqCode : {
                    number:' '
                },
                roleIds:{
                	required:' '
                }
            }
        });
    });
});
</script>