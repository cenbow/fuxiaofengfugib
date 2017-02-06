<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
    <c:choose>
      <c:when test="${fn:contains(back_url,'info_classify_list_special_sub')}">
         <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="专题子新闻管理|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改专题子新闻' : '新增专题子新闻'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:when>
      <c:when test="${fn:contains(back_url,'info_classify_list_special')}">
         <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="专题管理|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改专题' : '新增专题'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:when>
      <c:when test="${fn:contains(back_url,'info_classify_list_recommend')}">
         <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="推荐新闻管理|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改推荐新闻' : '新增新闻'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:when>
      <c:when test="${fn:contains(back_url,'info_classify_list_copy')}">
         <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="复制新闻管理|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改新闻' : '新增新闻'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:when>
      <c:when test="${fn:contains(back_url,'classify_news_list')}">
         <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="新闻查询|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改新闻' : '新增新闻'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:when>
      <c:otherwise>
          <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
				<jsp:param value="新闻管理|${back_url}" name="_breadcrumbs_1"/>
				<jsp:param value="${not empty infoClassify ? '修改新闻' : '新增新闻'}" name="_breadcrumbs_3"/>
		   </jsp:include>
      </c:otherwise>
    </c:choose>
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" canSumbit="yes">
	            	<input type="hidden" name="id" value="${infoClassify.id}" />
	            	<input type="hidden" name="informationId" value="${infoClassify.informationId }">
	            	<input type="hidden" name="news_copy" value="${news_copy}">
	            	<input type="hidden" name="hisColumnsId" value="${not empty news_copy ? infoClassify.columnsId : ''}">
	            	<input type="hidden" name="videoStatus" value="${empty item.videoStatus ? 3 : item.videoStatus}">
	            	<input type="hidden" name="multipleImgCount" value="${not empty item.multipleImgCount ? item.multipleImgCount : 0 }"/>
	            	<input type="hidden" name="status" value="${empty infoClassify ? 0 : infoClassify.status}"/>
	            	<input type="hidden" name="general_contextType" value="${item.contextType}"/>
	            	<input type="hidden" name="listImgHeight" value="${imgConfig.hight }">
	            	<input type="hidden" name="listImgWidth" value="${imgConfig.width }">
	            	<input type="hidden" name="qiniuPersistentId"/>
	            	<input type="hidden" name="qiniuHash"/>
	            	<input type="hidden" name="qiniuKey"/>
	            	<input type="hidden" name="qiniuDomain"/>
	            	<input type="hidden" name="originalName">
	            	<input type="hidden" name="extensions">
	            	<!-- 用于存放资源文件 -->
	            	<input type="hidden" name="appResourses">
                    <c:if test="${not empty  info_Themes}">
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="appId">选择专题分类 <i class="text-danger">*</i></label>
	                        <div class="col-sm-8">
	                            <input type="hidden" name="infoTheme" value="${info_Themes[0].infoClassifyId}">
	                            <select name="infoThemeSelect" class="form-control chosen-select">
	                               <c:forEach items="${info_Themes }" var="ite">
	                                  <option value="${ite.id }" <c:if test="${infoCorrelation.themeId eq ite.id}">selected</c:if>  >${ite.name }</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
                    </c:if>
                    
                    <c:if test="${fn:length(allApps) ge 2  }">
	                    <div class="form-group ${not empty session_user_obj.appId or not empty item or not empty param.appId ? 'hidden' : ''}">
	                        <label class="col-sm-2 control-label no-padding-right" for="appId">所属APP<i class="text-danger">*</i></label>
	                        <div class="col-sm-8">
	                            <select name="appId" class="form-control chosen-select">
	                               <c:forEach items="${allApps }" var="app">
	                                  <option value="${app.id }" <c:if test="${infoClassify.appId eq app.id or session_user_obj.appId eq app.id or param.appId eq app.id}">selected</c:if>  >${app.name }</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
                    </c:if>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="columnsId">所属栏目<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
	                        <input class="form-control" name="columnsId" id="columnsId" type="hidden" value="${infoClassify.columnsId}" maxlength="19" placeholder="请选择栏目">
                             <div class="input-group">
	                            <div class="dropdown-toggle input-group-btn">
									<button type="button" class="btn btn-sm btn-primary">
										选择栏目
										<span class="caret"></span>
									</button>
	                            </div>
	                            <ul class="dropdown-menu dropdown-menu-right" role="menu" style="width:100%;padding:0px;">
	                               <div class="dropdown-default" id="appcolumns_tree"></div>
	                            </ul>
	                            <input name="columnsName" type="text" placeholder="请选择所属栏目" value="${appColumnsDomain.name }" class="col-xs-12 form-control dropdown-toggle" readonly/>
	                        </div>
                            <div class="red alert_appcolumns"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="title">${not empty  special_info_add ? '专题前台标题' : '资讯详情标题'}<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="${not empty  special_info_add ? '请输入专题前台标题' : '请输入资讯详情标题'}"  value='${infoClassify.title}'>
                            <span class="sns-num title_num">还能输入<em>50</em>个字</span><span>，建议输入24个字</span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="listTitle">资讯列表标题</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="listTitle" name="listTitle" maxlength="100" placeholder="输入自定义列表标题，不填列表则显示为资讯详情标题"  value='${infoClassify.listTitle}'>
                            <span class="sns-num list_title_num">还能输入<em>50</em>个字</span><span>，建议输入24个字</span>
                        </div>
                    </div>
                    
                    <c:if test="${not empty  special_info_add}">
                       <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="specialTheme">专题主题<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="specialTheme" name="specialTheme" maxlength="100" placeholder="请输入专题主题"  value='${infoClassify.specialTheme}'>
                            <span class="sns-num special_theme_num">还能输入<em>50</em>个字</span><span>，建议输入24个字</span>
                        </div>
                       </div>
                    </c:if>
                    
                    <div class="form-group hidden">
                        <label class="col-sm-2 control-label no-padding-right" for="type">类型<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            
                            <input type="radio" class="ace" name="type" value="${not empty special_info_add ? 2 : 0 }" id="type0" checked/><span class="lbl"> ${not empty special_info_add ? '专题' : '普通' }新闻</span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="infoLabel">资讯标签</label>
                        <div class="col-sm-8">
                            <select name="infoLabel" class="form-control" data-placeholder="请选择资讯标签" multiple="multiple"> 
                              
                            </select>
                            <input type="hidden" class="form-control" id="infoLabel" maxlength="10" placeholder="请选择资讯标签"  value="${item.infoLabel}">
                        </div>
                    </div>
                    
                    <input class="hidden" type="checkbox" name="plViewType" value="0" checked/>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="commentType">允许评论<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commentType" value="0" id="commentType0"><span class="lbl">允许</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commentType" value="1" id="commentType1"><span class="lbl">不允许</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("commentType${empty infoClassify ? 0 : infoClassify.commentType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="commentValidateType">评论需审核<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commentValidateType" value="0" id="commentValidateType0"><span class="lbl">不需审核</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="commentValidateType" value="1" id="commentValidateType1"><span class="lbl">需要审核</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("commentValidateType${empty infoClassify ? 0 : infoClassify.commentValidateType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group ${1 eq defaultAppid ? '':'hidden'}">
                        <label class="col-sm-2 control-label no-padding-right" for="isViewWechat">推荐到微信小程序<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isViewWechat" value="0" id="isViewWechat0"><span class="lbl">不推荐</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isViewWechat" value="1" id="isViewWechat1"><span class="lbl">推荐</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("isViewWechat${empty infoClassify or 1 ne defaultAppid ? 1 : infoClassify.isViewWechat}").checked=true;
                            </script>
                        </div>
                    </div>
                    
                    <!-- 选择推荐需要上传轮播图(不是必传的) -->
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="listViewType">列表显示类型<i class="text-danger">*</i></label>
                        <input type="hidden" value="${infoClassify.listViewType }" name="infoClassify_listViewType">
                        <div class="col-sm-8 radio listViewType_form_radio">
                            <%-- <c:forEach items="${listViewTypes }" var="lvt"> --%>
                               <label class="radio-2">
                                  <input type="radio" class="ace" name="listViewType" value="0" id="listViewType_0" checked><span class="lbl">无图</span>
                               </label>
                            <%-- </c:forEach> --%>
                        </div>
                    </div>
                   
                    <div class="form-group" style="display:none;">
                        <label class="col-sm-3 control-label no-padding-right red">图片规格：720*720</label>
                        <div class="col-sm-8" id="img_upload">
                             <input type="hidden" name="infoClassifyListId" value="${infoClassifyList.id}">
                            <input type="hidden" class="form-control" id="infoClassifyList" name="infoClassifyList" value="${infoClassifyList.imageUrl}">
                            <i class="icon-cloud-upload"></i>
								上传新闻图片
                        </div>
                    </div>
                    
                    <div class="form-group" style="display:none;">
                        <label class="col-xs-12 col-sm-2 col-md-2 control-label"></label>
                        <div class="col-sm-9" id="img_thum">
                          <c:if test="${not empty  infoClassifyList.imageUrl}">
                           <ul class="ace-thumbnails">
                             <c:forEach items="${fn:split(infoClassifyList.imageUrl,',')}" var="img" varStatus="vs">
                                 <li id="${vs.count + 100 }">
                                   <c:choose>
                                      <c:when test="${empty news_copy}">
                                         <a href="${img }" data-rel="colorbox">
	                                        <img alt="150x150" src="${img }" style="height:150px;">
	                                     </a>
                                      </c:when>
                                      <c:otherwise>
                                          <img alt="150x150" src="${img }" style="height:150px;">
                                      </c:otherwise>
                                   </c:choose>
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
                       <div class="hr hr-24"></div>
                    </div>
                    
                    <div class="form-group hidden">
                        <label class="col-sm-2 control-label no-padding-right" for="validateType">内容需审核<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="validateType" value="0" id="validateType0"><span class="lbl"> 无需审核</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="validateType" value="1" id="validateType1" checked><span class="lbl"> 需审核</span>
                            </label>
                            <!-- <script type="text/javascript">
                                document.getElementById("validateType${empty item ? 0 : item.validateType}").checked=true;
                            </script> -->
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="onlineTime">上线时间<i class="text-danger">*</i></label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="onlineTime" id="onlineTime" readonly="readonly" value="<fmt:formatDate value="${empty infoClassify.onlineTime ? onlineTime : infoClassify.onlineTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="infoSource">来源网站</label>
                            <div class="col-sm-8">
                               <input type="text" class="form-control" id="infoSource" name="infoSource" maxlength="100" placeholder="请输入来源网站，文字"  value="${empty item.infoSource ? infoSource : item.infoSource}">
                            </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="initCount">初始阅读量<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
                            <input class="form-control" name="initCount" id="initCount" type="text" value="${empty item.initCount ? 0 : item.initCount}" maxlength="19" placeholder="请输入初始阅读量">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="addType">阅读量增加类型<i class="text-danger">*</i></label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="addType" value="0" id="addType0"><span class="lbl">一次添加</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="addType" value="1" id="addType1"><span class="lbl">逐步添加</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("addType${empty item ? 0 : item.addType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group ${empty item || item.addType eq 0 ? 'hidden' : ''}">
                        <label class="col-sm-2 control-label no-padding-right" for="topTime">达到峰值时间(秒)<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
                            <input class="form-control limited" name="topTime" id="topTime" type="text" value="${empty item.topTime ? 0 : item.topTime}" maxlength="10" placeholder="请输入达到峰值时间，以秒为单位">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="keywords">关键字</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control limited" id="keywords" name="keywords" maxlength="255" placeholder="请输入关键字"  value="${item.keywords}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="synopsis">新闻摘要</label>
                            <div class="col-sm-8">
                            <textarea class="form-control textarea" id="synopsis" name="synopsis" maxlength="600" placeholder="请输入新闻摘要">${item.synopsis}</textarea>
                            <span class="sns-num synopsis_num">还能输入<em>100</em>个字</span>
                        </div>
                    </div>
                    
                    <!-- 专题图片 -->
                     <div class="form-group ${item.type eq 2 or not empty special_info_add ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right">上传专题图片<i class="text-danger">*</i></label>
                        <div class="col-sm-8" id="special_img_upload">
                            <i class="icon-cloud-upload"></i>
								上传专题图片
                        </div>
                    </div>
                    
                    <div class="form-group ${item.type eq 2 or not empty special_info_add  ? '' : 'hidden' }">
                        <label class="col-xs-12 col-sm-2 col-md-2 control-label"></label>
                        <div class="col-sm-9" id="special_img_thum">
                          <c:if test="${not empty  item.contentUrl}">
                           <ul class="ace-thumbnails">
                               <li id="${item.id + 123 }">
                                 <a href="${item.contentUrl}" data-rel="colorbox">
                                    <img alt="150x150" src="${item.type eq 2 or not empty special_info_add ? item.contentUrl : ''}" style="height:150px;">
                                 </a>
                                 <div class="tools tools-top"> 
						          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
						 		 </div>
                              </li>
                           </ul>
                          </c:if>
                        </div>
                    </div>
                    
                    <c:if test="${empty special_info_add }">
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right ${empty contextType ? '' : 'hidden'}" for="contextType">新闻内容类型<i class="text-danger">*</i></label>
	                        <div class="col-sm-8 radio">
	                             <label class="radio-2 ${empty contextType ? '' : 'hidden'}">
	                                <input type="radio" class="ace" name="contextType" value="0" id="contextType0"><span class="lbl">文本</span>
	                             </label>
	                            <label class="radio-2  ${empty contextType ? '' : 'hidden'}">
	                                <input type="radio" class="ace" name="contextType" value="1" id="contextType1"><span class="lbl">多图</span>
	                             </label>
	                             <label class="radio-2  ${empty contextType ? '' : 'hidden'}">
	                                <input type="radio" class="ace" name="contextType" value="2" id="contextType2"><span class="lbl">原创</span>
	                             </label>
	                             <label class="radio-2  ${empty contextType ? '' : 'hidden'}">
	                                <input type="radio" class="ace" name="contextType" value="3" id="contextType3"><span class="lbl">外链</span>
	                             </label>
	                             <label class="radio-2  hidden">
	                                <input type="radio" class="ace" name="contextType" value="4" id="contextType4"><span class="lbl">音频</span>
	                             </label>
	                             <label class="radio-2  hidden">
	                                <input type="radio" class="ace" name="contextType" value="5" id="contextType5"><span class="lbl">视频</span>
	                             </label>
	                            <script type="text/javascript">
	                               try{
	                            	   <c:choose>
		                                  <c:when test="${not empty contextType and contextType ne 6}">
		                                      document.getElementById("contextType${contextType}").checked=true;
		                                  </c:when>
		                                  <c:when test="${not empty special_info_add}">
			                              </c:when>
		                                  <c:otherwise>
		                                    document.getElementById("contextType${empty item.contextType ? 0 : item.contextType}").checked=true;
		                                  </c:otherwise>
		                               </c:choose>
	                               }catch(e){}
	                               
	                            </script>
	                        </div>
	                    </div>
                    </c:if>
                    <!-- 文本  -->
                    <div class="form-group contextType ${empty item.contextType or item.contextType eq 0 ? '' : 'hidden' }   ${not empty special_info_add ? 'hidden' : '' }">
                        <label class="col-sm-2 control-label no-padding-right" for="contextText">资讯内容<i class="text-danger">*</i></label>
                             <div class="col-sm-8">
	                            <textarea class="hidden" name="content">${item.content }</textarea>
	                            <textarea class="hidden" name="contentText">${item.contentText}</textarea>
	                            <script id="infocontent_editor" type="text/plain">${item.content}</script>
                           </div>
                    </div>
                    
                    <!-- 多图 -->
                    <div class="form-group contextType ${item.contextType eq 1 ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="info_content_img_upload">
                            <i class="icon-cloud-upload"></i>
								上传新闻图片
                        </div>
                    </div>
                    <!-- 不是原创的多图，音视频资源 -->
                    <div class="form-group ${item.contextType eq 1 ? '':'hidden'}">
                        <label class="col-xs-12 col-sm-2 col-md-2 control-label"></label>
                        <div class="col-sm-9" id="app_resource">
                           <c:if test="${not empty appResourse}">
	                           <ul class="ace-thumbnails">
	                             <c:forEach items="${appResourse}" var="ar" varStatus="vs">
	                                 <li id="${myfn:uuid()}" arid="${ar.id}">
	                                   <input type="hidden" name="sortNo"   value="${ar.sortNo }">
	                                   <input type="hidden" name="infoFileId" value="${ar.infoFileId }">
	                                   <input type="hidden" name="ar_type" value="${ar.type }">
	                                   <input type="hidden" name="ar_name" value="${ar.name }">
	                                   <input type="text" name="imginput" style="position:relative" value="${ar.sortNo}"/>
	                                   <div class="topInput">
		                                   <a href="${ar.fileUrl }" data-rel="colorbox">
			                                 <img alt="150x150" src="${ar.fileUrl }" style="height:150px;">
			                               </a>
			                                 <textarea style="width:150px;" placeholder="请输入描述" name="description">${ar.description}</textarea>
			                                 <div class="tools tools-top"> 
									          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
									          	<a href="javascript:;"><i class="icon-copy red"></i></a> 
									          	<a href="javascript:;"><input type="file" name="cmg_${ar.id }" id="cmg_${ar.id}"><label>文件上传</label></a>
									 		 </div>
								 		</div>
		                              </li>
	                             </c:forEach>
	                           </ul>
                          </c:if>
                        </div>
                    </div>
                    
                    <!--  原创 -->
                    <div class="form-group contextType ${item.contextType eq 2 ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right" for="contextText">添加内容<i class="text-danger">*</i></label>
                        <div class="col-sm-10">
                           <a class="btn blue btn-primary add_info_content" href="javascript:;" role="button">
								<i class="icon-plus align-top bigger-125"></i>
								添加内容
							</a>
                        </div>
                    </div>

                   <div class="origion_news_content"></div>
                    
                   <!-- 外链 -->
                   <div class="form-group contextType ${item.contextType eq 3 ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right" for="contentUrl">外链链接<i class="text-danger">*</i></label>
                        <div class="col-sm-8">
                            <input class="form-control" name="contentUrl" id="contentUrl" type="text" value="${item.contentUrl}" maxlength="255" placeholder="请输入外链链接">
                        </div>
                    </div>
                   
                   <!-- 音频及视频 -->
                   <div class="form-group contextType ${not empty contextType ? '' : 'hidden' }">
                        <label class="col-sm-2 control-label no-padding-right">添加${contextType eq 4 ? '音频' : '视频' }<i class="text-danger">*</i></label>
                        
                        <div class="col-sm-8" style="height:42px;">
                           <label for="va_change"><strong>输入${contextType eq 4 ? '音频' : '视频' }地址</strong></label><input type="checkbox" id="va_change"/>
                        </div>
                        <div class="col-sm-8 hidden" style="margin-left: 180px;">
                            <input class="form-control" name="va_url" id="va_url" type="text" value="${item.contentUrl}" maxlength="255" placeholder="请输入${contextType eq 4 ? '音频' : '视频' }链接">
                        </div>
                        <div class="col-sm-3" id="content_text_upload" style="margin-left: 180px;">
							<div id="container" style="position: relative;">
	                            <a href="javascript:;" id="pickfiles" class="btn btn-primary" >
	                                <i class="glyphicon glyphicon-plus"></i>
	                                <span>上传${contextType eq 4 ? '音频' : '视频' }</span>
	                            </a>
                           </div>
                        </div>
                        <c:if test="${not empty item.contentUrl }">
	                        <div class="col-sm-4 va_up_div">
								<a href="${item.contentUrl}" target="_blank" class="btn btn-info btn-sm"><i class="icon-eye-open bigger-110"></i>浏览</a>
	                        </div>
                        </c:if>
                        
                    </div>
                    
                    <c:if test="${contextType eq 4 || contextType eq 5 }">
	                    <div class="col-md-12 va_up_div" id="success" style="display:none">
	                        <label class="col-sm-2 control-label no-padding-right"></label>
	                        <div class="col-sm-8">
		                        <div class="alert-success">
		                                                                队列全部文件处理完毕
		                        </div>
	                        </div>
	                    </div>
	                    <div class="col-md-12 va_up_div">
	                        <label class="col-sm-2 control-label no-padding-right"></label>
	                        <div class="col-sm-8">
		                        <table style="margin-top:40px;display:none" class="table table-striped table-hover text-left">
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
	                    </div>
                    </c:if>
                    
                    
                    <c:if test="${not empty special_info_add}">
                    <div class="col-xs-12">
                       <label class="col-sm-2 control-label no-padding-right">
                          <a class="btn btn-sm btn-primary">
                             <i class="icon icon-plus"></i>专题分类
                          </a>
                       </label>
                    </div>
                    <!-- 专题新闻内容 -->
                    <div class="special_info_theme_div">
                       <c:forEach items="${infoThemes }" var="infoTheme">
						   <div class="col-xs-10 widget-container-span padding150">
								<div style="opacity: 1;" class="widget-box collapsed form-group col-sm-7">
									<div class="widget-header" style="background:${infoTheme.color};">
									    <input type="hidden" name="id" value="${infoTheme.id }">
										<input type="text" name="name" value="${infoTheme.name }" placeholder="请输入分类名称">
										<div class="widget-toolbar  widget-toolbar-light">
											<a class="btn-sm infoTheme_move_up" href="javascript:;">上移</a> 
											<a class="btn-sm infoTheme_move_down" href="javascript:;">下移</a> 
											<a href="javascript:;" class="theme_remove"><i class="icon-remove"></i>删除</a>
											<div class="input-group colorpicker-component cp2"> 
											    <input type="text" value="#00AABB" name="color" class="form-control" readonly/> 
											   <span class="input-group-addon"><i></i></span> 
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
                    </div>
                    <input name="infoTheme" type="hidden" value="">
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8" id="content_text_thelist">
                        </div>
                    </div>
					<div class="form-group">
						<div class="col-sm-10">
							<div class="pull-right">
							
							   <cqliving-security2:hasPermission name="/module/info/common/info_view.html">
							       <button class="btn btn-info btn-sm draft_save view_button" type="button"  status="0" 
							       action_url="/module/info/update_status.html" save_url="/module/info/info_add.html" role="button">
									<i class="icon-eye-open bigger-110"></i>预览
								   </button>
							   </cqliving-security2:hasPermission>
								&nbsp;
								<cqliving-security2:hasPermission name="/module/info/info_add.html">
								   <button class="btn btn-success btn-sm draft_save" type="button"  status="0" action_url="/module/info/update_status.html" save_url="/module/info/info_add.html">
										<i class="icon-save bigger-110"></i>保存草稿
									</button>
								</cqliving-security2:hasPermission>
								&nbsp;
								<cqliving-security2:hasPermission name="/module/info/save_for_audit.html">
									<button class="btn btn-primary btn-sm add_save" type="button"  status="1" action_url="/module/info/update_audit.html" save_url="/module/info/save_for_audit.html">
										<i class="icon-edit bigger-110"></i>提交审核
									</button>
								</cqliving-security2:hasPermission>
								&nbsp;
								<cqliving-security2:hasPermission name="/module/info/save_for_push.html">
									<button class="btn btn-primary btn-sm push_save" type="button"  status="3" action_url="/module/info/update_push.html" save_url="/module/info/save_for_push.html">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
								</cqliving-security2:hasPermission>
								&nbsp;
								
								<!-- location.href='${not empty special_info_add ? '/module/infoClassify/info_classify_list_special.html' : back_url}' -->
								
								<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam" back_url="${not empty recommendList ? recommendList : back_url}">
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

<form id="information_save_form" action="common/update_status.html" method="get" class="hidden">
   <input type="text" name="status" value="0"/>
   <input type="hidden" name="recommendList" value="${not empty recommendList ? recommendList : back_url}">
   <input type="text" name="infoClassifyId" value="${infoClassify.id}">
</form>

<style type="text/css">

.jcrop-keymgr{
   display:none;
}

.jcrop-holder #preview-pane {
    background-color: white;
    border: 1px solid rgba(0, 0, 0, 0.4);
    border-radius: 6px;
    box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
    display: block;
    padding: 6px;
    position: absolute;
    right: -280px;
    top: 0;
    z-index: 2000;
}
#preview-pane .preview-container {
    height: 170px;
    overflow: hidden;
    width: 250px;
}
.button.active {
    opacity: 0.6;
}
.jcrop_div img{
   max-width:600px;
}
.padding150{
      padding-left:150px;
}
</style>

<!-- 图片切图弹层 -->
<div id="list_viewimg_modal" class="modal" tabindex="-1" >
	<div class="modal-dialog" style="width:950px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<span class="blue">图片修改</span>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
                        <div class="col-sm-9">
						    <img alt="" src="" id="jcrop_div"/>
                        </div>
                        <div id="preview-pane">
				          <div class="preview-container">
				            <img alt="Preview" class="jcrop-preview" src="">
				          </div>
				          <span class="red img_size">图片规格：</span>
				        </div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
				<button class="btn btn-sm btn-primary">
					<i class="icon-ok"></i>
					确定
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

<div id="modal-form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加内容</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
						</div>
						
						<cqliving-security2:hasPermission name="/module/info/add_image_text.html">
							<div class="form-group col-xs-3">
								<a class="btn blue btn-primary" origion_type="image_text_element" href="javascript:;" role="button" data_url="add_image_text.html">
									<i class="icon-plus align-top bigger-125"></i>
									添加图文
								</a>
							</div>
						</cqliving-security2:hasPermission>
						
						<cqliving-security2:hasPermission name="/module/info/add_images.html">
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="images_element" href="javascript:;" role="button" data_url="add_images.html">
								<i class="icon-plus align-top bigger-125"></i>
								添加多图
							</a>
						</div>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/info/add_audio.html">
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="audio_element" href="javascript:;" role="button" data_url="add_audio.html">
								<i class="icon-plus align-top bigger-125"></i>
								添加音频
							</a>
						</div>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/info/add_video.html">
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="video_element" href="javascript:;" role="button" data_url="add_video.html">
								<i class="icon-plus align-top bigger-125"></i>
								添加视频
							</a>
						</div>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/info/add_vote.html">
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="vote_element" href="javascript:;" role="button" data_url="add_vote.html">
								<i class="icon-plus align-top bigger-125"></i>
								添加投票
							</a>
						</div>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/info/add_arena.html">
						<div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="arena_element" href="javascript:;" role="button" data_url="add_arena.html">
								<i class="icon-plus align-top bigger-125"></i>
								添加打擂
							</a>
						</div>
						</cqliving-security2:hasPermission>
						<!-- 调研不要了 -->
						<!-- <div class="form-group col-xs-3">
							<a class="btn blue btn-primary" origion_type="survey_element" href="javascript:;" role="button" data_url="add_survey.html" original_news_type="survey">
								<i class="icon-plus align-top bigger-125"></i>
								添加调研
							</a>
						</div> -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
				<!-- <button class="btn btn-sm btn-primary">
					<i class="icon-ok"></i>
					Save
				</button> -->
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

<div  id="add_original_layer"></div>

<div id="info_theme_template" class="hidden">
   <div class="col-xs-10 widget-container-span padding150">
		<div style="opacity: 1;" class="widget-box collapsed form-group col-sm-7">
			<div class="widget-header">
			    <input type="hidden" name="id">
				<input type="text" name="name" value="" placeholder="请输入专题类型名称">
				<div class="widget-toolbar  widget-toolbar-light">
					<a class="btn-sm infoTheme_move_up" href="javascript:;">上移</a> 
					<a class="btn-sm infoTheme_move_down" href="javascript:;">下移</a> 
					<a href="javascript:;" class="theme_remove"><i class="icon-remove"></i>删除</a>
					<div class="input-group colorpicker-component cp2"> 
					    <input type="text" value="#00AABB" name="color" class="form-control" readonly/> 
					   <span class="input-group-addon"><i></i></span> 
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
window.UEDITOR_HOME_URL = '/resource/components/ueditor/';
var imgUrl = '${imageUrl}';
var appId = '${session_user_obj.appId}';
var webUrlPath = '${webUrlPath}';
var appColumns = ${appColumns};
 require(['common_colorbox','/resource/business/info/infomation.js?v=v1'],function(colorbox){
	   
	 colorbox.init();
	 $("#form1 .dropdown-toggle,#appcolumns_tree").bind("click",function(e){
		 e.cancelBubble = true;
		 e.stopPropagation();
		 $("#form1 .dropdown-menu").show();
	 });
	 
	 $("body").bind("click",function(e){
		 var tagName = e.target.tagName.toLowerCase();
		 if('li'.indexOf(tagName) != -1 || 'span'.indexOf(tagName) != -1){
			 return;
		 }
		 $("#form1 .dropdown-menu").hide();
	 });
	 
	 $("#va_change").bind("click",function(){
		 var $this = $(this);
		 if($this.is(":checked")){
			 $("#va_url").parent().removeClass("hidden");
			 $("#content_text_upload,.va_up_div").addClass("hidden");
		 }else{
			 $("#va_url").parent().addClass("hidden");
			 $("#content_text_upload,.va_up_div").removeClass("hidden");
		 }
	 });
	 
 });
</script>