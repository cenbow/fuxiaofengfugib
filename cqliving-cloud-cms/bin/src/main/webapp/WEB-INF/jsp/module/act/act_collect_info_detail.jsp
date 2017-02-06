<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div>
<%-- 	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"> --%>
<%-- 		<jsp:param value="活动配置管理|/module/act/act_collect_info_list.html" name="_breadcrumbs_1"/> --%>
<%-- 		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/> --%>
<%-- 	</jsp:include> --%>
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12 version-detail fieldset-form">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form-act-cfg" action="${action}">
	            	<input type="hidden" name="id" value="${item.id}" />
	            	<fieldset>
			            <legend>配置信息</legend>
                    <c:if test="${not empty appList}">
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="appId">客户端</label>
	                        <div class="col-sm-10">
	                            <select name="appId" id="appId" class="form-control chosen-select">
								   <c:forEach items="${appList}" var="app">
	                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
	                               </c:forEach>
								</select>
	                        </div>
	                    </div>
                    </c:if>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="name">参数名称</label>
                            <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" maxlength="10" placeholder="请输入参数名称"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12" style="display: none;">
                        <label class="col-sm-2 control-label no-padding-right" for="type">参数类型</label>
                        <div class="col-sm-10 radio">
                        	<c:forEach items="${allTypes}" var="type">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" <c:if test="${(item.type eq type.key) or (empty item.type and idx.first)}"> checked="checked" </c:if> name="type" value="${type.key}" id="type1"><span class="lbl"> ${type.value}</span>
	                            </label>
                            </c:forEach>
                        </div>
                    </div>
                    <c:if test="${!((TYPE1 eq item.type) or (TYPE3 eq item.type))}"> 
                    	<input name="length" id="length" type="hidden" value="0" >
                    </c:if>
                    <c:if test="${(TYPE1 eq item.type) or (TYPE3 eq item.type)}"> 
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="type">
                        	<c:if test="${TYPE1 eq item.type}">字数限制</c:if>
	                        <c:if test="${TYPE3 eq item.type}">最多选择</c:if>
                        </label>
                        <div class="col-sm-9">
                        	<input class="form-control" name="length" id="length" type="text" value="${item.length}" maxlength="10" placeholder="正整数类型"></input>
                        </div>
                        	<span class="">
	                        	<c:if test="${TYPE1 eq item.type}">字</c:if>
		                        <c:if test="${TYPE3 eq item.type}">项</c:if>
                        	</span>
                    </div>
                    </c:if>
                    </fieldset>
                    <c:if test="${TYPE1 ne item.type}">
                    <div>
                    <fieldset id="option-field">
			            <legend>选项信息</legend>
	                    <div class="form-group col-xs-6 length">
	                        <label class="col-sm-4 control-label no-padding-right">
	                            <button class="btn btn-sm btn-success add-option" type="button"><i class="icon-plus"></i>添加选项</button>
	                        </label>
	                        <div class="col-sm-8">
		                        <c:if test="${TYPE4 eq item.type}">
		                        	<a href="javascript:void(0);" id="import-a">从excel中导入</a>
								    <input style="display: none;" id="excelFile" name="excelFile" type="file" />
		                        	<a href="javascript:void(0);" id="down-a">下载模板</a>
		                        </c:if>
	                        	<span id="option-tip" style="color: #d16e6c;"></span>
	                        </div>
	                    </div>
	                    <div class="form-group col-xs-12 option-div option-tr" style="display: none;">
	                        <label class="col-sm-2 control-label no-padding-right">选项名称</label>
	                        <div class="col-sm-10">
			                        <div class="col-sm-10">
			                            <input class="form-control value" name="value" type="text" value="" maxlength="50" placeholder="请输入选项名称">
			                        </div>
			                        <div class="col-sm-2">
			                            <button class="btn btn-sm btn-danger del-option" type="button"><i class="icon-remove"></i>删除</button>
			                            <span style="color: #d16e6c;"></span>
			                        </div>
	                        </div>
	                    </div>
	                    <c:if test="${not empty item.optionList }">
	                    <c:forEach items="${item.optionList}" var="option">
	                    	<div class="form-group col-xs-12 option-tr">
		                        <label class="col-sm-2 control-label no-padding-right">选项名称</label>
		                        <div class="col-sm-10">
			                        <div class="col-sm-10">
			                            <input class="form-control value" name="value" type="text" value="${option.value}" maxlength="50" placeholder="请输入选项名称">
			                        </div>
			                        <div class="col-sm-2">
			                            <button class="btn btn-sm btn-danger del-option" type="button"><i class="icon-remove"></i>删除</button>
			                            <span style="color: #d16e6c;"></span>
			                        </div>
		                        </div>
	                    	</div>
	                    </c:forEach>
	                    </c:if>
                    </fieldset>
                    </div>
                    </c:if>
                	<!-- <div class="form-group col-xs-12">
						<div class="pull-right">
							<button class="btn btn-success btn-sm" type="button" id="saveBtn" back_url="/module/act/act_collect_info_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-danger popup-cancel-btn" type="button">
								<i class="icon-remove bigger-110"></i>取消
							</button>
						</div>
					</div> -->
		        </form>		        
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
</div>

<script type="text/javascript">
var TYPE1 = '${TYPE1}';
var type = '${item.type}';
require(['/resource/business/act/act_collect_info_detail.js'],function(act_collect_detail){
	act_collect_detail.init();
});
</script>