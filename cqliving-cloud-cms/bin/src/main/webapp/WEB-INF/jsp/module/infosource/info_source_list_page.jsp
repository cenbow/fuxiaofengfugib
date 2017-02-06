<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th>
                		<th>ID</th>
                		<c:if test="${fn:length(allApps) ge 2}">
                		  <th>所属客户端</th>
                		</c:if>
                		<th>名称</th>
                		<cqliving-security2:hasPermission name="/module/info/info_source_sort.html">
                		<th>排序号</th>
                		</cqliving-security2:hasPermission>
                		<th>最后修改时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.id}</td>
                		<c:if test="${fn:length(allApps) ge 2}">
                		  <td>${item.appName}</td>
                		</c:if>
                    	<td>${item.name}</td>
                    	<cqliving-security2:hasPermission name="/module/info/info_source_sort.html">
                		  <td><input name="sortNo" value="${item.sortNo ge 300 ? '' : item.sortNo}" id="${item.id }"></td>
                		</cqliving-security2:hasPermission>
                		<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<cqliving-security2:hasPermission name="/module/info/info_source_add.html">
								<%-- <a class="blue" href="javascript:void(0);" url="info_source_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top"> --%>
								<a class="blue" href="javascript:;"  forwordSaveParam="forwordSaveParam"  url="info_source_add.html?id=${item.id }"   data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/info_source_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="info_source_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="info_source_FormId" dataUrl="info_source_list.html" />
	</div>
</div>

<script type="text/javascript">

  require(['cqliving_ajax','cqliving_dialog'],function(cqliving_ajax,cqliving_dialog){
	  $("#sample-table-2 input[name=sortNo]").blur(function(){
		  
		  var sortNo = $(this).val();
		  if(!sortNo){
			  sortNo = 2147483647;
		  }
		  if(!parseInt(sortNo,10)){
			  cqliving_dialog.error("请输入数字序号");
			  return;
		  }
         var url = "info_source_sort.html";
         var params = {sortNo:sortNo,id:$(this).attr("id")};
         cqliving_ajax.ajaxOperate(url,null,params,function(){
        	 $("#searchButton").click();
         });
	  });
  });

</script>

