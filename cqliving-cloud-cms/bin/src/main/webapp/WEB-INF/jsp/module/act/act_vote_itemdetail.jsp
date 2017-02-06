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
                        <label class="col-sm-4 control-label no-padding-right" for="actVoteClassifyId">活动投票分类表ID（act_vote_classify表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actVoteClassifyId" id="actVoteClassifyId" type="text" value="${item.actVoteClassifyId}" maxlength="19" placeholder="请输入活动投票分类表ID（act_vote_classify表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="number">选项编号</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="number" name="number" maxlength="10" placeholder="请输入选项编号"  value="${item.number}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="itemTitle">选项标题，后台限制最多80个字</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="itemTitle" name="itemTitle" maxlength="100" placeholder="请输入选项标题，后台限制最多80个字"  value="${item.itemTitle}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="itemDesc">选项描述</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="itemDesc" name="itemDesc" maxlength="500" placeholder="请输入选项描述"  value="${item.itemDesc}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actValue">实际量</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actValue" id="actValue" type="text" value="${item.actValue}" maxlength="10" placeholder="请输入实际量">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="initValue">初始量</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="initValue" id="initValue" type="text" value="${item.initValue}" maxlength="10" placeholder="请输入初始量">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="imageUrl">选项图片</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" maxlength="255" placeholder="请输入选项图片"  value="${item.imageUrl}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="videoUrl">视频URL</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="videoUrl" name="videoUrl" maxlength="255" placeholder="请输入视频URL"  value="${item.videoUrl}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="content">内容,包含HTML标签的富文本内容</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="content" name="content" maxlength="0" placeholder="请输入内容,包含HTML标签的富文本内容"  value="${item.content}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="2" id="status2"><span class="lbl"> 转码中</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 正常</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 2 : item.status}").checked=true;
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
                        <label class="col-sm-4 control-label no-padding-right" for="createTime">创建时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_vote_itemlist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_vote_itemlist.html'">
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
                    actVoteClassifyId : {
                    required: true,
                    number:true
                },
                    number : {
                    required: true
                },
                    itemTitle : {
                    required: true
                },
                    itemDesc : {
                    required: true
                },
                    actValue : {
                    required: true,
                    number:true
                },
                    initValue : {
                    required: true,
                    number:true
                },
                    imageUrl : {
                    required: true
                },
                    videoUrl : {
                    required: true
                },
                    content : {
                    required: true
                },
                    status : {
                    required: true
                },
                    sortNo : {
                    required: true,
                    number:true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                actVoteClassifyId : {
                    required: ' ',
                    number:' '
                },
                number : {
                    required: ' '
                },
                itemTitle : {
                    required: ' '
                },
                itemDesc : {
                    required: ' '
                },
                actValue : {
                    required: ' ',
                    number:' '
                },
                initValue : {
                    required: ' ',
                    number:' '
                },
                imageUrl : {
                    required: ' '
                },
                videoUrl : {
                    required: ' '
                },
                content : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                sortNo : {
                    required: ' ',
                    number:' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                }
            }
        });
    });
});
</script>