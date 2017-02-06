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
                        <label class="col-sm-4 control-label no-padding-right">资讯标题</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.title}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.infoLabel}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">资讯内容URL</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.imformationUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">新闻摘要</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.synopsis}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.type}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">关键字</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.keywords}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">来源网站，文字</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.infoSource}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">平台可见类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.plViewType}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">允许评论</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.commentType}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">评论需审核</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.commentValidateType}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">内容需审核</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.validateType}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">初始阅读量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.initCount}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">阅读量增加类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.addType}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">达到峰值时间，以秒为单位</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.topTime}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">资讯浏览量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.viewCount}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">资讯回复量</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.replyCount}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">推送状态</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.sendStatus}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">内容,新闻的实际内容URL,对应生成后内容</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.content}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">资讯内容的全文本，不带HTML标签的</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.contextText}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">原始栏目ID，为方便后续统计，新闻先归属其中一个栏目。</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.classifyId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">新闻内容类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.contextType}
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
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-info" type="button" onclick="javascript:location.href='/module/info/info_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
