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
                        <label class="col-sm-4 control-label no-padding-right" for="informationId">实际对应的资讯ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="informationId" id="informationId" type="text" value="${item.informationId}" maxlength="19" placeholder="请输入实际对应的资讯ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="columnsId">栏目ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="columnsId" id="columnsId" type="text" value="${item.columnsId}" maxlength="19" placeholder="请输入栏目ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="classfieViewStatus">栏目新闻显示状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="classfieViewStatus" value="0" id="classfieViewStatus0"><span class="lbl"> 显示</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="classfieViewStatus" value="1" id="classfieViewStatus1"><span class="lbl"> 不显示</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("classfieViewStatus${empty item ? 0 : item.classfieViewStatus}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sortNo">排序号</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="listViewType">列表显示类型</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listViewType" value="0" id="listViewType0"><span class="lbl"> 无图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listViewType" value="1" id="listViewType1"><span class="lbl"> 单图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listViewType" value="2" id="listViewType2"><span class="lbl"> 大图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listViewType" value="3" id="listViewType3"><span class="lbl"> 多图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listViewType" value="4" id="listViewType4"><span class="lbl"> 轮播</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("listViewType${empty item ? 0 : item.listViewType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="viewCount">资讯浏览量</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="viewCount" id="viewCount" type="text" value="${item.viewCount}" maxlength="10" placeholder="请输入资讯浏览量">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="replyCount">资讯回复量</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="replyCount" id="replyCount" type="text" value="${item.replyCount}" maxlength="10" placeholder="请输入资讯回复量">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sourceAppId">源APPID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="sourceAppId" id="sourceAppId" type="text" value="${item.sourceAppId}" maxlength="19" placeholder="请输入源APPID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sourceInformationId">源新闻ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="sourceInformationId" id="sourceInformationId" type="text" value="${item.sourceInformationId}" maxlength="19" placeholder="请输入源新闻ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="title">资讯标题</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="请输入资讯标题"  value="${item.title}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="-1" id="status-1"><span class="lbl"> 审核不通过</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="0" id="status0"><span class="lbl"> 草稿</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="1" id="status1"><span class="lbl"> 保存</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 正常上线</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="88" id="status88"><span class="lbl"> 下线</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? -1 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="onlineTime">上线时间</label>
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#onlineTime");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" name="onlineTime" id="onlineTime" readonly="readonly" value="<fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="onlineTimeDate">上线时间年月日</label>
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#onlineTimeDate");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" name="onlineTimeDate" id="onlineTimeDate" readonly="readonly" value="<fmt:formatDate value="${item.onlineTimeDate}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="createTime">创建时间</label>
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#createTime");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
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
                        <label class="col-sm-4 control-label no-padding-right" for="loggedStatus">是否登陆后才能操作</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="loggedStatus" value="0" id="loggedStatus0"><span class="lbl"> 否</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="loggedStatus" value="1" id="loggedStatus1"><span class="lbl"> 是</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("loggedStatus${empty item ? 0 : item.loggedStatus}").checked=true;
                            </script>
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="submit" id="searchButton">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/infoClassify/info_classify_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script>
require(['validator.bootstrap'], function($){
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    informationId : {
                    required: true,
                    number:true
                },
                    columnsId : {
                    required: true,
                    number:true
                },
                    classfieViewStatus : {
                    required: true
                },
                    sortNo : {
                    required: true,
                    number:true
                },
                    listViewType : {
                    required: true
                },
                    viewCount : {
                    required: true,
                    number:true
                },
                    replyCount : {
                    required: true,
                    number:true
                },
                    sourceAppId : {
                    required: true,
                    number:true
                },
                    sourceInformationId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    status : {
                    required: true
                },
                    onlineTime : {
                    required: true,
                    date:true
                },
                    onlineTimeDate : {
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
                    loggedStatus : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                informationId : {
                    required: ' ',
                    number:' '
                },
                columnsId : {
                    required: ' ',
                    number:' '
                },
                classfieViewStatus : {
                    required: ' '
                },
                sortNo : {
                    required: ' ',
                    number:' '
                },
                listViewType : {
                    required: ' '
                },
                viewCount : {
                    required: ' ',
                    number:' '
                },
                replyCount : {
                    required: ' ',
                    number:' '
                },
                sourceAppId : {
                    required: ' ',
                    number:' '
                },
                sourceInformationId : {
                    required: ' ',
                    number:' '
                },
                title : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                onlineTime : {
                    required: ' ',
                    date:' '
                },
                onlineTimeDate : {
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
                loggedStatus : {
                    required: ' '
                }
            }
        });
    });
});
</script>