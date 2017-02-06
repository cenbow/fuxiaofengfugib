package com.cqliving.cloud.online.info.manager.impl;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.app.manager.AppResouseManager;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyListDao;
import com.cqliving.cloud.online.info.dao.InfoCorrelationDao;
import com.cqliving.cloud.online.info.dao.InfoFileDao;
import com.cqliving.cloud.online.info.dao.InfoThemeDao;
import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.dao.InformationDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.dto.InfoDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InfoSourceManager;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.survey.dao.SurveyInfoDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionDao;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.redis.client.DefaultRedisClient;
import com.cqliving.tool.common.util.HtmlRegexpUtil;
import com.cqliving.tool.common.util.JsonMapper;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("informationManager")
public class InformationManagerImpl extends EntityServiceImpl<Information, InformationDao> implements InformationManager {

	@Autowired
	InfoClassifyDao infoClassifyDao;
	@Autowired
	InfoSourceManager infoSourceManager;
	@Autowired
	InformationContentDao informationContentDao;
	@Autowired
	SurveyInfoDao surveyInfoDao;
	@Autowired
	SurveyQuestionDao surveyQuestionDao;
	@Autowired
	InfoClassifyListDao infoClassifyListDao;
	@Autowired
	AppResouseManager appResouseManager;
	@Autowired
	InfoThemeDao infoThemeDao;
	@Autowired
	InfoCorrelationDao infoCorrelationDao;
	@Autowired
	InfoFileDao infoFileDao;
	@Autowired
	DefaultRedisClient defaultRedisClient;
	
	private static final int EXPIRE_TIME = 60*60*24*3;
	@Value(value="${property_config}")
	private String propertyConfig;

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#createInfomation(com.cqliving.cloud.online.info.domain.Information, com.cqliving.cloud.online.info.domain.InfoClassify)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Information createInfomation(InfoDto infoDto) {
		
