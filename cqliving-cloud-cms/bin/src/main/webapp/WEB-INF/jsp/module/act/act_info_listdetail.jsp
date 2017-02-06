<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="appId">客户端_ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入客户端_ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actInfoId">活动ID（act_info表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actInfoId" id="actInfoId" type="text" value="${item.actInfoId}" maxlength="19" placeholder="请输入活动ID（act_info表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="type">活动类型</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 公告</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 外链</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="3" id="type3"><span class="lbl"> 投票</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="4" id="type4"><span class="lbl"> 答题</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="5" id="type5"><span class="lbl"> 报名</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="6" id="type6"><span class="lbl"> 问卷</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="7" id="type7"><span class="lbl"> 征集</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="8" id="type8"><span class="lbl"> 抽奖</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="1" id="status1"><span class="lbl"> 未激活</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 已激活</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="linkUrl">外链地址</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="linkUrl" name="linkUrl" maxlength="255" placeholder="请输入外链地址"  value="${item.linkUrl}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="startTime">活动开始时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="startTime" id="startTime" readonly="readonly" value="<fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="endTime">活动结束时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="endTime" id="endTime" readonly="readonly" value="<fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd" />">
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
                        <label class="col-sm-4 control-label no-padding-right" for="creatorId">创建人</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="creatorId" id="creatorId" type="text" value="${item.creatorId}" maxlength="19" placeholder="请输入创建人">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="creator">创建人姓名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="creator" name="creator" maxlength="100" placeholder="请输入创建人姓名"  value="${item.creator}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updateTime">更新时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="updateTime" id="updateTime" readonly="readonly" value="<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updatorId">更新人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="updatorId" id="updatorId" type="text" value="${item.updatorId}" maxlength="19" placeholder="请输入更新人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updator">更新人</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="updator" name="updator" maxlength="100" placeholder="请输入更新人"  value="${item.updator}">
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_info_listlist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_info_listlist.html'">
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
                    actInfoId : {
                    required: true,
                    number:true
                },
                    type : {
                    required: true
                },
                    status : {
                    required: true
                },
                    linkUrl : {
                    required: true
                },
                    startTime : {
                    required: true,
                    date:true
                },
                    endTime : {
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
                actInfoId : {
                    required: ' ',
                    number:' '
                },
                type : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                linkUrl : {
                    required: ' '
                },
                startTime : {
                    required: ' ',
                    date:' '
                },
                endTime : {
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