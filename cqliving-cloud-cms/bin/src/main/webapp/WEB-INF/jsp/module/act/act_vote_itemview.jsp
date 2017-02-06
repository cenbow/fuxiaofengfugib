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
                        <label class="col-sm-4 control-label no-padding-right">活动投票分类表ID（act_vote_classify表主键）</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.actVoteClassifyId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">选项编号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.number}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">选项标题，后台限制最多80个字</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.itemTitle}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">选项描述</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.itemDesc}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">实际量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.actValue}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">初始量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.initValue}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">选项图片</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.imageUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">视频URL</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.videoUrl}
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
                        <label class="col-sm-4 control-label no-padding-right">排序号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.sortNo}
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
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-info" type="button" onclick="javascript:location.href='/module/act/act_vote_itemlist.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
