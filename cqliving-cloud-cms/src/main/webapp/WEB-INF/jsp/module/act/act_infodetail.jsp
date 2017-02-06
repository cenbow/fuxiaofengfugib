<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	    <jsp:param value="活动管理|/module/act/act_infolist.html" name="_breadcrumbs_1"/>
	    <jsp:param value="${not empty item ? '修改' : '新增'}" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="act_infoupdate.html">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <input type="hidden" name="add_content" value="">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="appId">所属客户端<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
                            <select name="appId" class="form-control chosen-select" data-placeholder="请选择所属客户端">
                               <c:forEach items="${allApps }" var="app">
                                  <option value="${app.id }" <c:if test="${item.appId eq app.id}">selected</c:if> >${app.name }</option>
                               </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="title">标题<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" maxlength="50" placeholder="请输入标题"  value="${item.title}">
                            <span class="sns-num">还能输入<em>24</em>个字</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="startTime">活动开始时间<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="startTime" id="startTime" readonly="readonly" value="<fmt:formatDate value="${not empty item.startTime ? item.startTime : dateNow}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="endTime">活动结束时间<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="endTime" id="endTime" readonly="readonly" value="<fmt:formatDate value="${not empty item.endTime ? item.endTime : dateNow}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="digest">摘要</label>
                            <div class="col-sm-8">
                            <textarea class="form-control limited" id="digest" name="digest" maxlength="500" placeholder="请输入摘要">${item.digest}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="listImageUrl">列表图片<i class="text-danger">*</i></label>
                         <div class="col-sm-2" id="list_img">
                            <input type="text" class="hidden" id="listImageUrl" name="listImageUrl" maxlength="255" placeholder="请输入图片列表，只能一张"  value="${item.listImageUrl}">
                            <i class="icon-cloud-upload"></i>
								上传图片
                        </div>
                        <span class="red">图片尺寸：720*308</span>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="list_img_thum">
                          <c:if test="${not empty  item.listImageUrl}">
                           <ul class="ace-thumbnails">
                             <c:forEach items="${fn:split(item.listImageUrl,',')}" var="img" varStatus="vs">
                                 <li id="${vs.count + 100 }">
                                   <a href="${img }" data-rel="colorbox">
	                                 <img alt="150x150" src="${img }" style="width:150px;height:150px;">
	                               </a>
	                                 <div class="tools tools-top"> 
							          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
							 		 </div>
	                              </li>
                             </c:forEach>
                           </ul>
                          </c:if>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="actImageUrl">活动图片<i class="text-danger">*</i></label>
                        <div class="col-sm-8" id="content_img">
                            <input type="text" class="hidden" id="actImageUrl" name="actImageUrl" maxlength="1000" placeholder="请输入活动列表图片，只多三张"  value="${item.actImageUrl}">
                            <i class="icon-cloud-upload"></i>
								上传图片
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="content_img_thum">
                          <c:if test="${not empty  item.actImageUrl}">
                           <ul class="ace-thumbnails">
                             <c:forEach items="${fn:split(item.actImageUrl,',')}" var="img" varStatus="vs">
                                 <li id="${vs.count + 100 }">
                                   <a href="${img }" data-rel="colorbox">
	                                 <img alt="150x150" src="${img }" style="width:150px;height:150px;">
	                               </a>
	                                 <div class="tools tools-top"> 
							          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
							 		 </div>
	                              </li>
                             </c:forEach>
                           </ul>
                          </c:if>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="content">内容<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <textarea class="hidden" id="content" name="content" placeholder="请输入内容,包含HTML标签的富文本内容">${item.content}</textarea>
                            <script id="content_ueditor" type="text/plain">${item.content}</script>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="contextText">添加内容<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
                           <a class="btn blue btn-primary" href="#content_modal_form" role="button" data-toggle="modal">
								<i class="icon-plus align-top bigger-125"></i>
								添加内容
							</a>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="table-responsive col-sm-8">
							<table class="table table-striped table-bordered table-hover dataTable content_table">
								<thead>
				                    <tr>
				                		<th>序号</th>
				                		<th>时段</th>
				                		<th>类型</th>
				                		<th>状态</th>
				                		<th>操作</th>
				                    </tr>
				                </thead>
								<tbody>
								   <c:forEach items="${item.actInfoList }" var="aclist" varStatus="vs">
								      <tr actInfoListIndex="${vs.index}">
				                        <td>
				                           ${aclist.id }
				                           <input type="hidden" name="actInfoList[${vs.index }].type" value="${aclist.type}" class="type">
				                           <input type="hidden" name="actInfoList[${vs.index }].showType" value="${empty aclist.showType ? aclist.type : aclist.showType}" class="showType">
				                           <input type="hidden" name="actInfoList[${vs.index }].linkUrl" value="${aclist.linkUrl}" class="linkUrl">
				                           <input type="hidden" name="actInfoList[${vs.index }].id" value="${aclist.id }" class="id"/>
				                           <input type="hidden" name="actInfoList[${vs.index }].status" value="${aclist.status}" class="status"/>
				                        </td>
				                        <td><fmt:formatDate value="${aclist.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /> -
				                            <fmt:formatDate value="${aclist.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				                        </td>
				                        <td>${actListType[aclist.type] }</td>
				                        <td>${actListStatus[aclist.status] }</td>
				                        <td>
				                           <!-- 打擂  -->
				                           <c:if test="${aclist.type eq 3}">
	                                           <a class="blue" type="button" href="/module/act/act_voteadd.html?actInfoId=${item.id }&actInfoListId=${aclist.id}" data-rel="tooltip" data-original-title="编辑" data-placement="top"><i class="icon-pencil bigger-130"></i></a>
				                           </c:if>
				                           <!-- 答卷  -->
				                           <c:if test="${aclist.type eq 4}">
	                                           <a class="blue" type="button" href="/module/act/act_testupdate.html?actInfoId=${item.id }&actInfoListId=${aclist.id}" data-rel="tooltip" data-original-title="编辑" data-placement="top"><i class="icon-pencil bigger-130"></i></a>
				                           </c:if>
				                           <!-- 外链  -->
				                           <c:if test="${aclist.type eq 2 }">
				                              <a class="blue" href="#linkurl_modal_form" role="button" data-toggle="modal" data-rel="tooltip" data-original-title="编辑" data-placement="top"><i class="icon-pencil bigger-130"></i></a>
				                           </c:if>
				                           
                                           <c:if test="${aclist.status ne 99}">
                                             <a class="red btn-del" type="button" href="javascript:;" data-rel="tooltip" data-original-title="删除" data-placement="top"><i class="icon-trash bigger-130"></i></a>
                                           </c:if>
                                           
                                           <c:choose>
                                             <c:when test="${aclist.status eq 1 }">
                                                 <a class="blue active" href="javascript:;" data-rel="tooltip" data-original-title="激活" data-placement="top"><i class="icon-mail-forward bigger-130"></i></a>
                                             </c:when>
                                             <c:when test="${aclist.status eq 3 }">
                                                <a class="red active" href="javascript:;" data-rel="tooltip" data-original-title="取消激活" data-placement="top"><i class="icon-arrow-down bigger-130"></i></a>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                           </c:choose>
                                           <!-- 导出数据  FangHuiLin  2016年12月8日 -->
				                           <c:if test="${not empty item }">
									           <c:if test="${aclist.type eq TYPE3}">
													<cqliving-security2:hasPermission name="/module/act/act_export.html">
														 <a class="blue" id="export" href="javascript:;" url="/module/act/act_export.html?classfyId=${aclist.id}"><i class="icon-download-alt bigger-130"></i></a>
													</cqliving-security2:hasPermission>
											   </c:if>
										   </c:if>
                                        </td>
				                    </tr>
								   </c:forEach>
				                </tbody>
							</table>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10">
							<div class="pull-right">
								<cqliving-security2:hasPermission name="/module/act/act_infoupdate.html">
									<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_infolist.html">
										<i class="icon-save bigger-110"></i>提交
									</button>
								</cqliving-security2:hasPermission>
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:void(0);" backRestoreParam="backRestoreParam" back_url="/module/act/act_infolist.html" >
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<div id="content_modal_form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加活动</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
						</div>
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary announcement" href="javascript:;" role="button">
								<i class="icon-plus align-top bigger-125"></i>
								添加公告
							</a>
						</div>
						
						<cqliving-security2:hasPermission name="/module/act/act_voteadd.html">
						    <div class="form-group col-xs-3">
								<a class="btn blue btn-primary" href="javascript:;" role="button" href_url="/module/act/act_voteadd.html">
									<i class="icon-plus align-top bigger-125"></i>
									添加投票
								</a>
							</div>
						</cqliving-security2:hasPermission>
						
						<cqliving-security2:hasPermission name="/module/act/act_testadd.html">
							<div class="form-group col-xs-3">
								<a class="btn blue btn-primary" href="javascript:;" role="button" href_url="/module/act/act_testadd.html">
									<i class="icon-plus align-top bigger-125"></i>
									添加答题
								</a>
							</div>
						</cqliving-security2:hasPermission>
						
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" href="#linkurl_modal_form" role="button" data-toggle="modal">
								<i class="icon-plus align-top bigger-125"></i>
								添加外链
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

