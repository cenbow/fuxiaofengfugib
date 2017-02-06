<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="位置版本列表|/module/version/place_version_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="位置版本维护" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="appId">APP_ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入APP_ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="placeId">位置ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="placeId" id="placeId" type="text" value="${item.placeId}" maxlength="19" placeholder="请输入位置ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="type">客户端类型</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="type" name="type" maxlength="100" placeholder="请输入客户端类型"  value="${item.type}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="versionNo">版本号</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="versionNo" id="versionNo" type="text" value="${item.versionNo}" maxlength="10" placeholder="请输入版本号">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="publishTime">发布时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="publishTime" id="publishTime" readonly="readonly" value="<fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="createTime">创建时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="creatorId">创建人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="creatorId" id="creatorId" type="text" value="${item.creatorId}" maxlength="19" placeholder="请输入创建人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="creator">创建人名称</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="creator" name="creator" maxlength="100" placeholder="请输入创建人名称"  value="${item.creator}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 有效</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 已删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 3 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
					<div class="form-group col-xs-12">
						<div id="uploader" class="wu-example">
							<!--用来存放文件信息-->
						    <div id="thelist" class="uploader-list"></div>
						    <div class="btns">
						        <div id="picker">选择文件</div>
						        <button id="chunkedUpload_button" type="button" class="btn btn-default">开始上传</button>
						    </div>
						</div>
					</div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/version/place_version_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/version/place_version_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['/resource/business/webupload/chunkedUpload.js'], function(webuploadTest){
	//webuploadTest.init();
});
</script>
<script type="text/javascript">
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    placeId : {
                    required: true,
                    number:true
                },
                    type : {
                    required: true
                },
                    versionNo : {
                    required: true,
                    number:true
                },
                    publishTime : {
                    required: true,
                    date:true
                },
                    createTime : {
                    required: true,
                    date:true
                },
                    creatorId : {
                    required: true,
                    number:true
                },
                    creator : {
                    required: true
                },
                    status : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                placeId : {
                    required: ' ',
                    number:' '
                },
                type : {
                    required: ' '
                },
                versionNo : {
                    required: ' ',
                    number:' '
                },
                publishTime : {
                    required: ' ',
                    date:' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                },
                creatorId : {
                    required: ' ',
                    number:' '
                },
                creator : {
                    required: ' '
                },
                status : {
                    required: ' '
                }
            }
        });
    });
});
</script>