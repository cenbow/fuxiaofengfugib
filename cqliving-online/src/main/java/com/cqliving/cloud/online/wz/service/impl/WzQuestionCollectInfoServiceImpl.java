package com.cqliving.cloud.online.wz.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.cloud.online.wz.manager.WzQuestionCollectInfoManager;
import com.cqliving.cloud.online.wz.service.WzQuestionCollectInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("wzQuestionCollectInfoService")
@ServiceHandleMapping(managerClass = WzQuestionCollectInfoManager.class)
public class WzQuestionCollectInfoServiceImpl implements WzQuestionCollectInfoService {

	private static final Logger logger = LoggerFactory.getLogger(WzQuestionCollectInfoServiceImpl.class);
	
	@Autowired
	private WzQuestionCollectInfoManager wzQuestionCollectInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询问题信息收集表失败")})
	public Response<PageInfo<WzQuestionCollectInfo>> queryForPage(PageInfo<WzQuestionCollectInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询问题信息收集表失败")})
	public Response<WzQuestionCollectInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除问题信息收集表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存问题信息收集表失败")})
	public Response<Void> save(WzQuestionCollectInfo wzQuestionCollectInfo) {
		return null;
	}

    @Override
    public Response<List<WzQuestionCollectInfo>> getInfoByCollect(Long questionId, Long collectId) {
        Response<List<WzQuestionCollectInfo>> rs = Response.newInstance();
        try {
            rs.setData(wzQuestionCollectInfoManager.getInfoByCollect(questionId, collectId));
        } catch (Exception e) {
            logger.error("获取问题信息收集内容失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取问题信息收集内容失败");
        }
        return rs;
    }
}
