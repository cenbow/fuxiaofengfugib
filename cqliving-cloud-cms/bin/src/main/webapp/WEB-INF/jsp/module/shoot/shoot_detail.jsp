<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="摄影表列表|shoot_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        	<div class="col-md-12 col-lg-8">
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="title">作品标题<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="title" name="title" maxlength="50" placeholder="请输入作品标题"  value="${item.title}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="type">类型<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 小学1-3年级组</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 小学4-6年级组</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="3" id="type3"><span class="lbl"> 初中组</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="4" id="type4"><span class="lbl"> 高中组</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="typeName">类型名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="typeName" name="typeName" maxlength="128" placeholder="请输入类型名称"  value="${item.typeName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="clazzId">班级_id<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="clazzId" id="clazzId" type="text" value="${item.clazzId}" maxlength="19" placeholder="请输入班级_id">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="schoolName">学校名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="schoolName" name="schoolName" maxlength="128" placeholder="请输入学校名称"  value="${item.schoolName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="status">状态<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 正常</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("status${empty item ? 3 : item.status}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="phone">手机号<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="phone" name="phone" maxlength="20" placeholder="请输入手机号"  value="${item.phone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="numberStr">作品ID字符串,000001<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="numberStr" name="numberStr" maxlength="10" placeholder="请输入作品ID字符串,000001"  value="${item.numberStr}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl">图片地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" maxlength="255" placeholder="请输入图片地址"  value="${item.imageUrl}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="descri">作品描述<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="descri" name="descri" maxlength="300" placeholder="请输入作品描述"  value="${item.descri}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="uploadTime">上传时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="uploadTime" id="uploadTime" readonly="readonly" value="<fmt:formatDate value="${item.uploadTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="ipAddr">上传IP地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="ipAddr" name="ipAddr" maxlength="30" placeholder="请输入上传IP地址"  value="${item.ipAddr}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageSourceUrl">原图片地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="imageSourceUrl" name="imageSourceUrl" maxlength="255" placeholder="请输入原图片地址"  value="${item.imageSourceUrl}">
	                        </div>
	                    </div>
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-info btn-sm draft_save view_button" type="button" data-target="#info_view_modal" role="button" data-toggle="modal">
										<i class="icon-eye-open bigger-110"></i>预览
									</button>
									&nbsp;
									<button class="btn btn-primary btn-sm push_save" type="button">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
									&nbsp;
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/shoot/shoot_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shoot/shoot_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    title : {
                    required: true
                },
                    type : {
                    required: true
                },
                    typeName : {
                    required: true
                },
                    clazzId : {
                    required: true,
                    number:true
                },
                    schoolName : {
                    required: true
                },
                    status : {
                    required: true
                },
                    phone : {
                    required: true
                },
                    numberStr : {
                    required: true
                },
                    imageUrl : {
                    required: true
                },
                    descri : {
                    required: true
                },
                    uploadTime : {
                    required: true,
                    date:true
                },
                    ipAddr : {
                    required: true
                },
                    imageSourceUrl : {
                    required: true
                }
            },
            messages: {
                title : {
                    required: ' '
                },
                type : {
                    required: ' '
                },
                typeName : {
                    required: ' '
                },
                clazzId : {
                    required: ' ',
                    number:' '
                },
                schoolName : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                phone : {
                    required: ' '
                },
                numberStr : {
                    required: ' '
                },
                imageUrl : {
                    required: ' '
                },
                descri : {
                    required: ' '
                },
                uploadTime : {
                    required: ' ',
                    date:' '
                },
                ipAddr : {
                    required: ' '
                },
                imageSourceUrl : {
                    required: ' '
                }
            }
        });
    });
});
</script>