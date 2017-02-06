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
                        <label class="col-sm-4 control-label no-padding-right" for="templetCode">模板CODE</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="templetCode" name="templetCode" maxlength="100" placeholder="请输入模板CODE"  value="${item.templetCode}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="imageUrl">模板图片</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" maxlength="255" placeholder="请输入模板图片"  value="${item.imageUrl}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="type">模板类型</label>
                        <div class="col-sm-8 radio">
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
                                document.getElementById("type${empty item ? 3 : item.type}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="templetDesc">模版描述</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="templetDesc" name="templetDesc" maxlength="1,000" placeholder="请输入模版描述"  value="${item.templetDesc}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="name">模版名称</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name" maxlength="200" placeholder="请输入模版名称"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="commonType">模版公有状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commonType" value="1" id="commonType1"><span class="lbl"> 公有</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commonType" value="2" id="commonType2"><span class="lbl"> APP私有</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("commonType${empty item ? 1 : item.commonType}").checked=true;
                            </script>
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_templatelist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_templatelist.html'">
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
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    templetCode : {
                    required: true
                },
                    imageUrl : {
                    required: true
                },
                    type : {
                    required: true
                },
                    templetDesc : {
                    required: true
                },
                    name : {
                    required: true
                },
                    commonType : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                templetCode : {
                    required: ' '
                },
                imageUrl : {
                    required: ' '
                },
                type : {
                    required: ' '
                },
                templetDesc : {
                    required: ' '
                },
                name : {
                    required: ' '
                },
                commonType : {
                    required: ' '
                }
            }
        });
    });
});
</script>