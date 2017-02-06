package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯表 Service
 * Date: 2016-04-15 09:44:20
 * @author Code Generator
 */
public interface InformationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<Information>> queryForPage(PageInfo<Information> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<Information> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(Information domain);
	/** @author Code Generator *****end*****/
	
//	/**
//	 * <p>Description: 获取资讯分页列表</p>
//	 * @author Tangtao on 2016年4月20日
//	 * @param pageInfo
//	 * @param map
//	 * @param colsId 栏目id数组
//	 * @return
//	 */
//	Response<PageInfo<InformationDto>> queryDtoForPage(PageInfo<InformationDto> pageInfo, Map<String, Object> map, Long[] colsId);
	
	Response<Information> createInfomation(InfoDto infoDto);
	
	/**
	 * Title:
	 * <p>Description:删除原创新闻</p>
	 * @author fuxiaofeng on 2016年5月9日
	 * @param appContentId
	 * @return
	 */
	public Response<Void> deleteOrigionNews(Long infocontentId,Byte isViewWechat,Long appId);
	
	//修改及保存专题新闻
	public Response<Information> saveSpecialInfo(Information information,InfoClassify infoClassify,List<InfoTheme> infoThemes,String infoClassifyList);
	//删除主题新闻分类
	public Response<Void> delInfoTheme(Long infoThemeId);
	//新闻详情
	public Response<InformationDto> findDetail(Long infoClassifyId);
	//增加浏览量
	public Response<Integer> addViewCount(Long infoClassifyId,Long infomationId);
	//设置新闻内容图片数量
	public Response<Integer> setMultiImgNum(Long informationId);
	//删除新闻的内容(表info_content清空)
	public Response<Void> deleteInfoContent(Long infoId);
	
	//定时任务同步redis的浏览量
	public Response<Void> syncViewReplyCount(boolean immediate,Long infoClassifyId);
	
	//获取新闻的浏览量和回复量   数据库的数量+redis缓存中的数量
	public Response<InformationDto> setViewAndReplyCount(InformationDto dto);
	//获取新闻的浏览量和回复量数据库的数量+redis缓存中的数量,实际的数量
	public Response<InformationDto> setViewAndReplyCount(InformationDto dto, boolean addVirtualCount);
	//获取新闻的浏览量和回复量   数据库的数量+redis缓存中的数量
	public Response<List<InformationDto>> setViewAndReplyCount(List<InformationDto> data);
	//删除缓存并同步浏览量和评论量到数据库中
	public Response<Void> delCache(Long... infoClassifyId);
	//从数据库获取答案数量
	public Response<Map<Long, Object>> findAnswerNumByInfoId(Long infoId);
	//根据新闻内容id删除缓存
	public Response<Void> delCacheByInformationId(Long informationId);
}
