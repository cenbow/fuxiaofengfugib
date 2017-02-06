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
                		<th>编号</th>
                		<c:if test="${not empty apps and fn:length(apps) ge 2}">
                		   <th>客户端名称</th>
                		</c:if>
                		<th>全媒体名称</th>
                		<th>类型</th>
                		<th>所在栏目</th>
                		<th>排序号</th>
                		<th>状态</th>
                		<th>创建时间</th>
                		<th>更新时间</th>
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
                		<c:if test="${not empty apps and fn:length(apps) ge 2}">
                		  <td>${item.appName}</td>
                		</c:if>
                    	<td>${item.name}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                		<td>${item.columnsName}</td>
                		<td><input type="text" name="sortNo" value="${item.sortNo ge 200 ? '' : item.sortNo}" class="width-40"/></td>
                        <td>
                        	<span class="label label-success">${allStatuss[item.status] }</span>
                        </td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<cqliving-security2:hasPermission name="/module/all_media/all_media_add.html">
								<%-- <a class="blue" href="javascript:void(0);" url="all_media_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top"> --%>
							   <a class="blue" href="javascript:;" forwordSaveParam="forwordSaveParam" url="common/all_media_update.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/all_media/all_media_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="all_media_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="all_media_FormId" dataUrl="all_media_list.html" />
	</div>
</div>

<script type="text/javascript">

  require(["cqliving_ajax"],function(cqliving_ajax){
	  
	  $("#sample-table-2_wrapper input[name=sortNo]").blur(function(){
		  
		  var $this = $(this);
		  var $td = $this.closest("tr").find("td:eq(0)")
		  var sortNo = $this.val();
		  var id = $td.attr("id");
		  if(!sortNo)sortNo = 10000000;
		  var url = "/module/all_media/update_sort_no.html";
		  var param = {id:id,sortNo:sortNo};
		  cqliving_ajax.ajaxOperate(url,"",param,function(data,status){
			  $("#searchButton").click();
		  });
		  
	  });
	  
  });
</script>