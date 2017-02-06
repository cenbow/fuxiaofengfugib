<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品推荐表列表|shopping_recommend_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/shopping_recommend/shopping_recommend_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端_ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入客户端_ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl">图片地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" maxlength="255" placeholder="请输入图片地址"  value="${item.imageUrl}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="shoppingGoodsId">商品ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="shoppingGoodsId" id="shoppingGoodsId" type="text" value="${item.shoppingGoodsId}" maxlength="19" placeholder="请输入商品ID">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="type">类型<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 首页</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 轮播</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
	                            </script>
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
	                                <input type="radio" class="ace" name="status" value="1" id="status1"><span class="lbl"> 保存</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 已发布</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="88" id="status88"><span class="lbl"> 已下线</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
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
	                        <label class="col-sm-3 control-label no-padding-right" for="creatorId">创建人<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="creatorId" id="creatorId" type="text" value="${item.creatorId}" maxlength="19" placeholder="请输入创建人">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="creator">创建人姓名<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="creator" name="creator" maxlength="100" placeholder="请输入创建人姓名"  value="${item.creator}">
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
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/shopping_recommend/shopping_recommend_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shopping_recommend/shopping_recommend_list.html'">
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
                    imageUrl : {
                    required: true
                },
                    shoppingGoodsId : {
                    required: true,
                    number:true
                },
                    type : {
                    required: true
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
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                imageUrl : {
                    required: ' '
                },
                shoppingGoodsId : {
                    required: ' ',
                    number:' '
                },
                type : {
                    required: ' '
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
                }
            }
        });
    });
});
</script>