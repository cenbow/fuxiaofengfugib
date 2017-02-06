<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品表列表|shop_goods_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/shopgoods/shop_goods_add.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    <div class="form-group ${fn:length(allApps) le 1 or not empty item ? 'hidden' : '' }">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                          <select name="appId" class="chosen-select">
	                             <c:forEach items="${allApps }" var="app">
	                                 <option value="${app.id }"  ${defaultAppId eq app.id ? 'selected' : ''}>${app.name }</option>
	                            </c:forEach>
	                          </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="categoryLevelOneId">商品分类<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <select name="categoryLevelOneId" class="chosen-select" data-placeholder="请选择商品分类">
	                             <c:forEach items="${categoryDtos }" var="cg">
	                                 <option value="${cg.id }"  ${item.categoryLevelOneId eq cg.id ? 'selected' : ''}>${cg.name }</option>
	                              </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="synopsis">摘要<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <textarea style="resize:none;" placeholder="请输入摘要"  class="form-control textarea" id="synopsis" name="synopsis" maxlength="100">${item.synopsis}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="labels">标签（多个用","分隔）<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="labels" name="labels" maxlength="100" placeholder="请输入标签（逗号分隔）"  value="${item.labels}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="listImageUrl">列表图片(${_imgConfigSize_[17].thumb[0]})<i class="text-danger">*</i></label>
	                            <input type="hidden" class="form-control" id="listImageUrl" name="listImageUrl" maxlength="255" placeholder="请输入列表图片"  value="${item.listImageUrl}">
	                            <div class="col-sm-9" id="list_img_upload">
	                            <i class="icon-cloud-upload"></i>
								上传列表图片
	                        </div>
	                    </div>
	                    
	                  <div class="form-group">
                        <label class="col-xs-12 col-sm-3 control-label"></label>
                        <div class="col-sm-9" id="list_img_thum">
                          <c:if test="${not empty  item.listImageUrl}">
                           <ul class="ace-thumbnails">
                              <li>
                                  <a href="${item.listImageUrl }" data-rel="colorbox">
                                     <img alt="150x150" src="${item.listImageUrl }" style="height:150px;">
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
	                        <label class="col-sm-3 control-label no-padding-right" for="goodsImgUrl">商品图片(${_imgConfigSize_[17].thumb[0]})<i class="text-danger">*</i></label>
	                            <div class="col-sm-9" id="content_img_upload">
	                            <i class="icon-cloud-upload"></i>
								上传商品图片
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="content_img_thum">
	                          <c:if test="${not empty  item.shoppingImages}">
	                           <ul class="ace-thumbnails">
	                             <c:forEach items="${item.shoppingImages }" var="contentImg">
	                               <li id="${myfn:uuid()}">
	                                 <input type="hidden" name="imgId" value="${contentImg.id}">
	                                 <input type="hidden" name="shoppingGoodsId" value="${contentImg.shoppingGoodsId}">
	                                 <input type="hidden" name="cmstatus" value="3">
	                                 <input type="hidden" name="createTime" value="<fmt:formatDate value='${contentImg.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
	                                 <input type="text" name="imginput" style="position:relative" value="${contentImg.sortNo}" placeholder="请输入图片排序号"/>
	                                  <div class="topInput">
	                                     <a href="${contentImg.url }" data-rel="colorbox">
		                                  <img alt="150x150" src="${contentImg.url }" style="height:150px;">
		                                 </a>
		                                 <textarea style="width:150px;" placeholder="请输入描述" name="description">${contentImg.description}</textarea>
		                                 <div class="tools tools-top"> 
								          	<a href="javascript:;"><i class="icon-remove red"></i></a> 
								          	<a href="javascript:;"><i class="icon-copy red"></i></a> 
								          	<a href="javascript:;"><input type="file" name="cmg_${contentImg.id }" id="cmg_${contentImg.id}"><label>文件上传</label></a>
								 		 </div>
								 	</div>
	                              </li>
	                             </c:forEach>
	                           </ul>
	                          </c:if>
	                        </div>
                       </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="price">商品价格（元）<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="price" id="price" type="text" value="<fmt:formatNumber maxFractionDigits='2' value='${item.price/100}' type='number' groupingUsed='false'/>" maxlength="10" placeholder="请输入价格（元）">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="unit">商品单位<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="unit" name="unit" maxlength="10" placeholder="请输入单位"  value="${item.unit}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="stock">商品库存<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="stock" id="stock" type="text" value="${item.stock}" maxlength="10" placeholder="请输入库存">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="isFreeShipping">是否包邮<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="isFreeShipping" value="1" id="isFreeShipping1"><span class="lbl"> 是</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="isFreeShipping" value="2" id="isFreeShipping2"><span class="lbl">满金额包邮</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="isFreeShipping" value="0" id="isFreeShipping0"><span class="lbl"> 否</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("isFreeShipping${empty item ? 0 : item.isFreeShipping}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="freeShippingPrice">包邮金额（元）<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="freeShippingPrice" id="freeShippingPrice" type="text" value="<fmt:formatNumber maxFractionDigits='2' value='${item.freeShippingPrice/100}' type='number' groupingUsed='false'/>" maxlength="10" placeholder="请输入包邮金额（元）">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="shippingFareTemplateId">运费模板<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                           <select name="shippingFareTemplateId" class="chosen-select" data-placeholder="请选择运费模板">
	                              <c:forEach items="${tmps }" var="tmp">
	                                 <option value="${tmp.id }" ${item.shippingFareTemplateId eq tmp.id ? 'selected' : '' }>${tmp.name }</option>
	                              </c:forEach>
	                           </select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">详情<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <textarea class="hidden" name="content">${item.content }</textarea>
	                            <script id="content_editor" type="text/plain">${item.content}</script>
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="saveButton" back_url="/module/shopgoods/shop_goods_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" back_url="/module/shopgoods/shop_goods_list.html" backRestoreParam="backRestoreParam">
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

window.UEDITOR_HOME_URL = '/resource/components/ueditor/';
var imgUrl = '${imageUrl}';
var imgThumb = '${_imgConfig_[17]}';
var imgWH = '${_imgConfigSize_[17].thumb[0]}';
require(['validator.bootstrap','cloud.table.curd','chosen','/resource/business/shopping/shopping_goods_add.js?v=v3'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
	$(".chosen-select").chosen({search_contains:true});
	
    $(function(){
        $("#form1").validate({
        	ignore:'',
            rules: {
                    categoryLevelOneId : {
                    required: true,
                    number:true
                },
                    content : {
                    required: true
                },
                    labels : {
                    required: true
                },
                    listImageUrl : {
                    required: true
                },
                    name : {
                    required: true
                },
                    originalPrice : {
                    required: true,
                    number:true
                },
                    price : {
                    required: true,
                    number:true
                },
                    unit : {
                    required: true
                },
                    synopsis : {
                    required: true
                },
                    stock : {
                    required: true,
                    number:true
                }
            },
            messages: {
                categoryLevelOneId : {
                    required: ' ',
                    number:' '
                },
                content : {
                    required: ' '
                },
                labels : {
                    required: ' '
                },
                listImageUrl : {
                    required: ' '
                },
                name : {
                    required: ' '
                },
                originalPrice : {
                    required: ' ',
                    number:' '
                },
                price : {
                    required: ' ',
                    number:' '
                },
                unit : {
                    required: ' '
                },
                synopsis : {
                    required: ' '
                },
                stock : {
                    required: ' ',
                    number:' '
                }
            }
        });
    });
});
</script>