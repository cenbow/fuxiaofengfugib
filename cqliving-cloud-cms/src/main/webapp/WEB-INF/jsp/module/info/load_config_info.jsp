<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<div class="form-group">
		    <label class="col-sm-3 control-label no-padding-right"></label>
		    <div class="col-sm-9">
				<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
					<thead>
			            <tr>
				       		<th width="50%">栏目</th>
				       		<th width="50%">轮播图显示数量<i class="text-danger">*</i></th>
			            </tr>
			        </thead>
					<tbody>
					<c:if test="${not empty sliderList}">
						<c:forEach items="${sliderList}" var="slider" varStatus="idx">
			            <tr>
			        		<td width="50%">
			        			${slider.columnsName}
			        			<input name="columnsId" type="hidden" value="${slider.columnsId}"/>
					        	<input name="id" type="hidden" value="${empty slider.id ? '-1' : slider.id}"/>
			        		</td>
			        		<td width="50%">
			        			<input class="form-control val-input" name="value" type="text" value="${slider.value}" maxlength="3" placeholder="请输入轮播图显示数量">
			        			<span style="color: #d16e6c;"></span>
			        		</td>
			            </tr>
			        	</c:forEach>
					</c:if>
					<c:if test="${empty sliderList}">
						<tr>
			        		<td colspan="2" style="color: red;">
			        			没有栏目需要显示轮播图
			        		</td>
			            </tr>
					</c:if>
			        </tbody>
				</table>
		    </div>
		</div>