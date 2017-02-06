<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- <div> -->
<%-- 	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"> --%>
<%-- 		<jsp:param value="敏感词管理|/module/sensitiveWords/config_sensitive_words_list.html" name="_breadcrumbs_1"/> --%>
<%-- 		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/> --%>
<%-- 	</jsp:include> --%>
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12 version-detail fieldset-form">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="${action}">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <c:if test="${not empty appList}">
	                    <div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="appId">APP</label>
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
                        <label class="col-sm-2 control-label no-padding-right" for="name">敏感词</label>
                            <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入敏感词"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="type">类型</label>
                        <div class="col-sm-10 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 注册敏感词</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 普通敏感词</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="3" id="type3"><span class="lbl"> 短信下发敏感词</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
                            </script>
                        </div>
                    </div>
                	<!-- <div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="saveBtn" back_url="/module/sensitiveWords/config_sensitive_words_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-default popup-cancel-btn" type="button">
								<i class="icon-undo bigger-110"></i>取消
							</button>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->

<script type="text/javascript">
require(['/resource/business/config/config_sensitive_words_detail.js'],function(sensitive_words){
	sensitive_words.init();
});
</script>