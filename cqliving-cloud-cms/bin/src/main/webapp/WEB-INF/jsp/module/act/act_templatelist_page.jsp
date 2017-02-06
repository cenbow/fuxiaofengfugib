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
                		<th>客户端_ID</th>
                		<th>模板CODE</th>
                		<th>模板图片</th>
                		<th>模板类型</th>
                		<th>模版描述</th>
                		<th>模版名称</th>
                		<th>模版公有状态</th>
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
                		<td>${item.appId}</td>
                    	<td>${item.templetCode}</td>
                    	<td>${item.imageUrl}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                    	<td>${item.templetDesc}</td>
                    	<td>${item.name}</td>
                        <td>
                        	<span class="label label-info">${allCommonTypes[item.commonType] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_templateview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_templateview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_templateupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_templateupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_templatedelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_templatedelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_templateFormId" dataUrl="act_templatelist.html" />
	</div>
</div>