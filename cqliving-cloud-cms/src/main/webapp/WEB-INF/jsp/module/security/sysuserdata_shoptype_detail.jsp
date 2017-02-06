<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="用户商铺分类权限|${listUri }" name="_breadcrumbs_1"/>
		<jsp:param value="${not empty userId ? '编辑' : '新增'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/security/sys_user_data/shop_type_add.html">
		        	
		        	<input class="form-control" name="type" id="type" type="hidden" value="3">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	                    
	                    <div class="form-group  ${not empty userId or not empty sysUsers ? 'hidden' : ''}">
	                        <label class="col-sm-3 control-label no-padding-right" for="userId">客户端<i class="text-danger">*</i></label>
	                        <div class="col-sm-6">
	                           <select name="appId" class="chosen-select" data-placeholder="请选择客户端">
	                               <c:forEach items="${allApp }" var="app">
	                                     <option value="${app.id }">${app.name}</option>
	                               </c:forEach>
			                    </select>
	                        </div>
		                </div>
	                    
	                    <div class="form-group  ${not empty userId ? 'hidden' : ''}">
	                        <label class="col-sm-3 control-label no-padding-right" for="userId">用户<i class="text-danger">*</i></label>
	                        <div class="col-sm-6">
	                           <c:choose>
	                              <c:when test="${empty  userId}">
	                                  <select name="userId" class="chosen-select" data-placeholder="请选择用户">
			                               <c:forEach items="${sysUsers }" var="sysUser">
			                                     <option value="${sysUser.id }">${sysUser.nickname}</option>
			                               </c:forEach>
			                           </select>
	                              </c:when>
	                              <c:otherwise>
	                                 <input class="form-control" name="userId" id="userId" type="hidden" value="${userId}">
	                              </c:otherwise>
	                           </c:choose>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="value">商铺分类<i class="text-danger">*</i></label>
	                        <div class="col-sm-6">
	                            <select  name="dataValues" class="chosen-select" multiple="multiple" data-placeholder="选择商铺分类">
	                               <c:forEach items="${shopTypes }" var="st">
	                                  <option value="${st.id }"
	                                  <c:if test="${fn:contains(fn:join(userShopType,','),st.id)}">selected</c:if>
	                                  >${st.name }</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="${listUri }">
										<i class="icon-save bigger-110"></i>保存
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
require(['validator.bootstrap','cloud.table.curd','cqliving_ajax','cqliving_dialog','chosen'], function($,tableCurd,cqliving_ajax,cqliving_dialog){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    userId : {
                    required: true,
                    number:true
                },
                dataValues : {
                    required: true
                }
            },
            messages: {
                userId : {
                    required: ' ',
                    number:' '
                },
                dataValues : {
                    required: ' '
                }
            }
        });
    });
    
    
    $(".chosen-select").chosen({width:'300px',search_contains:true});
    
   $("select[name=appId]").on("change",function(){
    	
    	var $this = $(this);
    	var url = "/module/security/sys_user_data/common/sys_user_appid.html";
    	var param = {appId:$this.val()};
    	cqliving_ajax.ajaxOperate(url,null,param,function(data,status){
    		
    		var list = data.data;
    		if(data.code == -999992){
    			cqliving_dialog.error(data.message);
    			return;
    		}
    		if(list && list.length>=0){
    			var html  = "";
    			for(var i in list){
    				html += "<option value='"+list[i].id+"'>"+list[i].username+"</option>";
    			}
    			$("select[name=userId]").html(html);
    		}else{
    			cqliving_dialog.error("未找到用户");
    		}
    		$("select[name=userId]").trigger("chosen:updated");
    	});
    	
     });
});
</script>