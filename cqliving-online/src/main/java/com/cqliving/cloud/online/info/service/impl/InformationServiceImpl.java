package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("informationService")
@ServiceHandleMapping(managerClass = InformationManager.class)
public class InformationServiceImpl implements InformationService {

	private static final Logger logger = LoggerFactory.getLogger(InformationServiceImpl.class);
	
	@Autowired
	private InformationManager informationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯表失败")})
	public Response<PageInfo<Information>> queryForPage(PageInfo<Information> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯表失败")})
	public Response<Information> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯表失败")})
	public Response<Void> save(Information information) {
		return null;
	}

//	@Override
//	public Response<PageInfo<InformationDto>> queryDtoForPage(PageInfo<InformationDto> pageInfo,
//			Map<String, Object> map, Long[] colsId) {
//		Response<PageInfo<InformationDto>> response = Response.newInstance();
//		try {
//			PageInfo<InformationDto> data = informationManager.queryDtoForPage(pageInfo, map, colsId);
//			response.setData(data);
//		} catch (Exception e) {
//			logger.error("获取资讯分页列表失败：" + e.getMessage(), e);
//			response.setCode(ErrorCodes.FAILURE);
//			response.setMessage("获取资讯分页列表失败");
//		}
//		return response;
//	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#createInfomation(com.cqliving.cloud.online.info.domain.Information, com.cqliving.cloud.online.info.domain.InfoClassify)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Information> createInfomation(InfoDto infoDto) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#deleteOrigionNews(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> deleteOrigionNews(Long infocontentId,Byte isViewWechat,Long appId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#saveSpecialInfo(com.cqliving.cloud.online.info.domain.Information, com.cqliving.cloud.online.info.domain.InfoClassify, java.util.List)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Information> saveSpecialInfo(Information information, InfoClassify infoClassify,
			List<InfoTheme> infoThemes,String listViewFileUrl) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#delInfoTheme(javax.servlet.http.HttpServletRequest, java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> delInfoTheme(Long infoThemeId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#findDetail(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<InformationDto> findDetail(Long infoClassifyId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#addViewCount(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Integer> addViewCount(Long infoClassifyId,Long infomationId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#setMultiImgNum(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Integer> setMultiImgNum(Long informationId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#deleteImageNews(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> deleteInfoContent(Long infoId){
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#syncViewCount()
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> syncViewReplyCount(boolean immediate,Long infoClassifyId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#setViewAndReplyCount(com.cqliving.cloud.online.info.dto.InformationDto)
	 */
	@Override
	@ServiceMethodHandle
	public Response<InformationDto> setViewAndReplyCount(InformationDto dto) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#setViewAndReplyCount(java.util.List)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InformationDto>> setViewAndReplyCount(List<InformationDto> data) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#delCache()
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> delCache(Long... infoClassifyId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationService#findAnswerNumByInfoId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Map<Long, Object>> findAnswerNumByInfoId(Long infoId) {
		return null;
	}

	@Override
	@ServiceMethodHandle
	public Response<Void> delCacheByInformationId(Long informationId) {
		return null;
	}

	@Override
	@ServiceMethodHandle
	public Response<InformationDto> setViewAndReplyCount(InformationDto dto, boolean addVirtualCount) {
		return null;
	}
	
}