<div id="linkurl_modal_form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加活动</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="title">请输入外链地址<i class="text-danger">*</i></label>
                          <div class="col-sm-8">
                            <input type="text" class="form-control" id="modal_linkUrl" name="modal_linkUrl" maxlength="255" placeholder="请输入外链地址">
                            <input type="hidden" name="modal_actListId" value=""/>
                          </div>
                       </div>
                       
                       <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="title">请选择显示类型<i class="text-danger">*</i></label>
                          <div class="col-sm-8">
                            <select name="modal_showType" class="form-control">
                               <c:forEach items="${actShowType }" var="showType">
                                     <option value="${showType.key}" ${showType.key eq 2 ? 'selected=selected' : ''}>${showType.value }</option>
                               </c:forEach>
                            </select>
                          </div>
                       </div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-primary">
					<i class="icon-ok"></i>
					确定
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

  <table id="tr_tmp" class="hidden">
     <tbody>
	    <tr actInfoListIndex="0">
	        <td></td>
	        <td></td>
	        <td></td>
	        <td>未激活</td>
	        <td>
	           <input type="hidden" name="actInfoList[0].type" class="type">
	           <input type="hidden" name="actInfoList[0].showType" class="showType">
	           <input type="hidden" name="actInfoList[0].linkUrl" class="linkUrl">
	           <input type="hidden" name="actInfoList[0].id" class="id"/>
	           <input type="hidden" name="actInfoList[0].status" value="1" class="status"/>
	           <a class="blue edit" href="javascript:;" type="button" data-target="#linkurl_modal_form" data-toggle="modal" data-rel="tooltip" data-original-title="编辑" data-placement="top"><i class="icon-pencil bigger-130"></i></a>
	           <a class="red btn-del" href="javascript:;" data-rel="tooltip" data-original-title="删除" data-placement="top"><i class="icon-trash bigger-130"></i></a>
	           <a class="blue active" href="javascript:;" data-rel="tooltip" data-original-title="激活" data-placement="top"><i class="icon-mail-forward bigger-130"></i></a>
	       </td>
	    </tr>
      </tbody>
  </table>


