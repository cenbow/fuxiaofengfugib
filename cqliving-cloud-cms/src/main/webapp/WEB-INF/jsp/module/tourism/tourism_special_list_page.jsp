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
								<input type="checkbox" class="ace" value=""/>
								<span class="lbl"></span>
							</label>
						</th>
                		<th>编号</th>
                		<th>名称</th>
                		<th>分类</th>
                		<th>所处位置</th>
                		<th>所属区域</th>
                		<th>状态</th>
                		<th>排序号</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.tourismSpecialId}">
							<label>
								<input type="checkbox" class="ace" value="${item.tourismSpecialId}" id="${item.tourismSpecialId}"/>
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.id}</td>
                    	<td>${item.name}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                    	<td>${item.place}</td>
                    	<td>${item.regionName}</td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq 88}">
		                        	<span class="label label-danger">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 1}">
		                        	<span class="label label-warning">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 3}">
		                        	<span class="label label-success">${allStatuss[item.status]}</span>
								</c:when>
								<c:otherwise>
		                        	<span class="label label-info">${allStatuss[item.status]}</span>
								</c:otherwise>
                        	</c:choose>
                        </td>
                		<td><input type="text" class="only_num" value="${item.tourismSpecialSortNo gt 100 ? '' : item.tourismSpecialSortNo}" style="width: 42px;" maxlength="2" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/tourism/tourism_info_view.html">
								<a class="blue" href="javascript:void(0);" url="tourism_info_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
								<%-- 跳转页面的方式打开 <a class="blue" href="tourism_info_view.html?id=${item.id }" data-rel="tooltip" data-original-title="查看" data-placement="top"> --%>
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/tourism/tourism_info_update/1.html">
								<a class="blue" href="/module/tourism/tourism_info_update/1.html?id=${item.id }" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/tourism/tourism_special_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="tourism_special_delete.html?id=${item.tourismSpecialId}" data-original-title="移出专题" data-placement="top">
									<i class="icon-trash bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            
                            <c:choose>
                              <c:when test="${item.status eq 3 }">
                                 <cqliving-security2:hasPermission name="/module/tourism/tourism_special_offline.html">
		                            <a class="red offline_btn" href="javascript:void(0);" url="/module/tourism/tourism_special_offline.html?id=${item.tourismSpecialId}" data-rel="tooltip" data-original-title="下线" data-placement="top" status="88">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
							    </cqliving-security2:hasPermission>
                              </c:when>
                              <c:otherwise>
                                 <cqliving-security2:hasPermission name="/module/tourism/tourism_special_publish.html">
									<a class="blue publish_btn" href="javascript:;" data-rel="tooltip" url="/module/tourism/tourism_special_publish.html?id=${item.tourismSpecialId}" data-original-title="发布" data-placement="top" status="3">
										<i class="icon-mail-forward bigger-130"></i>
									</a>
							     </cqliving-security2:hasPermission>
                              </c:otherwise>
                            </c:choose>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="tourism_info_FormId" dataUrl="tourism_info_list.html" />
	</div>
</div>

<script type="text/javascript">

   require(['cqliving_dialog','cqliving_ajax','jquery'],function(cqliving_dialog,cq_ajax){
	   
	   $(".offline_btn,.publish_btn").bind("click",function(){
		    var jThis = $(this);
			var url = jThis.attr("url");
			var jstatus=jThis.attr("status");
			var msg = jstatus == 3 ? '发布' : '下线';
			cqliving_dialog.confirm('操作确认','确定'+msg+'吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{status:jstatus},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:msg+"成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:msg+"失败");
					}
				});
			});
		   
	   });
	  
   });
</script>

