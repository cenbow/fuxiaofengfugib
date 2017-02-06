package com.cqliving.cloud.online.wz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.cloud.online.wz.manager.WzQuestionImageManager;
import com.cqliving.cloud.online.wz.service.WzQuestionImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("wzQuestionImageService")
@ServiceHandleMapping(managerClass = WzQuestionImageManager.class)
public class WzQuestionImageServiceImpl implements WzQuestionImageService {

	//private static final Logger logger = LoggerFactory.getLogger(WzQuestionImageServiceImpl.class);
	
	@Autowired
	private WzQuestionImageManager wzQuestionImageManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询问政问题图片表失败")})
	public Response<PageInfo<WzQuestionImage>> queryForPage(PageInfo<WzQuestionImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询问政问题图片表失败")})
	public Response<WzQuestionImage> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除问政问题图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存问政问题图片表失败")})
	public Response<Void> save(WzQuestionImage wzQuestionImage) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="getByQuestion",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获取图片列表失败")})
    public Response<List<WzQuestionImage>> getByQuestion(Long questionId) {
        return null;
    }
}
