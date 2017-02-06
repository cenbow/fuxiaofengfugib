<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <input name="appId" id="appId" type="hidden" value="${actInfo.appId}" maxlength="19">
                    <input name="actInfoId" id="actInfoId" type="hidden" value="${actInfo.id}" maxlength="19">
                    <input name="actInfoListId" id="actInfoListId" type="hidden" value="${item.actInfoListId}" maxlength="19">
                    <input type="hidden" name="qiniuPersistentId"/>
	            	<input type="hidden" name="qiniuDomain"/>
	            	<input type="hidden" name="extensions">
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="type">活动类型</label>
                        <div class="col-sm-10 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 普通投票</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 分类投票</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
                            </script>
                        </div>
                    </div>
                    
                    <div class="form-group col-xs-12">
                        <label class="col-sm-1 control-label no-padding-right" for="type"></label>
                        <div class="col-sm-10 table-responsive">
							<table class="table table-striped table-bordered table-hover dataTable">
								<tbody>
								   <tr>
			                         <td>
			                            <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="startTime">活动开始时间</label>
					                            <div class="col-sm-8">
					                            <div class="input-prepend input-group">
					                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="startTime" id="startTime" readonly="readonly" value="<fmt:formatDate value="${empty item.startTime ? actInfo.startTime : item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
					                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
					                            </div>
					                        </div>
					                    </div>
					                    
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="endTime">活动结束时间</label>
					                            <div class="col-sm-8">
					                            <div class="input-prepend input-group">
					                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="endTime" id="endTime" readonly="readonly" value="<fmt:formatDate value="${empty item.endTime ? actInfo.endTime : item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
					                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
					                            </div>
					                        </div>
					                    </div>
                                     </td>
                                     </tr>
                                     <tr>
                                     <td>
                                       <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitRateType">投票频次</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="0" id="limitRateType0"><span class="lbl"> 无限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="1" id="limitRateType1"><span class="lbl"> 总数限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="2" id="limitRateType2"><span class="lbl"> 每日限制</span>
					                            </label>
					                            <script type="text/javascript">
					                                document.getElementById("limitRateType${empty item ? 0 : item.limitRateType}").checked=true;
					                            </script>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitRateTimes">数量</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" name="limitRateTimes" id="limitRateTimes" type="text" value="${item.limitRateTimes ge 0 ? item.limitRateTimes : ''}" maxlength="10" placeholder="请输入数量">
					                        </div>
					                    </div>
                                     </td>
                                     </tr><tr>
                                     <td>
                                         <div class="form-group col-xs-6 limitSingleTypeDiv">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitSingleType">单项限制</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="0" id="limitSingleType0"><span class="lbl"> 无限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="1" id="limitSingleType1"><span class="lbl"> 总数限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitSingleType" value="2" id="limitSingleType2"><span class="lbl"> 每日限制</span>
					                            </label>
					                            <script type="text/javascript">
					                                document.getElementById("limitSingleType${empty item ? 0 : item.limitSingleType}").checked=true;
					                            </script>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6 limitSingleTimesDiv">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitSingleTimes">单项限制数量</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" name="limitSingleTimes" id="limitSingleTimes" type="text" value="${item.limitSingleTimes ge 0 ? item.limitSingleTimes : ''}" maxlength="10" placeholder="请输入数量">
					                        </div>
					                    </div>
                                     </td></tr>
                                     <tr><td>
                                         <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="loggedStatus">是否登录后才能操作</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="loggedStatus" value="0" id="loggedStatus0"><span class="lbl"> 否</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="loggedStatus" value="1" id="loggedStatus1"><span class="lbl"> 是</span>
					                            </label>
					                            <script type="text/javascript">
					                                document.getElementById("loggedStatus${empty item ? 0 : item.loggedStatus}").checked=true;
					                            </script>
					                        </div>
					                    </div>
                                     </td></tr>
                                     
                                     
                                     <tr class="hidden"> <td>
                                        <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitRuleType">选项类型</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRuleType" value="0" id="limitRuleType0" checked><span class="lbl"> 单选</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRuleType" value="1" id="limitRuleType1"><span class="lbl"> 多选</span>
					                            </label>
					                            <!-- <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRuleType" value="2" id="limitRuleType2"><span class="lbl"> 限制选多少项</span>
					                            </label> -->
					                            <!-- <script type="text/javascript">
					                                document.getElementById("limitRuleType${empty item ? 0 : item.limitRuleType}").checked=true;
					                            </script> -->
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitRuleTimes">数量(不填为不限制)</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" name="limitRuleTimes" id="limitRuleTimes" type="text" value="${item.limitRuleTimes ge 0 ? item.limitRuleTimes : ''}" maxlength="10" placeholder="请输入限制项数">
					                        </div>
					                    </div>
                                     </td></tr>
                                    <tr class="hidden"> <td>
                                        <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="isShare">分享后是否增加投票次数</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="isShare" value="0" id="isShare0"><span class="lbl"> 否</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="isShare" value="1" id="isShare1"><span class="lbl"> 是</span>
					                            </label>
					                            <script type="text/javascript">
					                                document.getElementById("isShare0").checked=true;
					                            </script>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="shareAddTimes">分享后增加投票次数</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" name="shareAddTimes" id="shareAddTimes" type="text" value="${item.shareAddTimes ge 0 ? item.shareAddTimes : ''}" maxlength="10" placeholder="请输入分享后增加投票次数">
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitShareType">分享次数限制</label>
					                        <div class="col-sm-8 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitShareType" value="0" id="limitShareType0"><span class="lbl"> 无限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitShareType" value="1" id="limitShareType1"><span class="lbl"> 总数限制</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitShareType" value="2" id="limitShareType2"><span class="lbl"> 每日限制</span>
					                            </label>
					                            <script type="text/javascript">
					                                document.getElementById("limitShareType0").checked=true;
					                            </script>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="limitShareTimes">分享限制次数</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" name="limitShareTimes" id="limitShareTimes" type="text" value="${item.limitShareTimes ge 0 ? item.limitShareTimes : ''}" maxlength="10" placeholder="请输入限制次数">
					                        </div>
					                    </div>
                                     </td></tr>
                                     
                                     <!-- 分类 -->
                                    <c:forEach items="${item.actVoteClassifyDtos }" var="voteClassify">
                                     <tr>
										<td>
											<div class="form-group col-xs-6 vote_classify_title">
												<label class="col-sm-4 control-label no-padding-right"
													for="limitShareTimes">分类标题</label>
												<div class="col-sm-8">
												    <input type="hidden" name="createTime" class="createTime" value="<fmt:formatDate value='${voteClassify.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
												    <input type="hidden" name="id" class="id" value="${voteClassify.id }">
												    <input type="hidden" name="sortNo" class="sortNo" value="${voteClassify.sortNo }">
													<input class="form-control limited classify_title" name="classify_title"
														type="text" value="${voteClassify.title}" maxlength="8" placeholder="请输入分类标题">
												</div>
											</div>
											<div class="form-group col-xs-6">
												<label class="col-sm-4 control-label no-padding-right"
													for="limitShareTimes">投票标题</label>
												<div class="col-sm-8">
													<input class="form-control limited subject" name="subject" defaultName="subject"
														type="text" value="${voteClassify.subject }" maxlength="50" placeholder="请输入投票标题">
												</div>
											</div>
											
											<div class="form-group col-xs-6">
												<label class="col-sm-4 control-label no-padding-right" for="isImageVote"></label>
												<div class="col-sm-8 checkbox">
												    <label class="checkbox-2">
														<input type="checkbox" class="ace" defaultName="isImageVote" name="isImageVote" value="1"  ${voteClassify.isImageVote eq 1 ? 'checked' : ''}>
														<span class="lbl">图片投票</span>
													</label>
												</div>
											</div>
											
											<div class="form-group col-xs-12 btn-search">
												<div class="col-sm-12">
													<a class="btn btn-sm btn-primary add_vote_item" href="#vote_item_modal" role="button" data-toggle="modal"> 
													   <i class="icon-plus align-top"></i> 添加投票项
													</a>
													<button class="btn btn-sm btn-primary del_vote_classify"
														role="button">
														<i class="icon-plus align-top"></i> 删除分类
													</button>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 widget-span">
												<div class="widget-box collapsed">
													<div class="widget-header">
														<h6>编号－－－－－－－－－－－－－－－－－－投票项－－－－－－－－－－－－－－－－－初始投票数</h6>
													</div>
												</div>
												
											   <c:forEach items="${voteClassify.actVoteItems }" var="voteItem" varStatus="vs">
												<div class="widget-box collapsed" uuid="${myfn:uuid()}">
													<div class="widget-header">
														<h6>${voteItem.number}－－－－－－－－－${voteItem.itemTitle }－－－－－－－－－${voteItem.initValue}</h6>
														<div class="widget-toolbar">
															<a class="btn-sm vote_item_edit" href="#vote_item_modal" role="button" data-toggle="modal">编辑</a> 
															<a data-action="close" href="javascript:;"><i class="icon-remove"></i>删除</a>
														</div>
													</div>
													<input type="hidden" name="id" value="${voteItem.id}" defaultName="id"/> 
													<input type="hidden" name="actVoteClassifyId" defaultName="actVoteClassifyId" value="${voteItem.actVoteClassifyId}"> 
													<input type="hidden" name="createTime" value="<fmt:formatDate value='${voteItem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" defaultName="createTime"/>
													<input type="hidden" name="actValue" value="${voteItem.actValue}" defaultName="actValue"/>
													<input type="hidden" name="number" value="${voteItem.number}" defaultName="number"/> 
													<input type="hidden" name="itemTitle" value="${voteItem.itemTitle}" defaultName="itemTitle"/> 
													<input type="hidden" name="itemDesc" value="${voteItem.itemDesc}" defaultName="itemDesc"/> 
													<input type="hidden" name="imageUrl" value="${voteItem.imageUrl}" defaultName="imageUrl"/> 
													<input type="hidden" name="videoUrl" value="${voteItem.videoUrl}" defaultName="videoUrl"/>
													<input type="hidden" name="initValue" value="${voteItem.initValue}" defaultName="initValue"/>
													<input type="hidden" name="sortNo" value="${voteItem.sortNo}" defaultName="sortNo">
													<input type="hidden" name="status" value="${voteItem.status}" defaultName="status">
													<textarea name="content" class="hidden" defaultName="content">${voteItem.content}</textarea>
												</div>
												</c:forEach>
											</div>
										</td>
									</tr>
                                   </c:forEach>
				                    <tr class="vote_classify_add">
				                      <td>
				                       <a class="btn btn-sm btn-primary" href="javascript:;" role="button">
											<i class="icon-plus align-top bigger-125"></i>
											添加分类
										</a></td>
				                    </tr>
				                </tbody>
							</table>
						</div>
					</div>
                    
                    <%-- <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="actTemplateCode">模板</label>
                        <div class="col-sm-10">
                           <ul class="ace-thumbnails">
                             <c:forEach items="${actTemplate }" var="temp">
                               <li>
                                 <label class="radio-2">
                                     <input type="radio" class="ace" name="actTemplateCode" value="${temp.templetCode}"><span class="lbl">${temp.name }</span>
                                 </label>
                                 <img alt="" src="${temp.imageUrl}" width="150px;">
                                </li>
                             </c:forEach>
                           </ul>
                        </div>
                    </div> --%>
                    <input type="hidden" class="ace" name="actTemplateCode" value="test_tmpcode"><span class="lbl">${temp.name }</span>
                	<div class="form-group col-xs-12">
                		<div class="col-sm-11">
							<div class="pull-right">
							    <cqliving-security2:hasPermission name="/module/act/act_vote_save.html">
									<button class="btn btn-success btn-sm vote_save" type="button">
										<i class="icon-save bigger-110"></i>提交
									</button>
								</cqliving-security2:hasPermission>
								&nbsp;
								<button class="btn btn-sm  btn-danger" type="button" onclick="javascript:location.href='/module/act/act_infoadd.html?id=${actInfo.id}'">
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

<!-- 投票选项弹层 -->
<div id="vote_item_modal" class="modal" tabindex="-1" uuid="">
	<div class="modal-dialog" style="width:60%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加投票项</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				   <form id="vote_item_form">
				   
				    <input type="hidden" name="id" value="" /> 
					<input type="hidden" name="actVoteClassifyId" value=""> 
					<input type="hidden" name="createTime" value="" />
					<input type="hidden" name="actValue" value="" />
					<input type="hidden" name="status" value="3" />
				    <input type="hidden"  name="videoUrl" value="">
				    <input type="hidden" name="qiniuHash" value=""/>
	            	<input type="hidden" name="qiniuKey" value=""/>
	            	<input type="hidden" name="originalName" value="">
	            	
					<div class="form-group col-xs-12">
                        <div class="col-sm-12 table-responsive">
							<table class="table table-striped table-bordered table-hover dataTable">
								<tbody>
								   <tr>
			                         <td>
			                            <div class="form-group col-xs-6">
					                        <label class="col-sm-4 control-label no-padding-right" for="startTime">编号</label>
					                        <div class="col-sm-8">
					                           <input class="form-control" type="text" maxlength="8"  name="number" value="" placeholder="请输入投票编号">
					                        </div>
					                    </div>
                                     </td>
                                     </tr>
                                     <tr>
                                     <td>
                                       <div class="form-group col-xs-12">
					                        <label class="col-sm-2 control-label no-padding-right" for="limitRateType">投票项</label>
					                        <div class="col-sm-10">
					                            <input class="form-control" type="text"  name="itemTitle" value="" placeholder="请输入投票选项" maxlength="80">
					                            <div id="limit_item_title"></div>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-12">
					                        <label class="col-sm-2 control-label no-padding-right" for="limitRateTimes">投票项描述</label>
					                        <div class="col-sm-10">
					                            <textarea class="form-control" name="itemDesc" maxlength="300" placeholder="投票项描述"></textarea>
					                            <div id="limit_item_desc"></div>
					                        </div>
					                    </div>
                                     </td>
                                     </tr><tr>
                                     <td>
                                         <div class="form-group col-xs-12">
					                        <label class="col-sm-2 control-label no-padding-right" for="limitSingleType">投票项初始量</label>
					                        <div class="col-sm-8">
					                            <input class="form-control" type="text"  name="initValue" value="0" placeholder="请输入投票初始量" maxlength="10">
					                        </div>
					                    </div>
                                     </td></tr>
                                    <tr> <td>
                                        <div class="form-group col-xs-12">
					                        <label class="col-sm-2 control-label no-padding-right" for="limitRuleType">选项图片</label>
					                        <div class="col-sm-8" >
					                            <input class="hidden" type="text"  name="imageUrl" value="">
					                            <span id="item_img">
					                               <i class="icon-cloud-upload"></i>
								                                      上传图片
								                </span>
					                        </div>
					                    </div>
					                    <div class="form-group col-xs-12">
					                        <label class="col-sm-2 control-label no-padding-right" for="limitRuleTimes"></label>
					                        <div class="col-sm-8" id="item_img_thum">
					                            <ul class="ace-thumbnails"></ul>
					                        </div>
					                    </div>
                                     </td></tr>
                                     <tr><td>
					                         <div class="form-group col-xs-12">
						                        <label class="col-sm-2 control-label no-padding-right">上传视频</label>
						                        <div id="video_container" style="position: relative;">
						                            <a href="javascript:;" id="video_pickfiles" class="btn btn-primary">
						                                <i class="glyphicon glyphicon-plus"></i>
						                                <span>选择文件</span>
						                            </a>
						                         </div>
						                    </div>
							            	
						                    <div class="col-md-12" id="success" style="display:none">
						                        <div class="alert-success">
						                                                                队列全部文件处理完毕
						                        </div>
						                    </div>
						                    <div class="col-md-12 ">
						                        <table style="margin-top:40px;display:none" class="qiniu_table table table-striped table-hover text-left">
						                            <thead>
						                              <tr>
						                                <th class="col-md-3">文件名称</th>
						                                <th class="col-md-2">文件大小</th>
						                                <th class="col-md-7">详情</th>
						                              </tr>
						                            </thead>
						                            <tbody id="fsUploadProgress">
						                            </tbody>
						                        </table>
						                    </div>
                                     </td></tr>
                                     
                                    <tr> <td>
                                        <div class="form-group col-xs-12">
					                        <label class="col-sm-3 control-label no-padding-right" for="isShare">选项详情</label>
					                        <div class="col-sm-12">
					                            <textarea class="hidden" id="content" name="content"></textarea>
                                                <script id="content_ueditor" type="text/plain"></script>
					                        </div>
					                    </div>
                                     </td></tr>
				                </tbody>
							</table>
						</div>
					</div>
				  </form>
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

<!-- 分类模板 -->
<div id="classify_temp_div" class="hidden">
	<table>
		<tbody>
			<tr>
				<td>
					<div class="form-group col-xs-6 vote_classify_title">
						<label class="col-sm-4 control-label no-padding-right"
							for="limitShareTimes">分类标题</label>
						<div class="col-sm-8">
						    <input type="hidden" name="createTime" class="createTime">
						    <input type="hidden" name="id" class="id">
						    <input type="hidden" name="sortNo" class="sortNo">
							<input class="form-control limited classify_title" name="classify_title"
								type="text" value="" maxlength="8" placeholder="请输入分类标题">
						</div>
					</div>
					<div class="form-group col-xs-6">
						<label class="col-sm-4 control-label no-padding-right"
							for="limitShareTimes">投票标题</label>
						<div class="col-sm-8">
							<input class="form-control limited subject" name="subject"
								type="text" value="" maxlength="50" placeholder="请输入投票标题">
						</div>
					</div>
					
					<div class="form-group col-xs-6">
						<label class="col-sm-4 control-label no-padding-right" for="isImageVote"></label>
						<div class="col-sm-8 checkbox">
						    <label class="checkbox-2">
								<input type="checkbox" class="ace" name="isImageVote" value="1" defaultName="isImageVote">
								<span class="lbl">图片投票</span>
							</label>
						</div>
					</div>
					
					<div class="form-group col-xs-12 btn-search">
						<div class="col-sm-12">
							<a class="btn btn-sm btn-primary add_vote_item" href="#vote_item_modal" role="button" data-toggle="modal"> 
							   <i class="icon-plus align-top"></i> 添加投票项
							</a>
							<button class="btn btn-sm btn-primary del_vote_classify"
								role="button">
								<i class="icon-plus align-top"></i> 删除分类
							</button>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 widget-span">
						<div class="widget-box collapsed">
							<div class="widget-header">
								<h6>编号－－－－－－－－－－－－－－－－－－投票项－－－－－－－－－－－－－－－－－初始投票数</h6>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- 投票项模板 -->
<div id="vote_item_temp" class="hidden">
    <div class="widget-box collapsed" uuid="">
		<div class="widget-header">
			<h6></h6>
			<div class="widget-toolbar">
				<a class="btn-sm vote_item_edit" href="#vote_item_modal" role="button" data-toggle="modal">编辑</a> 
				<a data-action="close" href="javascript:;"><i class="icon-remove"></i>删除</a>
			</div>
		</div>
		<input type="hidden" name="id" value="" defaultName="id"/> 
		<input type="hidden" name="actVoteClassifyId" defaultName="actVoteClassifyId"> 
		<input type="hidden" name="createTime" value="" defaultName="createTime"/>
		<input type="hidden" name="actValue" value="" defaultName="actValue"/>
		<input type="hidden" name="number" value="" defaultName="number"/> 
		<input type="hidden" name="itemTitle" value="" defaultName="itemTitle"/> 
		<input type="hidden" name="itemDesc" value="" defaultName="itemDesc"/> 
		<input type="hidden" name="imageUrl" value="" defaultName="imageUrl"/> 
		<input type="hidden" name="videoUrl" value="" defaultName="videoUrl"/>
		<input type="hidden" name="initValue" value="" defaultName="initValue"/>
		<input type="hidden" name="sortNo" value="" defaultName="sortNo">
		<input type="hidden" name="status" value="3" defaultName="status">
		<input type="hidden" name="qiniuHash" value="" defaultName="qiniuHash"/>
       	<input type="hidden" name="qiniuKey" defaultName="qiniuKey" value=""/>
       	<input type="hidden" name="originalName" defaultName="originalName" value="">
		<textarea name="content" class="hidden" defaultName="content"></textarea>
	</div>
</div>

<script type="text/javascript">
	var imgUrl = '${imageUrl}';
	window.UEDITOR_HOME_URL = '/resource/components/ueditor/';

	require([ 'validator.bootstrap', 'cloud.table.curd', 'cloud.time.input',
			'/resource/business/acti/vote_add.js?v=v1' ], function($, tableCurd,
			timeInput) {

		timeInput.initTimeInput();
		tableCurd.bindSaveButton();
		
		//禁用enter键
		$(document).keypress(function(event) {   
		     if(event.keyCode==13){ 
		        return false;
		     }   
		}); 
		
			$("#form1").validate({
				rules : {
					type : {
						required : true
					},
					startTime : {
						required : true
					},
					endTime : {
						required : true
					},
					limitRateType : {
						required : true
					},
					limitRateTimes : {
						number : true
					},
					limitSingleType : {
						required : true
					},
					limitSingleTimes : {
						number : true
					},
					limitRuleType : {
						required : true
					},
					limitRuleTimes : {
						number : true
					},
					loggedStatus : {
						required : true
					},
					isShare : {
						required : true
					},
					shareAddTimes : {
						number : true
					},
					limitShareType : {
						required : true
					},
					limitShareTimes : {
						number : true
					}
				},
				messages : {
					type : {
						required : ' '
					},
					startTime : {
						required : ' '
					},
					endTime : {
						required : ' '
					},
					limitRateType : {
						required : ' '
					},
					limitRateTimes : {
						number : ' '
					},
					limitSingleType : {
						required : ' '
					},
					limitSingleTimes : {
						number : ' '
					},
					limitRuleType : {
						required : ' '
					},
					limitRuleTimes : {
						number : ' '
					},
					loggedStatus : {
						required : ' '
					},
					isShare : {
						required : ' '
					},
					shareAddTimes : {
						number : ' '
					},
					limitShareType : {
						required : ' '
					},
					limitShareTimes : {
						number : ' '
					}
				}
			});
			$("#vote_item_form").validate({
				ignore:"",
				rules : {
					number : {
						required : true
					},
					itemTitle : {
						required : true
					},
					itemDesc : {
						required : true
					},
					initValue : {
						required : true
					}
				},
				messages : {
					number : {
						required : ' '
					},
					itemTitle : {
						required : ' '
					},
					itemDesc : {
						required : ' '
					},
					initValue : {
						required : ' '
					}
				}
			});
	});
</script>