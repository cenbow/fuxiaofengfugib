<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
		
				<form class="form-horizontal" role="form" id="form1">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>
						<div class="col-sm-9">
							<input type="text" id="form-field-1" placeholder="" class="col-xs-5 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Password Field </label>
						<div class="col-sm-9">
							<input type="text" id="form-field-2" placeholder="" class="col-xs-5 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"> Readonly field </label>
						<div class="col-sm-9">
							<input type="text" class="col-xs-5 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-4">Relative Sizing</label>
						<div class="col-sm-9">
							<input class="input-sm" type="text" class="col-xs-5 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-select-2">推送状态</label>
						<div class="col-sm-4">
							<select class="form-control" id="form-field-select-2">
								<option value="">全部</option>
								<option value="">已推送</option>
								<option value="">未推送</option>
							</select>
						</div>
					</div>
		
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="button">
								<i class="icon-ok bigger-110"></i>
								保存
							</button>
		
							&nbsp; &nbsp; &nbsp;
							<!-- <button class="btn" type="reset">
								<i class="icon-undo bigger-110"></i>
								返回
							</button> -->
							<button class="btn" type="button" onclick="javascript:location.href='/module/security/sys_role_list.html'">
								<i class="icon-undo bigger-110"></i>
								返回
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div><!-- /.page-content -->
</div>

<!-- <script>
$(function(){
	$(function(){
        $("#form1").validate({
            rules: {
                    roleName : {
                    required: true
                }

            },
            messages: {
                roleName : {
                    required: ' '
                }
            }
        });
    });
});
</script> -->