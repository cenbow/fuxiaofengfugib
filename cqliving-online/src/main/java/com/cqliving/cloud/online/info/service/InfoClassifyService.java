package com.cqliving.cloud.online.info.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.cloud.online.interfacc.dto.NewsResult;
import com.cqliving.cloud.online.interfacc.dto.NewsWxlResult;
import com.cqliving.cloud.online.interfacc.dto.SpecialInfoDetailResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 资讯栏目表 Service
 * Date: 2016-04-15 09:44:28
 * @author Code Generator
 */
public interface InfoClassifyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoClassify>> queryForPage(PageInfo<InfoClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoClassify> get(Long id);
	public Response<Void> delete(Long id, Date updateTime, Long updatorId, String updator);
	public Response<Void> save(InfoClassify domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 发布</p>
	 * @author Tangtao on 2016年4月25日
	 * @param id 资讯栏目 id
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> publish(Long id, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 专题子新闻发布</p>
	 * @author Tangtao on 2016年12月6日
	 * @param id
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> publishCorrelation(Long id, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 批量发布</p>
	 * @author Tangtao on 2016年4月25日
	 * @param idList 资讯栏目 id 集合
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> publishBatch(List<Long> idList, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 下线</p>
	 * @author Tangtao on 2016年4月25日
	 * @param id 资讯栏目 id
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> offline(Long id, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 批量下线</p>
	 * @author Tangtao on 2016年4月25日
	 * @param idList 资讯栏目 id 集合
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> offlineBatch(List<Long> idList, Date updateTime, Long updatorId, String updator);
	/**
	 * <p>Description: 专题子新闻下线</p>
	 * @author Tangtao on 2016年12月6日
	 * @param id
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> offlineCorrelation(Long id, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 专题子新闻批量下线</p>
	 * @author Tangtao on 2016年12月6日
	 * @param idList
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> offlineCorrelationBatch(List<Long> idList, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 资讯栏目分页数据</p>
	 * @author Tangtao on 2016年4月28日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<InfoClassifyDto>> queryDtoForPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	 /**
	 * <p>Description: 复制新闻查询分页数据</p>
	 * @author Tangtao on 2016年5月4日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<InfoClassifyDto>> queryDtoForCopyPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	 
	/**
	 * <p>Description: 推荐新闻分页数据</p>
	 * @author Tangtao on 2016年5月11日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<InfoClassifyDto>> queryDtoForRecommendPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 专题子新闻分页数据</p>
	 * @author Tangtao on 2016年5月12日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<InfoClassifyDto>> queryDtoForSpecialSubPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 获取客户端资讯栏目数据</p>
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param columnId
	 * @param isCarousel 
	 * @param lastId
	 * @param lastSortNo
	 * @param lastOnlineTime 
	 * @return
	 */
	Response<NewsResult> getNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime);
	
	/**
	 * <p>Description: 获取客户端资讯栏目数据（微信小程序）</p>
	 * @author Tangtao on 2017年1月13日
	 * @param appId
	 * @param columnId
	 * @param isCarousel
	 * @param lastId
	 * @param lastSortNo
	 * @param lastOnlineTime
	 * @param onlyWechat 
	 * @return
	 */
	Response<NewsWxlResult> getWxlNewsByPage(Long appId, Long columnId, Boolean isCarousel, Long lastId, Integer lastSortNo, String lastOnlineTime, boolean onlyWechat);
	/**
	 * <p>Description: 资讯搜索</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param title
	 * @param lastId
	 * @param lastOnlineTime
	 * @return
	 */
	Response<CommonListResult<NewsData>> searchNewsByPage(Long appId, String title, Long lastId, String lastOnlineTime);
	
	/**
	 * <p>Description: 获取相关资讯</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	Response<CommonListResult<NewsData>> getCorrelation(Long appId, Long infoClassifyId);
	
	/**
	 * <p>Description: 批量删除</p>
	 * @author Tangtao on 2016年5月4日
	 * @param idList
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> delBatch(List<Long> idList, Date updateTime, Long updatorId, String updator);
	
	//修改新闻状态
	Response<Void> updateStatus(Long classifyId,Byte status, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 批量清空排序</p>
	 * @author Tangtao on 2016年5月10日
	 * @param ids
	 * @return
	 */
	Response<Void> clearSortNo(List<Long> ids);
	
	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年5月10日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	Response<Void> modifySortNo(Long id, Integer sortNo);
	
	/**
	 * <p>Description: 批量修改排序</p>
	 * @author Tangtao on 2017年2月3日
	 * @param ids
	 * @param sortNos
	 * @param appId
	 * @param columnsId
	 * @param listViewType
	 * @return
	 */
	Response<Void> modifySortNos(Long[] ids, Integer[] sortNos, Long appId, Long columnsId, Byte listViewType);
	
	/**
	 * <p>Description: 推荐到App</p>
	 * @author Tangtao on 2016年5月11日
	 * @param id
	 * @param appIds
	 * @return
	 */
	Response<Void> recommend(Long id, Long[] appIds);
	
	/**
	 * <p>Description: 推送</p>
	 * @author Tangtao on 2016年6月3日
	 * @param id
	 * @param title
	 * @param summary
	 * @param userId 
	 * @param nickName 
	 * @return
	 */
	Response<Void> push(Long id, String title, String summary, Long userId, String nickName);
	
	/**
	 * <p>Description: 获取资讯详情信息（评论数、点赞数等）</p>
	 * @author Tangtao on 2016年6月4日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType 
	 * @return
	 */
	Response<BusinessDetailResult> getBusinessDetail(Long appId, String sessionId, String token, Long sourceId, Byte sourceType);
	
	/**
	 * <p>Description: 获取专题资讯详情</p>
	 * @author Tangtao on 2016年6月6日
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	Response<SpecialInfoDetailResult> getSpecialDetail(Long appId, Long infoClassifyId);
	
	/**
	 * <p>Description: 获取专题子新闻</p>
	 * @author Tangtao on 2016年6月6日
	 * @param appId
	 * @param infoClassifyId
	 * @param themeId
	 * @param lastId
	 * @param lastSortNo
	 * @param lastOnlineTime
	 * @return
	 */
	Response<CommonListResult<NewsData>> getSpecialSubList(Long appId, Long infoClassifyId, Long themeId, Long lastId, Integer lastSortNo, String lastOnlineTime);
	
	/**
	 * <p>Description: 增加浏览量</p>
	 * @author Tangtao on 2016年6月20日
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	Response<Boolean> increaseViewCount(Long appId, Long infoClassifyId);
	
	/**
	 * <p>Description: 获取推荐新闻</p>
	 * @author Tangtao on 2016年8月18日
	 * @param appId
	 * @return
	 */
	Response<CommonListResult<NewsData>> getRecommended(Long appId);
	
	//查找新闻相关分页数据
	public Response<PageInfo<InfoClassifyDto>> queryInfoClassifyCorrelationPage(PageInfo<InfoClassifyDto> pageInfo,Map<String,Object> searchMap,Map<String,Boolean> orderMap);
	//查询已相关的相关新闻数据
	public Response<PageInfo<InfoClassifyDto>> queryHadCorrInfoClassifyPage(PageInfo<InfoClassifyDto> pageInfo,Map<String,Object> searchMap,Map<String,Boolean> orderMap);
	//推荐新闻编辑
	public Response<InfoClassify> infoClassifyRecommedEdit(Long commentId);
	//专题新闻滚动分页查询
	public Response<ScrollPage<InfoClassifyDto>> querySpecialSubDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions);
	
	/**
	 * Title:推荐到首页
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月17日
	 * @param id
	 * @param userId
	 * @param nickname
	 * @return
	 */
	public Response<Void> recommendToHome(Long id, Long userId, String nickname);
	//将新闻移动到指定栏目下
	public Response<Void> moveInfoClassify(Long[] infoClassifyIds,Long appColumnsId,String updator,Long updateId,Date updateTime);
}