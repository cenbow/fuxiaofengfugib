<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:choose>
   <c:when test="${empty type or type eq 1 }">
      <label class="col-sm-3 control-label no-padding-right" for="linkUrl">全媒体外链地址<i class="text-danger">*</i></label>
      <div class="col-sm-9">
          <input type="text" class="form-control" id="linkUrl" name="linkUrl" maxlength="255" placeholder="全媒体外链地址"  value="${linkUrl}">
      </div>
   </c:when>
   <c:otherwise>
       <label class="col-sm-3 control-label no-padding-right" for="linkUrl">栏目<i class="text-danger">*</i></label>
		<div class="col-sm-9">
			<input class="form-control" name="columnsId" id="columnsId" type="hidden" value="${selectAppColumn.id}" maxlength="19">
			<div class="input-group">
				<div class="dropdown-toggle input-group-btn">
					<button type="button" class="btn btn-sm btn-primary">
						选择栏目 <span class="caret"></span>
					</button>
				</div>
				<ul class="dropdown-menu dropdown-menu-right" role="menu" style="width: 100%; padding: 0px;">
					<div class="dropdown-default" id="appcolumns_tree"></div>
				</ul>
				<input name="columnsName" id="columnsName" type="text" placeholder="请选择所属栏目" value="${selectAppColumn.name }" class="col-xs-12 form-control" readonly/>
			</div>
			<div class="red alert_appcolumns"></div>
		</div>
   </c:otherwise>
</c:choose>

<script type="text/javascript">
  require(['common_treeview'],function(common_treeview){
	  $("#form1 .dropdown-toggle,#appcolumns_tree").bind("click",function(e){
			 e.cancelBubble = true;
			 e.stopPropagation();
			 $(this).next().show();
		 });
		 $("body").bind("click",function(e){
			 var tagName = e.target.tagName.toLowerCase();
			 if('li'.indexOf(tagName) != -1 || 'span'.indexOf(tagName) != -1){
				 return;
			 }
			 $("#form1 .dropdown-toggle").next().hide();
		 });
		 
		 initTree();
		 function initTree(){
			 var mediaType = $("#form1 input[name=type]:checked").val();
			 var appColumn = '${appColumnsJson}';
			 if(3 == mediaType && appColumn){
				appColumn = JSON.parse(appColumn);
				if(!appColumn)return;
				//第三级不展示
				$.each(appColumn,function(i,n){
					var thisCode = n.code;
					if(thisCode.split(".").length>=2){
						n.nodes = "";
					}else if(n.nodes){
						$.each(n.nodes,function(j,k){
							if(k.code.split(".").length>=2){
								k.nodes = "";
							}else if(k.nodes){
								$.each(k.nodes,function(s,m){
									m.nodes = "";
								});
							}
						});
					}
				});
				appColumn = JSON.stringify(appColumn);
			 }
			 common_treeview.treeview("appcolumns_tree",appColumn,function(data){
				 $("#columnsId").val(data.id);
				 $("#columnsName").val(data.name);
				 $("body").click();
			 });
		 }
		 
  });

</script>