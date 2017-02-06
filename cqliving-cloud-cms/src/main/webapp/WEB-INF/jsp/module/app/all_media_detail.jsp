<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="全媒体表列表|/module/all_media/all_media_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/all_media/${empty item.id ? 'all_media_add' : 'common/all_media_update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group  ${not empty apps and fn:length(apps) ge 2 ? '' : 'hidden'}">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端_ID<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                           <c:choose>
							       <c:when test="${not empty apps and fn:length(apps) ge 2}">
							          <select name="appId" class="chosen_select" id="appId">
							            <c:forEach items="${apps}" var="app">
								            <option value="${app.id }"  ${item.appId eq app.id ? 'selected' : ''}>${app.name }</option>
							            </c:forEach>
							          </select>
							       </c:when>
							       <c:otherwise>
							          <input type="hidden" id="appId" name="appId" value="${apps[0].id}" maxlength="19" class="form-control" />
							       </c:otherwise>
							    </c:choose>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">全媒体名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入全媒体名称"  value='${item.name}'>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="minImageUrl">2倍图标地址(48*48px)<i class="text-danger">*</i></label>
	                          <div class="col-sm-9" id="two_img_upload">
	                            <input type="hidden" class="form-control" id="minImageUrl" name="minImageUrl" maxlength="255"  value="${item.minImageUrl}">
	                            <i class="icon-cloud-upload"></i>
								上传2倍图标
	                         </div>
	                    </div>
	                    
	                  <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-3 control-label"></label>
                        <div class="col-sm-9" id="two_img_thum">
                          <c:if test="${not empty  item.minImageUrl}">
                           <ul class="ace-thumbnails">
                               <li>
                                  <a href="${item.minImageUrl}" data-rel="colorbox">
                                   <img alt="150x150" src="${item.minImageUrl}" style="width:150px;height:150px;">
                                </a>
                                 <div class="tools tools-top"> 
						          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
						 		 </div>
                              </li>
                           </ul>
                          </c:if>
                        </div>
                      </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="maxImageUrl">3倍图标地址(72*72px)<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" id="max_img_upload">
	                            <input type="hidden" class="form-control" id="maxImageUrl" name="maxImageUrl" maxlength="255" placeholder="请输入3倍图标地址"  value="${item.maxImageUrl}">
	                            <i class="icon-cloud-upload"></i>
								上传3倍图标
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-3 control-label"></label>
	                        <div class="col-sm-9" id="max_img_thum">
	                          <c:if test="${not empty  item.maxImageUrl}">
	                           <ul class="ace-thumbnails">
	                               <li>
	                                  <a href="${item.maxImageUrl}" data-rel="colorbox">
	                                   <img alt="150x150" src="${item.maxImageUrl}" style="width:150px;height:150px;">
	                                </a>
	                                 <div class="tools tools-top"> 
							          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
							 		 </div>
	                              </li>
	                           </ul>
	                          </c:if>
	                        </div>
	                      </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="type">类型<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 链接</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="2" id="type2"><span class="lbl"> 弹层跳栏目</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="type" value="3" id="type3"><span class="lbl"> 滚动跳栏目</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("type${empty item ? 1 : item.type}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group type_content"></div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/all_media/all_media_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam" back_url="/module/all_media/all_media_list.html">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>

<script type="text/javascript">

var imgUrl = "${imageUrl}";
var jsonMedia = '${jsonObj}';
require(['validator.bootstrap','cloud.table.curd','cqliving_dialog','chosen','/resource/business/app/all_media_add.js?v=v1'], function($,tableCurd,cqliving_dialog){
	tableCurd.bindSaveButton();
	$(".chosen_select").chosen({search_contains:true});
	
	$.validator.addMethod("notNull", function(value, element, param) {
		var type = $(":input[name=type]:checked").val();
        var linkUrl = $(":input[name=linkUrl]").val();
        var columnsId = $(":input[name=columnsId]").val();
        if(type == 1 && !linkUrl){
        		return false;
        }else if(type != 1 && !columnsId){
        	return false;
        }
		return true;
	}, "不能为空");
	
    $(function(){
        $("#form1").validate({
        	ignore:'',
            rules: {
                    name : {
                    required: true
                },
                    minImageUrl : {
                    required: true
                },
                    maxImageUrl : {
                    required: true
                },
                    type : {
                    required: true
                },
                linkUrl:{
                	notNull:true
                },
                columnsId:{
                	notNull:true
                }
            },
            messages: {
                name : {
                    required: ' '
                },
                minImageUrl : {
                    required: ' '
                },
                maxImageUrl : {
                    required: ' '
                },
                type : {
                    required: ' '
                },
                linkUrl:{
                	notNull:' '
                },
                columnsId:{
                	notNull:' '
                }
            }
        });
    });
});
</script>