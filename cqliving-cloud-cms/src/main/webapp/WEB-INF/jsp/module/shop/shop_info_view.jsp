<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">客户端_ID</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appId}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">栏目ID</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value=" ${item.columnsId}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">店铺名称</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所处位置纬度</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.lat}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所处位置经度</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.lng}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所属区域CODE</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.regionCode}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">所属区域名称</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.regionName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">地址</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.address}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">封面图</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.coverImg}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">电话</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.telephone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">状态</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.status}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">描述</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.description}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">内容</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.content}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">评论量</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.replyCount}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">价格（分）</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.price}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.createTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建人ID</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creatorId}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">创建人名称</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">更新时间</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.updateTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">更新人ID</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.updatorId}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">更新人</label>
                        <div class="col-sm-8">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.updator}">
                        </div>
                    </div>
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-default" type="button" onclick="javascript:location.href='/module/shop/shop_info_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
