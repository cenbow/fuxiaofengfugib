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
                		<th>标题</th>
                		<th>状态</th>
                		<th>活动类型</th>
                		<th>当前活动阶段</th>
                		<th>参加人数</th>
                		<th>参加人次</th>
						<!-- <th>分享量</th> -->
                		<th>编辑时间</th>
                		<th>激活状态</th>
                		<th>排序号</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" name="ace" class="ace" id="${item.id}" status="${item.status}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.title}</td>
                		<td><span class="label 
               			<c:choose>
                    		<c:when test="${item.status eq 1 }">label-info</c:when>
                    		<c:when test="${item.status eq 3 }">label-success</c:when>
                    		<c:when test="${item.status eq 88 }">label-danger</c:when>
                    		<c:otherwise>label-info</c:otherwise>
                    	</c:choose>
                		">${allStatuss[item.status]}</span></td>
                		<td>
                			<c:if test="${not empty item.showTypeSet}">
						        <c:forEach items="${item.showTypeSet}" var="type" >
						        	<c:if test="${not empty type }">
						        		${allShowTypesStr[type]}&nbsp;
						        	</c:if>						                
						        </c:forEach>
							</c:if>
                		</td>
                        <td>
                        	<c:set var="idx1" value="100" />
                        	<c:if test="${not empty item.actTypes and not empty item.actStatus}">
						        <c:forEach items="${fn:split(item.actStatus, ',')}" var="status" varStatus="idx">
						        	<c:if test="${not empty status and status eq STATUS3}">
						        		<c:set var="idx1" value="${idx.index}" />
						        	</c:if>						                
						        </c:forEach>
						        ${allTypesStr[fn:split(item.actTypes, ',')[idx1]]}
							</c:if>
                        </td>
                    	<td>${empty item.participantsNo ? 0 : item.participantsNo}</td>
                    	<td>${empty item.joinNo ? 0 : item.joinNo}</td>
<%--                     	<td>${empty item.shareNo ? 0 : item.shareNo}</td> --%>
                    	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td>${allActivationState[item.activationState]}</td>
                    	<td><input iid="${item.id}" type="text" url="/module/act/update_sort_no.html" class="only_num" value="${empty item.sortNo ? '' : item.sortNo}" style="width: 42px;" maxlength="2" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
<%--                         	<cqliving-security2:hasPermission name="/module/act/act_infoview.html"> --%>
<%-- 								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_infoview.html?id=${item.id}'">查看</button> --%>
<%--                             </cqliving-security2:hasPermission> --%>
							<cqliving-security2:hasPermission name="/module/act/act_infoadd.html">
		                        <a class="blue" url="/module/act/act_infoadd.html?id=${item.id}" forwordSaveParam="forwordSaveParam" save-form-id="act_infoFormId" href="javascript:void(0);" data-rel="tooltip" data-original-title="修改" data-placement="top">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            
                            <%-- 导出各个类型的活动数据 --%>
                            <%-- <cqliving-security2:hasPermission name="/module/act/export.html">
                            <c:forEach items="${item.typeSet}" var="type" >
					        	<c:if test="${not empty type and type ne TYPE1 and type ne TYPE2}">
					        		<button class="btn btn-xs btn-success export" type="button" actType="${type}" actId="${item.id}">导出${allTypesStr[type]}</button>
					        	</c:if>						                
					        </c:forEach>
					        </cqliving-security2:hasPermission> --%>
					        
					        <%-- 上线下线 --%>
					        <cqliving-security2:hasPermission name="/module/act/on_line.html">
						        <c:if test="${(STATUS88 eq item.status or STATUS1 eq item.status) and not empty item.typeSet}">
			                        <a class="blue on-out" href="javascript:;" tip="确认要上线么？" actId="${item.id}" url="on_line.html?id=${item.id}" data-rel="tooltip" data-original-title="上线" data-placement="top">
										<i class="icon-arrow-up bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
					        <cqliving-security2:hasPermission name="/module/act/out_line.html">
						        <c:if test="${(STATUS3 eq item.status)  and not empty item.typeSet}">
			                        <a class="red on-out" href="javascript:;" tip="确认要下线么？" actId="${item.id}" url="out_line.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<i class="icon-arrow-down bigger-130"></i>
									</a>
						        </c:if>
					        </cqliving-security2:hasPermission>
					        
					        <cqliving-security2:hasPermission name="/module/act/recommend.html">
					        	<c:if test="${(STATUS3 eq item.status) and (activationState3 eq item.activationState) and ((ISRECOMMEND0 eq item.isRecommend) or (empty item.isRecommend))}">
									<a class="blue" href="javascript:void(0);" url="recommend.html?id=${item.id}&_model_=_model" open-model="update" open-title="推荐到首页" data-rel="tooltip" data-original-title="推荐到首页" data-placement="top">
										<i class="icon-level-up bigger-130"></i>
									</a>
								</c:if>
                            </cqliving-security2:hasPermission>
                            
					        <cqliving-security2:hasPermission name="/module/act/cancel_recommend.html">
					        	<c:if test="${ISRECOMMEND1 eq item.isRecommend}">
									<a class="red on-out" href="javascript:;" tip="确认要下取消推荐么？" actId="${item.id}" url="cancel_recommend.html?id=${item.id}" data-rel="tooltip" data-original-title="取消推荐" data-placement="top">
										<i class="icon-level-down bigger-130"></i>
									</a>
								</c:if>
                            </cqliving-security2:hasPermission>
					        
							<cqliving-security2:hasPermission name="/module/act/act_infodelete.html">
			                    <a class="red" href="javascript:;" id="deleteButton" url="act_infodelete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="act_infoFormId" dataUrl="act_infolist.html" />
	</div>
</div>