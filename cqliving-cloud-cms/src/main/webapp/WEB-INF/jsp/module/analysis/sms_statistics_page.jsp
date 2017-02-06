<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>APPID</th>
                		<th>APP名称</th>
                		<th>发送总数</th>
                		<th>注册</th>
                		<th>登录</th>
                		<th>找回密码</th>
                		<th>修改密码</th>
                		<th>更换手机</th>
                    </tr>
                </thead>
				<tbody>
					<c:forEach items="${pageInfo.pageResults}" var="item">
	                    <tr>
	                		<td>${item.appId}</td>
	                		<td>${item.appName}</td>
	                		<td>${item.totalSend}</td>
	                		<td>${item.type0}</td>
	                		<td>${item.type1}</td>
	                		<td>${item.type2}</td>
	                		<td>${item.type3}</td>
	                		<td>${item.type4}</td>
	                    </tr>
	                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="sms_statistics_FormId" dataUrl="sms_statistics.html" />
	</div>
</div>