<script type="text/javascript">
var imgUrl = '${imageUrl}';
window.UEDITOR_HOME_URL = '/resource/components/ueditor/';

require(['validator.bootstrap','cloud.table.curd','cloud.time.input','common_colorbox','/resource/business/acti/acti_add.js'], function($,tableCurd,timeInput,common_colorbox){
	timeInput.initTimeInput();
	tableCurd.bindSaveButton();
	common_colorbox.init();
	$("#export").on("click",function(){
		var url = $(this).attr("url");
		window.location.href=url;
	});
	$('body').tooltip({selector: '[data-rel=tooltip]'});
    $(function(){
        $("#form1").validate({
        	ignore:"",
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    startTime : {
                    required: true
                },
                    endTime : {
                    required: true
                },
                    listImageUrl : {
                    required: true
                },
                    actImageUrl : {
                    required: true
                },
                    content : {
                    required: true
                },
                add_content : {
                	required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                title : {
                    required: ' '
                },
                startTime : {
                    required: ' '
                },
                endTime : {
                    required: ' '
                },
                listImageUrl : {
                    required: ' '
                },
                actImageUrl : {
                    required: ' '
                },
                content : {
                    required: ' '
                },
                add_content : {
                	required: ' '
                }
            }
        });
    });
});
</script>