<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻列表图模板管理|/module/infoeTmpleteImageConfig/info_templete_image_config_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        <div class="col-md-12 col-lg-8">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <c:if test="${not empty appList}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP</label>
                        <div class="col-sm-9">
                            <select name="appId" id="appId" class="form-control chosen-select">
								<c:forEach items="${appList}" var="app" varStatus="idx">
                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">模板名称</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入模板名称"  value="${item.name}">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="templetCode">模板CODE</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="templetCode" name="templetCode" maxlength="50" placeholder="请输入模板CODE"  value="${item.templetCode}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="limitType">压缩类型</label>
                        <div class="col-sm-9 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="limitType" value="0" id="limitType0"><span class="lbl"> 宽为维度，等比压缩</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="limitType" value="1" id="limitType1"><span class="lbl"> 固定压缩</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("limitType${empty item ? 0 : item.limitType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="width">宽</label>
                        <div class="col-sm-9">
                            <input class="form-control" name="width" id="width" type="text" value="${item.width}" maxlength="10" placeholder="请输入宽">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="hight">高</label>
                        <div class="col-sm-9">
                            <input class="form-control" name="hight" id="hight" type="text" value="${item.hight}" maxlength="10" placeholder="请输入高">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="context">备注说明</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="context" name="context" maxlength="50" placeholder="请输入备注说明"  value="${item.context}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="listType">列表图片类型</label>
                        <div class="col-sm-9 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listType" value="0" id="listType0"><span class="lbl"> 单图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listType" value="1" id="listType1"><span class="lbl"> 大图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listType" value="2" id="listType2"><span class="lbl"> 三图</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="listType" value="3" id="listType3"><span class="lbl"> 轮播图</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("listType${empty item ? 0 : item.listType}").checked=true;
                            </script>
                        </div>
                    </div>
                	<div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/infoeTmpleteImageConfig/info_templete_image_config_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/infoeTmpleteImageConfig/info_templete_image_config_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['/resource/business/info/info_templete_image_config_detail.js'],function(templete_image_config){
	templete_image_config.init();
});
</script>