		Information info = infoDto.getInformation();
        InfoClassify classify = infoDto.getInfoClassify();
		String infoClassifyList=infoDto.getInfoClassifyList();
		String appResource = infoDto.getAppResource();
		String news_copy = infoDto.getNewsCopy();
		String infoTheme = infoDto.getInfoTheme();
		InfoFile infoFile = infoDto.getInfoFile();
		//设置新闻图片数量
		this.setInfoMultiImgNum(info);
		//处理摘要，如果没有输入摘要，则截取内容60个中文字符长度保存到摘要里面
		String synopsis = info.getSynopsis();
		if(StringUtil.isEmpty(synopsis)){
			synopsis = info.getContentText();
			if(!StringUtil.isEmpty(synopsis)){
				synopsis = StringUtil.cutString(synopsis,100);
				info.setSynopsis(synopsis);
			}
		}
		info = this.getEntityDao().saveAndFlush(info);
		classify.setInformationId(info.getId());
		Long classifyId = classify.getId();
		if(!StringUtil.isEmpty(news_copy) && "news_copy".equals(news_copy)){//复制新闻
			classify.setSourceInfoClassifyId(classify.getId());
			classify.setSourceAppId(classify.getAppId());
			classify.setId(null);
		}
		//专题添加子新闻
		if(!StringUtil.isEmpty(news_copy) && "add_special_sub".equals(news_copy) && !StringUtil.isEmpty(infoTheme)){
			classify.setAddSpecialStatus(InfoClassify.ADDSPECIALSTATUS1);
		}
		List<InformationContent> infoContents = informationContentDao.findByInformationId(info.getId());
		//保存新闻资源
		InformationContent newInformationContent = this.saveAppResourse(info,appResource,infoFile,infoContents);
		if(null != newInformationContent){
			if(CollectionUtils.isEmpty(infoContents)){
				infoContents = Lists.newArrayList();
			}
			if(infoContents.contains(newInformationContent)){
				infoContents.remove(newInformationContent);
				infoContents.add(newInformationContent);
			}else{
				infoContents.add(newInformationContent);
			}
		}
		classify.setIsViewWechat(this.isViewWechat(infoContents, info, classify));
		//保存新闻主表
		classify = infoClassifyDao.saveAndFlush(classify);
		this.handleInfoCorrection(classify, news_copy, infoTheme);
		//第一次新增新闻的时候保存原始新闻ID
		if(null == classifyId || 0 == classifyId){
			//this.getEntityDao().updateClassifyId(classify.getId(), info.getId());
			info.setClassifyId(classify.getId());
		}
		infoSourceManager.saveInfoSource(info,info.getInfoSource());
		InfoClassifyList infoImgList = this.saveListViewFile(infoClassifyList, classify);
		if(null != infoImgList){
			info.setInfoClassifyListId(infoImgList.getId());
		}
		return info;
	}
	
	//处理专题子新闻新增和修改的时候的关联表info_correction
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private void handleInfoCorrection(InfoClassify classify,String news_copy,String infoTheme){
		
		//专题添加子新闻
		if(!StringUtil.isEmpty(news_copy) && "add_special_sub".equals(news_copy) && !StringUtil.isEmpty(infoTheme)){
			String[] infoThemeArr = infoTheme.split(",");
			List<InfoCorrelation> list = infoCorrelationDao.findByInfoClssifyId(StringUtil.stringToLong(infoThemeArr[0]), classify.getId());
			
			InfoCorrelation infoCorrelation = null;
			if(CollectionUtils.isNotEmpty(list)){
				infoCorrelation = list.get(0);
			}else{
				infoCorrelation = new InfoCorrelation();
				infoCorrelation.setCreateTime(classify.getUpdateTime());
				infoCorrelation.setCreator(classify.getUpdator());
				infoCorrelation.setCreatorId(classify.getUpdatorId());
				infoCorrelation.setSortNo(Integer.MAX_VALUE);
				infoCorrelation.setStatus(InfoCorrelation.STATUS3);
				infoCorrelation.setAppId(classify.getAppId());
				infoCorrelation.setInfoClassifyId(StringUtil.stringToLong(infoThemeArr[0]));
				infoCorrelation.setRefInfoClassifyId(classify.getId());
			}
			infoCorrelation.setThemeId(StringUtil.stringToLong(infoThemeArr[1]));
			infoCorrelation.setUpdateTime(classify.getUpdateTime());
			infoCorrelation.setUpdator(classify.getUpdator());
			infoCorrelation.setUpdatorId(classify.getUpdatorId());
			infoCorrelationDao.saveAndFlush(infoCorrelation);
		}
		
	}
	
	private void setInfoMultiImgNum(Information information){
		
		if(null != information){
			byte contextType = information.getContextType();
			if(Information.CONTEXTTYPE0.byteValue() == contextType){
				List<String> imgTags = HtmlRegexpUtil.findImageTags(information.getContent());
				if(CollectionUtils.isNotEmpty(imgTags)){
					information.setMultipleImgCount(imgTags.size());
				}
			}
		}
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InfoClassifyList saveListViewFile(String infoClassifyList,InfoClassify infoClassify){
		
		byte viewType = infoClassify.getListViewType();
		if(viewType == InfoClassify.LISTVIEWTYPE0.byteValue()){//列表无图要删除列表图
			this.handleInfoClassifyList(infoClassify);
			return null;
		}
		if(StringUtil.isEmpty(infoClassifyList) || infoClassify.getListViewType().byteValue() == InfoClassify.LISTVIEWTYPE0.byteValue())return null;
		JsonMapper jm = new JsonMapper();
		InfoClassifyList infoimgList = jm.fromJson(infoClassifyList,InfoClassifyList.class);
		infoimgList.setAppId(infoClassify.getAppId());
		infoimgList.setClassifyId(infoClassify.getId());
		infoimgList.setColumnsId(infoClassify.getColumnsId());
		infoimgList.setInformationId(infoClassify.getInformationId());
		infoimgList.setListViewType(infoClassify.getListViewType());
		//检查有不有
		List<InfoClassifyList> listImgs = infoClassifyListDao.getByInfoClassify(infoClassify.getId());
		if(CollectionUtils.isEmpty(listImgs)){
			return infoClassifyListDao.saveAndFlush(infoimgList);
		}
		InfoClassifyList sqlListImage = listImgs.get(0);
		sqlListImage.setAppId(infoimgList.getAppId());
		sqlListImage.setClassifyId(infoimgList.getClassifyId());
		sqlListImage.setColumnsId(infoimgList.getColumnsId());
		sqlListImage.setInformationId(infoimgList.getInformationId());
		sqlListImage.setListViewType(infoimgList.getListViewType());
		sqlListImage.setImageUrl(infoimgList.getImageUrl());
		return infoClassifyListDao.saveAndFlush(sqlListImage);
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InformationContent saveInfoContent(Information info,List<InformationContent> infoContents){
		
		InformationContent informationContent = null;
		if(CollectionUtils.isEmpty(infoContents)){
			informationContent = new InformationContent();
			informationContent.setStatus(InformationContent.STATUS3);
		}else{
			informationContent = infoContents.get(0);
		}
		informationContent.setAppId(info.getAppId());
		informationContent.setInformationId(info.getId());
		informationContent.setSortNo(1);
		informationContent.setTitle(info.getKeywords());
		byte type = info.getContextType();
		if(type == Information.CONTEXTTYPE1.byteValue()){
			informationContent.setType(InformationContent.TYPE6);
		}else if(type == Information.CONTEXTTYPE4.byteValue()){
			informationContent.setType(InformationContent.TYPE2);
		}else if(type == Information.CONTEXTTYPE5.byteValue()){
			informationContent.setType(InformationContent.TYPE1);
		}else{
			informationContent.setType((byte)(type+100));
		}
		return informationContentDao.saveAndFlush(informationContent);
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InformationContent saveAppResourse(Information info,String appResource,InfoFile infoFile,List<InformationContent> infoContents){
		
		List<AppResouse> listAppResouse = appResouseManager.jsonStrToList(appResource);
		//是原创的类型由原创保存方法处理，这里不处理
		if(Information.CONTEXTTYPE2.byteValue() == info.getContextType().byteValue()){
			return null;
		}
		appResouseManager.compareToDB(appResouseManager.findByInformationId(info.getId()),listAppResouse);
		if(CollectionUtils.isEmpty(listAppResouse)){
			return null;
		}
		//保存内容
		InformationContent informationContent = this.saveInfoContent(info,infoContents);
		for(AppResouse ar : listAppResouse){
			ar.setAppId(informationContent.getAppId());
			ar.setInformationContentId(informationContent.getId());
			ar.setInformationId(informationContent.getInformationId());
			ar.setInfoFileId(null != infoFile ? infoFile.getId() : ar.getInfoFileId());
			ar.setDescription(StringUtil.isEmpty(ar.getDescription()) ? ar.getFileUrl() : ar.getDescription());
			appResouseManager.save(ar);
		}
		return informationContent;
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InfoFile saveInfoFile(InfoFile infoFile,Information info,InformationContent informationContent){
		
		if(null == infoFile || StringUtil.isEmpty(infoFile.getQiniuPersistentId()))return null;
		List<InfoFile> sqlInfoFile = infoFileDao.findByHashCode(infoFile.getQiniuHash());
		if(CollectionUtils.isNotEmpty(sqlInfoFile)){
			InfoFile sqlFile =  sqlInfoFile.get(0);
			infoFile.setRefId(sqlFile.getId());
			infoFile.setId(null);
		}
		infoFile.setAppId(info.getAppId());
		infoFile.setCreateTime(info.getCreateTime());
		infoFile.setCreator(info.getCreator());
		infoFile.setCreatorId(info.getCreatorId());
		infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS1);
		infoFile.setStatus(InfoFile.STATUS3);
		if(informationContent!=null){
			if(informationContent.getType().byteValue() == InformationContent.TYPE1.byteValue()){
				infoFile.setType(InfoFile.TYPE1);
			}else if(informationContent.getType().byteValue() == InformationContent.TYPE2.byteValue()){
				infoFile.setType(InfoFile.TYPE2);
			}
		}
		return infoFileDao.saveAndFlush(infoFile);
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private void handleInfoClassifyList(InfoClassify infoClassify){
		byte info = infoClassify.getListViewType();
		if(info == InfoClassify.LISTVIEWTYPE0.byteValue()){
			infoClassifyListDao.deleteClassifyList(infoClassify.getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#deleteOrigionNews(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void deleteOrigionNews(Long infocontentId,Byte isViewWechat,Long appId) {
		//修改是否推荐到微信小程序
		if(null != appId && 1 != appId.longValue()){//不是重庆appId，自动修改是否推荐
			infoClassifyDao.updateIsViewWechat(infocontentId, isViewWechat);
		}
		informationContentDao.deleteById(infocontentId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#saveSpecialInfo(com.cqliving.cloud.online.info.domain.Information, com.cqliving.cloud.online.info.domain.InfoClassify, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Information saveSpecialInfo(Information information, InfoClassify infoClassify, List<InfoTheme> infoThemes,String infoClassifyList) {
		
		information = this.getEntityDao().saveAndFlush(information);
		infoClassify.setInformationId(information.getId());
		//专题不能推荐到小程序
		infoClassify.setIsViewWechat(InfoClassify.ISVIEWWECHAT0);
		infoClassify = infoClassifyDao.saveAndFlush(infoClassify);
		infoSourceManager.saveInfoSource(information,information.getInfoSource());
		if(CollectionUtils.isEmpty(infoThemes)){
			throw new BusinessException(ErrorCodes.FAILURE,"专题分类不能为空");
		}
        InfoClassifyList infoimgList = this.saveListViewFile(infoClassifyList, infoClassify);
		if(null != infoimgList){
			information.setInfoClassifyListId(infoimgList.getId());
		}
		for(InfoTheme infoTheme : infoThemes){
			infoTheme.setAppId(infoClassify.getAppId());
			infoTheme.setInfoClassifyId(infoClassify.getId());
			infoThemeDao.save(infoTheme);
		}
		return information;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#delInfoTheme(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delInfoTheme(Long infoThemeId) {
		if(null == infoThemeId || infoThemeId.longValue() == 0){
			return;
		}
		List<InfoCorrelation> list = infoCorrelationDao.findByThemeId(infoThemeId);
		if(!CollectionUtils.isEmpty(list)){
			throw new BusinessException(ErrorCodes.FAILURE,"该分类已被其它新闻引用，不能删除");
		}
		infoThemeDao.removeById(infoThemeId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#findDetail(java.lang.Long)
	 */
	@Override
	public InformationDto findDetail(Long infoClassifyId) {
		
		//先取缓存，缓存取不到再取数据库
		String key = InformationUtil.getRedisCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infoClassifyId);
		InformationDto dto = defaultRedisClient.get(key,InformationDto.class);
		if(null == dto){
			dto = this.getEntityDao().findDetail(infoClassifyId);
			if(null != dto && InfoClassify.STATUS3.byteValue() == dto.getStatus().byteValue()){//只缓存上线的
				defaultRedisClient.set(key, dto,EXPIRE_TIME);
			}
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#addReplyCount(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int addReplyCount(Long infoClassifyId,Long informationId) {
		//定义info_classify表的增加的回复量的Key，标识访问的哪个info_classify
		String key = InformationUtil.getInfoClassifyReplyCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infoClassifyId);
		String value = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key);
		if(!StringUtil.isEmpty(value)){
			value = String.valueOf(StringUtil.stringToInteger(value) + 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key, value);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key,"1");
		}
		
		//定义 information表的增加的回复量的key，实际取的时候取这个Key的数量
		String replyKey = InformationUtil.getInformationReplyCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, informationId);
		String replyvalue = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, replyKey);
		if(!StringUtil.isEmpty(replyvalue)){
			replyvalue = String.valueOf(StringUtil.stringToInteger(replyvalue) + 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, replyKey, replyvalue);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH,replyKey,"1");
		}
		return 1;
		//return this.getEntityDao().addReplyCount(infoClassifyId,1);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#addViewCount(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int addViewCount(Long infoClassifyId,Long infomationId) {
		
		if(null == infoClassifyId || null == infomationId)return 0;
		//infoclassify浏览数
		String key = InformationUtil.getInfoClassifyViewKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infoClassifyId);
		String value = defaultRedisClient.getHSet(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH, key);
		if(!StringUtil.isEmpty(value)){
			value = String.valueOf(StringUtil.stringToInteger(value) + 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH, key, value);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH, key,"1");
		}
		//information浏览量
		key = InformationUtil.getInfomationViewKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infomationId);
		value = defaultRedisClient.getHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, key);
		if(!StringUtil.isEmpty(value)){
			value = String.valueOf(StringUtil.stringToInteger(value) + 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, key, value);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, key,"1");
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#addPraiseCount(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int addPraiseCount(Long infomationId) {
		return this.getEntityDao().addPraiseCount(infomationId);
	}

	//减少回复量
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int minusReplyCount(Long infoClassifyId,Long informationId){
		//定义info_classify表的增加的回复量的Key
		String key = InformationUtil.getInfoClassifyReplyCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, infoClassifyId);
		String value = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key);
		if(!StringUtil.isEmpty(value)){
			value = String.valueOf(StringUtil.stringToInteger(value) - 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key, value);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key,"-1");
		}
		
		//定义 information表的增加的回复量的key，实际取的时候取这个Key的数量
		String replyKey = InformationUtil.getInformationReplyCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1, informationId);
		String replyvalue = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, replyKey);
		if(!StringUtil.isEmpty(replyvalue)){
			replyvalue = String.valueOf(StringUtil.stringToInteger(replyvalue) - 1);
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, replyKey, replyvalue);
		}else{
			defaultRedisClient.setHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH,replyKey,"-1");
		}
		return 1;
		//return this.getEntityDao().minusReplyCount(infoClassifyId);
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#setMultiImgNum(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int setMultiImgNum(Long informationId) {
		
		List<AppResouse> list = appResouseManager.findByInformationId(informationId);
		int imgNum = 0;
		if(CollectionUtils.isNotEmpty(list)){
			
			for(AppResouse appResouse : list){
				
				imgNum += this.getImgs(appResouse);
				
			}
			this.getEntityDao().setMultiImgNum(imgNum,informationId);
		}
		return imgNum;
	}
	
     private int getImgs(AppResouse appResouse){
		
		if(null == appResouse)return 0;
		byte type = appResouse.getType();
		
		if(AppResouse.TYPE0.byteValue() == type){
			return HtmlRegexpUtil.findImageTags(appResouse.getDescription()).size();
		}else if(AppResouse.TYPE6.byteValue() == type){
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#deleteImageNews(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void deleteInfoContent(Long infoId) {
		
		if(null == infoId)return;
		List<InformationContent> contents = informationContentDao.findByInformationId(infoId);
		if(CollectionUtils.isEmpty(contents)){
			return ;
		}
		for(InformationContent content : contents){
			informationContentDao.deleteById(content.getId());
		}
	}


	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#syncViewCount()
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void syncViewReplyCount(boolean immediate,Long infoClassifyId) {
		//同步浏览量
		this.syncViewCount(immediate,infoClassifyId);
		//同步回复量
		this.syncReplyCount(immediate,infoClassifyId);
	}

	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private void syncViewCount(boolean immediate,Long infoClassId){
		Set<String> hkeys = defaultRedisClient.hkeys(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH);
		if(CollectionUtils.isEmpty(hkeys)){
			return;
		}
		for(String key : hkeys){
			String infoClassifyId = key.split(":")[1];
			String cacheViewNum = defaultRedisClient.getHSet(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH, key);
			int cacheNum = StringUtil.stringToInteger(cacheViewNum);
			if(0!=cacheNum){
				//同步浏览量
				infoClassifyDao.addViewCount(StringUtil.stringToLong(infoClassifyId),StringUtil.stringToInteger(cacheViewNum));
			}
			defaultRedisClient.delHSet(CacheConstant.INFO_VIEW_INFOCLASSIFY_COUNT_HASH, key);
			defaultRedisClient.del(InformationUtil.getRedisCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1,StringUtil.stringToLong(infoClassifyId)));
		}
		
		hkeys = defaultRedisClient.hkeys(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH);
		if(CollectionUtils.isEmpty(hkeys)){
			return;
		}
		for(String key : hkeys){
			String infoClassifyId = key.split(":")[1];
			String cacheViewNum = defaultRedisClient.getHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, key);
			int cacheNum = StringUtil.stringToInteger(cacheViewNum);
			if(0!=cacheNum){
				//同步浏览量
				this.getEntityDao().addViewCount(StringUtil.stringToLong(infoClassifyId),StringUtil.stringToInteger(cacheViewNum));
			}
			defaultRedisClient.delHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, key);
		}
	}

	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private void syncReplyCount(boolean immediate,Long infoClassId){
		
		Set<String> hkeys = defaultRedisClient.hkeys(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH);
		if(CollectionUtils.isEmpty(hkeys)){
			return;
		}
		for(String key : hkeys){
			String infoClassifyId = key.split(":")[1];
			String cacheReplyNum = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key);
			int cacheNum = StringUtil.stringToInteger(cacheReplyNum);
			if(0!=cacheNum){
				//同步回复量
				infoClassifyDao.addReplyCount(StringUtil.stringToLong(infoClassifyId),StringUtil.stringToInteger(cacheReplyNum));
			}
			defaultRedisClient.delHSet(CacheConstant.INFO_REPLY_INFOCLASSIFY_COUNT_HASH, key);
			defaultRedisClient.del(InformationUtil.getRedisCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1,StringUtil.stringToLong(infoClassifyId)));
		}
		
		hkeys = defaultRedisClient.hkeys(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH);
		if(CollectionUtils.isEmpty(hkeys)){
			return;
		}
		for(String key : hkeys){
			String infoClassifyId = key.split(":")[1];
			String cacheReplyNum = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, key);
			int cacheNum = StringUtil.stringToInteger(cacheReplyNum);
			if(0!=cacheNum){
				//同步回复量
				this.getEntityDao().addReplyCount(StringUtil.stringToLong(infoClassifyId),StringUtil.stringToInteger(cacheReplyNum));
			}
			defaultRedisClient.delHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, key);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#setViewAndReplyCount(com.cqliving.cloud.online.info.dto.InformationDto)
	 */
	@Override
	public InformationDto setViewAndReplyCount(InformationDto dto) {
		return setViewAndReplyCount(dto, true);
	}
	
	@Override
	public InformationDto setViewAndReplyCount(InformationDto dto, boolean addVirtualCount) {
		if (null == dto || null == dto.getId()) {
			throw new BusinessException(ErrorCodes.FAILURE,"新闻不能为空");
		}
		int viewCount = addVirtualCount ? InformationUtil.syntaxViewCount(dto, null) : dto.getViewCount();
		//浏览量
		String ikey = InformationUtil.getInfomationViewKey(this.propertyConfig, BusinessType.SOURCE_TYPE_1,dto.getId());
		String cacheViewNum = defaultRedisClient.getHSet(CacheConstant.INFO_VIEW_INFOMATION_COUNT_HASH, ikey);
		if(!StringUtil.isEmpty(cacheViewNum)){
			viewCount += StringUtil.stringToInteger(cacheViewNum);
		}
		dto.setViewCount(viewCount);
		
		//回复量
		int replyCount = dto.getReplyCount();
		ikey = InformationUtil.getInformationReplyCacheKey(this.propertyConfig, BusinessType.SOURCE_TYPE_1, dto.getId());
		String cacheReplyNum = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, ikey);
		if(!StringUtil.isEmpty(cacheReplyNum)){
			replyCount += StringUtil.stringToInteger(cacheReplyNum);
			dto.setReplyCount(replyCount);
		}
		
		//栏目回复量
		Integer classifyReplyCount = dto.getClassifyReplyCount();
		if(null != dto.getInfoClassifyId()){
			ikey = InformationUtil.getInfoClassifyReplyCacheKey(this.propertyConfig, BusinessType.SOURCE_TYPE_1,dto.getInfoClassifyId());
		    cacheReplyNum = defaultRedisClient.getHSet(CacheConstant.INFO_REPLY_INFOMATION_COUNT_HASH, ikey);
			if(!StringUtil.isEmpty(cacheReplyNum)){
				classifyReplyCount += StringUtil.stringToInteger(cacheReplyNum);
				dto.setClassifyReplyCount(classifyReplyCount);
			}
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#setViewAndReplyCount(java.util.List)
	 */
	@Override
	public List<InformationDto> setViewAndReplyCount(List<InformationDto> data) {
		if(CollectionUtils.isEmpty(data)){
			return null;
		}
		for(InformationDto dto : data){
			this.setViewAndReplyCount(dto);
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#delCache()
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delCache(Long... infoClassifyId) {
		
		if(null == infoClassifyId)return;
		
		for(Long id : infoClassifyId){
			defaultRedisClient.del(InformationUtil.getRedisCacheKey(propertyConfig, BusinessType.SOURCE_TYPE_1,id));
			//TODO this.syncViewReplyCount(true,id);
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationManager#findAnswerNumByInfoId(java.lang.Long)
	 */
	@Override
	public Map<Long, Object> findAnswerNumByInfoId(Long infoId) {
		
		if(null == infoId)return null;
		List<Map<String, Object>> columnMaps = this.getEntityDao().findAnswerNumByInfoId(infoId);
		if(CollectionUtils.isEmpty(columnMaps))return null;
		Map<Long, Object> map = Maps.newHashMap();
		for(Map<String, Object> sqlMap : columnMaps){
			map.put((Long)sqlMap.get("id"),sqlMap.get("answer_num"));
		}
		return map;
	}

	@Override
	public void delCacheByInformationId(Long informationId) {
		if(null == informationId)return;
		List<Long> ids = infoClassifyDao.findclassifyIds(informationId);
		if(CollectionUtils.isEmpty(ids)){
			return;
		}
		this.delCache(ids.toArray(new Long[]{}));
	}
	
	//判断是否可以推荐到微信小程序
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public byte isViewWechat(List<InformationContent> sqlContents, Information info, InfoClassify classify) {
		if(null == info || null == classify){
			throw new BusinessException(ErrorCodes.FAILURE,"判断是否推荐到小程序时,新闻不能为空");
		}
		//外链不能推荐
		if(Information.CONTEXTTYPE3.byteValue() == info.getContextType().byteValue()){
			infoClassifyDao.updateIsViewWechatByInfoId(info.getId(), InfoClassify.ISVIEWWECHAT0);
			return InfoClassify.ISVIEWWECHAT0;
		}
		//专题不能推荐
		if(Information.TYPE2.byteValue() == info.getType().byteValue()){
			infoClassifyDao.updateIsViewWechatByInfoId(info.getId(), InfoClassify.ISVIEWWECHAT0);
			return InfoClassify.ISVIEWWECHAT0;
		}
		boolean contentIsViewWechat = true;
		if(CollectionUtils.isNotEmpty(sqlContents)){
			for(InformationContent content : sqlContents){
				if(InformationContent.TYPE3.byteValue() == content.getType().byteValue() ||
						InformationContent.TYPE4.byteValue() == content.getType().byteValue() ||
						InformationContent.TYPE5.byteValue() == content.getType().byteValue()){
					contentIsViewWechat = false;
					break;
				}
			}
		}
		//内容有投票打擂调研不能推荐
		if(!contentIsViewWechat){
			infoClassifyDao.updateIsViewWechatByInfoId(info.getId(), InfoClassify.ISVIEWWECHAT0);
			return InfoClassify.ISVIEWWECHAT0;
		}
		//页面选择推荐、不推荐,只针对此新闻
		return classify.getIsViewWechat();
	}
}