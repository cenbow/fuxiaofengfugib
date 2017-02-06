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
                        <label class="col-sm-4 control-label no-padding-right" for="actTestId">活动答题表ID（act_test表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actTestId" id="actTestId" type="text" value="${item.actTestId}" maxlength="19" placeholder="请输入活动答题表ID（act_test表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actCollectInfoId">信息收集表ID（act_collect_info表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actCollectInfoId" id="actCollectInfoId" type="text" value="${item.actCollectInfoId}" maxlength="19" placeholder="请输入信息收集表ID（act_collect_info表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="isRequired">是否必填</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isRequired" value="0" id="isRequired0"><span class="lbl"> 非必填</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isRequired" value="1" id="isRequired1"><span class="lbl"> 必填</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("isRequired${empty item ? 0 : item.isRequired}").checked=true;
                            </script>
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
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_test_collectlist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_test_collectlist.html'">
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
                    actTestId : {
                    required: true,
                    number:true
                },
                    actCollectInfoId : {
                    required: true,
                    number:true
                },
                    isRequired : {
                    required: true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                actTestId : {
                    required: ' ',
                    number:' '
                },
                actCollectInfoId : {
                    required: ' ',
                    number:' '
                },
                isRequired : {
                    required: ' '
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