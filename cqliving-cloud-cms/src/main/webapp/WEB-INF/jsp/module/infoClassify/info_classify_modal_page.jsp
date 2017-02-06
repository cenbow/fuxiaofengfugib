<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
                    	  选择专题
						</th>
                		<th>专题主题</th>
                		<th>前台标题</th>
                		<th>状态</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center">
							<label class="radio-2">
								<input type="radio" class="ace" name="infoClassifyId" value="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.specialTheme}</td>
                    	<td>${item.title}</td>
                        <td>
                        	<span class="label label-info">${allStatus[item.status] }</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax pageLength="5" paramFormId="info_classify_modal_form" dataUrl="/module/info/common/info_classify_modal.html" loadId="info_classify_load"/>
	</div>
	
</div>