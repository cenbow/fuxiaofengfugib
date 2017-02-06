package com.cqliving.cloud.online.info.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.dto.InfoDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯表 Manager
 * Date: 2016-04-15 09:44:20
 * @author Code Generator
 */
public interface InformationManager extends EntityService<Information> {

//	/**
//	 * <p>Description: 获取资讯分页列表</p>
//	 * @author Tangtao on 2016年4月20日
//	 * @param pageInfo
//	 * @param map
//	 * @param colsId 栏目id数组
//	 * @return
//	 */
//	PageInfo<InformationDto> queryDtoForPage(PageInfo<InformationDto> pageInfo, Map<String, Object> map, Long[] colsId);
	
	public Information createInfomation(InfoDto infoDto);
	
	public void deleteOrigionNews(Long infocontentId,Byte isViewWechat,Long appId);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月14日
	 * @param information
	 * @param infoClassify
	 * @param infoThemes
	 * @return
	 */
	public Information saveSpecialInfo(Information information, InfoClassify infoClassify,List<InfoTheme> infoThemes,String listViewFileUrl);
	
	public void delInfoTheme(Long infoThemeId);
	
	//新闻详情
	public InformationDto findDetail(Long infoClassifyId);
	
	//增加回复量
	public int addReplyCount(Long infoClassifyId,Long infomationId);
	//增加浏览量
	public int addViewCount(Long infoClassifyId,Long infomationId);
	//增加点赞数
	public int addPraiseCount(Long infomationId);
	//减少回复量
	public int minusReplyCount(Long infoClassifyId,Long informationId);
	
	public int setMultiImgNum(Long infomationId);
	//删除新闻的内容(表info_content清空)
	public void deleteInfoContent(Long infoId);
	
	public void syncViewReplyCount(boolean immediate,Long infoClassifyId);
	//获取新闻的浏览量和回复量   数据库的数量+redis缓存中的数量
	public InformationDto setViewAndReplyCount(InformationDto dto);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月21日
	 * @param dto
	 * @param addVirtualCount 是否计算虚拟阅读量
	 * @return
	 */
	InformationDto setViewAndReplyCount(InformationDto dto, boolean addVirtualCount);

	//获取新闻的浏览量和回复量   数据库的数量+redis缓存中的数量
    public List<InformationDto> setViewAndReplyCount(List<InformationDto> data);
    //删除缓存并同步浏览量和回复量到数据库中
    public void delCache(Long... infoClassifyId);
    
    public Map<Long,Object> findAnswerNumByInfoId(Long infoId);
    
    public void delCacheByInformationId(Long informationId);
    //是否可以推荐到微信小程序
    public byte isViewWechat(List<InformationContent> sqlContents,Information info,InfoClassify classify);
}
