package com.cqliving.cloud.online.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.manager.InfoClassifyManager;
import com.cqliving.cloud.online.info.manager.InfoCorrelationManager;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.cloud.online.interfacc.dto.NewsResult;
import com.cqliving.cloud.online.interfacc.dto.NewsWxlResult;
import com.cqliving.cloud.online.interfacc.dto.SpecialInfoDetailResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoClassifyService")
@ServiceHandleMapping(managerClass = InfoClassifyManager.class)
public class InfoClassifyServiceImpl implements InfoClassifyService {

	private static final Logger logger = LoggerFactory.getLogger(InfoClassifyServiceImpl.class);
	
	@Autowired
	private InfoClassifyManager infoClassifyManager;
	@Autowired
	private InfoCorrelationManager infoCorrelationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯栏目表失败")})
	public Response<PageInfo<InfoClassify>> queryForPage(PageInfo<InfoClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯栏目表失败")})
	public Response<InfoClassify> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="delete",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯栏目表失败")})
	public Response<Void> delete(Long id, Date updateTime, Long updatorId, String updator) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯栏目表失败")})
	public Response<Void> save(InfoClassify infoClassify) {
		return null;
	}

	@Override
	public Response<Void> publish(Long id, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.publish(id, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("发布失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布失败");
		}
		return response;
	}

	@Override
	public Response<Void> publishCorrelation(Long id, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.publishCorrelation(id, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("发布失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布失败");
		}
		return response;
	}

	@Override
	public Response<Void> publishBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.publishBatch(idList, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("批量发布失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量发布失败");
		}
		return response;
	}

	@Override
	public Response<Void> offline(Long id, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.offline(id, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("下线失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("下线失败");
		}
		return response;
	}

	@Override
	public Response<Void> offlineBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.offlineBatch(idList, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("批量下线失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量下线失败");
		}
		return response;
	}

