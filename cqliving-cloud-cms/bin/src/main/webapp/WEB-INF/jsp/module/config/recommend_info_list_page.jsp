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
                		<th>名称</th>
                		<c:if test="${sourceType ne 1 }">
	                		<th>图片</th>
	                		<th>链接地址</th>
                		</c:if>
                		<th>排序号</th>
                		<th>状态</th>
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
                    	<td>${item.sourceName}</td>
                    	<c:if test="${sourceType ne 1 }">
                    		<td>
	                    		<c:if test="${not empty item.imageUrl}">
	                    			<img height="50 px;" width="50 px;" src="${item.imageUrl}"/>
	                    		</c:if>
                    		</td>
	                    	<td>${item.linkUrl}</td>
                    	</c:if>
                		<td><input type="text" class="sortNo" style="width:60px;" value="${item.sortNo}" maxlength="9" data-id="${item.id }"></td>
                        <td>
                        	<span class="label 
                        	<c:choose>
                        		<c:when test="${item.status eq 3 }">label-success</c:when>
                        		<c:when test="${item.status eq 88 }">label-warning</c:when>
                        		<c:otherwise>label-info</c:otherwise>
                        	</c:choose>
                        	">${allStatuss[item.status] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                            <c:choose>
                            	<c:when test="${sourceType eq 1 }">
                            		<cqliving-security2:hasPermission name="/module/info/common/info_view.html">
                         				<a class="blue preview_btn" href="javascript:;" url="/module/info/common/info_view.html?infoId=${item.sourceId}" data-rel="tooltip" data-original-title="预览" data-placement="top">
											<i class="icon-search bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:when>
                            	<c:otherwise>
									<a class="blue" href="javascript:void(0);" url="/module/config/${sourceType }/common/recommend_info_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
                            	</c:otherwise>
                            </c:choose>
                            <cqliving-security2:hasPermission name="/module/config/${sourceType }/recommend_info_publish.html">
                            <c:if test="${item.status eq 1 || item.status eq 88 }">
								<a class="blue publishBtn" href="javascript:void(0);" url="/module/config/${sourceType }/recommend_info_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
									<i class="icon-mail-forward bigger-130"></i>
								</a>
							</c:if>
							<c:if test="${item.status eq 3 }">
								<a class="red publishCancelBtn" href="javascript:void(0);" url="/module/config/${sourceType }/recommend_info_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="取消发布" data-placement="top">
									<i class="icon-mail-reply bigger-130"></i>
								</a>
							</c:if>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/config/${sourceType }/recommend_info_delete.html">
								<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="/module/config/${sourceType }/recommend_info_delete.html?id=${item.id }" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="recommend_info_FormId" dataUrl="recommend_info_list.html" />
	</div>
</div>