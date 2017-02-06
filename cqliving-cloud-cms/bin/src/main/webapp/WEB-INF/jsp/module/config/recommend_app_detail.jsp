<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="区县推荐APP表，在重庆APP中使用列表|recommend_app_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/recommend_app/recommend_app_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端ID,APP_INFO表ID，一个客户端上面可以有多个推荐客户端<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入客户端ID,APP_INFO表ID，一个客户端上面可以有多个推荐客户端">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="recommendAppId">推荐客户端ID,APP_INFO表ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="recommendAppId" id="recommendAppId" type="text" value="${item.recommendAppId}" maxlength="19" placeholder="请输入推荐客户端ID,APP_INFO表ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="columnsId">推荐客户端的栏目表ID,app_columns表的ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="columnsId" id="columnsId" type="text" value="${item.columnsId}" maxlength="19" placeholder="请输入推荐客户端的栏目表ID,app_columns表的ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="status">状态<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 上线</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="88" id="status88"><span class="lbl"> 下线</span>
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
	                        <label class="col-sm-3 control-label no-padding-right" for="createTime">创建时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="creatorId">创建人ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="creatorId" id="creatorId" type="text" value="${item.creatorId}" maxlength="19" placeholder="请输入创建人ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="creator">创建人名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="creator" name="creator" maxlength="100" placeholder="请输入创建人名称"  value="${item.creator}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="updateTime">更新时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="updateTime" id="updateTime" readonly="readonly" value="<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="updatorId">更新人ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="updatorId" id="updatorId" type="text" value="${item.updatorId}" maxlength="19" placeholder="请输入更新人ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="updator">更新人<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="updator" name="updator" maxlength="100" placeholder="请输入更新人"  value="${item.updator}">
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
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
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/recommend_app/recommend_app_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/recommend_app/recommend_app_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>

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
                    recommendAppId : {
                    required: true,
                    number:true
                },
                    columnsId : {
                    required: true,
                    number:true
                },
                    sortNo : {
                    required: true,
                    number:true
                },
                    status : {
                    required: true
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
                    updateTime : {
                    required: true,
                    date:true
                },
                    updatorId : {
                    required: true,
                    number:true
                },
                    updator : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                recommendAppId : {
                    required: ' ',
                    number:' '
                },
                columnsId : {
                    required: ' ',
                    number:' '
                },
                sortNo : {
                    required: ' ',
                    number:' '
                },
                status : {
                    required: ' '
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
                updateTime : {
                    required: ' ',
                    date:' '
                },
                updatorId : {
                    required: ' ',
                    number:' '
                },
                updator : {
                    required: ' '
                }
            }
        });
    });
});
</script>