	@Override
	public Response<Void> offlineCorrelation(Long id, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.offlineCorrelation(id, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("下线失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("下线失败");
		}
		return response;
	}

	@Override
	public Response<Void> offlineCorrelationBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.offlineCorrelationBatch(idList, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("批量下线失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量下线失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<InfoClassifyDto>> queryDtoForPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap) {
		Response<PageInfo<InfoClassifyDto>> response = Response.newInstance();
		try {
			PageInfo<InfoClassifyDto> data = infoClassifyManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取资讯分页数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取资讯分页数据失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<InfoClassifyDto>> queryDtoForCopyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<InfoClassifyDto>> response = Response.newInstance();
		try {
			PageInfo<InfoClassifyDto> data = infoClassifyManager.queryDtoForCopyPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取复制新闻分页数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取复制新闻分页数据失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<InfoClassifyDto>> queryDtoForRecommendPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<InfoClassifyDto>> response = Response.newInstance();
		try {
			PageInfo<InfoClassifyDto> data = infoClassifyManager.queryDtoForRecommendPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取推荐新闻分页数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取推荐新闻分页数据失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<InfoClassifyDto>> queryDtoForSpecialSubPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<InfoClassifyDto>> response = Response.newInstance();
		try {
			PageInfo<InfoClassifyDto> data = infoClassifyManager.queryDtoForSpecialSubPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取专题子新闻分页数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取专题子新闻分页数据失败");
		}
		return response;
	}

	@Override
	public Response<NewsResult> getNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime) {
		Response<NewsResult> response = Response.newInstance();
		try {
			NewsResult data = infoClassifyManager.getNewsByPage(appId, columnId, isCarousel, lastId, lastSortNo, lastOnlineTime);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取客户端咨询栏目数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取客户端咨询栏目数据失败");
		}
		return response;
	}
	
	@Override
	public Response<NewsWxlResult> getWxlNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime, boolean onlyWechat) {
		Response<NewsWxlResult> response = Response.newInstance();
		try {
			NewsWxlResult data = infoClassifyManager.getWxlNewsByPage(appId, columnId, isCarousel, lastId, lastSortNo, lastOnlineTime, onlyWechat);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取客户端咨询栏目数据失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取客户端咨询栏目数据失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<NewsData>> searchNewsByPage(Long appId, String title, Long lastId, String lastOnlineTime) {
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		try {
			CommonListResult<NewsData> data = infoClassifyManager.searchNewsByPage(appId, title, lastId, lastOnlineTime);
			response.setData(data);
		} catch (Exception e) {
			logger.error("资讯搜索失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("资讯搜索失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<NewsData>> getCorrelation(Long appId, Long infoClassifyId) {
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		try {
			CommonListResult<NewsData> data = infoCorrelationManager.getCorrelation(appId, infoClassifyId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取相关资讯失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取相关资讯失败");
		}
		return response;
	}

	@Override
	public Response<Void> delBatch(List<Long> idList, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.delBatch(idList, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("批量删除失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量删除失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyService#updateStatus(java.lang.Long, java.lang.Byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateStatus(Long classifyId, Byte status, Date updateTime, Long updatorId, String updator) {
		return null;
	}

	@Override
	public Response<Void> clearSortNo(List<Long> ids) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.clearSortNo(ids);
		} catch (Exception e) {
			logger.error("清空排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("清空排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.modifySortNo(id, sortNo);
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifySortNos(Long[] ids, Integer[] sortNos, Long appId, Long columnsId, Byte listViewType) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.modifySortNos(ids, sortNos, appId, columnsId, listViewType);
		} catch (BusinessException e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> recommend(Long id, Long[] appIds) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.recommend(id, appIds);
		} catch (Exception e) {
			logger.error("推荐到App失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("推荐到App失败");
		}
		return response;
	}

	@Override
	public Response<Void> push(Long id, String title, String summary, Long userId, String nickName) {
		Response<Void> response = Response.newInstance();
		try {
			infoClassifyManager.push(id, title, summary, userId, nickName);
		} catch (BusinessException e) {
			logger.error("推送失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("推送失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("推送失败");
		}
		return response;
	}

	@Override
	public Response<BusinessDetailResult> getBusinessDetail(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		Response<BusinessDetailResult> response = Response.newInstance();
		try {
			BusinessDetailResult data = infoClassifyManager.getBusinessDetail(appId, sessionId, token, sourceId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取业务详情信息失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取业务详情信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取业务详情信息失败");
		}
		return response;
	}

	@Override
	public Response<SpecialInfoDetailResult> getSpecialDetail(Long appId, Long infoClassifyId) {
		Response<SpecialInfoDetailResult> response = Response.newInstance();
		try {
			SpecialInfoDetailResult data = infoClassifyManager.getSpecialDetail(appId, infoClassifyId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取专题资讯详情失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取专题资讯详情失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取专题资讯详情失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<NewsData>> getSpecialSubList(Long appId, Long infoClassifyId, Long themeId, Long lastId, Integer lastSortNo, String lastOnlineTime) {
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		try {
			CommonListResult<NewsData> data = infoClassifyManager.getSpecialSubList(appId, infoClassifyId, themeId, lastId, lastSortNo, lastOnlineTime);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取专题子新闻失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取专题子新闻失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取专题子新闻失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> increaseViewCount(Long appId, Long infoClassifyId) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = infoClassifyManager.increaseViewCount(appId, infoClassifyId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("增加浏览量失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("增加浏览量失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("增加浏览量失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<CommonListResult<NewsData>> getRecommended(Long appId) {
		Response<CommonListResult<NewsData>> response = Response.newInstance();
		try {
			CommonListResult<NewsData> data = infoClassifyManager.getRecommended(appId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取推荐新闻失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取推荐新闻失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取推荐新闻失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyService#queryInfoClassifyCorrelationPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<InfoClassifyDto>> queryInfoClassifyCorrelationPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyService#queryHadCorrInfoClassifyPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<InfoClassifyDto>> queryHadCorrInfoClassifyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyService#infoClassifyRecommedEdit(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<InfoClassify> infoClassifyRecommedEdit(Long commentId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyService#querySpecialSubDtoForScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ScrollPage<InfoClassifyDto>> querySpecialSubDtoForScrollPage(ScrollPage<InfoClassifyDto> page,
			Map<String, Object> conditions) {
		return null;
	}

	@Override
	public Response<Void> recommendToHome(Long id, Long userId, String nickname) {
		Response<Void> res = Response.newInstance();
		try {
			infoClassifyManager.recommendToHome(id, userId, nickname);
		} catch (BusinessException e) {
			logger.error("新闻推荐到首页失败:" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("新闻推荐到首页失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("推荐到首页失败");
		}
		return res;
	}

	@Override
	@ServiceMethodHandle
	public Response<Void> moveInfoClassify(Long[] infoClassifyIds, Long appColumnsId,String updator,Long updateId,Date updateTime) {
		return null;
	}

}