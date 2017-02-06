<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="用户栏目权限|${listUri }" name="_breadcrumbs_1"/>
		<jsp:param value="${not empty userId ? '编辑' : '新增'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/security/sys_user_data/app_column_add.html">
		             <input class="form-control" name="type" id="type" type="hidden" value="2">
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
	                        <label class="col-sm-3 control-label no-padding-right" for="columnsId">所属栏目<i class="text-danger">*</i></label>
	                        <div class="col-sm-6">
	                            <div class="input-group">
	                                <input name="dataValues" value="${fn:join(appColumnMap.APP_COLUMN_ID,',')}" id="columnIds" type="hidden">
		                            <input name="columnsName" type="text" placeholder="请选择所属栏目" value="${fn:join(appColumnMap.APP_COLUMN_NAME,',')}" class="col-xs-12 form-control"/>
		                            <div class="dropdown-toggle input-group-btn">
										<button type="button" class="btn btn-sm btn-primary">
											选择栏目
											<span class="caret"></span>
										</button>
		                            </div>
		                            <ul class="dropdown-menu dropdown-menu-right" role="menu" style="width:100%;padding:0px;">
		                               <div class="dropdown-default" id="appcolumns_tree"></div>
		                            </ul>
		                        </div>
	                            <div class="red alert_appcolumns"></div>
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
require(['validator.bootstrap','cloud.table.curd','treeview','cqliving_ajax','cqliving_dialog','chosen'], function($,tableCurd,treeview,cqliving_ajax,cqliving_dialog){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
        	ignore:'',
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
    
    
    $("#form1 .dropdown-toggle,#appcolumns_tree").bind("click",function(e){
		 e.cancelBubble = true;
		 e.stopPropagation();
		 $(this).next().show();
	 });
	 
	 $("body").bind("click",function(e){
		 if('li'.indexOf(e.target.tagName.toLowerCase()) != -1){
			 return;
		 }
		 $("#form1 .dropdown-toggle").next().hide();
	 });
    $('#appcolumns_tree').treeview({
		data: ${appColumnsJson},
		showIcon:true,
		selectedIcon:"glyphicon glyphicon-check",
		multiSelect:true,
		onNodeSelected: function(event, data) {
			setColumnData(data);
		},
		onNodeUnselected:function(event,data){
			removeColumnData(data);
		}
	});
    
    
    function setColumnData(data){
    	
    	var $columnIds= $(":input[name=dataValues]");
    	var $columnsName = $(":input[name=columnsName]");
    	var ids = $columnIds.val();
    	if(!ids){
    		$columnIds.val(data.id);
    		$columnsName.val(data.name);
    		return;
    	}
    	ids = ids.split(",");
    	ids.push(data.id);
    	$columnIds.val(ids);
    	var names = $columnsName.val();
    	names = names.split(",");
    	names.push(data.name);
    	$columnsName.val(names);
    	
    }
    
    function removeColumnData(data){
    	
    	var $columnIds= $(":input[name=dataValues]");
    	var $columnsName = $(":input[name=columnsName]");
    	var ids = $columnIds.val();
    	if(!ids){
    		return;
    	}
    	ids = ids.split(",");
    	remove(ids,data.id);
    	$columnIds.val(ids);
    	var names = $columnsName.val();
    	names = names.split(",");
    	remove(names,data.name);
    	$columnsName.val(names);
    }
    
    function getIndex(arr,el){
    	
    	if(!arr || arr.length<=0)return -1;
    	el += "";
    	for(var i in arr){
    		if(el.indexOf(arr[i]) != -1){
    			return i;
    		}
    	}
    	return -1;
    }
    
    function remove(arr,el){
    	var index = getIndex(arr,el);
    	if(-1 == index)return arr;
    	arr.splice(index,1);
    	return arr;
    }
    
});
</script>