<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style>
.modal-dialog{padding-top:150px; padding-bottom:150px; min-width:1000px;}
</style>

<!-- <div> -->
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12 fieldset-form version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <fieldset>
			        <legend>配置信息</legend>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">客户端</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">参数名称</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">参数类型</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allTypes[item.type]}">
                        </div>
                    </div>
                    
                    <c:if test="${(TYPE1 eq item.type) or (TYPE3 eq item.type)}">
		                <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right">
	                        	<c:if test="${TYPE1 eq item.type}">字数限制</c:if>
		                        <c:if test="${TYPE3 eq item.type}">最多选择</c:if>
	                        </label>
	                        <div class="col-sm-8">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.length}<c:if test="${TYPE1 eq item.type}">字</c:if><c:if test="${TYPE3 eq item.type}">项</c:if>">
	                        </div>
	                    </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.createTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建人</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">修改时间</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.updateTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">修改人</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.updator}">
                        </div>
                    </div>
                    </fieldset>
                    <c:if test="${not empty item.optionList }">
                    <fieldset id="option-field">
			            <legend>选项信息</legend>
	                    <c:forEach items="${item.optionList}" var="option">
	                    	<div class="form-group">
		                        <label class="col-sm-2 control-label no-padding-right">选项名称</label>
		                        <div class="col-sm-8">
                        			<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${option.value}">
		                        </div>
		                    </div>
	                    </c:forEach>
                    </fieldset>
                    </c:if>
		           <!--  <div class="form-group col-xs-12">
						<div class="pull-right">
							<button class="btn btn-sm btn-danger popup-cancel-btn" type="button">
								<i class="icon-remove bigger-110"></i>关闭
							</button>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->
