<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="/module/security/sys_role_list.html" id="sysRoleListFormId">
								<label class="col-sm-1 control-label no-padding-right" for="form-field-1">新闻标题</label>
								<div class="col-sm-2">
									<input type="text" id="form-field-1" name="search_LIKE_roleName" value="${param.search_LIKE_roleName }" placeholder="" class="col-xs-12" />
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-2">栏目</label>
								<div class="col-sm-2">
									<input type="text" id="form-field-2" placeholder="" class="col-xs-12" />
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-select-1">状态</label>
								<div class="col-sm-2">
									<select class="form-control" id="form-field-select-1" name="search_EQ_id" value="${param.search_EQ_id }">
										<option value="">全部</option>
										<option value="1" <c:if test="${param.search_EQ_id eq 1}">selected</c:if>>已发布</option>
										<option value="2" <c:if test="${param.search_EQ_id eq 2}">selected</c:if>>未发布</option>
									</select>
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-select-2">推送状态</label>
								<div class="col-sm-2">
									<select class="form-control" id="form-field-select-2">
										<option value="">全部</option>
										<option value="">已推送</option>
										<option value="">未推送</option>
									</select>
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-select-3">新闻类型</label>
								<div class="col-sm-2">
									<select class="form-control" id="form-field-select-3">
										<option value="">全部</option>
										<option value="">纯文本</option>
										<option value="">非原创</option>
										<option value="">原创</option>
										<option value="">多图</option>
										<option value="">外链</option>
									</select>
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-select-4">数据类型</label>
								<div class="col-sm-2">
									<select class="form-control" id="form-field-select-4">
										<option value="">全部</option>
										<option value="">视频</option>
										<option value="">多图</option>
										<option value="">音频</option>
										<option value="">投票</option>
										<option value="">调研</option>
										<option value="">打擂</option>
									</select>
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="form-field-3">创建人</label>
								<div class="col-sm-2">
									<input type="text" id="form-field-3" placeholder="" class="col-xs-12" />
								</div>
								<label class="col-sm-1 control-label no-padding-right" for="id-date-range-picker-1">发布时间</label>
								<div class="col-sm-2">
									<input readonly="readonly" class="form-control" type="text" name="date-range-picker" id="id-date-range-picker-1">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-10 col-sm-2 text-right">
									<button class="btn btn-info btn-sm" type="button" id="searchButton">
										<i class="icon-search bigger-110"></i>查询
									</button>
									&nbsp;
									<button class="btn btn-sm" type="reset">
										<i class="icon-undo bigger-110"></i>重置
									</button>
								</div>
							</div>
							<div class="well">
								<button class="btn btn-sm">批量发布</button>
								<button class="btn btn-sm">批量下线</button>
								<button class="btn btn-sm">清空排序</button>
								<button class="btn btn-sm">撤稿</button>
								<button class="btn btn-sm">加入专题</button>
							</div>
						</form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="sys_role_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.table.curd'], function(tableCurd){
	tableCurd.initTableCrud();
});
</script>