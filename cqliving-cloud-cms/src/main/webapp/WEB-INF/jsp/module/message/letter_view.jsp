<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="站内信管理|/module/message/letter_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form detail-form" rid="${item.rId}">
		        <div class="col-md-12 col-lg-8">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">客户端</label>
                        <div class="col-sm-9">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">标题</label>
                        <div class="col-sm-9">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.title}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">发布人</label>
                        <div class="col-sm-9">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">发送时间</label>
                        <div class="col-sm-9">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">内容</label>
                        <div class="col-sm-9">
                             <textarea disabled="disabled" class="col-xs-10 form-control col-sm-5">${item.context}</textarea>
                        </div>
                    </div>
                    <input type="hidden" id="readStatus-hidden" value="${item.readStatus}">
		            <div class="form-group">
						<div class="col-sm-12">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/message/letter_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
					</div>
		        </form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/message/letter_view.js'],function(letter){
	letter.init();
});
</script>
