<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">客户端_ID</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.appId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">标题</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.title}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">活动开始时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">活动结束时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">回复量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.replyCount}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">点赞量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.priseCount}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">摘要</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.digest}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">图片列表，只能一张</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.listImageUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">活动列表图片，只多三张</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.actImageUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">内容,包含HTML标签的富文本内容</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.content}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">状态</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.status}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">创建人</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.creatorId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">创建人姓名</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.creator}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">更新时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">更新人ID</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.updatorId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">更新人</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.updator}
                            </p>
                        </div>
                    </div>
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-info" type="button" onclick="javascript:location.href='/module/act/act_infolist.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
