<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="角色管理|/module/security/sys_role_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="${not empty item ? '修改' : '新增' }" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
		        <form class="form-horizontal form" method="post" id="form1" action="${not empty item ? 'sys_role_update.html' : 'sys_role_add.html'}">
		            <c:if test="${not empty item}">
		            <input type="hidden" name="id" value="${item.id}" />
		            </c:if>
	                    <div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">角色名称</label>
	                        <div class="col-sm-9">
	                            <input type="text" class="form-control" id="roleName" name="roleName" placeholder="请输入角色名称"  value="${item.roleName}" maxlength="16">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述信息</label>
	                        <div class="col-sm-9">
	                            <textarea name="descn" rows="5" class="form-control" id="descn" placeholder="请输入描述信息">${item.descn}</textarea>
	
	                        </div>
	                    </div>
	                    
	                    <input type="hidden" name="type" value="2">
	                    
	                    <div class="form-group  ${empty item and empty session_user_obj.appId ? '' : 'hidden'}">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所属APP</label>
	                        <div class="col-sm-9">
	                            <select name="appId" class="form-control chosen-select">
	                               <option value="">系统管理员</option>
	                               <c:forEach items="${allApps}" var="app">
	                                  <option value="${app.id }" <c:if test="${item.appId eq app.id or app.id eq session_user_obj.appId}">selected</c:if>  >${app.name}</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    
                      <!-- ////////////////////////////////// -->
                       <div class="form-group">
								<label class="col-xs-12 col-sm-3 col-md-2 control-label">拥有的资源</label>
								<div class="col-sm-9">
									<div class="tabbable">
										<ul class="nav nav-tabs" id="myTab">
											<c:forEach items="${sysResTypes }" var="resType" varStatus="vs">
											   <li resTypeId="${resType.id}"  class="${vs.count eq 1 ? 'active' : ''}">
												<a data-toggle="tab" href="#tab_pane_resc_${resType.id}">
													${resType.name}
												</a>
											   </li>
											</c:forEach>
										</ul>
										<div class="tab-content">
											<!-- 资源内容 -->
											<c:forEach items="${sysResTypes }" var="resType">
											   <div id="tab_pane_resc_${resType.id}" class="tab-pane"></div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
                      <!-- ////////////////////////////////// -->
		            
		            <div class="form-group">
						<div class="col-sm-11">
							<div class="pull-right">
								<button class="btn btn-success btn-sm commonSaveButton" type="button" back_url="/module/security/sys_role_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/security/sys_role_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
		    </div>
		</div>
	</div>
</div>

<!-- 父级资源模板 -->
<div id="parent_tmp" class="hidden">
   <div class="widget-box no-margin no-padding checkbox">
		<div class="widget-header widget-header-flat">
			<h4 class="smaller">
				<label class="checkbox-2">
					<input type="checkbox" class="ace" name="resIds[]" defaultName="resIds"><span class="lbl">新闻管理</span>
				</label>
				<label class="checkbox-2">
					<input type="checkbox" class="ace" name="checked_all"><span class="lbl">全选</span>
				</label>
			</h4>
		</div>
		<div class="space-6"></div>
	</div>
</div>

<!-- 子集资源模板 -->
<div id="child_temp" class="hidden">
   <div class="widget-body">
		<div class="widget-main">
			
		</div>
	</div>
</div>

<div id="checkBoxTmp" class="hidden">
   <label class="checkbox-2">
		<input type="checkbox" class="ace" name="resIds[]" defaultName="resIds"><span class="lbl">删除专题分类</span>
	</label>
</div>

<script type="text/javascript">

var roleRescs = '${roleRescs}';

require(['chosen','cloud.table.curd','cqliving_dialog','validator.bootstrap','/resource/business/security/role/sys_role_detail.js'],function(c,curd,cqliving_dialog){
	    
	   curd.initTableCrud();
	   $(".chosen-select").chosen({search_contains:true});
	   
	   $("#form1").validate({
	        rules: {
	            roleName : {
	                required: true,
	                resc:true
	               }
	        },
	        messages: {
	            roleName : {
	                required: ' ',
	                resc:' '
	            }
	        }
	    });

	   $.validator.addMethod("resc", function(value, element, param) {
		   
		    return this.optional(element) || rescRequired();
		}, "请选择该角色的资源");
	   
	   function rescRequired(){
		   
		   var flag = true;
		   var $input = $("#form1 :input[type=checkbox]:checked");
		   
		   if($input.length<=0){
			   cqliving_dialog.error("请选择该角色的资源");
			   return flag = false;
		   }
		   return flag;
	   }
});
</script>

